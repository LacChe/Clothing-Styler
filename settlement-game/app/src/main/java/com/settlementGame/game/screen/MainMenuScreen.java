package com.settlementGame.game.screen;

import android.graphics.Color;
import android.graphics.Point;

import com.settlementGame.framework.Game;
import com.settlementGame.framework.Graphics;
import com.settlementGame.framework.Input.TouchEvent;
import com.settlementGame.framework.Screen;
import com.settlementGame.game.AStar.AStar;
import com.settlementGame.game.AStar.Node;
import com.settlementGame.game.Assets;
import com.settlementGame.game.SettlementGame;

import java.util.List;
import java.util.Random;

// first screen user sees, select game, achievement page, help, audio toggle
public class MainMenuScreen extends Screen {


    // music selection var
    public static int music = -1;
    private Random rand;

    public MainMenuScreen(Game game) {
        super(game);
        /*
        rand = new Random();
        if(Settings.soundEnabled == 1 && (music > 2 || music < 0)){
            music = rand.nextInt(3);
            setMusic();
        }
         */
    }

    private void setMusic(){
        Assets.lvl3.stop();
        Assets.lvl4.stop();
        Assets.lvl5.stop();
        Assets.lvl6.stop();
        Assets.lvl7.stop();
        Assets.lvl8.stop();
        Assets.lvl9.stop();
        Assets.lvl10.stop();
        switch(music){
            case 0:
                Assets.main0.play();
                break;
            case 1:
                Assets.main1.play();
                break;
            case 2:
                Assets.main2.play();
                break;
        }
    }

    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                // sound button
                /*
                if(inBounds(event, SettlementGame.WIDTH - (int)(16 * g.getScale()) - (int)(10 * g.getScale()), (int)(10 * g.getScale()), (int)(16 * g.getScale()), (int)(16 * g.getScale()))) {
                    // sound button
                    if(Settings.soundEnabled == 0){
                        Settings.soundEnabled = 1;
                        music = rand.nextInt(3);
                        setMusic();
                        Assets.playMusic();
                    }
                    else if(Settings.soundEnabled == 1){
                        Settings.soundEnabled = 0;
                        Assets.stopMusic();
                    }
                    if (Settings.soundEnabled == 1) Assets.pop.play(1);
                }
                 */
                // save slots
                // 1
                if(inBounds(event, (int)(SettlementGame.WIDTH / 2.0f - SettlementGame.WIDTH / 16.0f), (int)(SettlementGame.HEIGHT / 2.0f - (SettlementGame.HEIGHT / 8.0f)), (int)(SettlementGame.WIDTH / 8.0f), (int)(SettlementGame.HEIGHT / 8.0f))) {
                    game.setScreen(new SelectBiomeScreen(game));
                }
                // 2
                if(inBounds(event, (int)(SettlementGame.WIDTH / 2.0f - SettlementGame.WIDTH / 16.0f), (int)(SettlementGame.HEIGHT / 2.0f + (SettlementGame.HEIGHT / 16.0f)), (int)(SettlementGame.WIDTH / 8.0f), (int)(SettlementGame.HEIGHT / 8.0f))) {
                    game.setScreen(new SelectBiomeScreen(game));
                }
                // 3
                if(inBounds(event, (int)(SettlementGame.WIDTH / 2.0f - SettlementGame.WIDTH / 16.0f), (int)(SettlementGame.HEIGHT / 2.0f + (SettlementGame.HEIGHT / 8.0f) + (SettlementGame.HEIGHT / 16.0f)*2), (int)(SettlementGame.WIDTH / 8.0f), (int)(SettlementGame.HEIGHT / 8.0f))) {
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

        g.drawRect(SettlementGame.WIDTH / 2.0f - SettlementGame.WIDTH / 16.0f, SettlementGame.HEIGHT / 2.0f - (SettlementGame.HEIGHT / 8.0f), SettlementGame.WIDTH / 8.0f, SettlementGame.HEIGHT / 8.0f, Color.rgb(24, 74, 255));

        g.drawRect(SettlementGame.WIDTH / 2.0f - SettlementGame.WIDTH / 16.0f, SettlementGame.HEIGHT / 2.0f + (SettlementGame.HEIGHT / 16.0f), SettlementGame.WIDTH / 8.0f, SettlementGame.HEIGHT / 8.0f, Color.rgb(24, 74, 255));

        g.drawRect(SettlementGame.WIDTH / 2.0f - SettlementGame.WIDTH / 16.0f, SettlementGame.HEIGHT / 2.0f + (SettlementGame.HEIGHT / 8.0f) + (SettlementGame.HEIGHT / 16.0f)*2, SettlementGame.WIDTH / 8.0f, SettlementGame.HEIGHT / 8.0f, Color.rgb(24, 74, 255));

    }

    public void pause() {
    }

    public void resume() {
    }

    public void dispose() {
    }
}