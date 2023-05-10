package com.settlementGame.game.gameObject;

import com.settlementGame.R;
import com.settlementGame.framework.Graphics;
import com.settlementGame.game.Assets;
import com.settlementGame.game.SettlementGame;
import com.settlementGame.game.terrain.BiomeInfo;
import com.settlementGame.game.terrain.TerrainInfo;

import java.util.ArrayList;
import java.util.Random;

public class Plant extends GameObject {

    protected static int spriteWidth = 0, spriteHeight = 0, spriteDrawOffset = 0, spriteFromGround = 0;

    // todo temp
    protected int age = 5;
    protected int waterLevelMin = 30, waterLevelMax = 70, waterLevel = 50;

    protected boolean wild = true;

    public Plant(String n, int i){
        super(n,0,1, 1, i);
    }

    public void setWild(boolean w){
        wild = w;
    }

    public void draw(Graphics g, float worldOffSetX, float worldOffSetY, int i, int j, float scale){
        // todo
    }

    public int getAdjustedMaterialAmount(int matIndex){
        return materials.getAmounts()[matIndex] * age;
    }

    public String[] getInfo(){
        int len = 1 + 1 + materials.getMats().length;
        String[] info = new String[len];
        info[0] = name;
        for(int i = 0; i < materials.getMats().length; i++){
            info[i + 1] = getAdjustedMaterialAmount(i) + " " + materials.getMats()[i].name;
        }
        int waterString = 1;
        if (waterLevel < waterLevelMin) waterString = 0;
        if (waterLevel > waterLevelMax) waterString = 2;
        info[materials.getMats().length + 1] = SettlementGame.AGame.getResources().getStringArray(R.array.water_level)[waterString];
        return info;
    }

}