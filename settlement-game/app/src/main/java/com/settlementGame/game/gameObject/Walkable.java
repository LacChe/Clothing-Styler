package com.settlementGame.game.gameObject;

import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.KeyEvent;

import com.settlementGame.R;
import com.settlementGame.framework.Graphics;
import com.settlementGame.framework.Input;
import com.settlementGame.framework.Pixmap;
import com.settlementGame.game.AStar.AStar;
import com.settlementGame.game.AStar.Node;
import com.settlementGame.game.Assets;
import com.settlementGame.game.SettlementGame;
import com.settlementGame.game.Task;
import com.settlementGame.game.screen.Button;
import com.settlementGame.game.screen.GameScreen;
import com.settlementGame.game.terrain.BiomeInfo;
import com.settlementGame.game.terrain.TerrainInfo;

import java.util.ArrayList;
import java.util.List;

public class Walkable extends GameObject{

    // menu stuff
    private static Button citizenButton = new Button("Citizen Info", new Rect(50, 150, 50 + 200, 150 + 100), new Rect(0, 0, 0, 0));
    private static Button schedulesButton = new Button("Schedules", new Rect(50, 250, 50 + 200, 250 + 100), new Rect(0, 0, 0, 0));
    private static Button jobsButton = new Button("Jobs", new Rect(50, 350, 50 + 200, 350 + 100), new Rect(0, 0, 0, 0));
    private static Button retButton = new Button("return", new Rect(50, 450, 50 + 200, 450 + 100), new Rect(0, 0, 0, 0));

    private static Button changeSubButton;
    private static Button bioSubButton;
    private static Button bodySubButton;
    private static Button socialSubButton;

    private static Button bioSubBioButton;
    private static Button bioSubPersonalityButton;
    private static Button bioSubMemoryButton;

    private static Button bodySubItemsButton;
    private static Button bodySubSkillsButton;
    private static Button bodySubStatsButton;

    private static boolean subButtonsMade = false;
    private static boolean showChangeMenu = true, showSocialMenu = false, showBioMenu = false, showBodyMenu = false;
    private static boolean showSkillsSubMenu = false, showStatsSubMenu = false, showItemsSubMenu = true;
    private static boolean showBioWantsSubMenu = false, showPersonalitySubMenu = true, showMemoriesSubMenu = false;

    // todo move to civ class
    public static ArrayList<Walkable> homeCitizens = new ArrayList<Walkable>();

    private static int[] SKILL_LEVELS = new int[]{
            0,
            50,
            150,
            300,
            500,
            750,
            1050,
            1400,
            1800,
            2250,
            2750,
            3300,
            3900,
            4550,
            5250,
            6000,
            6800,
            7650,
            8550,
            9500,
            10500
    };
    private static String[] SKILL_NAMES = new String[]{
            "Lvl 0 Dabbling",
            "Lvl 1 Novice",
            "Lvl 2 Adequate",
            "Lvl 3 Competent",
            "Lvl 4 Skilled",
            "Lvl 5 Proficient",
            "Lvl 6 Talented",
            "Lvl 7 Adept",
            "Lvl 8 Expert",
            "Lvl 9 Professional",
            "Lvl 10 Accomplished",
            "Lvl 11 Great",
            "Lvl 12 Master",
            "Lvl 13 High Master",
            "Lvl 14 Grand Master",
            "Lvl 15 Legendary",
            "Lvl 16 Legendary+1",
            "Lvl 17 Legendary+2",
            "Lvl 18 Legendary+3",
            "Lvl 19 Legendary+4",
            "Lvl 20 Legendary+5",
    };

    protected static int spriteWidth = 32, spriteHeight = 48, spriteDrawOffset = 16, spriteFromGround = 42;

    // for individual ppl
    protected float x, y;
    protected float fDir = 0; // -1 = left, 0 = down, 1 = right
    protected float animFrame = 0f;
    protected boolean isMoving = false;
    // items
    protected GameObject carriedObject;
    protected ArrayList<GameObject> inventoryObjects = new ArrayList<GameObject>();
    protected GameObject[] equippedObjects = new GameObject[20]; // todo different index for different slot
    //stats
    protected int hunger = 100, energy = 100, mood = 100;
    protected Health health = Health.HEALTHY;
    // Miner, Farmer, Stonemason, Metalsmith, Jeweler, Carpenter, Chef, Tailor
    protected boolean[] skillsEnabled = new boolean[8];
    protected int[] skillsExp = new int[8];
    // mind
    protected ScheduleAction[] schedule = new ScheduleAction[12];
    protected ArrayList<SocialRelation> socialRelations = new ArrayList<SocialRelation>();
    protected Personality personality;
    protected ArrayList<Thought> memory = new ArrayList<Thought>();
    protected ArrayList<Thought> wants = new ArrayList<Thought>();

    protected Pixmap pixmap;
    protected Task currentTask;

    public Walkable(Pixmap pixmap, String n, float x, float y){
        super(n,0,1, 1, -1);
        this.x = x;
        this.y = y;
        this.pixmap = pixmap;
        spriteFromGround = 35;
    }

    public void draw(Graphics g, float worldOffSetX, float worldOffSetY, float scale){
        float drawX = worldOffSetX + (x - y + TerrainInfo.tilesGridSize * BiomeInfo.biomeGridSize) * spriteDrawOffset - drawShiftX;
        float drawY = worldOffSetY + ((y + x) * spriteDrawOffset) / 2.0f - 0 - spriteFromGround;

        if((drawX + spriteWidth) * scale > 0 && (drawX) * scale < SettlementGame.WIDTH && (drawY + spriteHeight) * scale > 0 && (drawY) * scale < SettlementGame.HEIGHT)
            g.drawPixmap(pixmap, drawX * scale, drawY * scale, spriteWidth * scale, spriteHeight * scale, (1 + facingDir) * spriteWidth, (isMoving ? (int)animFrame : 0) * spriteHeight, spriteWidth, spriteHeight);

        // render path
        if(currentTask != null){
            if(currentTask.path != null && currentTask.path.size() > 0){
                for(int i = 0; i < currentTask.path.size(); i++){
                    float drawXx = worldOffSetX + (currentTask.path.get(i).getObj().x - currentTask.path.get(i).getObj().y + TerrainInfo.tilesGridSize * BiomeInfo.biomeGridSize) * 16;
                    float drawYy = worldOffSetY + ((currentTask.path.get(i).getObj().y + currentTask.path.get(i).getObj().x) * 16) / 2.0f - 2;
                    g.drawPixmap(Assets.flooring, drawXx * scale, drawYy * scale, 32 * scale, 32 * scale, 1 * 32, 0.0f, 32, 32);
                }
            }
        }
    }

    public void update(){
        updateTask();
        updateMove();
    }

    private void updateTask(){
        if(currentTask == null){
            findTask();
        } else {
            Task.CompletionStatus status = currentTask.update();
            if (status != Task.CompletionStatus.Continue){
                if(status == Task.CompletionStatus.Complete){
                    // todo add skills etc.
                }
                currentTask.target.claimedByTask = false;
                currentTask = null;
            }
        }
    }

    private void findTask(){ // todo sometimes doesnt do stuff

        // find a task
        int shortestDist = 9999;
        List<Node<Point>> checkPath = null;
        List<Node<Point>> shortPath = null;

        // get closest designation
        int dx = -1, dy = -1;
        for(int i = 0; i < TerrainInfo.tilesGrid.length; i++){
            for(int j = 0; j < TerrainInfo.tilesGrid.length; j++){
                if(Designation.designations[i][j] != null
                        && !Designation.designations[i][j].claimedByTask /*&& todo fits walkable*/){
                    checkPath = AStar.getPathNextTo((int)x, (int)y, i, j, this);
                    if(checkPath != null && checkPath.size() < shortestDist) {
                        shortestDist = checkPath.size();
                        shortPath = checkPath;
                        dx = i;
                        dy = j;
                    }
                }
            }
        }
        if(shortPath != null) {
            currentTask = new Task(this, Designation.designations[dx][dy], dx, dy);
        }

        // get closest blueprint
        checkPath = null;
        shortPath = null;
        int ri = -1;
        for(int i = 0; i < renderOrder.size(); i++){
            if(gameObjects[renderOrder.get(i).x][renderOrder.get(i).y].getClass() == Blueprint.class
                    && !gameObjects[renderOrder.get(i).x][renderOrder.get(i).y].claimedByTask /*&& todo fits walkable*/){
                checkPath = AStar.getPathNextTo((int)x, (int)y, renderOrder.get(i).x, renderOrder.get(i).y, this);
                if(checkPath != null && checkPath.size() < shortestDist) {
                    shortestDist = checkPath.size();
                    shortPath = checkPath;
                    ri = i;
                }
            }
        }
        if(shortPath != null) {
            currentTask = new Task(this, (Blueprint)gameObjects[renderOrder.get(ri).x][renderOrder.get(ri).y], renderOrder.get(ri).x, renderOrder.get(ri).y);
        }

        // get closest building w/ recipe
        checkPath = null;
        shortPath = null;
        ri = -1;
        for(int i = 0; i < renderOrder.size(); i++){
            if((gameObjects[renderOrder.get(i).x][renderOrder.get(i).y].getClass() == Building.class && ((Building)gameObjects[renderOrder.get(i).x][renderOrder.get(i).y]).recipeList.size() > 0)
                    && !gameObjects[renderOrder.get(i).x][renderOrder.get(i).y].claimedByTask /*&& todo fits walkable*/){
                checkPath = AStar.getPathNextTo((int)x, (int)y, renderOrder.get(i).x, renderOrder.get(i).y, this);
                if(checkPath != null && checkPath.size() < shortestDist) {
                    shortestDist = checkPath.size();
                    shortPath = checkPath;
                    ri = i;
                }
            }
        }
        if(shortPath != null) {
            currentTask = new Task(this, (Building)gameObjects[renderOrder.get(ri).x][renderOrder.get(ri).y], renderOrder.get(ri).x, renderOrder.get(ri).y);
        }
    }

    private void updateMove(){
        isMoving = false;
        facingDir = 0;

        if(currentTask != null && currentTask.path != null && currentTask.path.size() > 0){ // todo collision lock bug

            if(((Point)currentTask.path.get(0).getObj()).x == x && ((Point)currentTask.path.get(0).getObj()).y == y){
                // System.out.println(x + " " + y + " " + ((Point)currentTask.path.get(0).getObj()).x + " " + ((Point)currentTask.path.get(0).getObj()).y);
                currentTask.path.remove(0);
            }
            if(currentTask.path.size() > 0){
                if(((Point)currentTask.path.get(0).getObj()).x == x && ((Point)currentTask.path.get(0).getObj()).y < y) { // up
                    facingDir = 1;
                    isMoving = true;
                    if(TerrainInfo.notOccupied(this, (int)(x), (int)(y-0.25f))){
                        // System.out.println("up");
                        y -= 0.25f;
                    }
                } else if(((Point)currentTask.path.get(0).getObj()).x == x && ((Point)currentTask.path.get(0).getObj()).y > y) { // down
                    facingDir = -1;
                    isMoving = true;
                    if(TerrainInfo.notOccupied(this, (int)(x), (int)(y+1))){
                        // System.out.println("down");
                        y += 0.25f;
                    }
                } else if(((Point)currentTask.path.get(0).getObj()).x < x && ((Point)currentTask.path.get(0).getObj()).y == y) { // left
                    facingDir = -1;
                    isMoving = true;
                    if(TerrainInfo.notOccupied(this, (int)(x-0.25f), (int)(y))){
                        // System.out.println("left");
                        x -= 0.25f;
                    }
                } else if(((Point)currentTask.path.get(0).getObj()).x > x && ((Point)currentTask.path.get(0).getObj()).y == y) { // right
                    facingDir = 1;
                    isMoving = true;
                    if(TerrainInfo.notOccupied(this, (int)(x+1), (int)(y))){
                        // System.out.println("right");
                        x += 0.25f;
                    }
                }
            }
        }

        // update Anim
        if(isMoving){
            animFrame += 0.5f;
            if(animFrame >= 4) animFrame = 0;
            updateRenderOrder();
        }

    }

    public static String getNameFromID(int id){
        // todo search all walkables
        if(id == -1) return SettlementGame.AGame.getResources().getString(R.string.natural);
        else return SettlementGame.AGame.getResources().getString(R.string.unknown);
    }

    public String getBio(){
        String ret = "";
        // todo gen bio
        return "bio placeholder";
        // return ret;
    }

    public static void drawMainMenu(Graphics g, float scale){
        if(!subButtonsMade){
            changeSubButton = new Button(SettlementGame.AGame.getResources().getStringArray(R.array.walkable_menu_tabs)[0],
                    new Rect((int) (SettlementGame.WIDTH * 0.2f + 0 * (SettlementGame.WIDTH * 0.7f / 4)) + 2,
                            (int) (SettlementGame.HEIGHT * 0.1f) + 2,
                            (int) (SettlementGame.WIDTH * 0.2f + 0 * (SettlementGame.WIDTH * 0.7f / 4)) + 2 + (int) (SettlementGame.WIDTH * 0.7f / 4) - 4,
                            (int) (SettlementGame.HEIGHT * 0.1f) + 2 + (int) (SettlementGame.HEIGHT * 0.1f) - 4),
                    new Rect(0, 0, 0, 0));
            bioSubButton = new Button(SettlementGame.AGame.getResources().getStringArray(R.array.walkable_menu_tabs)[1],
                    new Rect((int) (SettlementGame.WIDTH * 0.2f + 1 * (SettlementGame.WIDTH * 0.7f / 4)) + 2,
                            (int) (SettlementGame.HEIGHT * 0.1f) + 2,
                            (int) (SettlementGame.WIDTH * 0.2f + 1 * (SettlementGame.WIDTH * 0.7f / 4)) + 2 + (int) (SettlementGame.WIDTH * 0.7f / 4) - 4,
                            (int) (SettlementGame.HEIGHT * 0.1f) + 2 + (int) (SettlementGame.HEIGHT * 0.1f) - 4),
                    new Rect(0, 0, 0, 0));
            bodySubButton = new Button(SettlementGame.AGame.getResources().getStringArray(R.array.walkable_menu_tabs)[2],
                    new Rect((int) (SettlementGame.WIDTH * 0.2f + 2 * (SettlementGame.WIDTH * 0.7f / 4)) + 2,
                            (int) (SettlementGame.HEIGHT * 0.1f) + 2,
                            (int) (SettlementGame.WIDTH * 0.2f + 2 * (SettlementGame.WIDTH * 0.7f / 4)) + 2 + (int) (SettlementGame.WIDTH * 0.7f / 4) - 4,
                            (int) (SettlementGame.HEIGHT * 0.1f) + 2 + (int) (SettlementGame.HEIGHT * 0.1f) - 4),
                    new Rect(0, 0, 0, 0));
            socialSubButton = new Button(SettlementGame.AGame.getResources().getStringArray(R.array.walkable_menu_tabs)[3],
                    new Rect((int) (SettlementGame.WIDTH * 0.2f + 3 * (SettlementGame.WIDTH * 0.7f / 4)) + 2,
                            (int) (SettlementGame.HEIGHT * 0.1f) + 2,
                            (int) (SettlementGame.WIDTH * 0.2f + 3 * (SettlementGame.WIDTH * 0.7f / 4)) + 2 + (int) (SettlementGame.WIDTH * 0.7f / 4) - 4,
                            (int) (SettlementGame.HEIGHT * 0.1f) + 2 + (int) (SettlementGame.HEIGHT * 0.1f) - 4),
                    new Rect(0, 0, 0, 0));

            bioSubBioButton = new Button(SettlementGame.AGame.getResources().getStringArray(R.array.walkable_menu_bio_tabs)[0],
                    new Rect((int) (SettlementGame.WIDTH * 0.55f + 0 * (SettlementGame.WIDTH * 0.7f / 6)) + 2,
                            (int) (SettlementGame.HEIGHT * 0.2f) + 2,
                            (int) (SettlementGame.WIDTH * 0.55f + 0 * (SettlementGame.WIDTH * 0.7f / 6)) + 2 + (int) (SettlementGame.WIDTH * 0.7f / 6) - 4,
                            (int) (SettlementGame.HEIGHT * 0.2f) + 2 + (int) (SettlementGame.HEIGHT * 0.1f) - 4),
                    new Rect(0, 0, 0, 0));
            bioSubPersonalityButton = new Button(SettlementGame.AGame.getResources().getStringArray(R.array.walkable_menu_bio_tabs)[1],
                    new Rect((int) (SettlementGame.WIDTH * 0.55f + 1 * (SettlementGame.WIDTH * 0.7f / 6)) + 2,
                            (int) (SettlementGame.HEIGHT * 0.2f) + 2,
                            (int) (SettlementGame.WIDTH * 0.55f + 1 * (SettlementGame.WIDTH * 0.7f / 6)) + 2 + (int) (SettlementGame.WIDTH * 0.7f / 6) - 4,
                            (int) (SettlementGame.HEIGHT * 0.2f) + 2 + (int) (SettlementGame.HEIGHT * 0.1f) - 4),
                    new Rect(0, 0, 0, 0));
            bioSubMemoryButton = new Button(SettlementGame.AGame.getResources().getStringArray(R.array.walkable_menu_bio_tabs)[2],
                    new Rect((int) (SettlementGame.WIDTH * 0.55f + 2 * (SettlementGame.WIDTH * 0.7f / 6)) + 2,
                            (int) (SettlementGame.HEIGHT * 0.2f) + 2,
                            (int) (SettlementGame.WIDTH * 0.55f + 2 * (SettlementGame.WIDTH * 0.7f / 6)) + 2 + (int) (SettlementGame.WIDTH * 0.7f / 6) - 4,
                            (int) (SettlementGame.HEIGHT * 0.2f) + 2 + (int) (SettlementGame.HEIGHT * 0.1f) - 4),
                    new Rect(0, 0, 0, 0));

            bodySubItemsButton = new Button(SettlementGame.AGame.getResources().getStringArray(R.array.walkable_menu_body_tabs)[0],
                    new Rect((int) (SettlementGame.WIDTH * 0.55f + 0 * (SettlementGame.WIDTH * 0.7f / 6)) + 2,
                            (int) (SettlementGame.HEIGHT * 0.2f) + 2,
                            (int) (SettlementGame.WIDTH * 0.55f + 0 * (SettlementGame.WIDTH * 0.7f / 6)) + 2 + (int) (SettlementGame.WIDTH * 0.7f / 6) - 4,
                            (int) (SettlementGame.HEIGHT * 0.2f) + 2 + (int) (SettlementGame.HEIGHT * 0.1f) - 4),
                    new Rect(0, 0, 0, 0));
            bodySubSkillsButton = new Button(SettlementGame.AGame.getResources().getStringArray(R.array.walkable_menu_body_tabs)[1],
                    new Rect((int) (SettlementGame.WIDTH * 0.55f + 1 * (SettlementGame.WIDTH * 0.7f / 6)) + 2,
                            (int) (SettlementGame.HEIGHT * 0.2f) + 2,
                            (int) (SettlementGame.WIDTH * 0.55f + 1 * (SettlementGame.WIDTH * 0.7f / 6)) + 2 + (int) (SettlementGame.WIDTH * 0.7f / 6) - 4,
                            (int) (SettlementGame.HEIGHT * 0.2f) + 2 + (int) (SettlementGame.HEIGHT * 0.1f) - 4),
                    new Rect(0, 0, 0, 0));
            bodySubStatsButton = new Button(SettlementGame.AGame.getResources().getStringArray(R.array.walkable_menu_body_tabs)[2],
                    new Rect((int) (SettlementGame.WIDTH * 0.55f + 2 * (SettlementGame.WIDTH * 0.7f / 6)) + 2,
                            (int) (SettlementGame.HEIGHT * 0.2f) + 2,
                            (int) (SettlementGame.WIDTH * 0.55f + 2 * (SettlementGame.WIDTH * 0.7f / 6)) + 2 + (int) (SettlementGame.WIDTH * 0.7f / 6) - 4,
                            (int) (SettlementGame.HEIGHT * 0.2f) + 2 + (int) (SettlementGame.HEIGHT * 0.1f) - 4),
                    new Rect(0, 0, 0, 0));

            subButtonsMade = true;
        }

        g.drawRect(SettlementGame.WIDTH * 0.2f, SettlementGame.HEIGHT * 0.1f, SettlementGame.WIDTH * 0.7f, SettlementGame.HEIGHT * 0.8f, Color.rgb(24, 74, 255));

        citizenButton.draw(g, scale);
        schedulesButton.draw(g, scale);
        jobsButton.draw(g, scale);
        retButton.draw(g, scale);

        changeSubButton.draw(g, scale);
        bioSubButton.draw(g, scale);
        bodySubButton.draw(g, scale);
        socialSubButton.draw(g, scale);

        if(showBioMenu){
            g.drawText("BIO",SettlementGame.WIDTH * 0.2f + 50, SettlementGame.HEIGHT * 0.1f + 200);
            bioSubBioButton.draw(g, scale);
            bioSubPersonalityButton.draw(g, scale);
            bioSubMemoryButton.draw(g, scale);
            if(showBioWantsSubMenu){
                g.drawText("BIO/WANTS",SettlementGame.WIDTH * 0.55f + 50, SettlementGame.HEIGHT * 0.2f + 200);
            }
            if(showPersonalitySubMenu){
                g.drawText("PERSONALITY",SettlementGame.WIDTH * 0.55f + 50, SettlementGame.HEIGHT * 0.2f + 200);
            }
            if(showMemoriesSubMenu){
                g.drawText("MEMORIES",SettlementGame.WIDTH * 0.55f + 50, SettlementGame.HEIGHT * 0.2f + 200);
            }
        }
        if(showBodyMenu){
            g.drawText("BODY",SettlementGame.WIDTH * 0.2f + 50, SettlementGame.HEIGHT * 0.1f + 200);
            bodySubItemsButton.draw(g, scale);
            bodySubSkillsButton.draw(g, scale);
            bodySubStatsButton.draw(g, scale);
            if(showSkillsSubMenu){
                g.drawText("SKILLS",SettlementGame.WIDTH * 0.55f + 50, SettlementGame.HEIGHT * 0.2f + 200);
            }
            if(showStatsSubMenu){
                g.drawText("STATS",SettlementGame.WIDTH * 0.55f + 50, SettlementGame.HEIGHT * 0.2f + 200);
            }
            if(showItemsSubMenu){
                g.drawText("ITEMS",SettlementGame.WIDTH * 0.55f + 50, SettlementGame.HEIGHT * 0.2f + 200);
            }
        }
        if(showSocialMenu){
            g.drawText("SOCIAL",SettlementGame.WIDTH * 0.2f + 50, SettlementGame.HEIGHT * 0.1f + 200);
        }
        if(showChangeMenu){
            g.drawText("CHANGE",SettlementGame.WIDTH * 0.2f + 50, SettlementGame.HEIGHT * 0.1f + 200);
        }

        // g.drawRect(SettlementGame.WIDTH * 0.2f, SettlementGame.HEIGHT * 0.1f, SettlementGame.WIDTH * 0.7f, SettlementGame.HEIGHT * 0.8f, Color.rgb(24, 74, 255));
        // g.drawText("People",SettlementGame.WIDTH * 0.2f + 50, SettlementGame.HEIGHT * 0.1f + 200);

    }

    public static void updateMainMenu(Input.TouchEvent e){

        citizenButton.update(e);
        schedulesButton.update(e);
        jobsButton.update(e);
        retButton.update(e);

        changeSubButton.update(e);
        bioSubButton.update(e);
        bodySubButton.update(e);
        socialSubButton.update(e);

        bioSubBioButton.update(e);
        bioSubPersonalityButton.update(e);
        bioSubMemoryButton.update(e);

        bodySubItemsButton.update(e);
        bodySubSkillsButton.update(e);
        bodySubStatsButton.update(e);

        // todo make scrollable
        if(changeSubButton.clicked(e)){
            showChangeMenu = true;
            showSocialMenu = false;
            showBioMenu = false;
            showBodyMenu = false;
        }
        if(bioSubButton.clicked(e)){
            showChangeMenu = false;
            showSocialMenu = false;
            showBioMenu = true;
            showBodyMenu = false;
        }
        if(bodySubButton.clicked(e)){
            showChangeMenu = false;
            showSocialMenu = false;
            showBioMenu = false;
            showBodyMenu = true;
        }
        if(socialSubButton.clicked(e)){
            showChangeMenu = false;
            showSocialMenu = true;
            showBioMenu = false;
            showBodyMenu = false;
        }

        if(showChangeMenu){
            // nothing yet
        }
        if(showSocialMenu){
            // nothing yet
        }
        if(showBioMenu){
            if(bioSubBioButton.clicked(e)){
                showBioWantsSubMenu = true;
                showPersonalitySubMenu = false;
                showMemoriesSubMenu = false;
            }
            if(bioSubPersonalityButton.clicked(e)){
                showBioWantsSubMenu = false;
                showPersonalitySubMenu = true;
                showMemoriesSubMenu = false;
            }
            if(bioSubMemoryButton.clicked(e)){
                showBioWantsSubMenu = false;
                showPersonalitySubMenu = false;
                showMemoriesSubMenu = true;
            }
        }
        if(showBodyMenu){
            if(bodySubItemsButton.clicked(e)){
                showItemsSubMenu = true;
                showSkillsSubMenu = false;
                showStatsSubMenu = false;
            }
            if(bodySubSkillsButton.clicked(e)){
                showItemsSubMenu = false;
                showSkillsSubMenu = true;
                showStatsSubMenu = false;
            }
            if(bodySubStatsButton.clicked(e)){
                showItemsSubMenu = false;
                showSkillsSubMenu = false;
                showStatsSubMenu = true;
            }
        }

        if(retButton.clicked(e)){
            GameScreen.showDesignationMenu = false;
            GameScreen.showNoMenu = true;
        }
    }

    protected enum Health{
        // todo
        HEALTHY, COLD, FLU
    }

    protected enum ScheduleAction{
        // todo
        WORK, PLAY, FREE_TIME, SLEEP, EAT
    }

    protected enum Thought{
        // todo
        WALKED_IN_THE_RAIN,
        LIKES_THE_SAME_COLOR,
        WANTS_MORE_VARIETY_IN_CLOTHING,
        WAS_AROUND_FRIENDS_WHEN_WORKING,
        SPENT_TIME_ALONE,
        WORKED_ON_MINING,
        ADMIRED_ART
    }

    protected class SocialRelation{

        // todo
        Walkable relation;
        ArrayList<Thought> thoughtsAboutRelation = new ArrayList<Thought>();

        SocialRelation(Walkable relation){
            this.relation = relation;
        }

    }

    protected class Personality{

        // todo
        int[] placements = new int[10]; // 10 planets in 12 signs

        int getRating(Thought t){
            return -1;
        }

    }

    public static void updateRenderOrder(){
        // sort homecitizens
        for(int i = 0; i < homeCitizens.size() - 1; i++){
            Walkable swap;
            for(int j = i + 1; j < homeCitizens.size(); j++){
                if(homeCitizens.get(i).x + homeCitizens.get(i).y > homeCitizens.get(j).x + homeCitizens.get(j).y){
                    swap = homeCitizens.get(i);
                    homeCitizens.set(i, homeCitizens.get(j));
                    homeCitizens.set(j, swap);
                } else if(homeCitizens.get(i).x + homeCitizens.get(i).y == homeCitizens.get(j).x + homeCitizens.get(j).y) {
                    swap = homeCitizens.get(i);
                    homeCitizens.set(i, homeCitizens.get(j));
                    homeCitizens.set(j, swap);
                }
            }
        }
    }

    public static Walkable getFromID(long id){
        for(Walkable w : homeCitizens){
            if(w.objectID == id) return w;
        }
        return null;
    }

    public int getX(){
        return (int)x;
    }

    public int getY(){
        return (int)y;
    }

    public int hasAmountOfItem(GameObject g){
        int count = 0;
        for(int i = 0; i < inventoryObjects.size(); i++){
            if(inventoryObjects.get(i).name.equals(g.name)) count++;
        }
        return count;
    }

    public ArrayList<GameObject> getInventory(){
        return inventoryObjects;
    }

}