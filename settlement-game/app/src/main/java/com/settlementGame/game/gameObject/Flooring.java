package com.settlementGame.game.gameObject;

import com.settlementGame.R;
import com.settlementGame.framework.Graphics;
import com.settlementGame.game.Assets;
import com.settlementGame.game.SettlementGame;
import com.settlementGame.game.screen.GameScreen;
import com.settlementGame.game.terrain.BiomeInfo;
import com.settlementGame.game.terrain.TerrainInfo;

public class Flooring extends GameObject {

    private static final int SPRITE_WIDTH = 32, SPRITE_HEIGHT = 18, SPRITE_DRAW_OFFSET = 16, SPRITE_Y_CROP = 24;

    public static final Flooring VOID = new Flooring(SettlementGame.AGame.getResources().getStringArray(R.array.flooring_names)[0], -1,true, -1, -1, new Materials());
    public static final Flooring BORDER_GRASS = new Flooring(SettlementGame.AGame.getResources().getStringArray(R.array.flooring_names)[0], 0,false,-1, -1, new Materials());
    public static final Flooring BORDER_SNOW = new Flooring(SettlementGame.AGame.getResources().getStringArray(R.array.flooring_names)[0], 1,false, -1, -1, new Materials());
    public static final Flooring GRASS = new Flooring(SettlementGame.AGame.getResources().getStringArray(R.array.flooring_names)[1], 2,false,-1, -1,
            new Materials(new GameObject[]{ Consumable.createConsumable(Consumable.GrassSeed)}, new int[]{10}));
    public static final Flooring SNOW = new Flooring(SettlementGame.AGame.getResources().getStringArray(R.array.flooring_names)[2], 3,false, -1, -1, new Materials());
    public static final Flooring STONE = new Flooring(SettlementGame.AGame.getResources().getStringArray(R.array.flooring_names)[3], 4,true,-1, -1,
            new Materials(new GameObject[]{ Resource.createResource(Resource.STONE)}, new int[]{1}));
    public static final Flooring ORE = new Flooring(SettlementGame.AGame.getResources().getStringArray(R.array.flooring_names)[4], 5,true,-1, -1,
            new Materials(new GameObject[]{ Resource.createResource(Resource.ORE)}, new int[]{1}));
    public static final Flooring FLOOR_WOOD_1 = new Flooring(SettlementGame.AGame.getResources().getStringArray(R.array.flooring_names)[5], 6, true,0, 0,
            new Materials(new GameObject[]{ Resource.createResource(Resource.WOOD)}, new int[]{1}));
    public static final Flooring FLOOR_WOOD_2 = new Flooring(SettlementGame.AGame.getResources().getStringArray(R.array.flooring_names)[6], 7,true,0, 0,
            new Materials(new GameObject[]{ Resource.createResource(Resource.WOOD)}, new int[]{1}));
    public static final Flooring FLOOR_WOOD_3 = new Flooring(SettlementGame.AGame.getResources().getStringArray(R.array.flooring_names)[7], 8,true,0, 0,
            new Materials(new GameObject[]{ Resource.createResource(Resource.WOOD)}, new int[]{1}));
    public static final Flooring FLOOR_WOOD_4 = new Flooring(SettlementGame.AGame.getResources().getStringArray(R.array.flooring_names)[8], 9,true,0, 0,
            new Materials(new GameObject[]{ Resource.createResource(Resource.WOOD)}, new int[]{1}));

    private boolean indoors;

    public Flooring(String n, int i, boolean in, int quality, int creatorID, Materials materials){
        super(n, 0,1, 1, i);
        indoors = in;
        objectID = -1;
        this.materials = materials;
        this.quality = quality;
        this.creatorID = creatorID;
    }

    public void draw(Graphics g, float worldOffSetX, float worldOffSetY, int i, int j, float scale){
        if(this != VOID && TerrainInfo.tilesGrid[i][j] != Tile.VOID){
            if(GameObject.gameObjects[i][j] != null
                    && GameObject.gameObjects[i][j].opaque
                    && i + 1 < GameObject.gameObjects.length
                    && j + 1 < GameObject.gameObjects.length
                    && TerrainInfo.flooringGrid[i + 1][j] != Flooring.VOID
                    && TerrainInfo.flooringGrid[i][j + 1] != Flooring.VOID) {
                return;
            }
            float x = worldOffSetX + (i - j + TerrainInfo.tilesGridSize * BiomeInfo.biomeGridSize) * SPRITE_DRAW_OFFSET;
            float y = worldOffSetY + ((j + i) * SPRITE_DRAW_OFFSET) / 2.0f - 2;
            // crop or not
            float cropHeight = SPRITE_Y_CROP;
            if(i + 1 == TerrainInfo.tilesGrid.length || j + 1 == TerrainInfo.tilesGrid.length)
                cropHeight = SPRITE_HEIGHT;
            else if((i + 1 < TerrainInfo.tilesGrid.length && TerrainInfo.tilesGrid[i + 1][j] == Tile.VOID)
                    || (j + 1 < TerrainInfo.tilesGrid.length && TerrainInfo.tilesGrid[i][j + 1] == Tile.VOID))
                cropHeight = SPRITE_HEIGHT;
            if((x + SPRITE_WIDTH) * scale > 0 && (x - SPRITE_WIDTH) * scale < SettlementGame.WIDTH && (y + SPRITE_HEIGHT) * scale > 0 && (y - SPRITE_HEIGHT) * scale < SettlementGame.HEIGHT)
                g.drawPixmap(Assets.flooring, x * scale, y * scale, SPRITE_WIDTH * scale, cropHeight * scale, index * SPRITE_WIDTH, 0.0f, SPRITE_WIDTH, cropHeight);
        }
    }

    public void drawBorderingFloor(Graphics g, float worldOffSetX, float worldOffSetY, int i, int j, float scale){
        float x = worldOffSetX + (i - j + TerrainInfo.tilesGridSize * BiomeInfo.biomeGridSize) * SPRITE_DRAW_OFFSET;
        float y = worldOffSetY + ((j + i) * SPRITE_DRAW_OFFSET) / 2.0f;
        // crop or not
        if((x + SPRITE_WIDTH) * scale > 0 && (x - SPRITE_WIDTH) * scale < SettlementGame.WIDTH && (y + 22) * scale > 0 && (y - 22) * scale < SettlementGame.HEIGHT)
            g.drawPixmap(Assets.flooring, x * scale, y * scale, SPRITE_WIDTH * scale, 22 * scale, index * SPRITE_WIDTH, 0.0f, SPRITE_WIDTH, 22);
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

    public boolean indoors(){
        return indoors;
    }

}