package com.settlementGame.game.screen;

import android.graphics.Color;
import android.view.MotionEvent;

import androidx.core.view.MotionEventCompat;

import com.settlementGame.framework.Game;
import com.settlementGame.framework.Graphics;
import com.settlementGame.framework.Input.TouchEvent;
import com.settlementGame.framework.Screen;
import com.settlementGame.game.SettlementGame;

import java.util.List;

public class SelectStartingCivScreen extends Screen {

    private int civSelected = 0;

    public SelectStartingCivScreen(Game game) {
        super(game);
    }

    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {

                // civ selection buttons
                for(int j = 1; j < 5; j++){
                    if(inBounds(event, (int)(SettlementGame.WIDTH / 2.0f + SettlementGame.WIDTH / 16.0f), (int)(SettlementGame.HEIGHT / 10.0f + (SettlementGame.HEIGHT / 8.0f) * j), (int)(SettlementGame.WIDTH / 4.0f), (int)(SettlementGame.HEIGHT / 10.0f))) {
                        switch(j){
                            case 1:
                                civSelected = 0;
                                break;
                            case 2:
                                civSelected = 1;
                                break;
                            case 3:
                                civSelected = 2;
                                break;
                            case 4:
                                civSelected = 3;
                                break;
                        }
                    }
                }
                // continue
                if(inBounds(event, (int)(SettlementGame.WIDTH / 2.0f + SettlementGame.WIDTH / 16.0f + SettlementGame.WIDTH / 12.0f), (int)(SettlementGame.HEIGHT / 10.0f + (SettlementGame.HEIGHT / 8.0f) * 5), (int)(SettlementGame.WIDTH / 8.0f), (int)(SettlementGame.HEIGHT / 10.0f))) {
                    game.setScreen(new SelectStartingCitizensScreen(game));
                }

                // back
                if(inBounds(event, 50, 50, 200, 100)) {
                    game.setScreen(new SelectBiomeScreen(game));
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
        g.drawText(civSelected+"",SettlementGame.WIDTH / 2.0f - SettlementGame.WIDTH / 4.0f, SettlementGame.HEIGHT / 10.0f);

        // civ selection buttons
        for(int i = 1; i < 5; i++){
            g.drawRect(SettlementGame.WIDTH / 2.0f + SettlementGame.WIDTH / 16.0f, SettlementGame.HEIGHT / 10.0f + (SettlementGame.HEIGHT / 8.0f) * i, SettlementGame.WIDTH / 4.0f, SettlementGame.HEIGHT / 10.0f, Color.rgb(24, 74, 255));
            switch(i){
                case 1:
                    g.drawText("civ 1",SettlementGame.WIDTH / 2.0f + SettlementGame.WIDTH / 16.0f, SettlementGame.HEIGHT / 10.0f + (SettlementGame.HEIGHT / 8.0f) * (i +0.5f)); // todo remove strings after making civ class
                    break;
                case 2:
                    g.drawText("civ 2",SettlementGame.WIDTH / 2.0f + SettlementGame.WIDTH / 16.0f, SettlementGame.HEIGHT / 10.0f + (SettlementGame.HEIGHT / 8.0f) * (i +0.5f));
                    break;
                case 3:
                    g.drawText("civ 3",SettlementGame.WIDTH / 2.0f + SettlementGame.WIDTH / 16.0f, SettlementGame.HEIGHT / 10.0f + (SettlementGame.HEIGHT / 8.0f) * (i +0.5f));
                    break;
                case 4:
                    g.drawText("civ 4",SettlementGame.WIDTH / 2.0f + SettlementGame.WIDTH / 16.0f, SettlementGame.HEIGHT / 10.0f + (SettlementGame.HEIGHT / 8.0f) * (i +0.5f));
                    break;
            }
        }
        g.drawRect(SettlementGame.WIDTH / 2.0f + SettlementGame.WIDTH / 16.0f + SettlementGame.WIDTH / 12.0f, SettlementGame.HEIGHT / 10.0f + (SettlementGame.HEIGHT / 8.0f) * 5, SettlementGame.WIDTH / 8.0f, SettlementGame.HEIGHT / 10.0f, Color.rgb(24, 74, 255));
        g.drawText("Next",SettlementGame.WIDTH / 2.0f + SettlementGame.WIDTH / 16.0f + SettlementGame.WIDTH / 12.0f, SettlementGame.HEIGHT / 10.0f + (SettlementGame.HEIGHT / 8.0f) * (5 + 0.5f)); // todo replace with sprites

        // back
        g.drawRect(50, 50, 200, 100, Color.rgb(24, 74, 255));
        g.drawText("Back",50, 100); // todo replace with sprites
    }

    public void pause() {
    }

    public void resume() {
    }

    public void dispose() {
    }
}