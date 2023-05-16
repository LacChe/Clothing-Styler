package com.settlementGame.game;

import android.content.Context;
import android.view.MotionEvent;

import com.settlementGame.framework.Screen;
import com.settlementGame.framework.impl.AndroidGame;
import com.settlementGame.game.screen.LoadingScreen;
import com.settlementGame.game.screen.MainMenuScreen;

// root class that android runs
public class SettlementGame extends AndroidGame {

    public Screen getStartScreen() {
        return new LoadingScreen(this);
    }

    @Override
    public void onBackPressed() {
        if(getCurrentScreen().getClass() == MainMenuScreen.class){
            super.onBackPressed();
            return;
        }
        setScreen(new MainMenuScreen(this));
    }

    @Override
    public void onResume() {
        super.onResume();
        Settings.load(fileIO);
        screen.resume();
        renderView.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        Settings.save(fileIO);
        renderView.pause();
        screen.pause();
        if (isFinishing()){
            screen.dispose();
            Assets.biomeGenTiles.dispose();
            Assets.flooring.dispose();
            Assets.tables.dispose();
            Assets.plants.dispose();
            Assets.resources.dispose();
            Assets.tiles.dispose();
            Assets.walls.dispose();
            Assets.stations.dispose();
            Assets.trees.dispose();
            Assets.walkables.dispose();
            Assets.pop.dispose();
            Assets.win.dispose();
            Assets.lose.dispose();
            Assets.lvl3.dispose();
            Assets.lvl4.dispose();
            Assets.lvl5.dispose();
            Assets.lvl6.dispose();
            Assets.lvl7.dispose();
            Assets.lvl8.dispose();
            Assets.lvl9.dispose();
            Assets.lvl10.dispose();
            Assets.main0.dispose();
            Assets.main1.dispose();
            Assets.main2.dispose();
        }
    }

}