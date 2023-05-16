package com.settlementGame.game.screen;

import com.settlementGame. framework.Game;
import com.settlementGame.framework.Graphics;
import com.settlementGame.framework.Graphics.PixmapFormat;
import com.settlementGame.framework.Screen;
import com.settlementGame.game.AStar.AStar;
import com.settlementGame.game.Assets;
import com.settlementGame.game.gameObject.Resource;
import com.settlementGame.game.gameObject.Tile;
import com.settlementGame.game.terrain.BiomeInfo;
import com.settlementGame.game.Settings;
import com.settlementGame.game.terrain.TerrainInfo;

// load assets here
public class LoadingScreen extends Screen {

    public LoadingScreen(Game game) {
        super(game);
    }

    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.biomeGenTiles = g.newPixmap("biomeGenTiles.png", PixmapFormat.ARGB8888);
        Assets.flooring = g.newPixmap("flooring.png", PixmapFormat.ARGB8888);
        Assets.tables = g.newPixmap("tables.png", PixmapFormat.ARGB8888);
        Assets.walls = g.newPixmap("walls.png", PixmapFormat.ARGB8888);
        Assets.stations = g.newPixmap("stations.png", PixmapFormat.ARGB8888);
        Assets.plants = g.newPixmap("plants.png", PixmapFormat.ARGB8888);
        Assets.resources = g.newPixmap("resources.png", PixmapFormat.ARGB8888);
        Assets.tiles = g.newPixmap("tiles.png", PixmapFormat.ARGB8888);
        Assets.trees = g.newPixmap("trees.png", PixmapFormat.ARGB8888);
        Assets.walkables = g.newPixmap("walkable.png", PixmapFormat.ARGB8888);
        Assets.pop = game.getAudio().newSound("pop.mp3");
        Assets.win = game.getAudio().newSound("win.mp3");
        Assets.lose = game.getAudio().newSound("lose.mp3");
        Assets.lvl3 = game.getAudio().newMusic("lvl3.mp3");
        Assets.lvl4 = game.getAudio().newMusic("lvl4.mp3");
        Assets.lvl5 = game.getAudio().newMusic("lvl5.mp3");
        Assets.lvl6 = game.getAudio().newMusic("lvl6.mp3");
        Assets.lvl7 = game.getAudio().newMusic("lvl7.mp3");
        Assets.lvl8 = game.getAudio().newMusic("lvl8.mp3");
        Assets.lvl9 = game.getAudio().newMusic("lvl9.mp3");
        Assets.lvl10 = game.getAudio().newMusic("lvl10.mp3");
        Assets.main0 = game.getAudio().newMusic("main0.mp3");
        Assets.main1 = game.getAudio().newMusic("main1.mp3");
        Assets.main2 = game.getAudio().newMusic("main2.mp3");
        Assets.lvl3.setLooping(true);
        Assets.lvl4.setLooping(true);
        Assets.lvl5.setLooping(true);
        Assets.lvl6.setLooping(true);
        Assets.lvl7.setLooping(true);
        Assets.lvl8.setLooping(true);
        Assets.lvl9.setLooping(true);
        Assets.lvl10.setLooping(true);
        Assets.main0.setLooping(true);
        Assets.main1.setLooping(true);
        Assets.main2.setLooping(true);
        Settings.load(game.getFileIO());
        game.setScreen(new MainMenuScreen(game));
        Resource.initResources();
        Tile.initTiles();
        BiomeInfo.init();
        TerrainInfo.init();
        AStar.init();
    }

    public void present(float deltaTime) {
    }

    public void pause() {
    }

    public void resume() {
    }

    public void dispose() {
    }
}