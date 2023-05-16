package com.settlementGame.game;

import android.graphics.Point;

import com.settlementGame.game.AStar.AStar;
import com.settlementGame.game.AStar.Node;
import com.settlementGame.game.gameObject.Blueprint;
import com.settlementGame.game.gameObject.Building;
import com.settlementGame.game.gameObject.Designation;
import com.settlementGame.game.gameObject.GameObject;
import com.settlementGame.game.gameObject.Materials;
import com.settlementGame.game.gameObject.Walkable;
import com.settlementGame.game.terrain.TerrainInfo;

import java.util.ArrayList;
import java.util.List;

public class Task {

    private enum Type {
        Designation, Blueprint, Building, Other
    }

    private enum Status {
        GetComponents, DeliverComponents, NeedsOperation, GetProducts, DeliverProducts
    }

    public enum CompletionStatus {
        Continue, Canceled, Complete
    }

    public static ArrayList<Task> taskPool = new ArrayList<Task>();

    public GameObject target;
    public int x, y;
    private Type type;
    public List<Node<Point>> path;
    private Walkable worker;
    public Status status;

    public Task(Walkable worker, Designation designation, int x, int y){
        this.worker = worker;
        target = designation;
        type = Type.Designation;
        target.claimedByTask = true;
        this.x = x;
        this.y = y;
        if(worker != null) {
            if(path == null || path.size() == 0) path = AStar.getPathNextTo(worker.getX(), worker.getY(), x, y, worker);
        }
    }

    public Task(Walkable worker, Blueprint blueprint, int x, int y){
        this.worker = worker;
        target = blueprint;
        type = Type.Blueprint;
        target.claimedByTask = true;
        this.x = x;
        this.y = y;
        if(worker != null) {
            if(path == null || path.size() == 0) path = AStar.getPathNextTo(worker.getX(), worker.getY(), x, y, worker);
        }
    }

    public Task(Walkable worker, Building building, int x, int y){
        this.worker = worker;
        target = building;
        target.claimedByTask = true;
        type = Type.Building;
        this.x = x;
        this.y = y;
    }

    public boolean addProgression(int skillLevel){
        switch(type){
            case Designation:
                return Designation.addProgression(x, y, skillLevel);
            case Blueprint:
                return Blueprint.addProgression(x, y, skillLevel);
            case Building:
                return Building.addProgression(x, y, skillLevel);
        }
        return GameObject.addProgression(x, y, skillLevel);
    }

    public CompletionStatus update(){
        switch(type){
            case Designation:
                status = Status.NeedsOperation;
                if (worker != null) {
                    // if next to task target
                    if(path != null){
                        // System.out.println(worker.getX() + " " + worker.getY() + " " + x + " " + y + " " + path.size());
                    }
                    if ((x == worker.getX() && (y == worker.getY() - 1 || y == worker.getY() + 1))
                            || (y == worker.getY() && (x == worker.getX() - 1 || x == worker.getX() + 1))) {
                        if(addProgression(15 /*todo worker.skillLevel*/)){
                            return CompletionStatus.Complete;
                        } else {
                            return CompletionStatus.Continue;
                        }
                    } else { // otherwise get there
                        if(path == null || path.size() == 0) path = AStar.getPathNextTo(worker.getX(), worker.getY(), x, y, worker);
                        if(path == null) return CompletionStatus.Canceled;
                    }
                }
                return CompletionStatus.Continue;
            case Blueprint:

            case Building:
                Materials neededMats = ((Building)target).ingredientsNeededForFirstRecipe();
                if(neededMats == null) {
                    // recipe probably removed
                    return CompletionStatus.Canceled;
                }
                if (neededMats.getTotalAmount() > 0) {
                    // check if worker has items in inventory
                    boolean deliver = true;
                    for (int m = 0; m < neededMats.getMats().length; m++) {
                        if (worker.hasAmountOfItem(neededMats.getMats()[m]) < neededMats.getAmounts()[m]) {
                            status = Status.GetComponents;
                            deliver = false;
                        }
                    }
                    if (!deliver) {
                        // for each type of material needed
                        for (int m = 0; m < neededMats.getMats().length; m++) {
                            // check if it is all gathered
                            if (worker.hasAmountOfItem(neededMats.getMats()[m]) < neededMats.getAmounts()[m]) {
                                // if not then look for and get it
                                int shortestDist = 9999;
                                List<Node<Point>> checkPath = null;
                                // get closest designation
                                int pickUpX = -1, pickUpY = -1;
                                for (int i = 0; i < TerrainInfo.tilesGrid.length; i++) {
                                    for (int j = 0; j < TerrainInfo.tilesGrid.length; j++) {
                                        // check if this spot has material or storage with material
                                        if (GameObject.gameObjects[i][j] != null
                                                && (GameObject.gameObjects[i][j].name.equals(neededMats.getMats()[m].name) // todo not looking for item on ground
                                                || (GameObject.gameObjects[i][j].getClass() == Building.class && GameObject.gameObjects[i][j].hasInStorage(neededMats.getMats()[m])))) {
                                            checkPath = AStar.getPathNextTo(worker.getX(), worker.getY(), i, j, worker);
                                            if (checkPath != null && checkPath.size() < shortestDist) {
                                                shortestDist = checkPath.size();
                                                path = checkPath;
                                                pickUpX = i;
                                                pickUpY = j;
                                            }
                                        }
                                    }
                                }
                                if(pickUpX == -1 || pickUpY == -1){
                                    return CompletionStatus.Canceled;
                                }
                                // if next to task target
                                if ((pickUpX == worker.getX() && (pickUpY == worker.getY() - 1 || pickUpY == worker.getY() + 1))
                                        || (pickUpY == worker.getY() && (pickUpX == worker.getX() - 1 || pickUpX == worker.getX() + 1))) {
                                    if (GameObject.gameObjects[pickUpX][pickUpY].getClass() == Building.class) {
                                        // move every item to target
                                        for (int m2 = 0; m2 < ((Building) target).ingredientsNeededForFirstRecipe().getMats().length; m2++) {
                                            for (int a = 0; a < ((Building) target).ingredientsNeededForFirstRecipe().getAmounts()[m2]; a++) {
                                                for (int i = 0; i < GameObject.gameObjects[pickUpX][pickUpY].getStorage().size(); i++) {
                                                    if (GameObject.gameObjects[pickUpX][pickUpY].getStorage().get(i).name.equals(((Building) target).ingredientsNeededForFirstRecipe().getMats()[m2].name)) {
                                                        worker.getInventory().add(GameObject.gameObjects[pickUpX][pickUpY].getStorage().remove(i));
                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                    } else { // otherwise just get item
                                        worker.getInventory().add(GameObject.gameObjects[pickUpX][pickUpY]);
                                        GameObject.gameObjects[pickUpX][pickUpY] = null;
                                    }
                                }
                                break;
                            }
                        }
                    } else {
                        status = Status.DeliverComponents;
                        if (worker != null) {
                            // if next to task target
                            if ((x == worker.getX() && (y == worker.getY() - 1 || y == worker.getY() + 1))
                                    || (y == worker.getY() && (x == worker.getX() - 1 || x == worker.getX() + 1))) {
                                // move every item to target
                                for (int m = 0; m < ((Building) target).ingredientsNeededForFirstRecipe().getMats().length; m++) {
                                    for (int a = 0; a < ((Building) target).ingredientsNeededForFirstRecipe().getAmounts()[m]; a++) {
                                        for (int i = 0; i < worker.getInventory().size(); i++) {
                                            if (worker.getInventory().get(i).name.equals(((Building) target).ingredientsNeededForFirstRecipe().getMats()[m].name)) {
                                                ((Building) target).addItem(worker.getInventory().remove(i));
                                                break;
                                            }
                                        }
                                    }
                                }
                            } else { // otherwise get there
                                if(path == null || path.size() == 0) path = AStar.getPathNextTo(worker.getX(), worker.getY(), x, y, worker);
                                if(path == null) return CompletionStatus.Canceled;
                            }
                        }
                    }
                } else {
                    status = Status.NeedsOperation;
                    if (worker != null) {
                        // if next to task target
                        if ((x == worker.getX() && (y == worker.getY() - 1 || y == worker.getY() + 1))
                                || (y == worker.getY() && (x == worker.getX() - 1 || x == worker.getX() + 1))) {
                            if(addProgression(15 /*todo worker.skillLevel*/)){
                                return CompletionStatus.Complete;
                            } else {
                                return CompletionStatus.Continue;
                            }
                        } else { // otherwise get there
                            if(path == null || path.size() == 0) path = AStar.getPathNextTo(worker.getX(), worker.getY(), x, y, worker);
                            if(path == null) return CompletionStatus.Canceled;
                        }
                    }
                }
                return CompletionStatus.Continue;
        }
        return CompletionStatus.Canceled;
    }


}