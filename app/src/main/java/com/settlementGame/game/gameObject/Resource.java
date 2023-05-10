package com.settlementGame.game.gameObject;

import com.settlementGame.R;
import com.settlementGame.framework.Graphics;
import com.settlementGame.game.Assets;
import com.settlementGame.game.SettlementGame;
import com.settlementGame.game.terrain.BiomeInfo;
import com.settlementGame.game.terrain.TerrainInfo;

public class Resource extends GameObject{

    private static int spriteWidth = 32, spriteHeight = 32, spriteDrawOffset = 16;

    public static Resource VOID;
    public static Resource WOOD;
    public static Resource STONE;
    public static Resource DIRT;
    public static Resource SAND;
    public static Resource GLASS;
    public static Resource BAR;
    public static Resource ORE;
    public static Resource WATER;
    public static Resource CLAY;

    public static void initResources(){
        VOID = new Resource(SettlementGame.AGame.getResources().getStringArray(R.array.resource_names)[0], -1);
        WOOD = new Resource(SettlementGame.AGame.getResources().getStringArray(R.array.resource_names)[1], 0);
        STONE = new Resource(SettlementGame.AGame.getResources().getStringArray(R.array.resource_names)[2], 1);
        DIRT = new Resource(SettlementGame.AGame.getResources().getStringArray(R.array.resource_names)[3], 2);
        SAND = new Resource(SettlementGame.AGame.getResources().getStringArray(R.array.resource_names)[4], 3);
        GLASS = new Resource(SettlementGame.AGame.getResources().getStringArray(R.array.resource_names)[5], 4);
        BAR = new Resource(SettlementGame.AGame.getResources().getStringArray(R.array.resource_names)[6], 5);
        ORE = new Resource(SettlementGame.AGame.getResources().getStringArray(R.array.resource_names)[7], 6);
        WATER = new Resource(SettlementGame.AGame.getResources().getStringArray(R.array.resource_names)[8], 7);
        CLAY = new Resource(SettlementGame.AGame.getResources().getStringArray(R.array.resource_names)[9], 8);
    }

    private Resource(String n, int i){
        super(n,0,1, 1, i);
    }

    public void draw(Graphics g, float worldOffSetX, float worldOffSetY, int i, int j, float scale){

        float drawX = worldOffSetX + (i - j + TerrainInfo.tilesGridSize * BiomeInfo.biomeGridSize) * spriteDrawOffset - drawShiftX;
        float drawY = worldOffSetY + ((j + i) * spriteDrawOffset) / 2.0f - 16;

        if((drawX + spriteWidth) * scale > 0 && (drawX) * scale < SettlementGame.WIDTH && (drawY + spriteHeight) * scale > 0 && (drawY) * scale < SettlementGame.HEIGHT)
            g.drawPixmap(Assets.resources, drawX * scale, drawY * scale, spriteWidth * scale, spriteHeight * scale, index * spriteWidth, 0, spriteWidth, spriteHeight);
    }

    public static Resource createResource(Resource res){
        return new Resource(res.name, res.index);
    }

    public String[] getInfo(){
        int len = 1 + 1 + 1;
        String[] info = new String[len];
        info[0] = name;
        String madeBy;
        if(creatorID == -1) madeBy = SettlementGame.AGame.getResources().getString(R.string.naturally_produced);
        else madeBy = Walkable.getNameFromID(creatorID) + " " + SettlementGame.AGame.getResources().getString(R.string.produced_this);
        info[1] = madeBy;
        info[2] = (quality == -1 ? SettlementGame.AGame.getResources().getString(R.string.natural) : Integer.toString(quality)) + " " + SettlementGame.AGame.getResources().getString(R.string.quality);
        return info;
    }

}