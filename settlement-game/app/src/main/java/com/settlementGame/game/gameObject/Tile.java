package com.settlementGame.game.gameObject;

import android.graphics.Color;


import com.settlementGame.R;
import com.settlementGame.framework.Graphics;
import com.settlementGame.game.Assets;
import com.settlementGame.game.SettlementGame;
import com.settlementGame.game.screen.GameScreen;
import com.settlementGame.game.terrain.BiomeInfo;
import com.settlementGame.game.terrain.TerrainInfo;

public class Tile extends GameObject {

    private static final int SPRITE_WIDTH = 32, SPRITE_HEIGHT = 96, SPRITE_DRAW_OFFSET = 16, SPRITE_Y_CROP = 24;

    public static Tile VOID;
    public static Tile DIRT;
    public static Tile SAND;
    public static Tile STONE;
    public static Tile ORE;
    public static Tile WATER;
    public static Tile LAVA;

    private boolean cultivatable;

    public static void initTiles(){
        VOID = new Tile(SettlementGame.AGame.getResources().getStringArray(R.array.tile_names)[0], -1,false, false, new Materials());
        DIRT = new Tile(SettlementGame.AGame.getResources().getStringArray(R.array.tile_names)[1], 0,true,true,
                new Materials(new GameObject[]{ Resource.createResource(Resource.DIRT)}, new int[]{10}));
        SAND = new Tile(SettlementGame.AGame.getResources().getStringArray(R.array.tile_names)[2], 1,true,true,
                new Materials(new GameObject[]{ Resource.createResource(Resource.SAND)}, new int[]{10}));
        STONE = new Tile(SettlementGame.AGame.getResources().getStringArray(R.array.tile_names)[3], 2,false,true,
                new Materials(new GameObject[]{ Resource.createResource(Resource.STONE)}, new int[]{10}));
        ORE = new Tile(SettlementGame.AGame.getResources().getStringArray(R.array.tile_names)[4], 3,false,true,
                new Materials(new GameObject[]{ Resource.createResource(Resource.STONE), Resource.createResource(Resource.ORE)}, new int[]{2, 8}));
        WATER = new Tile(SettlementGame.AGame.getResources().getStringArray(R.array.tile_names)[5], 4,true,false,
                new Materials(new GameObject[]{ Resource.createResource(Resource.WATER)}, new int[]{10}));
        LAVA = new Tile(SettlementGame.AGame.getResources().getStringArray(R.array.tile_names)[6], 5,false, false, new Materials());
    }

    public Tile(String n, int i, boolean cult, boolean passable, Materials materials){
        super(n, 0,1, 1, i);
        cultivatable = cult;
        this.materials = materials;
        this.passable = passable;
        for(int ii = 0; ii < materials.getMats().length; ii++){
            // System.out.println(materials.getAmounts()[ii] + " " + materials.getMats()[ii].name);
        }
        objectID = -1;
    }

    public String[] getInfo(){
        int len = 1 + materials.getMats().length;
        String[] info = new String[len];
        info[0] = name;
        for(int i = 0; i < materials.getMats().length; i++){
            info[i + 1] = materials.getAmounts()[i] + " " + materials.getMats()[i].name;
        }
        return info;
    }

    public void draw(Graphics g, float worldOffSetX, float worldOffSetY, int i, int j, float scale){
        if(TerrainInfo.tilesGrid[i][j] != VOID){
            if(TerrainInfo.flooringGrid[i][j] != Flooring.VOID
                    && i + 1 < TerrainInfo.tilesGrid.length
                    && j + 1 < TerrainInfo.tilesGrid.length
                    && TerrainInfo.tilesGrid[i + 1][j] != Tile.WATER
                    && TerrainInfo.tilesGrid[i][j + 1] != Tile.WATER
                    && TerrainInfo.tilesGrid[i + 1][j] != Tile.LAVA
                    && TerrainInfo.tilesGrid[i][j + 1] != Tile.LAVA
                    && TerrainInfo.tilesGrid[i + 1][j] != Tile.VOID
                    && TerrainInfo.tilesGrid[i][j + 1] != Tile.VOID) {
                return;
            }
            float x = worldOffSetX + (i - j + TerrainInfo.tilesGridSize * BiomeInfo.biomeGridSize) * SPRITE_DRAW_OFFSET;
            float y = worldOffSetY + ((j + i) * SPRITE_DRAW_OFFSET) / 2.0f;

            // crop or not
            float cropHeight = SPRITE_Y_CROP;
            if((i + 1 < TerrainInfo.tilesGrid.length && TerrainInfo.tilesGrid[i + 1][j] == VOID)
                    || (j + 1 < TerrainInfo.tilesGrid.length && TerrainInfo.tilesGrid[i][j + 1] == VOID))
                cropHeight = SPRITE_HEIGHT;
            if((x + SPRITE_WIDTH) * scale > 0 && (x - SPRITE_WIDTH) * scale < SettlementGame.WIDTH && (y + SPRITE_HEIGHT) * scale > 0 && (y - SPRITE_HEIGHT) * scale < SettlementGame.HEIGHT)
                g.drawPixmap(Assets.tiles, x * scale, y * scale, SPRITE_WIDTH * scale, cropHeight * scale, index * SPRITE_WIDTH, 0.0f, SPRITE_WIDTH, cropHeight);

        }
    }
}