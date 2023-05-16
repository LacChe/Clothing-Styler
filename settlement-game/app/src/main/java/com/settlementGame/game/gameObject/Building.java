package com.settlementGame.game.gameObject;

import android.graphics.Color;
import android.graphics.Rect;

import com.settlementGame.R;
import com.settlementGame.framework.Graphics;
import com.settlementGame.framework.Input;
import com.settlementGame.framework.Pixmap;
import com.settlementGame.game.Assets;
import com.settlementGame.game.SettlementGame;
import com.settlementGame.game.Recipe;
import com.settlementGame.game.screen.Button;
import com.settlementGame.game.screen.GameScreen;
import com.settlementGame.game.terrain.BiomeInfo;
import com.settlementGame.game.terrain.TerrainInfo;

import java.util.ArrayList;

public class Building extends GameObject{

    private static int maxStorage = 100;

    private boolean buttonsMadeSubMenu = false;
    private static Button retButtonSubMenu;
    private static Button lessButtonSubMenu;
    private static Button moreButtonSubMenu;
    private static Button rptButtonSubMenu;
    private static Button confirmButtonSubMenu;
    private static Button addButtonSubMenu;

    // todo recipe button list
    // todo remove button list

    // test furniture 0, 32, 96

    public static final Building WALL_NATURAL_STONE = new Building(Assets.walls, SettlementGame.AGame.getResources().getStringArray(R.array.wall_names)[0], //
            -1, -1,  0, 0, 32, 32, 16,17,16,true,0,1,1,false, false,0,0, //
            new Materials(new GameObject[]{ Resource.createResource(Resource.STONE)}, new int[]{10}), new Recipe[]{}, false);
    public static final Building WALL_NATURAL_ORE = new Building(Assets.walls, SettlementGame.AGame.getResources().getStringArray(R.array.wall_names)[1], //
            -1, -1,  32, 0, 32, 32, 16,17,16,true,0,1,1,false, false,0,0, //
            new Materials(new GameObject[]{ Resource.createResource(Resource.STONE), Resource.createResource(Resource.ORE)}, new int[]{2, 8}), new Recipe[]{}, false);

    public static final Building TABLE_WOOD_1x1 = new Building(Assets.tables, SettlementGame.AGame.getResources().getStringArray(R.array.table_names)[0], //
            0, 0,  0, 0, 32, 56, 16,24,56,false,0,1,1,false, true,16,17, //
            new Materials(new GameObject[]{ Resource.createResource(Resource.WOOD)}, new int[]{4}), new Recipe[]{}, false);

    public static final Building TABLE_WOOD_2x1 = new Building(Assets.tables, SettlementGame.AGame.getResources().getStringArray(R.array.table_names)[1], //
            0, 0,  32, 16, 64, 56, 16,24,56,false,0,1,2,false, true,24,24, //
            new Materials(new GameObject[]{ Resource.createResource(Resource.WOOD)}, new int[]{8}), new Recipe[]{}, false);

    public static final Building TABLE_WOOD_2x2 = new Building(Assets.tables, SettlementGame.AGame.getResources().getStringArray(R.array.table_names)[2], //
            0, 0,  96, 16, 64, 56, 16,24,56,false,0,2,2,false, true,32,27, //
            new Materials(new GameObject[]{ Resource.createResource(Resource.WOOD)}, new int[]{16}), new Recipe[]{}, false);

    public static final Building STATION_DRILL_STONE = new Building(Assets.stations, SettlementGame.AGame.getResources().getStringArray(R.array.station_names)[0], //
            0, 0,  0, 0, 32, 56, 16,17,16,true,0,1,1,true, false,0,0, //
            new Materials(new GameObject[]{ Resource.createResource(Resource.WOOD), Resource.createResource(Resource.STONE)}, new int[]{10, 10}), //
            new Recipe[]{Recipe.createRecipe(Recipe.mineStone)}, false);

    public static final Building STATION_DRILL_ORE = new Building(Assets.stations, SettlementGame.AGame.getResources().getStringArray(R.array.station_names)[1], //
            0, 0,  32, 0, 32, 56, 16,17,16,true,0,1,1,true, false,0,0, //
            new Materials(new GameObject[]{ Resource.createResource(Resource.WOOD), Resource.createResource(Resource.STONE), Resource.createResource(Resource.BAR)}, new int[]{10, 10, 10}), //
            new Recipe[]{Recipe.createRecipe(Recipe.mineOre)}, false);

    public static final Building STATION_FURNACE = new Building(Assets.stations, SettlementGame.AGame.getResources().getStringArray(R.array.station_names)[2], //
            0, 0,  64, 0, 32, 56, 16,17,16,true,0,1,1,true, false,0,0, //
            new Materials(new GameObject[]{ Resource.createResource(Resource.WOOD), Resource.createResource(Resource.STONE), Resource.createResource(Resource.BAR)}, new int[]{10, 10, 10}), //
            new Recipe[]{Recipe.createRecipe(Recipe.smeltOre)}, false);


    private Pixmap pixmap;

    private int spriteWidth, spriteHeight, spriteDrawOffset, spriteFromGround, cropHeight;

    // recipe
    protected ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
    protected boolean addRecipeMenu = false;
    protected int recipeIndexSelected = 0;
    protected int recipeAmountSelected = 1;
    protected boolean setRecipeAmountMenu = false;

    // occupiable
    protected boolean occupiable;

    // todo make is light
    protected boolean isLight;
    protected int lightOffX, lightOffY;


    public Building(Pixmap pixmap, String n, int quality, int creatorID, int spriteOffX, int drawShiftX,  int spriteWidth, int spriteHeight, int spriteDrawOffset, int spriteFromGround, int cropHeight, boolean opaque, int facingDir, //
                    int width, int height, boolean hasStorage, boolean hasDisplaySlot, int displaySlotDrawX, int displaySlotDrawY, Materials materials, Recipe[] availableRecipes, boolean occupiable){
        super(n, facingDir, width, height, -1);
        this.pixmap = pixmap;
        this.quality = quality;
        this.creatorID = creatorID;
        this.spriteOffX = spriteOffX;
        this.drawShiftX = drawShiftX;
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
        this.spriteDrawOffset = spriteDrawOffset;
        this.spriteFromGround = spriteFromGround;
        this.cropHeight = cropHeight;
        this.opaque = opaque;
        this.materials = materials;
        this.hasStorage = hasStorage;
        this.hasDisplaySlot = hasDisplaySlot;
        this.displaySlotDrawX = displaySlotDrawX;
        this.displaySlotDrawY = displaySlotDrawY;
        this.availableRecipes = availableRecipes;
        this.occupiable = occupiable;
        passable = false;
    }

    public void draw(Graphics g, float worldOffSetX, float worldOffSetY, int i, int j, float scale){

        float x = worldOffSetX + (i - j + TerrainInfo.tilesGridSize * BiomeInfo.biomeGridSize) * spriteDrawOffset - drawShiftX;
        float y = worldOffSetY + ((j + i) * spriteDrawOffset) / 2.0f - 0 - spriteFromGround;

        int drawH = spriteHeight;
        if(i + 1 < gameObjects.length && gameObjects[i+1][j] != null &&gameObjects[i+1][j].opaque
                && j + 1 < gameObjects.length && gameObjects[i][j+1] != null &&gameObjects[i][j+1].opaque)
            drawH = cropHeight;

        if((x + spriteWidth) * scale > 0 && (x) * scale < SettlementGame.WIDTH && (y + drawH) * scale > 0 && (y) * scale < SettlementGame.HEIGHT)
            g.drawPixmap(pixmap, x * scale, y * scale, spriteWidth * scale, drawH * scale, spriteOffX, facingDir * spriteHeight, spriteWidth, drawH);
    }

    protected void drawMenuRecipe(Graphics g, float scale){
        if(!buttonsMadeSubMenu){
            retButtonSubMenu = new Button("return", new Rect((int)(SettlementGame.WIDTH * 0.7f), (int)(SettlementGame.HEIGHT * 0.1f + 200), (int)(SettlementGame.WIDTH * 0.7f + 200), (int)(SettlementGame.HEIGHT * 0.1f + 200 + 90)), new Rect(0, 0, 0, 0));
            lessButtonSubMenu = new Button("Less", new Rect((int)(SettlementGame.WIDTH * 0.7f), (int)(SettlementGame.HEIGHT * 0.1f + 300 + 0 * 100), (int)(SettlementGame.WIDTH * 0.7f + 90), (int)(SettlementGame.HEIGHT * 0.1f + 300 + 0 * 100 + 90)), new Rect(0, 0, 0, 0));
            moreButtonSubMenu = new Button("More", new Rect((int)(SettlementGame.WIDTH * 0.7f), (int)(SettlementGame.HEIGHT * 0.1f + 300 + 1 * 100), (int)(SettlementGame.WIDTH * 0.7f + 90), (int)(SettlementGame.HEIGHT * 0.1f + 300 + 1 * 100 + 90)), new Rect(0, 0, 0, 0));
            rptButtonSubMenu = new Button("RPT", new Rect((int)(SettlementGame.WIDTH * 0.7f), (int)(SettlementGame.HEIGHT * 0.1f + 300 + 2 * 100), (int)(SettlementGame.WIDTH * 0.7f + 90), (int)(SettlementGame.HEIGHT * 0.1f + 300 + 2 * 100 + 90)), new Rect(0, 0, 0, 0));
            confirmButtonSubMenu = new Button("Confirm", new Rect((int)(SettlementGame.WIDTH * 0.7f), (int)(SettlementGame.HEIGHT * 0.1f + 300 + 3 * 100), (int)(SettlementGame.WIDTH * 0.7f + 90), (int)(SettlementGame.HEIGHT * 0.1f + 300 + 3 * 100 + 90)), new Rect(0, 0, 0, 0));

            addButtonSubMenu = new Button("Add", new Rect((int)(SettlementGame.WIDTH * 0.7f), (int)(SettlementGame.HEIGHT * 0.1f + 200), (int)(SettlementGame.WIDTH * 0.7f + 200), (int)(SettlementGame.HEIGHT * 0.1f + 200 + 90)), new Rect(0, 0, 0, 0));

            buttonsMadeSubMenu = true;
        }

        if(setRecipeAmountMenu){
            // top row
            g.drawText("Set Repeat Amount: " + (recipeAmountSelected == -1 ? "RPT" : recipeAmountSelected + ""), SettlementGame.WIDTH * 0.2f + 50, SettlementGame.HEIGHT * 0.1f + 250);

            retButtonSubMenu.draw(g, scale);
            lessButtonSubMenu .draw(g, scale);
            moreButtonSubMenu.draw(g, scale);
            rptButtonSubMenu.draw(g, scale);
            confirmButtonSubMenu.draw(g, scale);

        } else if(addRecipeMenu){
            // top row
            g.drawText("recipes Available", SettlementGame.WIDTH * 0.2f + 50, SettlementGame.HEIGHT * 0.1f + 250);

            retButtonSubMenu.draw(g, scale);

            for(int i = 0; i < availableRecipes.length; i++){
                // todo change to button, make scrollable
                g.drawRect(SettlementGame.WIDTH * 0.7f, SettlementGame.HEIGHT * 0.1f + 300 + i * 100, 90, 90, Color.rgb(255, 74, 24)); // choose recipe button
                g.drawText(availableRecipes[i].recipeHidden ? "Hidden Recipe" : availableRecipes[i].name, SettlementGame.WIDTH * 0.2f + 50, SettlementGame.HEIGHT * 0.1f + 350 + i * 50);
            }
        } else {
            g.drawText("RECIPES",SettlementGame.WIDTH * 0.2f + 50, SettlementGame.HEIGHT * 0.1f + 200);
            // top row
            g.drawText("Name", SettlementGame.WIDTH * 0.2f + 50, SettlementGame.HEIGHT * 0.1f + 250);
            g.drawText("#", SettlementGame.WIDTH * 0.55f, SettlementGame.HEIGHT * 0.1f + 250);

            addButtonSubMenu.draw(g, scale);

            // list recipes
            for(int i = 0; i < recipeList.size(); i++){

                g.drawText(recipeList.get(i).name + " (" + recipeList.get(i).progression + "/" + recipeList.get(i).progressionNeeded + ")", SettlementGame.WIDTH * 0.2f + 50, SettlementGame.HEIGHT * 0.1f + 350 + i * 100);
                g.drawText(recipeList.get(i).repeatCount == -1 ?  "RPT" : "" + recipeList.get(i).repeatCount, SettlementGame.WIDTH * 0.55f, SettlementGame.HEIGHT * 0.1f + 350 + i * 100);

                // todo change to button, make scrollable
                g.drawRect(SettlementGame.WIDTH * 0.7f, SettlementGame.HEIGHT * 0.1f + 300 + i * 100, 90, 90, Color.rgb(255, 74, 24)); // remove recipe button
                g.drawText("RMV", SettlementGame.WIDTH * 0.7f, SettlementGame.HEIGHT * 0.1f + 350 + i * 100);
            }
        }
    }

    public void update(float deltaTime){

    }

    public static boolean addProgression(int x, int y, int skillLevel) {
        boolean repeatComplete = false;
        // for(int i = 0; i < ((Building)gameObjects[x][y]).recipeList.size(); i++){
        if(((Building)gameObjects[x][y]).recipeList.size() > 0) {
            ((Building)gameObjects[x][y]).recipeList.get(0).progression++;
            if(((Building)gameObjects[x][y]).recipeList.get(0).progression >= ((Building)gameObjects[x][y]).recipeList.get(0).progressionNeeded){

                repeatComplete = true;

                ((Building)gameObjects[x][y]).recipeList.get(0).progression = 0;
                if(((Building)gameObjects[x][y]).recipeList.get(0).repeatCount != -1) ((Building)gameObjects[x][y]).recipeList.get(0).repeatCount--;

                // add product
                for(int j = 0; j < ((Building)gameObjects[x][y]).recipeList.get(0).results.getMats().length; j++){
                    for(int k = 0; k < ((Building)gameObjects[x][y]).recipeList.get(0).results.getAmounts()[j]; k++){
                        ((Building)gameObjects[x][y]).storage.add(((Building)gameObjects[x][y]).recipeList.get(0).results.getMats()[j]); // may have a bug, no unique item id?
                    }
                }

                // check for every material to remove
                for(int m = 0; m < ((Building)gameObjects[x][y]).recipeList.get(0).components.getMats().length; m++){
                    for(int a = 0; a < ((Building)gameObjects[x][y]).recipeList.get(0).components.getAmounts()[m]; a++){
                        // remove that item from storage
                        for(int s = 0; s < ((Building)gameObjects[x][y]).storage.size(); s++){
                            if(((Building)gameObjects[x][y]).storage.get(s).name.equals(((Building)gameObjects[x][y]).recipeList.get(0).components.getMats()[m].name)){
                                ((Building)gameObjects[x][y]).storage.remove(s);
                                break;
                            }
                        }
                    }
                }
            }
        }

        // for every recipe
        for (int i = ((Building)gameObjects[x][y]).recipeList.size() - 1; i >= 0; i--) {
            // if it is complete
            if(((Building)gameObjects[x][y]).recipeList.get(i).repeatCount <= 0 && ((Building)gameObjects[x][y]).recipeList.get(i).repeatCount != -1) {
                // remove the completed recipe
                ((Building)gameObjects[x][y]).recipeList.remove(i);
            }
        }
        return repeatComplete;
    }

    protected void updateMenuRecipe(Input.TouchEvent e){
        // todo move to one spot
        if(!buttonsMadeSubMenu){
            retButtonSubMenu = new Button("return", new Rect((int)(SettlementGame.WIDTH * 0.7f), (int)(SettlementGame.HEIGHT * 0.1f + 200), (int)(SettlementGame.WIDTH * 0.7f + 200), (int)(SettlementGame.HEIGHT * 0.1f + 200 + 90)), new Rect(0, 0, 0, 0));
            lessButtonSubMenu = new Button("Less", new Rect((int)(SettlementGame.WIDTH * 0.7f), (int)(SettlementGame.HEIGHT * 0.1f + 300 + 0 * 100), (int)(SettlementGame.WIDTH * 0.7f + 90), (int)(SettlementGame.HEIGHT * 0.1f + 300 + 0 * 100 + 90)), new Rect(0, 0, 0, 0));
            moreButtonSubMenu = new Button("More", new Rect((int)(SettlementGame.WIDTH * 0.7f), (int)(SettlementGame.HEIGHT * 0.1f + 300 + 1 * 100), (int)(SettlementGame.WIDTH * 0.7f + 90), (int)(SettlementGame.HEIGHT * 0.1f + 300 + 1 * 100 + 90)), new Rect(0, 0, 0, 0));
            rptButtonSubMenu = new Button("RPT", new Rect((int)(SettlementGame.WIDTH * 0.7f), (int)(SettlementGame.HEIGHT * 0.1f + 300 + 2 * 100), (int)(SettlementGame.WIDTH * 0.7f + 90), (int)(SettlementGame.HEIGHT * 0.1f + 300 + 2 * 100 + 90)), new Rect(0, 0, 0, 0));
            confirmButtonSubMenu = new Button("Confirm", new Rect((int)(SettlementGame.WIDTH * 0.7f), (int)(SettlementGame.HEIGHT * 0.1f + 300 + 3 * 100), (int)(SettlementGame.WIDTH * 0.7f + 90), (int)(SettlementGame.HEIGHT * 0.1f + 300 + 3 * 100 + 90)), new Rect(0, 0, 0, 0));

            addButtonSubMenu = new Button("Add", new Rect((int)(SettlementGame.WIDTH * 0.7f), (int)(SettlementGame.HEIGHT * 0.1f + 200), (int)(SettlementGame.WIDTH * 0.7f + 200), (int)(SettlementGame.HEIGHT * 0.1f + 200 + 90)), new Rect(0, 0, 0, 0));

            buttonsMadeSubMenu = true;
        }

        if(setRecipeAmountMenu){
            if(retButtonSubMenu.clicked(e)){ // return button
                setRecipeAmountMenu = false;
                addRecipeMenu = true;
                return;
            }
            if(lessButtonSubMenu.clicked(e)){ // less button
                if(recipeAmountSelected > 1) recipeAmountSelected--;
                if(recipeAmountSelected == -1) recipeAmountSelected = 1;
                return;
            }
            if(moreButtonSubMenu.clicked(e)){ // more button
                if(recipeAmountSelected < 99 && recipeAmountSelected != -1) recipeAmountSelected++;
                if(recipeAmountSelected == -1) recipeAmountSelected = 1;
                return;
            }
            if(rptButtonSubMenu.clicked(e)){ // repeat button
                if(recipeAmountSelected == -1) recipeAmountSelected = 1;
                else recipeAmountSelected = -1;
                return;
            }
            if(confirmButtonSubMenu.clicked(e)){ // confirm button
                if(recipeAmountSelected != 0){
                    setRecipeAmountMenu = false;
                    Recipe t = Recipe.createRecipe(availableRecipes[recipeIndexSelected]);
                    t.repeatCount = recipeAmountSelected;
                    recipeList.add(t);
                    recipeIndexSelected = 0;
                    recipeAmountSelected = 1;
                    return;
                }
            }

        } else if(addRecipeMenu){
            if(retButtonSubMenu.clicked(e)){ // return button
                addRecipeMenu = false;
                return;
            }
            for(int i = 0; i < availableRecipes.length; i++){
                // todo change to button list
                if(GameScreen.inBounds(e, (int)(SettlementGame.WIDTH * 0.7f), (int)(SettlementGame.HEIGHT * 0.1f + 300 + i * 100), 90, 90)){ // choose recipe button
                    recipeIndexSelected = i;
                    setRecipeAmountMenu = true;
                    addRecipeMenu = false;
                    return;
                }
            }
        } else {
            // add recipe
            if(addButtonSubMenu.clicked(e)){ // add recipe button
                addRecipeMenu = true;
                return;
            }
            // remove recipe
            int recipeToRemove = -1;
            for(int i = 0; i < recipeList.size(); i++){
                // todo change to button list
                if(GameScreen.inBounds(e, (int)(SettlementGame.WIDTH * 0.7f), (int)(SettlementGame.HEIGHT * 0.1f + 300 + i * 100), 90, 90)){ // remove recipe button
                    recipeToRemove = i;
                    break;
                }
            }
            if(recipeToRemove != -1) recipeList.remove(recipeToRemove);
        }
    }

    public static Building createBuilding(Building b){
        Building bRet = new Building(b.pixmap, b.name, //
                b.quality, b.creatorID, b.spriteOffX, b.drawShiftX, b.spriteWidth, b.spriteHeight, b.spriteDrawOffset, b.spriteFromGround, b.cropHeight, b.opaque, b.facingDir, b.width,b.height,b.hasStorage, b.hasDisplaySlot,b.displaySlotDrawX,b.displaySlotDrawY, //
                b.materials, b.availableRecipes, b.occupiable);
        return bRet;
    }

    public static void addBuilding(int x, int y, Building b){
        addGameObject(createBuilding(b), x, y);;
    }

    public String[] getInfo(){
        int len = 1 + 1 + 1 + materials.getMats().length;
        String[] info = new String[len];
        info[0] = name;
        for(int i = 0; i < materials.getMats().length; i++){
            info[i + 1] = materials.getAmounts()[i] + " " + materials.getMats()[i].name;
        }
        String madeBy;
        if(creatorID == -1) madeBy = SettlementGame.AGame.getResources().getString(R.string.naturally_produced);
        else madeBy = Walkable.getNameFromID(creatorID) + " " + SettlementGame.AGame.getResources().getString(R.string.produced_this);
        info[materials.getMats().length + 1] = madeBy;
        info[materials.getMats().length + 2] = (quality == -1 ? SettlementGame.AGame.getResources().getString(R.string.natural) : Integer.toString(quality)) + " " + SettlementGame.AGame.getResources().getString(R.string.quality);
        return info;
    }

    public Materials ingredientsNeededForFirstRecipe(){
        Materials checkMats = null;
        if(recipeList.size() != 0) {
            checkMats = Materials.createMaterials(recipeList.get(0).components);
            for (int i = 0; i < storage.size(); i++) {
                for (int m = 0; m < checkMats.getMats().length; m++) {
                    if (storage.get(i).name.equals(checkMats.getMats()[m].name)) {
                        checkMats.getAmounts()[m]--;
                        if(checkMats.getAmounts()[m] < 0) checkMats.getAmounts()[m] = 0;
                    }
                }
            }
        }
        return checkMats;
    }

    public boolean hasStorageSpaceForFirstRecipe() {
        int matsNeeded = 0;
        if (recipeList.size() != 0) {
            matsNeeded = ingredientsNeededForFirstRecipe().getTotalAmount();
        }
        if(!hasStorage || storage.size() >= maxStorage - matsNeeded) return false;
        return true;
    }

    public Materials getMaterialsForFirstRecipe(){
        if (recipeList.size() != 0) {
            return recipeList.get(0).components;
        }
        return null;
    }

    public void addItem(GameObject g){
        storage.add(g);
    }

    public ArrayList<Recipe> recipeList(){
        return recipeList;
    }


}