package com.settlementGame.game.gameObject;

import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;

import com.settlementGame.R;
import com.settlementGame.framework.Graphics;
import com.settlementGame.framework.Input;
import com.settlementGame.game.SettlementGame;
import com.settlementGame.game.Recipe;
import com.settlementGame.game.screen.Button;
import com.settlementGame.game.screen.GameScreen;
import com.settlementGame.game.terrain.BiomeInfo;
import com.settlementGame.game.terrain.TerrainInfo;

import java.util.ArrayList;
import java.util.Random;

public class GameObject {

    // todo when changing object location must update renderOrder
    public static GameObject[][] gameObjects;
    public static ArrayList<Point> renderOrder = new ArrayList<Point>();

    private boolean buttonsMade = false;
    private ArrayList<Button> tabButtons = new ArrayList<Button>();
    private Button retButton = new Button("return", new Rect(50, 150, 50 + 200, 150 + 100), new Rect(0, 0, 0, 0));

    protected static Random rand = new Random();

    protected long objectID = -1;

    protected int index;
    public String name;
    protected int creatorID = -1;
    protected int quality = 1;
    protected ArrayList<Integer> ownerID = new ArrayList<Integer>();
    protected String desc = "object description";
    protected boolean passable = true;

    // item storage
    protected boolean hasStorage = false;
    protected ArrayList<GameObject> storage = new ArrayList<GameObject>();

    // display item
    protected boolean hasDisplaySlot = false;
    protected int displaySlotDrawX, displaySlotDrawY;
    protected GameObject displaySlot;

    public int facingDir; // up 0, right 1, down, 2, left 3
    protected int width, height;
    protected int spriteOffX = 0, drawShiftX = 0;
    protected boolean opaque = false;

    // components
    protected Materials materials;
    protected GameObject encrustedWith;

    // recipes
    protected Recipe[] availableRecipes;

    // task
    public boolean claimedByTask = false;

    // menu
    protected int drawTab = 0; // 0 == info+actions, 1 = storage+display slot, 2 = recipes, 3 = set owner
    protected ArrayList<Integer> menuTabs= new ArrayList<Integer>();

    public GameObject(String n, int fDir, int w, int h, int i){
        this.name = n;
        index = i;
        facingDir = fDir;
        width = w;
        height = h;
        setID();
    }

    public void draw(Graphics g, float worldOffSetX, float worldOffSetY, int i, int j, float scale) {

    }

    public void drawMenu(Graphics g, float scale) {
        if (!buttonsMade){
            // menu stuff
            menuTabs = new ArrayList<Integer>();
            menuTabs.add(0);
            if (hasStorage || hasDisplaySlot) menuTabs.add(1);
            if (availableRecipes != null && availableRecipes.length > 0) menuTabs.add(2);
            if (creatorID != -1) menuTabs.add(3);

            for (int i = 0; i < menuTabs.size(); i++) {
                tabButtons.add(new Button(SettlementGame.AGame.getResources().getStringArray(R.array.object_menu_tabs)[menuTabs.get(i)],
                        new Rect((int) (SettlementGame.WIDTH * 0.2f + i * (SettlementGame.WIDTH * 0.7f / 4)) + 2,
                                (int) (SettlementGame.HEIGHT * 0.1f) + 2,
                                (int) (SettlementGame.WIDTH * 0.2f + i * (SettlementGame.WIDTH * 0.7f / 4)) + 2 + (int) (SettlementGame.WIDTH * 0.7f / 4) - 4,
                                (int) (SettlementGame.HEIGHT * 0.1f) + 2 + (int) (SettlementGame.HEIGHT * 0.1f) - 4),
                        new Rect(0, 0, 0, 0)));
            }
            buttonsMade = true;
        }

        // return button
        retButton.draw(g, scale);

        g.drawRect(SettlementGame.WIDTH * 0.2f, SettlementGame.HEIGHT * 0.1f, SettlementGame.WIDTH * 0.7f, SettlementGame.HEIGHT * 0.8f, Color.rgb(24, 74, 255));

        // todo convert strings
        // todo make scrollable

        // draw tab buttons
        for(int i = 0; i < tabButtons.size(); i++){
            tabButtons.get(i).draw(g, scale);
        }

        if(drawTab == 0){
            String[] info = getInfo();
            for(int x = 0; x < info.length; x++){
                g.drawText(info[x],SettlementGame.WIDTH * 0.2f + 50, SettlementGame.HEIGHT * 0.1f + 200 + x * 50);
            }
        } else if(drawTab == 1){
            g.drawText("STORAGE",SettlementGame.WIDTH * 0.2f + 50, SettlementGame.HEIGHT * 0.1f + 200);
            for(int i = 0; i < storage.size(); i++){
                g.drawText(storage.get(i).name,SettlementGame.WIDTH * 0.2f + 50, SettlementGame.HEIGHT * 0.1f + 250 + i * 50);
            }
            // todo next menu show item menu, and can return to here
        } else if(drawTab == 2){
            drawMenuRecipe(g, scale);
        } else if(drawTab == 3){
            g.drawText("OWNERSHIP",SettlementGame.WIDTH * 0.2f + 50, SettlementGame.HEIGHT * 0.1f + 200);
            for(int i = 0; i < Walkable.homeCitizens.size(); i++){
                // todo make citizen buttons list
                g.drawRect(SettlementGame.WIDTH - SettlementGame.WIDTH * 0.2f - 50, SettlementGame.HEIGHT * 0.1f + 200 + i * 50, 40, 40, Color.rgb(255, 74, 24));
                g.drawText(Walkable.homeCitizens.get(i).name,SettlementGame.WIDTH * 0.2f + 50, SettlementGame.HEIGHT * 0.1f + 250 + i * 50);
            }
        }
    }

    protected void drawMenuRecipe(Graphics g, float scale){
    }

    public void update(){
        //
    }

    public void updateMenu(Input.TouchEvent e){
        for(Button b : tabButtons){
            b.update(e);
        }
        retButton.update(e);

        // todo make scrollable
        if(retButton.clicked(e)){
            GameScreen.showObjectMenu = false;
            GameScreen.showNoMenu = true;
        }
        // check tap on tabs
        for(int i = 0; i < menuTabs.size(); i++){
            if(tabButtons.get(i).clicked(e)){
                drawTab = menuTabs.get(i);
                break;
            }
        }

        if(drawTab == 2){
            updateMenuRecipe(e);
        }
    }

    protected void updateMenuRecipe(Input.TouchEvent e){
    }

    public static void addGameObject(GameObject go, int x, int y){
        gameObjects[x][y] = go;
        renderOrder.add(new Point(x, y));
    }

    public static void updateRenderOrder(){
        for(int i = 0; i < renderOrder.size() - 1; i++){
            Point swap;
            for(int j = i + 1; j < renderOrder.size(); j++){
                if(renderOrder.get(i).x + renderOrder.get(i).y > renderOrder.get(j).x + renderOrder.get(j).y){
                    swap = renderOrder.get(i);
                    renderOrder.set(i, renderOrder.get(j));
                    renderOrder.set(j, swap);
                } else if(renderOrder.get(i).x + renderOrder.get(i).y == renderOrder.get(j).x + renderOrder.get(j).y) {
                    if(gameObjects[renderOrder.get(i).x][renderOrder.get(i).y].width > gameObjects[renderOrder.get(j).x][renderOrder.get(j).y].width
                        || gameObjects[renderOrder.get(i).x][renderOrder.get(i).y].height > gameObjects[renderOrder.get(j).x][renderOrder.get(j).y].height) {
                        swap = renderOrder.get(i);
                        renderOrder.set(i, renderOrder.get(j));
                        renderOrder.set(j, swap);
                    }
                }
            }
        }
    }

    public void shiftFacingDir(int shiftAmt){
        for(int i= 0; i < shiftAmt; i++) {
            facingDir++;
            if(facingDir > 3)
                facingDir = 0;
        }
    }

    public void setID(){
        objectID = System.nanoTime() * 1000 + rand.nextInt(999);
    }

    public int getAdjustedWidth(){
        return (facingDir == 0 || facingDir == 2) ? width : height;
    }

    public int getAdjustedHeight(){
        return (facingDir == 0 || facingDir == 2) ? height : width;
    }

    protected int getValue(){
        // todo calc value
        return 0;
    }

    public String[] getInfo(){
        return new String[0];
    }

    public static GameObject getAtPos(int x, int y){

        for(int a = 0; a < GameObject.renderOrder.size(); a++){
            Point p = GameObject.renderOrder.get(a);

            if(GameObject.gameObjects[p.x][p.y] != null){
                // check all spots in found object
                for(int i2 = p.x; i2 < p.x + GameObject.gameObjects[p.x][p.y].getAdjustedWidth(); i2++) {
                    for (int j2 = p.y; j2 < p.y + GameObject.gameObjects[p.x][p.y].getAdjustedHeight(); j2++) {

                        // if match then quit
                        if(i2 == x && j2 == y) return GameObject.gameObjects[p.x][p.y];

                    }
                }
            }
        }
        return null;
    }

    public boolean isPassable(){
        return passable;
    }

    public long getID(){
        return objectID;
    }

    protected static boolean canPlace(int x, int y){
        if(!inWorld(x, y)  /*|| (TerrainInfo.flooringGrid[x][y] != null && TerrainInfo.flooringGrid[x][y].indoors())*/
                || TerrainInfo.tilesGrid[x][y] == Tile.WATER || TerrainInfo.tilesGrid[x][y] == Tile.LAVA) return false;
        return true;
    }

    protected static boolean inWorld(int x, int y){
        if(x < 0 || x >= GameObject.gameObjects.length
                || y < 0 || y >= GameObject.gameObjects.length
                || BiomeInfo.biomeGrid[x / TerrainInfo.tilesGridSize][y / TerrainInfo.tilesGridSize] == BiomeInfo.BiomeType.VOID)
            return false;
        return true;
    }

    public static boolean addProgression(int x, int y, int skillLevel) {
        return true;
    }

    public boolean hasInStorage(GameObject g){
        if(!hasStorage) return false;
        for(GameObject s : storage){
            if(s.name.equals(g.name)) return true;
        }
        return false;
    }

    public ArrayList<GameObject> getStorage(){
        return storage;
    }

}