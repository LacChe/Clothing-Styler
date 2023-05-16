package com.settlementGame.game.terrain;

import android.graphics.Point;

import com.settlementGame.game.Assets;
import com.settlementGame.game.gameObject.Building;
import com.settlementGame.game.gameObject.Designation;
import com.settlementGame.game.gameObject.Flooring;
import com.settlementGame.game.gameObject.GameObject;
import com.settlementGame.game.gameObject.Resource;
import com.settlementGame.game.gameObject.Tile;
import com.settlementGame.game.gameObject.Tree;
import com.settlementGame.game.gameObject.Walkable;

import java.util.ArrayList;
import java.util.Random;

public class TerrainInfo {

    private static Random rand;
    private static int iterations = 5;

    public static Tile[][] tilesGrid;
    public static int tilesGridSize = 15;

    public static Flooring[][] flooringGrid;

    public static void init(){
        rand = new Random();
        tilesGrid = new Tile[BiomeInfo.biomeGridSize * tilesGridSize][BiomeInfo.biomeGridSize * tilesGridSize];
        flooringGrid = new Flooring[BiomeInfo.biomeGridSize * tilesGridSize][BiomeInfo.biomeGridSize * tilesGridSize];
        GameObject.gameObjects = new GameObject[BiomeInfo.biomeGridSize * tilesGridSize][BiomeInfo.biomeGridSize * tilesGridSize];
        GameObject.renderOrder = new ArrayList<Point>();
        Designation.designations = new Designation[BiomeInfo.biomeGridSize * tilesGridSize][BiomeInfo.biomeGridSize * tilesGridSize];
    }

    public static void genTerrainGrid() {
        // clear all
        tilesGrid = new Tile[BiomeInfo.biomeGridSize * tilesGridSize][BiomeInfo.biomeGridSize * tilesGridSize];
        flooringGrid = new Flooring[BiomeInfo.biomeGridSize * tilesGridSize][BiomeInfo.biomeGridSize * tilesGridSize];
        GameObject.gameObjects = new GameObject[BiomeInfo.biomeGridSize * tilesGridSize][BiomeInfo.biomeGridSize * tilesGridSize];
        for(int i = 0; i < BiomeInfo.biomeGridSize * tilesGridSize; i++){
            for(int j = 0; j < BiomeInfo.biomeGridSize * tilesGridSize; j++){
                tilesGrid[i][j] = Tile.VOID;
                flooringGrid[i][j] = Flooring.VOID;
                GameObject.gameObjects[i][i] = null;
                GameObject.renderOrder = new ArrayList<Point>();
            }
        }

        // todo for testing
        Walkable.homeCitizens.add(new Walkable(Assets.walkables, "W1",45,45));
        Walkable.homeCitizens.add(new Walkable(Assets.walkables, "W2",45,47));

        for (int i = 0; i < BiomeInfo.biomeGridSize; i++) {
            for (int j = 0; j < BiomeInfo.biomeGridSize; j++) {

                // todo for testing
                if((i == 2 || i == 3) && (j == 2 || j == 3)){
                    genField(i, j);
                } else {
                    switch(BiomeInfo.biomeGrid[i][j]){
                        case STONE:
                            genStone(i, j);
                            break;
                        case ORE:
                            genOre(i, j);
                            break;
                        case WOODS:
                            genWoods(i, j);
                            break;
                        case WATER:
                            genWater(i, j);
                            break;
                        case SAND:
                            genSand(i, j);
                            break;
                        case FIELD:
                            genField(i, j);
                            break;
                        case LAVASTONE:
                            genLavaStone(i, j);
                            break;
                        case LAVAORE:
                            genLavaOre(i, j);
                            break;
                        case VOID:
                            default:
                            break;
                    }
                }
            }
        }

        Building.addBuilding(50, 50, Building.createBuilding(Building.STATION_DRILL_ORE));
        Building.addBuilding(50, 45, Building.createBuilding(Building.STATION_FURNACE));

/*
        // temp add furniture test
        for(int i = 0; i < 500;){
            Building b = Building.createBuilding(Building.TABLE_WOOD_2x2);
            int facingShift = 0;
            if(rand.nextInt(100) < 50) facingShift = 1;
            b.shiftFacingDir(facingShift);
            int x = rand.nextInt(tilesGrid.length);
            int y = rand.nextInt(tilesGrid.length);
            if(notOccupied(b, x, y)){
                Building.addBuilding(x, y, b);
                i++;
            }
        }
        for(int i = 0; i < 500;){
            Building b = Building.createBuilding(Building.TABLE_WOOD_2x1);
            int facingShift = 0;
            if(rand.nextInt(100) < 50) facingShift = 1;
            b.shiftFacingDir(facingShift);
            int x = rand.nextInt(tilesGrid.length);
            int y = rand.nextInt(tilesGrid.length);
            if(notOccupied(b, x, y)){
                Building.addBuilding(x, y, b);
                i++;
            }
        }
        for(int i = 0; i < 500;){
            Building b = Building.createBuilding(Building.TABLE_WOOD_1x1);
            int facingShift = 0;
            if(rand.nextInt(100) < 50) facingShift = 1;
            b.shiftFacingDir(facingShift);
            int x = rand.nextInt(tilesGrid.length);
            int y = rand.nextInt(tilesGrid.length);
            if(notOccupied(b, x, y)){
                Building.addBuilding(x, y, b);
                i++;
            }
        }
        // temp add station test
        for(int i = 0; i < 500;){
            Building b = Building.createBuilding(Building.DRILL_STATION_ORE);
            int facingShift = 0;
            if(rand.nextInt(100) < 75) facingShift = 1;
            if(rand.nextInt(100) < 50) facingShift = 2;
            if(rand.nextInt(100) < 25) facingShift = 3;
            b.shiftFacingDir(facingShift);
            int x = rand.nextInt(tilesGrid.length);
            int y = rand.nextInt(tilesGrid.length);
            if(notOccupied(b, x, y)){
                Building.addBuilding(x, y, b);
                i++;
            }
        }
        for(int i = 0; i < 500;){
            Building b = Building.createBuilding(Building.DRILL_STATION_STONE);
            int facingShift = 0;
            if(rand.nextInt(100) < 25) facingShift = 3;
            if(rand.nextInt(100) < 50) facingShift = 2;
            if(rand.nextInt(100) < 75) facingShift = 1;
            b.shiftFacingDir(facingShift);
            int x = rand.nextInt(tilesGrid.length);
            int y = rand.nextInt(tilesGrid.length);
            if(notOccupied(b, x, y)){
                Building.addBuilding(x, y, b);
                i++;
            }
        }
*/


        GameObject.updateRenderOrder();

    }

    private static void genFlooring(int x, int y){
        for(int i = 0; i < tilesGridSize; i++){
            for(int j = 0; j < tilesGridSize; j++){
                if(BiomeInfo.biomeTypeSelected.defaultFlooring == Flooring.SNOW){
                    if(tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] != Tile.WATER
                            && tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] != Tile.LAVA
                            && tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] != Tile.VOID
                            && tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] != Tile.STONE
                            && tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] != Tile.ORE){
                        int percentage = rand.nextInt(100);
                        if(percentage < 98) flooringGrid[x * tilesGridSize + i][y * tilesGridSize + j] = BiomeInfo.biomeTypeSelected.defaultFlooring;
                        else if(percentage < 99) flooringGrid[x * tilesGridSize + i][y * tilesGridSize + j] = Flooring.GRASS;
                    }
                } else if(BiomeInfo.biomeTypeSelected.defaultFlooring == Flooring.GRASS) {
                    if(tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] == Tile.DIRT){
                        int percentage = rand.nextInt(100);
                        if(percentage < (BiomeInfo.biomeGrid[x][y] == BiomeInfo.BiomeType.WATER ? 60 : 99))
                            flooringGrid[x * tilesGridSize + i][y * tilesGridSize + j] = BiomeInfo.biomeTypeSelected.defaultFlooring;
                    }
                }
                if(tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] == Tile.STONE){
                    flooringGrid[x * tilesGridSize + i][y * tilesGridSize + j] = Flooring.STONE;
                }
                if(tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] == Tile.ORE){
                    flooringGrid[x * tilesGridSize + i][y * tilesGridSize + j] = Flooring.ORE;
                }
            }
        }
    }

    private static void genWalls(int x, int y){
        for(int i = 0; i < tilesGridSize; i++) {
            for (int j = 0; j < tilesGridSize; j++) {
                if(tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] == Tile.STONE){
                    Building.addBuilding(x * tilesGridSize + i, y * tilesGridSize + j, Building.WALL_NATURAL_STONE);
                }
                if(tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] == Tile.ORE){
                    Building.addBuilding(x * tilesGridSize + i, y * tilesGridSize + j, Building.WALL_NATURAL_ORE);
                }
            }
        }
    }

    private static void genStone(int x, int y){
        for(int i = 0; i < tilesGridSize; i++){
            for(int j = 0; j < tilesGridSize; j++){
                if(i == 0){
                    tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = BiomeInfo.biomeTypeSelected.defaultTile;
                } else if (j == 0){
                    tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = BiomeInfo.biomeTypeSelected.defaultTile;
                } else if (i == tilesGridSize - 1){
                    tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = BiomeInfo.biomeTypeSelected.defaultTile;
                } else if (j == tilesGridSize - 1){
                    tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = BiomeInfo.biomeTypeSelected.defaultTile;
                } else {
                    int percentage = rand.nextInt(100);
                    if(percentage < 40) tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = Tile.STONE;
                    else if(percentage < 99) tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = BiomeInfo.biomeTypeSelected.defaultTile;
                    else tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = BiomeInfo.biomeTypeSelected.defaultTile != Tile.DIRT ? Tile.DIRT : Tile.SAND;
                }
            }
        }
        for(int i = 1; i < tilesGridSize-1; i++){
            for(int j = 1; j < tilesGridSize-1; j++){
                // count
                int count = 0;
                if(tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] == Tile.STONE) {
                    if(tilesGrid[x * tilesGridSize + i - 1][y * tilesGridSize + j - 1] == Tile.STONE) count++;
                    if(tilesGrid[x * tilesGridSize + i - 1][y * tilesGridSize + j] == Tile.STONE) count++;
                    if(tilesGrid[x * tilesGridSize + i - 1][y * tilesGridSize + j + 1] == Tile.STONE) count++;
                    if(tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j - 1] == Tile.STONE) count++;
                    if(tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j + 1] == Tile.STONE) count++;
                    if(tilesGrid[x * tilesGridSize + i + 1][y * tilesGridSize + j - 1] == Tile.STONE) count++;
                    if(tilesGrid[x * tilesGridSize + i + 1][y * tilesGridSize + j] == Tile.STONE) count++;
                    if(tilesGrid[x * tilesGridSize + i + 1][y * tilesGridSize + j + 1] == Tile.STONE) count++;
                    if(count < 1) tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = BiomeInfo.biomeTypeSelected.defaultTile;
                } else if(tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] == Tile.ORE) {
                    if(tilesGrid[x * tilesGridSize + i - 1][y * tilesGridSize + j - 1] == Tile.ORE) count++;
                    if(tilesGrid[x * tilesGridSize + i - 1][y * tilesGridSize + j] == Tile.ORE) count++;
                    if(tilesGrid[x * tilesGridSize + i - 1][y * tilesGridSize + j + 1] == Tile.ORE) count++;
                    if(tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j - 1] == Tile.ORE) count++;
                    if(tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j + 1] == Tile.ORE) count++;
                    if(tilesGrid[x * tilesGridSize + i + 1][y * tilesGridSize + j - 1] == Tile.ORE) count++;
                    if(tilesGrid[x * tilesGridSize + i + 1][y * tilesGridSize + j] == Tile.ORE) count++;
                    if(tilesGrid[x * tilesGridSize + i + 1][y * tilesGridSize + j + 1] == Tile.ORE) count++;
                    if(count < 1) tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = BiomeInfo.biomeTypeSelected.defaultTile;
                } else {
                    if(tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] == Tile.SAND || tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] == Tile.DIRT){
                        if(tilesGrid[x * tilesGridSize + i - 1][y * tilesGridSize + j - 1] == Tile.ORE) count++;
                        if(tilesGrid[x * tilesGridSize + i - 1][y * tilesGridSize + j] == Tile.ORE) count++;
                        if(tilesGrid[x * tilesGridSize + i - 1][y * tilesGridSize + j + 1] == Tile.ORE) count++;
                        if(tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j - 1] == Tile.ORE) count++;
                        if(tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j + 1] == Tile.ORE) count++;
                        if(tilesGrid[x * tilesGridSize + i + 1][y * tilesGridSize + j - 1] == Tile.ORE) count++;
                        if(tilesGrid[x * tilesGridSize + i + 1][y * tilesGridSize + j] == Tile.ORE) count++;
                        if(tilesGrid[x * tilesGridSize + i + 1][y * tilesGridSize + j + 1] == Tile.ORE) count++;
                    }
                    if(count > 3) tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = Tile.ORE;
                    else {
                        if(tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] == Tile.SAND || tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] == Tile.DIRT){
                            if(tilesGrid[x * tilesGridSize + i - 1][y * tilesGridSize + j - 1] == Tile.STONE) count++;
                            if(tilesGrid[x * tilesGridSize + i - 1][y * tilesGridSize + j] == Tile.STONE) count++;
                            if(tilesGrid[x * tilesGridSize + i - 1][y * tilesGridSize + j + 1] == Tile.STONE) count++;
                            if(tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j - 1] == Tile.STONE) count++;
                            if(tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j + 1] == Tile.STONE) count++;
                            if(tilesGrid[x * tilesGridSize + i + 1][y * tilesGridSize + j - 1] == Tile.STONE) count++;
                            if(tilesGrid[x * tilesGridSize + i + 1][y * tilesGridSize + j] == Tile.STONE) count++;
                            if(tilesGrid[x * tilesGridSize + i + 1][y * tilesGridSize + j + 1] == Tile.STONE) count++;
                        }
                        if(count > 5) tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = Tile.STONE;
                    }
                }
            }
        }
        genFlooring(x, y);
        genWalls(x, y);
    }

    private static void genOre(int x, int y){
        for(int i = 0; i < tilesGridSize; i++){
            for(int j = 0; j < tilesGridSize; j++){
                if(i == 0){
                    tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = BiomeInfo.biomeTypeSelected.defaultTile;
                } else if (j == 0){
                    tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = BiomeInfo.biomeTypeSelected.defaultTile;
                } else if (i == tilesGridSize - 1){
                    tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = BiomeInfo.biomeTypeSelected.defaultTile;
                } else if (j == tilesGridSize - 1){
                    tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = BiomeInfo.biomeTypeSelected.defaultTile;
                } else {
                    int percentage = rand.nextInt(100);
                    if(percentage < 10) tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = Tile.ORE;
                    else if(percentage < 40) tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = Tile.STONE;
                    else if(percentage < 99) tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = BiomeInfo.biomeTypeSelected.defaultTile;
                    else tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = BiomeInfo.biomeTypeSelected.defaultTile != Tile.DIRT ? Tile.DIRT : Tile.SAND;
                }
            }
        }
        for(int i = 1; i < tilesGridSize-1; i++){
            for(int j = 1; j < tilesGridSize-1; j++){
                // count
                int count = 0;
                if(tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] == Tile.STONE) {
                    if(tilesGrid[x * tilesGridSize + i - 1][y * tilesGridSize + j - 1] == Tile.STONE) count++;
                    if(tilesGrid[x * tilesGridSize + i - 1][y * tilesGridSize + j] == Tile.STONE) count++;
                    if(tilesGrid[x * tilesGridSize + i - 1][y * tilesGridSize + j + 1] == Tile.STONE) count++;
                    if(tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j - 1] == Tile.STONE) count++;
                    if(tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j + 1] == Tile.STONE) count++;
                    if(tilesGrid[x * tilesGridSize + i + 1][y * tilesGridSize + j - 1] == Tile.STONE) count++;
                    if(tilesGrid[x * tilesGridSize + i + 1][y * tilesGridSize + j] == Tile.STONE) count++;
                    if(tilesGrid[x * tilesGridSize + i + 1][y * tilesGridSize + j + 1] == Tile.STONE) count++;
                    if(count < 1) tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = BiomeInfo.biomeTypeSelected.defaultTile;
                } else if(tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] == Tile.ORE) {
                    if(tilesGrid[x * tilesGridSize + i - 1][y * tilesGridSize + j - 1] == Tile.ORE) count++;
                    if(tilesGrid[x * tilesGridSize + i - 1][y * tilesGridSize + j] == Tile.ORE) count++;
                    if(tilesGrid[x * tilesGridSize + i - 1][y * tilesGridSize + j + 1] == Tile.ORE) count++;
                    if(tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j - 1] == Tile.ORE) count++;
                    if(tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j + 1] == Tile.ORE) count++;
                    if(tilesGrid[x * tilesGridSize + i + 1][y * tilesGridSize + j - 1] == Tile.ORE) count++;
                    if(tilesGrid[x * tilesGridSize + i + 1][y * tilesGridSize + j] == Tile.ORE) count++;
                    if(tilesGrid[x * tilesGridSize + i + 1][y * tilesGridSize + j + 1] == Tile.ORE) count++;
                    if(count < 1) tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = BiomeInfo.biomeTypeSelected.defaultTile;
                } else {
                    if(tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] == Tile.SAND || tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] == Tile.DIRT){
                        if(tilesGrid[x * tilesGridSize + i - 1][y * tilesGridSize + j - 1] == Tile.ORE) count++;
                        if(tilesGrid[x * tilesGridSize + i - 1][y * tilesGridSize + j] == Tile.ORE) count++;
                        if(tilesGrid[x * tilesGridSize + i - 1][y * tilesGridSize + j + 1] == Tile.ORE) count++;
                        if(tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j - 1] == Tile.ORE) count++;
                        if(tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j + 1] == Tile.ORE) count++;
                        if(tilesGrid[x * tilesGridSize + i + 1][y * tilesGridSize + j - 1] == Tile.ORE) count++;
                        if(tilesGrid[x * tilesGridSize + i + 1][y * tilesGridSize + j] == Tile.ORE) count++;
                        if(tilesGrid[x * tilesGridSize + i + 1][y * tilesGridSize + j + 1] == Tile.ORE) count++;
                    }
                    if(count > 3) tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = Tile.ORE;
                    else {
                        if(tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] == Tile.SAND || tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] == Tile.DIRT){
                            if(tilesGrid[x * tilesGridSize + i - 1][y * tilesGridSize + j - 1] == Tile.STONE) count++;
                            if(tilesGrid[x * tilesGridSize + i - 1][y * tilesGridSize + j] == Tile.STONE) count++;
                            if(tilesGrid[x * tilesGridSize + i - 1][y * tilesGridSize + j + 1] == Tile.STONE) count++;
                            if(tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j - 1] == Tile.STONE) count++;
                            if(tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j + 1] == Tile.STONE) count++;
                            if(tilesGrid[x * tilesGridSize + i + 1][y * tilesGridSize + j - 1] == Tile.STONE) count++;
                            if(tilesGrid[x * tilesGridSize + i + 1][y * tilesGridSize + j] == Tile.STONE) count++;
                            if(tilesGrid[x * tilesGridSize + i + 1][y * tilesGridSize + j + 1] == Tile.STONE) count++;
                        }
                        if(count > 5) tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = Tile.STONE;
                    }
                }
            }
        }

        genFlooring(x, y);
        genWalls(x, y);
    }

    private static void genWater(int x, int y){
        Tile[][] swap = new Tile[tilesGridSize][tilesGridSize];
        for(int i = 0; i < tilesGridSize; i++){
            for(int j = 0; j < tilesGridSize; j++){
                if(i == 0){
                    if(x - 1 >= 0 && BiomeInfo.biomeGrid[x - 1][y] != BiomeInfo.BiomeType.WATER && BiomeInfo.biomeGrid[x - 1][y] != BiomeInfo.BiomeType.VOID) swap[i][j] = tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = BiomeInfo.biomeTypeSelected.defaultTile;
                    else swap[i][j] = tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = Tile.WATER;
                } else if (j == 0){
                    if(y - 1 >= 0 && BiomeInfo.biomeGrid[x][y - 1] != BiomeInfo.BiomeType.WATER && BiomeInfo.biomeGrid[x][y - 1] != BiomeInfo.BiomeType.VOID) swap[i][j] = tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = BiomeInfo.biomeTypeSelected.defaultTile;
                    else swap[i][j] = tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = Tile.WATER;
                } else if (i == tilesGridSize - 1){
                    if(x + 1 < BiomeInfo.biomeGridSize && BiomeInfo.biomeGrid[x + 1][y] != BiomeInfo.BiomeType.WATER && BiomeInfo.biomeGrid[x + 1][y] != BiomeInfo.BiomeType.VOID) swap[i][j] = tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = BiomeInfo.biomeTypeSelected.defaultTile;
                    else swap[i][j] = tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = Tile.WATER;
                } else if (j == tilesGridSize - 1){
                    if(y + 1 < BiomeInfo.biomeGridSize && BiomeInfo.biomeGrid[x][y + 1] != BiomeInfo.BiomeType.WATER && BiomeInfo.biomeGrid[x][y + 1] != BiomeInfo.BiomeType.VOID) swap[i][j] = tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = BiomeInfo.biomeTypeSelected.defaultTile;
                    else swap[i][j] = tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = Tile.WATER;
                } else {
                    int percentage = rand.nextInt(100);
                    if(percentage < 55) swap[i][j] = tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = Tile.WATER;
                    else swap[i][j] = tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = BiomeInfo.biomeTypeSelected.defaultTile;
                }
            }
        }
        for(int iter = 0; iter < iterations; iter++){
            for(int i = 1; i < tilesGridSize - 1; i++){
                for(int j = 1; j < tilesGridSize - 1; j++) {
                    // count bordering
                    int count = 0;
                    if(tilesGrid[x * tilesGridSize + i - 1][y * tilesGridSize + j - 1] == BiomeInfo.biomeTypeSelected.defaultTile) count++;
                    if(tilesGrid[x * tilesGridSize + i - 1][y * tilesGridSize + j] == BiomeInfo.biomeTypeSelected.defaultTile) count++;
                    if(tilesGrid[x * tilesGridSize + i - 1][y * tilesGridSize + j + 1] == BiomeInfo.biomeTypeSelected.defaultTile) count++;
                    if(tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j - 1] == BiomeInfo.biomeTypeSelected.defaultTile) count++;
                    if(tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j + 1] == BiomeInfo.biomeTypeSelected.defaultTile) count++;
                    if(tilesGrid[x * tilesGridSize + i + 1][y * tilesGridSize + j - 1] == BiomeInfo.biomeTypeSelected.defaultTile) count++;
                    if(tilesGrid[x * tilesGridSize + i + 1][y * tilesGridSize + j] == BiomeInfo.biomeTypeSelected.defaultTile) count++;
                    if(tilesGrid[x * tilesGridSize + i + 1][y * tilesGridSize + j + 1] == BiomeInfo.biomeTypeSelected.defaultTile) count++;


                    if(Math.abs(i - tilesGridSize / 2) > 10 || Math.abs(j - tilesGridSize / 2) > 10
                            // &&((i < tilesGridSize / 2 && x - 1 >= 0 && BiomeInfo.biomeGrid[x - 1][y] != BiomeInfo.BiomeType.WATER && BiomeInfo.biomeGrid[x - 1][y] != BiomeInfo.BiomeType.VOID)
                            // ||(j < tilesGridSize / 2 && y - 1 >= 0 && BiomeInfo.biomeGrid[x][y - 1] != BiomeInfo.BiomeType.WATER && BiomeInfo.biomeGrid[x][y - 1] != BiomeInfo.BiomeType.VOID)
                            // ||(i > tilesGridSize / 2 && x + 1 < BiomeInfo.biomeGridSize && BiomeInfo.biomeGrid[x + 1][y] != BiomeInfo.BiomeType.WATER && BiomeInfo.biomeGrid[x + 1][y] != BiomeInfo.BiomeType.VOID)
                            // ||(j > tilesGridSize / 2 && y + 1 < BiomeInfo.biomeGridSize && BiomeInfo.biomeGrid[x][y + 1] != BiomeInfo.BiomeType.WATER && BiomeInfo.biomeGrid[x][y + 1] != BiomeInfo.BiomeType.VOID))
                    ) {
                        if(tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] == BiomeInfo.biomeTypeSelected.defaultTile) {
                            if (count > 2)
                                swap[i][j] = tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j];
                            else
                                swap[i][j] = Tile.WATER;
                        } else if (tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] == Tile.WATER)
                            if (count > 5)
                                swap[i][j] = BiomeInfo.biomeTypeSelected.defaultTile;
                    } else {
                        if(tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] == BiomeInfo.biomeTypeSelected.defaultTile) {
                            if (count > 3)
                                swap[i][j] = tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j];
                            else
                                swap[i][j] = Tile.WATER;
                        } else if (tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] == Tile.WATER)
                            if (count > 6)
                                swap[i][j] = BiomeInfo.biomeTypeSelected.defaultTile;
                    }
                }
            }
            // swap
            for(int i = 0; i < tilesGridSize; i++){
                for(int j = 0; j < tilesGridSize; j++) {
                    tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = swap[i][j];
                }
            }
        }


        genFlooring(x, y);
    }

    private static void genWoods(int x, int y){

        genField(x, y);

        // gen woods
        boolean[][] treeGrid = new boolean[tilesGridSize][tilesGridSize];
        boolean[][] swap = new boolean[tilesGridSize][tilesGridSize];
        for(int i = 0; i < tilesGridSize; i++){
            for(int j = 0; j < tilesGridSize; j++){
                int percentage = rand.nextInt(100);
                if(percentage < 60) {
                    treeGrid[i][j] = true;
                }
            }
        }
        for(int iter = 0; iter < 2; iter++){
            for(int i = 1; i < tilesGridSize - 1; i++){
                for(int j = 1; j < tilesGridSize - 1; j++) {
                    // count bordering
                    int count = 0;
                    if(treeGrid[i - 1][j - 1]) count++;
                    if(treeGrid[i - 1][j]) count++;
                    if(treeGrid[i - 1][j + 1]) count++;
                    if(treeGrid[i][j - 1]) count++;
                    if(treeGrid[i][j + 1]) count++;
                    if(treeGrid[i + 1][j - 1]) count++;
                    if(treeGrid[i + 1][j]) count++;
                    if(treeGrid[i + 1][j + 1]) count++;
                    // iterate
                    if(!treeGrid[i][j]) {
                        if (count > 4)
                            swap[i][j] = true;
                        else
                            swap[i][j] = false;
                    } else if (treeGrid[i][j])
                        if (count < 1)
                            swap[i][j] = false;
                }
            }
            // swap
            for(int i = 0; i < tilesGridSize; i++){
                for(int j = 0; j < tilesGridSize; j++) {
                    treeGrid[i][j] = swap[i][j];
                }
            }
        }
        for(int i = 0; i < tilesGridSize; i++){
            for(int j = 0; j < tilesGridSize; j++) {
                if(treeGrid[i][j] && tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] == Tile.DIRT){
                    Tree.addTree(x * tilesGridSize + i, y * tilesGridSize + j, true);
                }
            }
        }
    }

    private static void genField(int x, int y){
        if(BiomeInfo.biomeTypeSelected.defaultTile == Tile.SAND){
            // gen for sand biome
            Tile[][] swap = new Tile[tilesGridSize][tilesGridSize];
            for(int i = 0; i < tilesGridSize; i++){
                for(int j = 0; j < tilesGridSize; j++){
                    if(i == 0){
                        if(x - 1 >= 0 && BiomeInfo.biomeGrid[x - 1][y] != BiomeInfo.BiomeType.FIELD && BiomeInfo.biomeGrid[x - 1][y] != BiomeInfo.BiomeType.VOID) swap[i][j] = tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = BiomeInfo.biomeTypeSelected.defaultTile;
                        else swap[i][j] = tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = Tile.SAND;
                    } else if (j == 0){
                        if(y - 1 >= 0 && BiomeInfo.biomeGrid[x][y - 1] != BiomeInfo.BiomeType.FIELD && BiomeInfo.biomeGrid[x][y - 1] != BiomeInfo.BiomeType.VOID) swap[i][j] = tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = BiomeInfo.biomeTypeSelected.defaultTile;
                        else swap[i][j] = tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = Tile.SAND;
                    } else if (i == tilesGridSize - 1){
                        if(x + 1 < BiomeInfo.biomeGridSize && BiomeInfo.biomeGrid[x + 1][y] != BiomeInfo.BiomeType.FIELD && BiomeInfo.biomeGrid[x + 1][y] != BiomeInfo.BiomeType.VOID) swap[i][j] = tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = BiomeInfo.biomeTypeSelected.defaultTile;
                        else swap[i][j] = tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = Tile.SAND;
                    } else if (j == tilesGridSize - 1){
                        if(y + 1 < BiomeInfo.biomeGridSize && BiomeInfo.biomeGrid[x][y + 1] != BiomeInfo.BiomeType.FIELD && BiomeInfo.biomeGrid[x][y + 1] != BiomeInfo.BiomeType.VOID) swap[i][j] = tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = BiomeInfo.biomeTypeSelected.defaultTile;
                        else swap[i][j] = tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = Tile.SAND;
                    } else {
                        int percentage = rand.nextInt(100);
                        if(percentage < 40) swap[i][j] = tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = Tile.DIRT;
                        else swap[i][j] = tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = Tile.SAND;
                    }
                }
            }
            for(int iter = 0; iter < iterations; iter++){
                for(int i = 1; i < tilesGridSize - 1; i++){
                    for(int j = 1; j < tilesGridSize - 1; j++) {
                        // count bordering
                        int count = 0;
                        if(tilesGrid[x * tilesGridSize + i - 1][y * tilesGridSize + j - 1] == BiomeInfo.biomeTypeSelected.defaultTile) count++;
                        if(tilesGrid[x * tilesGridSize + i - 1][y * tilesGridSize + j] == BiomeInfo.biomeTypeSelected.defaultTile) count++;
                        if(tilesGrid[x * tilesGridSize + i - 1][y * tilesGridSize + j + 1] == BiomeInfo.biomeTypeSelected.defaultTile) count++;
                        if(tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j - 1] == BiomeInfo.biomeTypeSelected.defaultTile) count++;
                        if(tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j + 1] == BiomeInfo.biomeTypeSelected.defaultTile) count++;
                        if(tilesGrid[x * tilesGridSize + i + 1][y * tilesGridSize + j - 1] == BiomeInfo.biomeTypeSelected.defaultTile) count++;
                        if(tilesGrid[x * tilesGridSize + i + 1][y * tilesGridSize + j] == BiomeInfo.biomeTypeSelected.defaultTile) count++;
                        if(tilesGrid[x * tilesGridSize + i + 1][y * tilesGridSize + j + 1] == BiomeInfo.biomeTypeSelected.defaultTile) count++;
                        // iterate
                        if(tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] != BiomeInfo.biomeTypeSelected.defaultTile) {
                            if (count > 5)
                                swap[i][j] = tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j];
                            else
                                swap[i][j] = Tile.DIRT;
                        } else if (tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] == Tile.DIRT)
                            if (count > 3)
                                swap[i][j] = Tile.SAND;
                    }
                }
                // swap
                for(int i = 0; i < tilesGridSize; i++){
                    for(int j = 0; j < tilesGridSize; j++) {
                        tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = swap[i][j];
                    }
                }
            }
        } else {
            // gen for dirt biome
            for(int i = 0; i < tilesGridSize; i++){
                for(int j = 0; j < tilesGridSize; j++){
                    int percentage = rand.nextInt(100);
                    if(percentage < 99) tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = Tile.DIRT;
                    else tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = Tile.SAND;
                }
            }
        }

        genFlooring(x, y);
    }

    private static void genSand(int x, int y){
        if(BiomeInfo.biomeTypeSelected.defaultTile == Tile.DIRT){
            // gen for dirt biome
            Tile[][] swap = new Tile[tilesGridSize][tilesGridSize];
            for(int i = 0; i < tilesGridSize; i++){
                for(int j = 0; j < tilesGridSize; j++){
                    if(i == 0){
                        if(x - 1 >= 0 && BiomeInfo.biomeGrid[x - 1][y] != BiomeInfo.BiomeType.SAND && BiomeInfo.biomeGrid[x - 1][y] != BiomeInfo.BiomeType.VOID) swap[i][j] = tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = BiomeInfo.biomeTypeSelected.defaultTile;
                        else swap[i][j] = tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = Tile.SAND;
                    } else if (j == 0){
                        if(y - 1 >= 0 && BiomeInfo.biomeGrid[x][y - 1] != BiomeInfo.BiomeType.SAND && BiomeInfo.biomeGrid[x][y - 1] != BiomeInfo.BiomeType.VOID) swap[i][j] = tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = BiomeInfo.biomeTypeSelected.defaultTile;
                        else swap[i][j] = tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = Tile.SAND;
                    } else if (i == tilesGridSize - 1){
                        if(x + 1 < BiomeInfo.biomeGridSize && BiomeInfo.biomeGrid[x + 1][y] != BiomeInfo.BiomeType.SAND && BiomeInfo.biomeGrid[x + 1][y] != BiomeInfo.BiomeType.VOID) swap[i][j] = tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = BiomeInfo.biomeTypeSelected.defaultTile;
                        else swap[i][j] = tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = Tile.SAND;
                    } else if (j == tilesGridSize - 1){
                        if(y + 1 < BiomeInfo.biomeGridSize && BiomeInfo.biomeGrid[x][y + 1] != BiomeInfo.BiomeType.SAND && BiomeInfo.biomeGrid[x][y + 1] != BiomeInfo.BiomeType.VOID) swap[i][j] = tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = BiomeInfo.biomeTypeSelected.defaultTile;
                        else swap[i][j] = tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = Tile.SAND;
                    } else {
                        int percentage = rand.nextInt(100);
                        if(percentage < 60) swap[i][j] = tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = Tile.SAND;
                        else swap[i][j] = tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = Tile.DIRT;
                    }
                }
            }
            for(int iter = 0; iter < iterations; iter++){
                for(int i = 1; i < tilesGridSize - 1; i++){
                    for(int j = 1; j < tilesGridSize - 1; j++) {
                        // count bordering
                        int count = 0;
                        if(tilesGrid[x * tilesGridSize + i - 1][y * tilesGridSize + j - 1] == BiomeInfo.biomeTypeSelected.defaultTile) count++;
                        if(tilesGrid[x * tilesGridSize + i - 1][y * tilesGridSize + j] == BiomeInfo.biomeTypeSelected.defaultTile) count++;
                        if(tilesGrid[x * tilesGridSize + i - 1][y * tilesGridSize + j + 1] == BiomeInfo.biomeTypeSelected.defaultTile) count++;
                        if(tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j - 1] == BiomeInfo.biomeTypeSelected.defaultTile) count++;
                        if(tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j + 1] == BiomeInfo.biomeTypeSelected.defaultTile) count++;
                        if(tilesGrid[x * tilesGridSize + i + 1][y * tilesGridSize + j - 1] == BiomeInfo.biomeTypeSelected.defaultTile) count++;
                        if(tilesGrid[x * tilesGridSize + i + 1][y * tilesGridSize + j] == BiomeInfo.biomeTypeSelected.defaultTile) count++;
                        if(tilesGrid[x * tilesGridSize + i + 1][y * tilesGridSize + j + 1] == BiomeInfo.biomeTypeSelected.defaultTile) count++;
                        // iterate
                        if(tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] != BiomeInfo.biomeTypeSelected.defaultTile) {
                            if (count > 5)
                                swap[i][j] = tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j];
                            else
                                swap[i][j] = Tile.SAND;
                        } else if (tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] == Tile.SAND)
                            if (count > 3)
                                swap[i][j] = Tile.DIRT;
                    }
                }
                // swap
                for(int i = 0; i < tilesGridSize; i++){
                    for(int j = 0; j < tilesGridSize; j++) {
                        tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = swap[i][j];
                    }
                }
            }
        } else {
            // gen for sand biome
            for(int i = 0; i < tilesGridSize; i++){
                for(int j = 0; j < tilesGridSize; j++){
                    tilesGrid[x * tilesGridSize + i][y * tilesGridSize + j] = Tile.SAND;
                }
            }
        }

        genFlooring(x, y);
    }

    private static void genLavaStone(int x, int y){

        genStone(x, y);
        genLava(x, y);

    }

    private static void genLavaOre(int x, int y){

        genOre(x, y);
        genLava(x, y);

    }

    private static void genLava(int x, int y){
        int lavaCount = rand.nextInt(3) + 1;
        for(int lava = 0; lava < lavaCount;) {
            int lavaX = rand.nextInt(tilesGridSize) - 1;
            int lavaY = rand.nextInt(tilesGridSize) - 1;
            if(tilesGrid[x * tilesGridSize + lavaX + 1][y * tilesGridSize + lavaY + 1] == Tile.STONE || tilesGrid[x * tilesGridSize + lavaX + 1][y * tilesGridSize + lavaY + 1] == Tile.ORE){
                tilesGrid[x * tilesGridSize + lavaX + 1][y * tilesGridSize + lavaY + 1] = Tile.LAVA;
                lava++;
            }
        }
    }

    public static boolean notOccupied(GameObject go, int x, int y){
        // check grid and tiles
        for(int i = x; i < x + go.getAdjustedWidth(); i++){
            for(int j = y; j < y + go.getAdjustedHeight(); j++){
                if(i >= tilesGrid.length) return false;
                if(j >= tilesGrid.length) return false;
                if(!tilesGrid[i][j].isPassable()) return false;
            }
        }

        for(int a = 0; a < GameObject.renderOrder.size(); a++){
            Point p = GameObject.renderOrder.get(a);

            if(GameObject.gameObjects[p.x][p.y] != null && !GameObject.gameObjects[p.x][p.y].isPassable()){
                // check all spots in found object
                for(int i2 = p.x; i2 < p.x + GameObject.gameObjects[p.x][p.y].getAdjustedWidth(); i2++) {
                    for (int j2 = p.y; j2 < p.y + GameObject.gameObjects[p.x][p.y].getAdjustedHeight(); j2++) {

                        // check all spots in param object
                        for(int i3 = x; i3 < x + go.getAdjustedWidth(); i3++) {
                            for (int j3 = y; j3 < y + go.getAdjustedHeight(); j3++) {

                                // if match then quit
                                if(i2 == i3 && j2 == j3) return false;

                            }
                        }
                    }
                }
            }
        }

        for(int i = 0; i < Walkable.homeCitizens.size(); i++){
            if(Walkable.homeCitizens.get(i).getID() != go.getID() && Walkable.homeCitizens.get(i).getX() == x && Walkable.homeCitizens.get(i).getY() == y)
                return false;
        }
        return true;
    }

}