package com.settlementGame.game.gameObject;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;

import com.settlementGame.framework.Graphics;
import com.settlementGame.framework.Input;
import com.settlementGame.game.Assets;
import com.settlementGame.game.screen.Button;
import com.settlementGame.game.screen.GameScreen;
import com.settlementGame.game.terrain.BiomeInfo;
import com.settlementGame.game.terrain.TerrainInfo;

import java.util.ArrayList;

public class Designation extends GameObject {

    public static Designation[][] designations;

    private static Button plantButton = new Button("plant", new Rect(50, 150, 50 + 200, 150 + 100), new Rect(0, 0, 0, 0));
    private static Button removeButton = new Button("remove", new Rect(50, 250, 50 + 200, 250 + 100), new Rect(0, 0, 0, 0));
    private static Button harvestButton = new Button("harvest", new Rect(50, 350, 50 + 200, 350 + 100), new Rect(0, 0, 0, 0));
    private static Button cleanButton = new Button("clean", new Rect(50, 450, 50 + 200, 450 + 100), new Rect(0, 0, 0, 0));
    private static Button cancelButton = new Button("cancel", new Rect(50, 550, 50 + 200, 550 + 100), new Rect(0, 0, 0, 0));
    private static Button retButton = new Button("return", new Rect(50, 650, 50 + 200, 650 + 100), new Rect(0, 0, 0, 0));
    // todo select tile floor building

    private static int buttonSelected = -1;

    private int x, y, progression = 0;

    // todo temp
    public Designation(String n, int x, int y) {
        super(n, 0, 1, 1, -1);
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g, float worldOffSetX, float worldOffSetY, float scale) {
        // todo
        float drawX = worldOffSetX + (x - y + TerrainInfo.tilesGridSize * BiomeInfo.biomeGridSize) * 16 + 12;
        float drawY = worldOffSetY + ((y + x) * 16) / 2.0f + 12 - 16;
        g.drawRect(drawX * scale, drawY * scale, 8 * scale, 8 * scale, Color.rgb(124, 174, 255));
        g.drawRect(drawX * scale, drawY * scale, 8 * scale, 8 * scale * (progression / 100.0f), Color.rgb(124, 255, 174));
    }

    public static boolean addProgression(int x, int y, int skillLevel) {
        if(designations[x][y] == null) return true;
        designations[x][y].progression += skillLevel;
        if (designations[x][y].progression >= 100) {
            switch (designations[x][y].name) {
                case "Plant": // plant
                    break;
                case "Remove": // remove // todo select floor building
                    if (GameObject.gameObjects[x][y] != null && GameObject.gameObjects[x][y].getClass() == Building.class) {
                        designations[x][y] = null;
                        addRes(x, y, GameObject.gameObjects[x][y]);
                    }
                    break;
                case "Harvest": // harvest
                    if (GameObject.gameObjects[x][y] != null && GameObject.gameObjects[x][y].getClass() == Tree.class) {
                        designations[x][y] = null;
                        addRes(x, y, skillLevel, Resource.WOOD);
                    } else if (GameObject.gameObjects[x][y] != null && GameObject.gameObjects[x][y].getClass() == Plant.class) {
                        designations[x][y] = null;
                        // addRes(x, y, skillLevel, GameObject.gameObjects[x][y].); todo return seed and harvest
                    }
                    break;
                case "Clean": // clean
                    break;
                case "Cancel": // cancel
                    break;
            }
            updateRenderOrder();
        }
        return false;
    }

    private static void addRes(int x, int y, GameObject go) {
        Materials mats = go.materials;
        int index = 0;
        int amt = 1;
        if(go.encrustedWith == null){
            GameObject.gameObjects[x][y] = mats.getMats()[0];
        } else {
            GameObject.gameObjects[x][y] = go.encrustedWith;
        }
        for(int circle = 1; circle < 4; circle ++){
            for (int r = -circle; r < 1 + circle; r++) {
                if (canPlace(x + r, y - circle) && GameObject.gameObjects[x + r][y - circle] == null) {
                    GameObject.addGameObject(Resource.createResource((Resource)mats.getMats()[index]), x + r, y - circle);
                    amt++;
                    if(amt >= mats.getAmounts()[index]) {
                        index++;
                        amt = 0;
                    }
                    if (index >= mats.getMats().length) return;
                }
                if (canPlace(x + r, y + circle) && GameObject.gameObjects[x + r][y + circle] == null) {
                    GameObject.addGameObject(Resource.createResource((Resource)mats.getMats()[index]), x + r, y + circle);
                    amt++;
                    if(amt >= mats.getAmounts()[index]) {
                        index++;
                        amt = 0;
                    }
                    if (index >= mats.getMats().length) return;
                }
                if (canPlace(x - circle, y + r) && GameObject.gameObjects[x - circle][y + r] == null) {
                    GameObject.addGameObject(Resource.createResource((Resource)mats.getMats()[index]), x - circle, y + r);
                    amt++;
                    if(amt >= mats.getAmounts()[index]) {
                        index++;
                        amt = 0;
                    }
                    if (index >= mats.getMats().length) return;
                }
                if (canPlace(x + circle, y + r) && GameObject.gameObjects[x + circle][y + r] == null) {
                    GameObject.addGameObject(Resource.createResource((Resource)mats.getMats()[index]), x + circle, y + r);
                    amt++;
                    if(amt >= mats.getAmounts()[index]) {
                        index++;
                        amt = 0;
                    }
                    if (index >= mats.getMats().length) return;
                }
            }
        }
    }

    private static void addRes(int x, int y, int skillLevel, Resource res) {
        GameObject.gameObjects[x][y] = Resource.createResource(res);
        int maxGen = 2 + rand.nextInt(3) + skillLevel;
        int a = 0;
        for(int circle = 1; circle < 4; circle ++){
            for (int r = -circle; r < 1 + circle; r++) {
                if (canPlace(x + r, y - circle) && GameObject.gameObjects[x + r][y - circle] == null) {
                    GameObject.addGameObject(Resource.createResource(res),x + r, y - circle);
                    a++;
                    if (a >= maxGen) return;
                }
                if (canPlace(x + r, y + circle) && GameObject.gameObjects[x + r][y + circle] == null) {
                    GameObject.addGameObject(Resource.createResource(res),x + r, y + circle);
                    a++;
                    if (a >= maxGen) return;
                }
                if (canPlace(x - circle, y + r) && GameObject.gameObjects[x - circle][y + r] == null) {
                    GameObject.addGameObject(Resource.createResource(res),x - circle, y + r);
                    a++;
                    if (a >= maxGen) return;
                }
                if (canPlace(x + circle, y + r) && GameObject.gameObjects[x + circle][y + r] == null) {
                    GameObject.addGameObject(Resource.createResource(res),x + circle, y + r);
                    a++;
                    if (a >= maxGen) return;
                }
            }
        }
    }

    public static void drawMainMenu(Graphics g, float scale){

        plantButton.draw(g, scale);
        harvestButton.draw(g, scale);
        cleanButton.draw(g, scale);
        removeButton.draw(g, scale);
        cancelButton.draw(g, scale);
        retButton.draw(g, scale);

        // g.drawRect(SettlementGame.WIDTH * 0.2f, SettlementGame.HEIGHT * 0.1f, SettlementGame.WIDTH * 0.7f, SettlementGame.HEIGHT * 0.8f, Color.rgb(24, 74, 255));
        // g.drawText("Designation",SettlementGame.WIDTH * 0.2f + 50, SettlementGame.HEIGHT * 0.1f + 200);

    }

    public static void updateMainMenu(Input.TouchEvent e, float xOff, float yOff, float scale){

        int tapX = (int)(Math.floor(((e.y / scale - yOff) / (16.0f) + ((e.x - 16.0f) / scale - xOff) / (32.0f)) - 45));
        int tapY = (int)(Math.floor(((e.y / scale - yOff) / (16.0f) - ((e.x - 16.0f) / scale - xOff) / (32.0f)) + 45));

        plantButton.update(e);
        harvestButton.update(e);
        cleanButton.update(e);
        removeButton.update(e);
        cancelButton.update(e);
        retButton.update(e);

        if(plantButton.clicked(e)) buttonSelected = 0;
        if(removeButton.clicked(e)) buttonSelected = 1;
        if(harvestButton.clicked(e)) buttonSelected = 2;
        if(cleanButton.clicked(e)) buttonSelected = 3;
        if(cancelButton.clicked(e)) buttonSelected = 4;

        if(inWorld(tapX, tapY) && GameObject.gameObjects[tapX][tapY] != null) {
            switch(buttonSelected){
                case 0 : // plant // todo select what plant
                    break;
                case 1 : // remove // todo select tile floor building
                    if(GameObject.gameObjects[tapX][tapY].getClass() == Building.class) {
                        if(designations[tapX][tapY] == null) {
                            designations[tapX][tapY] = new Designation("Remove", tapX, tapY);
                        }
                    }
                    break;
                case 2 : // harvest
                    if(GameObject.gameObjects[tapX][tapY].getClass() == Tree.class || GameObject.gameObjects[tapX][tapY].getClass() == Plant.class) {
                        if(designations[tapX][tapY] == null) {
                            designations[tapX][tapY] = new Designation("Harvest", tapX, tapY);
                        }
                    }
                    break;
                case 3 : // clean
                    break;
                case 4 : // cancel
                    break;
            }
        }

        if(retButton.clicked(e)){
            GameScreen.showDesignationMenu = false;
            GameScreen.showNoMenu = true;
        }
    }

}