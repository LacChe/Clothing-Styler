package com.settlementGame.game.screen;

import android.graphics.Color;
import android.view.MotionEvent;

import androidx.core.view.MotionEventCompat;

import com.settlementGame.framework.Game;
import com.settlementGame.framework.Graphics;
import com.settlementGame.framework.Input.TouchEvent;
import com.settlementGame.framework.Screen;
import com.settlementGame.game.SettlementGame;
import com.settlementGame.game.terrain.TerrainInfo;

import java.util.List;

public class SelectStartingCitizensScreen extends Screen {

    public SelectStartingCitizensScreen(Game game) {
        super(game);
    }

    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                // continue
                if(inBounds(event, (int)(SettlementGame.WIDTH / 2.0f + SettlementGame.WIDTH / 16.0f + SettlementGame.WIDTH / 12.0f), (int)(SettlementGame.HEIGHT / 10.0f + (SettlementGame.HEIGHT / 8.0f) * 5), (int)(SettlementGame.WIDTH / 8.0f), (int)(SettlementGame.HEIGHT / 10.0f))) {
                    TerrainInfo.genTerrainGrid();
                    game.setScreen(new GameScreen(game));
                }

                // back
                if(inBounds(event, 50, 50, 200, 100)) {
                    game.setScreen(new SelectStartingCivScreen(game));
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

        // citizen selection buttons
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