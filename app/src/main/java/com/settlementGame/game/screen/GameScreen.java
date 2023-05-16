package com.settlementGame.game.screen;

import static com.settlementGame.framework.impl.AndroidGame.WIDTH;

import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;

import com.settlementGame.framework.Game;
import com.settlementGame.framework.Graphics;
import com.settlementGame.framework.Input.TouchEvent;
import com.settlementGame.framework.Screen;
import com.settlementGame.game.AStar.AStar;
import com.settlementGame.game.AStar.Node;
import com.settlementGame.game.Assets;
import com.settlementGame.game.SettlementGame;
import com.settlementGame.game.gameObject.Blueprint;
import com.settlementGame.game.gameObject.Building;
import com.settlementGame.game.gameObject.Designation;
import com.settlementGame.game.gameObject.Flooring;
import com.settlementGame.game.gameObject.GameObject;
import com.settlementGame.game.gameObject.Tile;
import com.settlementGame.game.gameObject.Walkable;
import com.settlementGame.game.terrain.BiomeInfo;
import com.settlementGame.game.terrain.TerrainInfo;
import com.settlementGame.game.terrain.WorldInfo;

import java.util.List;

// todo zooming ?
public class GameScreen extends Screen {

    // buttons
    private static Button scrollLockButton = new Button("scrollLock", new Rect(50, 50, 50 + 200, 50 + 100), new Rect(0, 0, 0, 0));
    private static Button objectMenuButton = new Button("objectMenu", new Rect(50, 150,  50 + 200, 150 + 100), new Rect(0, 0, 0, 0));
    private static Button blueprintMenuButton = new Button("blueprintMenu", new Rect(50, 250, 50 + 200, 250 + 100), new Rect(0, 0, 0, 0));
    private static Button designationMenuButton = new Button("designationMenu", new Rect(50, 350, 50 + 200, 350 + 100), new Rect(0, 0, 0, 0));
    private static Button peopleMenuButton = new Button("peopleMenu", new Rect(50, 450, 50 + 200, 450 + 100), new Rect(0, 0, 0, 0));
    private static Button worldMenuButton = new Button("worldMenu", new Rect(50, 550, 50 + 200, 550 + 100), new Rect(0, 0, 0, 0));

    // selected object
    private static float gridTapPosX = 0, gridTapPosY = 0;
    private static GameObject objectSelected;

    // menus
    public static boolean showNoMenu = true;
    public static boolean showObjectMenu = false;
    public static boolean showBlueprintMenu = false;
    public static boolean showDesignationMenu = false;
    public static boolean showPeopleMenu = false;
    public static boolean showWorldMenu = false;

    // scaling
    // private static final float SCALE = 6;
    private static float SCALE = 6;
    private static float SPEED = 1;
    // todo testing move
    public static boolean up = false, down = false, left = false, right = false;

    private float worldOffSetX;
    private float worldOffSetY;

    // scrolling
    private static Point lastPos = new Point(0,0);
    private static boolean scrollLock = false;

    public GameScreen(Game game) {
        super(game);
        worldOffSetX = -(90 + (120 - 60)) * 16 + (WIDTH / 2.0f);
        worldOffSetY = -(60 + 90) * 16 / 2.0f + (SettlementGame.HEIGHT / 2.0f);

    }

    public void update(float deltaTime) {

        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
        int len = touchEvents.size();

        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);

            scrollLockButton.update(event);
            objectMenuButton.update(event);
            blueprintMenuButton.update(event);
            designationMenuButton.update(event);
            peopleMenuButton.update(event);
            worldMenuButton.update(event);

            // todo temp speed
            if(inBounds(event, 500, 100, 100, 100)){
                SPEED = 1;
            } else if(inBounds(event, 650, 100, 100, 100)){
                SPEED = 2;
            } else if(inBounds(event, 800, 100, 100, 100)){
                SPEED = 3;
            }
            if(inBounds(event, 1000, 100, 100, 100)){
                // todo test object placing
                if(event.type == TouchEvent.TOUCH_UP){
                    if(GameObject.gameObjects[(int)gridTapPosX][(int)gridTapPosX] == null){
                        Building.addBuilding((int)gridTapPosX, (int)gridTapPosY, Building.createBuilding(Building.TABLE_WOOD_2x2));
                        // GameObject.updateRenderOrder();
                    }
                }
            }
            // todo testing movement
            /*
            if(event.type == TouchEvent.TOUCH_DOWN && inBounds(event, SettlementGame.WIDTH - 250, SettlementGame.HEIGHT - 250, 90, 90)){
                left = true;
            } else if(event.type == TouchEvent.TOUCH_DOWN && inBounds(event, SettlementGame.WIDTH - 250 + 100, SettlementGame.HEIGHT - 250, 90, 90)){
                up = true;
            } else if(event.type == TouchEvent.TOUCH_DOWN && inBounds(event, SettlementGame.WIDTH - 250, SettlementGame.HEIGHT - 250 + 100, 90, 90)){
                down = true;
            } else if(event.type == TouchEvent.TOUCH_DOWN && inBounds(event, SettlementGame.WIDTH - 250 + 100, SettlementGame.HEIGHT - 250 + 100, 90, 90)){
                right = true;
            }
            if(event.type == TouchEvent.TOUCH_UP && inBounds(event, SettlementGame.WIDTH - 250, SettlementGame.HEIGHT - 250, 90, 90)){
                left = false;
            } else if(event.type == TouchEvent.TOUCH_UP && inBounds(event, SettlementGame.WIDTH - 250 + 100, SettlementGame.HEIGHT - 250, 90, 90)){
                up = false;
            } else if(event.type == TouchEvent.TOUCH_UP && inBounds(event, SettlementGame.WIDTH - 250, SettlementGame.HEIGHT - 250 + 100, 90, 90)){
                down = false;
            } else if(event.type == TouchEvent.TOUCH_UP && inBounds(event, SettlementGame.WIDTH - 250 + 100, SettlementGame.HEIGHT - 250 + 100, 90, 90)){
                right = false;
            }
            */

            if (showNoMenu) {

                // scrollLock
                if (scrollLockButton.clicked(event)) {
                    scrollLock = !scrollLock;
                    return;
                }

                // show item menu
                if (objectMenuButton.clicked(event) && objectSelected != null) {
                    showNoMenu = false;
                    showObjectMenu = true;
                    showBlueprintMenu = false;
                    showDesignationMenu = false;
                    showPeopleMenu = false;
                    showWorldMenu = false;
                    return;
                }

                // show blueprint menu
                if (blueprintMenuButton.clicked(event)) {
                    showNoMenu = false;
                    showObjectMenu = false;
                    showBlueprintMenu = true;
                    showDesignationMenu = false;
                    showPeopleMenu = false;
                    showWorldMenu = false;
                    return;
                }

                // show designation menu
                if (designationMenuButton.clicked(event)) {
                    showNoMenu = false;
                    showObjectMenu = false;
                    showBlueprintMenu = false;
                    showDesignationMenu = true;
                    showPeopleMenu = false;
                    showWorldMenu = false;
                    return;
                }

                // show people menu
                if (peopleMenuButton.clicked(event)) {
                    showNoMenu = false;
                    showObjectMenu = false;
                    showBlueprintMenu = false;
                    showDesignationMenu = false;
                    showPeopleMenu = true;
                    showWorldMenu = false;
                    return;
                }

                // show world menu
                if (worldMenuButton.clicked(event)) {
                    showNoMenu = false;
                    showObjectMenu = false;
                    showBlueprintMenu = false;
                    showDesignationMenu = false;
                    showPeopleMenu = false;
                    showWorldMenu = true;
                    return;
                }

            } else if (showObjectMenu) {
                if (objectSelected != null) {
                    objectSelected.updateMenu(event);
                    return;
                }
            } else if (showBlueprintMenu) {
                Blueprint.updateMainMenu(event);
                return;
            } else if (showDesignationMenu) {
                Designation.updateMainMenu(event, worldOffSetX, worldOffSetY, SCALE);
                return;
            } else if (showPeopleMenu) {
                Walkable.updateMainMenu(event);
                return;
            } else if (showWorldMenu) {
                WorldInfo.updateMenu(event);
                return;
            }

            if(!scrollLock && showNoMenu){
                // get mouse tap pos on grid
                //  > 300 to avoid buttons
                if (event.x > 300 && event.x < WIDTH * 0.99f && event.y > SettlementGame.HEIGHT * 0.02f && event.y < SettlementGame.HEIGHT * 0.99f) {
                    gridTapPosX = (float) (Math.floor(((event.y / SCALE - worldOffSetY) / (16.0f) + ((event.x - 16.0f) / SCALE - worldOffSetX) / (32.0f)) - 45));
                    gridTapPosY = (float) (Math.floor(((event.y / SCALE - worldOffSetY) / (16.0f) - ((event.x - 16.0f) / SCALE - worldOffSetX) / (32.0f)) + 45));
                    System.out.println("tapPos " + gridTapPosX + " " + gridTapPosY);
                }
                // get selected object from tap pos
                if((int) gridTapPosX >= 0 && (int) gridTapPosY >= 0
                        && (int) gridTapPosX < GameObject.gameObjects.length && (int) gridTapPosY < GameObject.gameObjects.length){
                    objectSelected = GameObject.getAtPos((int) gridTapPosX, (int) gridTapPosY);
                    if(objectSelected == null){
                        if(TerrainInfo.flooringGrid[(int) gridTapPosX][(int) gridTapPosY] != Flooring.VOID){
                            objectSelected = TerrainInfo.flooringGrid[(int) gridTapPosX][(int) gridTapPosY];
                        } else if(TerrainInfo.tilesGrid[(int) gridTapPosX][(int) gridTapPosY] != Tile.VOID){
                            objectSelected = TerrainInfo.tilesGrid[(int) gridTapPosX][(int) gridTapPosY];
                        }
                    }
                }
            }

            // scroll
            if(scrollLock && event.type == TouchEvent.TOUCH_DOWN){
                lastPos.x = event.x;
                lastPos.y = event.y;
            }
            if(scrollLock && event.type == TouchEvent.TOUCH_DRAGGED){

                // worldOffSetX += (holdP.x - downP.x) / SCALE;
                // worldOffSetY += (holdP.y - downP.y) / SCALE;

                // x
                int screenMidPosNextX = (int) (Math.floor(((SettlementGame.HEIGHT / 2.0f * 0.95 / SCALE - (worldOffSetY)) / (16.0f) + ((WIDTH / 2.0f - 16.0f) * 0.90 / SCALE - (worldOffSetX - (lastPos.x - event.x) / SCALE)) / (32.0f)) - 45));
                int screenMidPosNextY = (int) (Math.floor(((SettlementGame.HEIGHT / 2.0f * 0.95 / SCALE - (worldOffSetY)) / (16.0f) - ((WIDTH / 2.0f - 16.0f) * 0.90 / SCALE - (worldOffSetX - (lastPos.x - event.x) / SCALE)) / (32.0f)) + 45));
                if (!(screenMidPosNextY < 0) && !(screenMidPosNextY >= TerrainInfo.tilesGrid.length)
                        && !(screenMidPosNextX < 0) && !(screenMidPosNextX >= TerrainInfo.tilesGrid.length)
                        && BiomeInfo.biomeGrid[screenMidPosNextX / TerrainInfo.tilesGridSize][screenMidPosNextY / TerrainInfo.tilesGridSize] != BiomeInfo.BiomeType.VOID) {
                    worldOffSetX -= (lastPos.x - event.x) / SCALE;
                }

                // y
                screenMidPosNextX = (int) (Math.floor(((SettlementGame.HEIGHT / 2.0f * 0.95 / SCALE - (worldOffSetY - (lastPos.y - event.y) / SCALE)) / (16.0f) + ((WIDTH / 2.0f - 16.0f) * 0.90 / SCALE - (worldOffSetX)) / (32.0f)) - 45));
                screenMidPosNextY = (int) (Math.floor(((SettlementGame.HEIGHT / 2.0f * 0.95 / SCALE - (worldOffSetY - (lastPos.y - event.y) / SCALE)) / (16.0f) - ((WIDTH / 2.0f - 16.0f) * 0.90 / SCALE - (worldOffSetX)) / (32.0f)) + 45));
                if (!(screenMidPosNextY < 0) && !(screenMidPosNextY >= TerrainInfo.tilesGrid.length)
                        && !(screenMidPosNextX < 0) && !(screenMidPosNextX >= TerrainInfo.tilesGrid.length)
                        && BiomeInfo.biomeGrid[screenMidPosNextX / TerrainInfo.tilesGridSize][screenMidPosNextY / TerrainInfo.tilesGridSize] != BiomeInfo.BiomeType.VOID) {
                    worldOffSetY -= (lastPos.y - event.y) / SCALE;
                }

                lastPos.x = event.x;
                lastPos.y = event.y;
            }
        }

        for(int s = 0; s < SPEED; s++){
            // update all objects on map
            for(int go = 0; go < GameObject.renderOrder.size(); go++){
                GameObject.gameObjects[GameObject.renderOrder.get(go).x][GameObject.renderOrder.get(go).y].update();
            }
            // update walkables
            for(int i = 0; i < Walkable.homeCitizens.size(); i++){
                Walkable.homeCitizens.get(i).update();
            }
        }
    }

    public void present(float deltaTime) {

        Graphics g = game.getGraphics();
        // g.clipCanvas(new Rect(0, 0, SettlementGame.WIDTH, SettlementGame.HEIGHT)); // already clipped in fast render

        g.drawRect(0, 0, WIDTH, SettlementGame.HEIGHT, Color.rgb(124, 174, 255));

        for(int i = -50; i < TerrainInfo.tilesGridSize * BiomeInfo.biomeGridSize + 50; i++){
            for(int j = -50; j < TerrainInfo.tilesGridSize * BiomeInfo.biomeGridSize + 50; j++){

                if(i >= 0 && j >= 0 && i < TerrainInfo.tilesGridSize * BiomeInfo.biomeGridSize && j < TerrainInfo.tilesGridSize * BiomeInfo.biomeGridSize //
                        && BiomeInfo.biomeGrid[i/TerrainInfo.tilesGridSize][j/TerrainInfo.tilesGridSize] != BiomeInfo.BiomeType.VOID){

                    // draw tile
                    TerrainInfo.tilesGrid[i][j].draw(g, worldOffSetX, worldOffSetY, i, j, SCALE);

                    // draw flooring
                    TerrainInfo.flooringGrid[i][j].draw(g, worldOffSetX, worldOffSetY, i, j, SCALE);

                } else {

                    // draw bordering stuff
                    Flooring d = BiomeInfo.biomeTypeSelected.defaultFlooring == Flooring.GRASS ? Flooring.BORDER_GRASS : Flooring.BORDER_SNOW;
                    d.drawBorderingFloor(g, worldOffSetX, worldOffSetY, i, j, SCALE);

                }
            }
        }

        // todo combine
        int walkableIndex = 0, gameObjectIndex = 0;
        while(walkableIndex < Walkable.homeCitizens.size() || gameObjectIndex < GameObject.renderOrder.size()){
            // only walkables left
            if(walkableIndex < Walkable.homeCitizens.size() && gameObjectIndex >= GameObject.renderOrder.size()){
                Walkable.homeCitizens.get(walkableIndex).draw(g, worldOffSetX, worldOffSetY, SCALE);
                walkableIndex++;
            }
            // only gameobj left
            if(gameObjectIndex < GameObject.renderOrder.size() && walkableIndex >= Walkable.homeCitizens.size()){
                Point p = GameObject.renderOrder.get(gameObjectIndex);
                GameObject.gameObjects[p.x][p.y].draw(g, worldOffSetX, worldOffSetY, p.x, p.y, SCALE);
                gameObjectIndex++;
            }
            // both left
            if(gameObjectIndex < GameObject.renderOrder.size() && walkableIndex < Walkable.homeCitizens.size()){
                // walkable behind object
                if((Walkable.homeCitizens.get(walkableIndex).getX() + Walkable.homeCitizens.get(walkableIndex).getY()) < GameObject.renderOrder.get(gameObjectIndex).x + GameObject.renderOrder.get(gameObjectIndex).y){
                    Walkable.homeCitizens.get(walkableIndex).draw(g, worldOffSetX, worldOffSetY, SCALE);
                    walkableIndex++;
                } else {
                    Point p = GameObject.renderOrder.get(gameObjectIndex);
                    GameObject.gameObjects[p.x][p.y].draw(g, worldOffSetX, worldOffSetY, p.x, p.y, SCALE);
                    gameObjectIndex++;
                }
            }
        }

        // draw designations
        for(int i = 0; i < Designation.designations.length; i++){
            for(int j = 0; j < Designation.designations.length; j++){
                if(Designation.designations[i][j]!= null) Designation.designations[i][j].draw(g, worldOffSetX, worldOffSetY, SCALE);
            }
        }

        // todo draw tapspot
        float drawX = worldOffSetX + (gridTapPosX - gridTapPosY + TerrainInfo.tilesGridSize * BiomeInfo.biomeGridSize) * 16;
        float drawY = worldOffSetY + ((gridTapPosY + gridTapPosX) * 16) / 2.0f - 2;
        g.drawPixmap(Assets.flooring, drawX * SCALE, drawY * SCALE, 32 * SCALE, 32 * SCALE, 1 * 32, 0.0f, 32, 32);

        // draw menus
        if(showNoMenu){

            scrollLockButton.draw(g, SCALE);

            objectMenuButton.draw(g, SCALE);

            blueprintMenuButton.draw(g, SCALE);

            designationMenuButton.draw(g, SCALE);

            peopleMenuButton.draw(g, SCALE);

            worldMenuButton.draw(g, SCALE);

        } else if(showObjectMenu && objectSelected != null){

            objectSelected.drawMenu(g, SCALE);

        } else if(showBlueprintMenu){

            Blueprint.drawMainMenu(g, SCALE);

        } else if(showDesignationMenu){

            Designation.drawMainMenu(g, SCALE);

        } else if(showPeopleMenu){

            Walkable.drawMainMenu(g, SCALE);

        } else if(showWorldMenu){

            WorldInfo.drawMenu(g, SCALE);

        }

        // todo temp speed buttons
        g.drawRect(500, 100, 100, 100, Color.rgb(124, 174, 255));
        g.drawText("111", 500, 150);
        g.drawRect(650, 100, 100, 100, Color.rgb(124, 174, 255));
        g.drawText("222", 650, 150);
        g.drawRect(800, 100, 100, 100, Color.rgb(124, 174, 255));
        g.drawText("333", 800, 150);
        g.drawRect(1000, 100, 100, 100, Color.rgb(124, 174, 255));
        g.drawText("obj", 1000, 150);
        /*
        g.drawRect(SettlementGame.WIDTH - 250, SettlementGame.HEIGHT - 250, 90, 90, Color.rgb(124, 174, 255));
        g.drawRect(SettlementGame.WIDTH - 250 + 100, SettlementGame.HEIGHT - 250, 90, 90, Color.rgb(124, 174, 255));
        g.drawRect(SettlementGame.WIDTH - 250, SettlementGame.HEIGHT - 250 + 100, 90, 90, Color.rgb(124, 174, 255));
        g.drawRect(SettlementGame.WIDTH - 250 + 100, SettlementGame.HEIGHT - 250 + 100, 90, 90, Color.rgb(124, 174, 255));
        */
    }

    public static boolean inBounds(TouchEvent event, int x, int y, int width, int height) {
        if(event.x > x && event.x < x + width - 1 &&
                event.y > y && event.y < y + height - 1)
            return true;
        else
            return false;
    }
    public static boolean inBounds(int i, int j, int x, int y, int width, int height) {
        if(i > x && i < x + width - 1 &&
                j > y && j < y + height - 1)
            return true;
        else
            return false;
    }

    public void pause() {

    }

    public void resume() {
    }

    public void dispose() {
    }
}