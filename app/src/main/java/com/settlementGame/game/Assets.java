package com.settlementGame.game;

import com.settlementGame.framework.Music;
import com.settlementGame.framework.Pixmap;
import com.settlementGame.framework.Sound;
import com.settlementGame.game.screen.MainMenuScreen;

// access assets here
public class Assets {
    public static Pixmap biomeGenTiles;
    public static Pixmap flooring;
    public static Pixmap tables;
    public static Pixmap walls;
    public static Pixmap stations;
    public static Pixmap plants;
    public static Pixmap resources;
    public static Pixmap tiles;
    public static Pixmap trees;
    public static Pixmap walkables;
    public static Sound pop;
    public static Sound win;
    public static Sound lose;
    public static Music lvl3;
    public static Music lvl4;
    public static Music lvl5;
    public static Music lvl6;
    public static Music lvl7;
    public static Music lvl8;
    public static Music lvl9;
    public static Music lvl10;
    public static Music main0;
    public static Music main1;
    public static Music main2;

    public static void stopMusic(){
        Assets.main0.stop();
        Assets.main1.stop();
        Assets.main2.stop();
        Assets.lvl3.stop();
        Assets.lvl4.stop();
        Assets.lvl5.stop();
        Assets.lvl6.stop();
        Assets.lvl7.stop();
        Assets.lvl8.stop();
        Assets.lvl9.stop();
        Assets.lvl10.stop();
    }

    public static void playMusic(){
        switch(MainMenuScreen.music){
            case 0:
                Assets.main0.play();
                break;
            case 1:
                Assets.main1.play();
                break;
            case 2:
                Assets.main2.play();
                break;
            case 3:
                Assets.lvl3.play();
                break;
            case 4:
                Assets.lvl4.play();
                break;
            case 5:
                Assets.lvl5.play();
                break;
            case 6:
                Assets.lvl6.play();
                break;
            case 7:
                Assets.lvl7.play();
                break;
            case 8:
                Assets.lvl8.play();
                break;
            case 9:
                Assets.lvl9.play();
                break;
            case 10:
                Assets.lvl10.play();
                break;
        }
    }
}