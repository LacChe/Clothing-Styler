package com.settlementGame.game.gameObject;

import com.settlementGame.R;
import com.settlementGame.framework.Graphics;
import com.settlementGame.game.Assets;
import com.settlementGame.game.SettlementGame;
import com.settlementGame.game.screen.GameScreen;
import com.settlementGame.game.terrain.BiomeInfo;
import com.settlementGame.game.terrain.TerrainInfo;

import java.util.ArrayList;
import java.util.Random;

public class Tree extends Plant{

    public static Tree tree1 = new Tree(SettlementGame.AGame.getResources().getStringArray(R.array.tree_names)[0], 0,
            new Materials(new GameObject[]{ Resource.createResource(Resource.WOOD)}, new int[]{2}));
    public static Tree tree2 = new Tree(SettlementGame.AGame.getResources().getStringArray(R.array.tree_names)[1], 1,
            new Materials(new GameObject[]{ Resource.createResource(Resource.WOOD)}, new int[]{2}));
    public static Tree tree3 = new Tree(SettlementGame.AGame.getResources().getStringArray(R.array.tree_names)[2], 2,
            new Materials(new GameObject[]{ Resource.createResource(Resource.WOOD)}, new int[]{2}));
    public static Tree tree4 = new Tree(SettlementGame.AGame.getResources().getStringArray(R.array.tree_names)[3], 3,
            new Materials(new GameObject[]{ Resource.createResource(Resource.WOOD)}, new int[]{2}));

    private Tree(String n, int i, Materials materials){
        super(n, i);
        spriteWidth = 32;
        spriteHeight = 72;
        spriteDrawOffset = 16;
        spriteFromGround = 58;
        this.materials = materials;
        passable = false;
    }

    public void drawBorderingTree(Graphics g, float worldOffSetX, float worldOffSetY, int i, int j, float scale){
        float drawX = worldOffSetX + (i - j + TerrainInfo.tilesGridSize * BiomeInfo.biomeGridSize) * spriteDrawOffset;
        float drawY = worldOffSetY + ((j + i) * spriteDrawOffset) / 2.0f - spriteFromGround;
        if((drawX + spriteWidth) * scale > 0 && (drawX) * scale < SettlementGame.WIDTH && (drawY + spriteHeight) * scale > 0 && (drawY) * scale < SettlementGame.HEIGHT)
            g.drawPixmap(Assets.trees, drawX * scale, drawY * scale, spriteWidth * scale, spriteHeight * scale, age * spriteWidth, index * spriteHeight, spriteWidth, spriteHeight);
    }

    public void draw(Graphics g, float worldOffSetX, float worldOffSetY, int i, int j, float scale){
        float drawX = worldOffSetX + (i - j + TerrainInfo.tilesGridSize * BiomeInfo.biomeGridSize) * spriteDrawOffset;
        float drawY = worldOffSetY + ((j + i) * spriteDrawOffset) / 2.0f - 2 - spriteFromGround;
        if((drawX + spriteWidth) * scale > 0 && (drawX) * scale < SettlementGame.WIDTH && (drawY + spriteHeight) * scale > 0 && (drawY) * scale < SettlementGame.HEIGHT)
            // g.drawPixmap(Assets.trees, drawX * scale, drawY * scale, spriteWidth * scale, spriteHeight * scale, age * spriteWidth, index * spriteHeight, spriteWidth, spriteHeight);
            g.drawPixmap(Assets.trees, drawX * scale, drawY * scale, spriteWidth * scale, spriteHeight * scale, (3 + index / 2) * spriteWidth, 0 * spriteHeight, spriteWidth, spriteHeight);
    }

    public static Tree getRandomTree(BiomeInfo typeSelected, boolean w){
        // todo tree depends on biome type

        int t = rand.nextInt(SettlementGame.AGame.getResources().getStringArray(R.array.tree_names).length);
        switch(t){
            case 0:
            default:
                return createTree(tree1);
            case 1:
                return createTree(tree2);
            case 2:
                return createTree(tree3);
            case 3:
                return createTree(tree4);
        }
    }

    public static void addTree(int x, int y, boolean w){
        // todo random?
        addGameObject(getRandomTree(BiomeInfo.biomeTypeSelected, w), x, y);
    }

    public static Tree createTree(Tree t){
        Tree tRet = new Tree(t.name, t.index, t.materials);
        return tRet;
    }

}