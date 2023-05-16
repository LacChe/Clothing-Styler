package com.settlementGame.game.screen;

import android.graphics.Color;
import android.view.MotionEvent;

import androidx.core.view.MotionEventCompat;

import com.settlementGame.R;
import com.settlementGame.framework.Game;
import com.settlementGame.framework.Graphics;
import com.settlementGame.framework.Input.TouchEvent;
import com.settlementGame.framework.Screen;
import com.settlementGame.game.Assets;
import com.settlementGame.game.SettlementGame;
import com.settlementGame.game.terrain.BiomeInfo;

import java.util.List;
import java.util.Random;

// todo organise sprite rectangles

public class SelectBiomeScreen extends Screen {

    private final Random rand;
    private static final int BIOME_DRAW_OFF_SET_X = 50, BIOME_DRAW_OFF_SET_Y = 200, SCALE = 8;
    private boolean fingerDown = false;

    public SelectBiomeScreen(Game game) {
        super(game);
        rand = new Random();
    }

    public void update(float deltaTime) {
        // todo some gens with much sand or grass crash
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                // random
                if(inBounds(event, (int)(SettlementGame.WIDTH / 2.0f + SettlementGame.WIDTH / 16.0f + SettlementGame.WIDTH / 12.0f), (int)(SettlementGame.HEIGHT / 10.0f), (int)(SettlementGame.WIDTH / 8.0f), (int)(SettlementGame.HEIGHT / 10.0f))) {
                    int type = rand.nextInt(10) + 1;
                    switch(type){
                        case 1:
                            BiomeInfo.genBiome(BiomeInfo.biomeTypeSelected = BiomeInfo.HILLS);
                            break;
                        case 2:
                            BiomeInfo.genBiome(BiomeInfo.biomeTypeSelected = BiomeInfo.MOUNTAINS);
                            break;
                        case 3:
                            BiomeInfo.genBiome(BiomeInfo.biomeTypeSelected = BiomeInfo.PLAINS);
                            break;
                        case 4:
                            BiomeInfo.genBiome(BiomeInfo.biomeTypeSelected = BiomeInfo.SAVANNA);
                            break;
                        case 5:
                            BiomeInfo.genBiome(BiomeInfo.biomeTypeSelected = BiomeInfo.DESERT);
                            break;
                        case 6:
                            BiomeInfo.genBiome(BiomeInfo.biomeTypeSelected = BiomeInfo.ISLAND);
                            break;
                        case 7:
                            BiomeInfo.genBiome(BiomeInfo.biomeTypeSelected = BiomeInfo.BEACH);
                            break;
                        case 8:
                            BiomeInfo.genBiome(BiomeInfo.biomeTypeSelected = BiomeInfo.FOREST);
                            break;
                        case 9:
                            BiomeInfo.genBiome(BiomeInfo.biomeTypeSelected = BiomeInfo.RAINFOREST);
                            break;
                        case 10:
                            BiomeInfo.genBiome(BiomeInfo.biomeTypeSelected = BiomeInfo.TUNDRA);
                            break;
                    }
                }
                // biome selection buttons
                for(int j = 1; j < 6; j++){
                    if(inBounds(event, (int)(SettlementGame.WIDTH / 2.0f + SettlementGame.WIDTH / 16.0f), (int)(SettlementGame.HEIGHT / 10.0f + (SettlementGame.HEIGHT / 8.0f) * j), (int)(SettlementGame.WIDTH / 8.0f), (int)(SettlementGame.HEIGHT / 10.0f))) {
                        switch(j){
                            case 1:
                                BiomeInfo.genBiome(BiomeInfo.biomeTypeSelected = BiomeInfo.HILLS);
                                break;
                            case 2:
                                BiomeInfo.genBiome(BiomeInfo.biomeTypeSelected = BiomeInfo.MOUNTAINS);
                                break;
                            case 3:
                                BiomeInfo.genBiome(BiomeInfo.biomeTypeSelected = BiomeInfo.PLAINS);
                                break;
                            case 4:
                                BiomeInfo.genBiome(BiomeInfo.biomeTypeSelected = BiomeInfo.SAVANNA);
                                break;
                            case 5:
                                BiomeInfo.genBiome(BiomeInfo.biomeTypeSelected = BiomeInfo.DESERT);
                                break;
                        }
                    } else if(inBounds(event, (int)(SettlementGame.WIDTH / 2.0f + SettlementGame.WIDTH / 16.0f + SettlementGame.WIDTH / 6.0f), (int)(SettlementGame.HEIGHT / 10.0f + (SettlementGame.HEIGHT / 8.0f) * j), (int)(SettlementGame.WIDTH / 8.0f), (int)(SettlementGame.HEIGHT / 10.0f))) {
                        switch(j){
                            case 1:
                                BiomeInfo.genBiome(BiomeInfo.biomeTypeSelected = BiomeInfo.ISLAND);
                                break;
                            case 2:
                                BiomeInfo.genBiome(BiomeInfo.biomeTypeSelected = BiomeInfo.BEACH);
                                break;
                            case 3:
                                BiomeInfo.genBiome(BiomeInfo.biomeTypeSelected = BiomeInfo.FOREST);
                                break;
                            case 4:
                                BiomeInfo.genBiome(BiomeInfo.biomeTypeSelected = BiomeInfo.RAINFOREST);
                                break;
                            case 5:
                                BiomeInfo.genBiome(BiomeInfo.biomeTypeSelected = BiomeInfo.TUNDRA);
                                break;
                        }
                    }
                }
                // continue
                if(inBounds(event, (int)(SettlementGame.WIDTH / 2.0f + SettlementGame.WIDTH / 16.0f + SettlementGame.WIDTH / 12.0f), (int)(SettlementGame.HEIGHT / 10.0f + (SettlementGame.HEIGHT / 8.0f) * 6), (int)(SettlementGame.WIDTH / 8.0f), (int)(SettlementGame.HEIGHT / 10.0f))) {
                    game.setScreen(new SelectStartingCivScreen(game));
                }
                // back
                if(inBounds(event, 50, 50, 200, 100)) {
                    game.setScreen(new MainMenuScreen(game));
                }
            }
        }
    }

    private boolean inBounds(TouchEvent event, int x, int y, int width, int height) {
        if(event.x > x && event.x < x + width - 1 &&
                event.y > y && event.y < y + height - 1)
            return true;
        else
            return false;
    }
    private boolean inBounds(int i, int j, int x, int y, int width, int height) {
        if(i > x && i < x + width - 1 &&
                j > y && j < y + height - 1)
            return true;
        else
            return false;
    }

    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawRect(0, 0, SettlementGame.WIDTH, SettlementGame.HEIGHT, Color.rgb(124, 174, 255));

        // currently selected
        g.drawText(BiomeInfo.biomeTypeSelected.name,SettlementGame.WIDTH / 2.0f - SettlementGame.WIDTH / 4.0f, SettlementGame.HEIGHT / 10.0f);

        // draw biomes
        for(int i = 0; i < BiomeInfo.biomeGridSize; i++){
            for(int j = 0; j < BiomeInfo.biomeGridSize; j++){
                if(BiomeInfo.biomeGrid[i][j] != BiomeInfo.BiomeType.VOID){
                    float srcXOff = BiomeInfo.BiomeType.getIndex(BiomeInfo.biomeGrid[i][j]) * BiomeInfo.biomeSpriteWidth;
                    if(BiomeInfo.biomeTypeSelected == BiomeInfo.TUNDRA)
                        if (BiomeInfo.biomeGrid[i][j] == BiomeInfo.BiomeType.WOODS)
                            srcXOff = 9 * BiomeInfo.biomeSpriteWidth;
                        else if(BiomeInfo.biomeGrid[i][j] == BiomeInfo.BiomeType.FIELD)
                            srcXOff = 8 * BiomeInfo.biomeSpriteWidth;
                    g.drawPixmap(Assets.biomeGenTiles, BIOME_DRAW_OFF_SET_X + (i + BiomeInfo.biomeGridSize - j) * BiomeInfo.biomeSpriteDrawOffset * SCALE, BIOME_DRAW_OFF_SET_Y + ((j + i) * BiomeInfo.biomeSpriteDrawOffset) / 2.0f * SCALE, BiomeInfo.biomeSpriteWidth * SCALE, BiomeInfo.biomeSpriteHeight * SCALE, srcXOff, 0.0f, BiomeInfo.biomeSpriteWidth, BiomeInfo.biomeSpriteHeight);
                }
            }
        }

        // biome selection buttons
        g.drawRect(SettlementGame.WIDTH / 2.0f + SettlementGame.WIDTH / 16.0f + SettlementGame.WIDTH / 12.0f, SettlementGame.HEIGHT / 10.0f, SettlementGame.WIDTH / 8.0f, SettlementGame.HEIGHT / 10.0f, Color.rgb(24, 74, 255));
        g.drawText("Randomize",SettlementGame.WIDTH / 2.0f + SettlementGame.WIDTH / 16.0f + SettlementGame.WIDTH / 12.0f, SettlementGame.HEIGHT / 10.0f + (SettlementGame.HEIGHT / 8.0f) * (0.5f)); // todo replace with sprite

        for(int i = 1; i < 6; i++){
            g.drawRect(SettlementGame.WIDTH / 2.0f + SettlementGame.WIDTH / 16.0f, SettlementGame.HEIGHT / 10.0f + (SettlementGame.HEIGHT / 8.0f) * i, SettlementGame.WIDTH / 8.0f, SettlementGame.HEIGHT / 10.0f, Color.rgb(24, 74, 255));
            g.drawRect(SettlementGame.WIDTH / 2.0f + SettlementGame.WIDTH / 16.0f + SettlementGame.WIDTH / 6.0f, SettlementGame.HEIGHT / 10.0f + (SettlementGame.HEIGHT / 8.0f) * i, SettlementGame.WIDTH / 8.0f, SettlementGame.HEIGHT / 10.0f, Color.rgb(24, 74, 255));
            switch(i){
                case 1:
                    g.drawText(SettlementGame.AGame.getResources().getStringArray(R.array.biome_names)[0],SettlementGame.WIDTH / 2.0f + SettlementGame.WIDTH / 16.0f, SettlementGame.HEIGHT / 10.0f + (SettlementGame.HEIGHT / 8.0f) * (i +0.5f));
                    g.drawText(SettlementGame.AGame.getResources().getStringArray(R.array.biome_names)[5],SettlementGame.WIDTH / 2.0f + SettlementGame.WIDTH / 16.0f + SettlementGame.WIDTH / 6.0f, SettlementGame.HEIGHT / 10.0f + (SettlementGame.HEIGHT / 8.0f) * (i +0.5f));
                    break;
                case 2:
                    g.drawText(SettlementGame.AGame.getResources().getStringArray(R.array.biome_names)[1],SettlementGame.WIDTH / 2.0f + SettlementGame.WIDTH / 16.0f, SettlementGame.HEIGHT / 10.0f + (SettlementGame.HEIGHT / 8.0f) * (i +0.5f));
                    g.drawText(SettlementGame.AGame.getResources().getStringArray(R.array.biome_names)[6],SettlementGame.WIDTH / 2.0f + SettlementGame.WIDTH / 16.0f + SettlementGame.WIDTH / 6.0f, SettlementGame.HEIGHT / 10.0f + (SettlementGame.HEIGHT / 8.0f) * (i +0.5f));
                    break;
                case 3:
                    g.drawText(SettlementGame.AGame.getResources().getStringArray(R.array.biome_names)[2],SettlementGame.WIDTH / 2.0f + SettlementGame.WIDTH / 16.0f, SettlementGame.HEIGHT / 10.0f + (SettlementGame.HEIGHT / 8.0f) * (i +0.5f));
                    g.drawText(SettlementGame.AGame.getResources().getStringArray(R.array.biome_names)[7],SettlementGame.WIDTH / 2.0f + SettlementGame.WIDTH / 16.0f + SettlementGame.WIDTH / 6.0f, SettlementGame.HEIGHT / 10.0f + (SettlementGame.HEIGHT / 8.0f) * (i +0.5f));
                    break;
                case 4:
                    g.drawText(SettlementGame.AGame.getResources().getStringArray(R.array.biome_names)[3],SettlementGame.WIDTH / 2.0f + SettlementGame.WIDTH / 16.0f, SettlementGame.HEIGHT / 10.0f + (SettlementGame.HEIGHT / 8.0f) * (i +0.5f));
                    g.drawText(SettlementGame.AGame.getResources().getStringArray(R.array.biome_names)[8],SettlementGame.WIDTH / 2.0f + SettlementGame.WIDTH / 16.0f + SettlementGame.WIDTH / 6.0f, SettlementGame.HEIGHT / 10.0f + (SettlementGame.HEIGHT / 8.0f) * (i +0.5f));
                    break;
                case 5:
                    g.drawText(SettlementGame.AGame.getResources().getStringArray(R.array.biome_names)[4],SettlementGame.WIDTH / 2.0f + SettlementGame.WIDTH / 16.0f, SettlementGame.HEIGHT / 10.0f + (SettlementGame.HEIGHT / 8.0f) * (i +0.5f));
                    g.drawText(SettlementGame.AGame.getResources().getStringArray(R.array.biome_names)[9],SettlementGame.WIDTH / 2.0f + SettlementGame.WIDTH / 16.0f + SettlementGame.WIDTH / 6.0f, SettlementGame.HEIGHT / 10.0f + (SettlementGame.HEIGHT / 8.0f) * (i +0.5f));
                    break;
            }
        }
        g.drawRect(SettlementGame.WIDTH / 2.0f + SettlementGame.WIDTH / 16.0f + SettlementGame.WIDTH / 12.0f, SettlementGame.HEIGHT / 10.0f + (SettlementGame.HEIGHT / 8.0f) * 6, SettlementGame.WIDTH / 8.0f, SettlementGame.HEIGHT / 10.0f, Color.rgb(24, 74, 255));
        g.drawText("Next",SettlementGame.WIDTH / 2.0f + SettlementGame.WIDTH / 16.0f + SettlementGame.WIDTH / 12.0f, SettlementGame.HEIGHT / 10.0f + (SettlementGame.HEIGHT / 8.0f) * (6 + 0.5f)); // todo replace with sprite

        // back
        g.drawRect(50, 50, 200, 100, Color.rgb(24, 74, 255));
        g.drawText("Back",50, 100); // todo replace with sprite
    }

    public void pause() {
    }

    public void resume() {
    }

    public void dispose() {
    }
}