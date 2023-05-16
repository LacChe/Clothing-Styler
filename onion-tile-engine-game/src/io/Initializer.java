package io;

import entity.Entity;
import entity.StaticEntity;
import menu.Item;
import menu.Menu;
import render.Animation;
import render.Camera;
import render.Sprite;
import render.SpriteSheet;
import world.Tile;
import world.World;

public class Initializer {

	public static boolean done = false;

	public static void initialize() {
		Boot.boot.render();

		loadSpriteSheet();
		Boot.loaded++;
		Boot.boot.render();

		loadEntities();
		Boot.loaded++;
		Boot.boot.render();

		loadSprites();
		Boot.loaded++;
		Boot.boot.render();

		loadTiles();
		Boot.loaded++;
		Boot.boot.render();

		loadStaticEntities();
		Boot.loaded++;
		Boot.boot.render();

		loadItems();
		Boot.loaded++;
		Boot.boot.render();

		loadWorlds();
		Boot.loaded++;
		Boot.boot.render();

		// world = Save.load("save1.ser").world();
		// if (world.w() == 1) world = World.ISLAND;

		World.world(World.MAIN_NW);
		Camera.camera(new Camera(World.world(), Boot.WIDTH, Boot.HEIGHT));
		Menu.init();
		Boot.loaded++;
		Boot.boot.render();

		done = true;
	}

	private static void loadEntities() {
		Entity.PLAYER_S = 64;
		Entity.GARLIC1_W = 32;
		Entity.GARLIC1_H = 64;
		Entity.EGGPLANT_W = 64;
		Entity.EGGPLANT_H = 128;
		Entity.CORN_W = 64;
		Entity.CORN_H = 128;

		Entity.ANIM_IDLE_F = 0;
		Entity.ANIM_IDLE_R = 1;
		Entity.ANIM_IDLE_L = 2;
		Entity.ANIM_IDLE_B = 3;
		Entity.ANIM_WALK_F = 4;
		Entity.ANIM_WALK_R = 5;
		Entity.ANIM_WALK_L = 6;
		Entity.ANIM_WALK_B = 7;
	}

	private static void loadItems() {
		Item.empty = new Item();
		Item.mushCube = new Item(Sprite.mushCube, "A mushcube.");
		Item.jellyDisc = new Item(Sprite.jellyDisc, "A jellydisc.");
		Item.rubbish = new Item(Sprite.rubbish, "Rubbish");
	}

	private static void loadTiles() {
		Tile.TILE_S = 64;

		Tile.DIRT_N // 000,000,000
				= new Tile(new Animation(new Sprite[] { Sprite.DIRT_N }, 3));
		Tile.DIRT // 000,000,010
				= new Tile(new Animation(new Sprite[] { Sprite.DIRT }, 3));
		Tile.DIRT_W // 000,000,020
				= new Tile(new Animation(new Sprite[] { Sprite.DIRT_W }, 3));
		Tile.DIRT_E // 000,000,030
				= new Tile(new Animation(new Sprite[] { Sprite.DIRT_E }, 3));
		Tile.DIRT_NW_CRUST // 000,000,040
				= new Tile(new Animation(new Sprite[] { Sprite.DIRT_NW_CRUST }, 3));
		Tile.DIRT_NE_CRUST // 000,000,050
				= new Tile(new Animation(new Sprite[] { Sprite.DIRT_NE_CRUST }, 3));
		Tile.DIRT_NW // 000,000,060
				= new Tile(new Animation(new Sprite[] { Sprite.DIRT_NW }, 3));
		Tile.DIRT_NE // 000,000,070
				= new Tile(new Animation(new Sprite[] { Sprite.DIRT_NE }, 3));
		Tile.BRUSH // 000,000,080
				= new Tile(new Animation(new Sprite[] { Sprite.BRUSH }, 3)).setPassable(false);
		Tile.DIRT_STAIRS // 000,000,090
				= new Tile(new Animation(new Sprite[] { Sprite.DIRT_STAIRS }, 3));

		Tile.CLIFF // 000,000,100
				= new Tile(new Animation(Sprite.CLIFF, 3)).setPassable(false);
		Tile.WATER // 000,000,110
				= new Tile(new Animation(Sprite.WATER, 3)).setPassable(false);
		Tile.WATER_E // 000,000,120
				= new Tile(new Animation(Sprite.WATER_E, 3)).setPassable(false);
		Tile.WATER_W // 000,000,130
				= new Tile(new Animation(Sprite.WATER_W, 3)).setPassable(false);

		Tile.MushRoomFloor // 000,010,000
				= new Tile(new Animation(new Sprite[] { Sprite.MushRoomFloor }, 3));
		Tile.FLOOR // 000,010,010
				= new Tile(new Animation(new Sprite[] { Sprite.FLOOR }, 3));
		Tile.WALL_W // 000,010,020
				= new Tile(new Animation(new Sprite[] { Sprite.WALL_W }, 3)).setPassable(false);
		Tile.WALL_E // 000,010,030
				= new Tile(new Animation(new Sprite[] { Sprite.WALL_E }, 3)).setPassable(false);
		Tile.CEILING // 000,010,040
				= new Tile(new Animation(new Sprite[] { Sprite.CEILING }, 3)).setPassable(false);
		Tile.WALL_SW_CRUST // 000,010,050
				= new Tile(new Animation(new Sprite[] { Sprite.WALL_SW_CRUST }, 3)).setPassable(false);
		Tile.WALL_SE_CRUST // 000,010,060
				= new Tile(new Animation(new Sprite[] { Sprite.WALL_SE_CRUST }, 3)).setPassable(false);
		Tile.WALL_SW // 000,010,070
				= new Tile(new Animation(new Sprite[] { Sprite.WALL_SW }, 3)).setPassable(false);
		Tile.WALL_SE // 000,010,080
				= new Tile(new Animation(new Sprite[] { Sprite.WALL_SE }, 3)).setPassable(false);
		Tile.VOID // 000,010,090
				= new Tile(new Animation(new Sprite[] { Sprite.VOID }, 3)).setPassable(false);
	}

	private static void loadWorlds() {
		World.ROOM_ONION = new World(SpriteSheet.ROOM_ONION);
		// World.ROOM_GARLIC = new World(SpriteSheet.ROOM_GARLIC);
		// World.ROOM_LETTUCE = new World(SpriteSheet.ROOM_LETTUCE);
		// World.ROOM_CARROT = new World(SpriteSheet.ROOM_CARROT);
		// World.ROOM_EGGPLANT = new World(SpriteSheet.ROOM_EGGPLANT);
		// World.ROOM_CORN = new World(SpriteSheet.ROOM_CORN);

		// World.ROOM_WATERMELON = new World(SpriteSheet.ROOM_WATERMELON);
		// World.ROOM_CHERRY = new World(SpriteSheet.ROOM_CHERRY);
		// World.ROOM_PEAR = new World(SpriteSheet.ROOM_PEAR);
		// World.ROOM_RASPBERRY = new World(SpriteSheet.ROOM_RASPBERRY);
		// World.ROOM_BANANA = new World(SpriteSheet.ROOM_BANANA);
		// World.ROOM_MANGO = new World(SpriteSheet.ROOM_MANGO);

		World.ROOM_CLOTHES = new World(SpriteSheet.ROOM_CLOTHES);
		World.ROOM_SALON = new World(SpriteSheet.ROOM_SALON);

		World.ROOM_GYM = new World(SpriteSheet.ROOM_GYM);
		World.ROOM_SCHOOL = new World(SpriteSheet.ROOM_SCHOOL);
		World.ROOM_LIBRARY = new World(SpriteSheet.ROOM_LIBRARY);
		World.ROOM_STORAGE = new World(SpriteSheet.ROOM_STORAGE);

		World.CAVE = new World(SpriteSheet.CAVE);
		World.VOLCANO = new World(SpriteSheet.VOLCANO);
		World.FARM = new World(SpriteSheet.FARM);

		World.MAIN_NW = new World(SpriteSheet.MAIN_NW);
		World.MAIN_N = new World(SpriteSheet.MAIN_N);
		World.MAIN_NE = new World(SpriteSheet.MAIN_NE);
		World.MAIN_E = new World(SpriteSheet.MAIN_E);
		World.MAIN_SE = new World(SpriteSheet.MAIN_SE);
		World.MAIN_S = new World(SpriteSheet.MAIN_S);
		World.MAIN_SW = new World(SpriteSheet.MAIN_SW);
		World.MAIN_W = new World(SpriteSheet.MAIN_W);
		World.MAIN_C = new World(SpriteSheet.MAIN_C);

		World.world(World.MAIN_NW);
	}

	private static void loadStaticEntities() {
		StaticEntity.CAVE_UNDERLAY_1 = new StaticEntity(11 * 64, 12 * 64, Sprite.CAVE_UNDERLAY_1);
		StaticEntity.VOLCANO_UNDERLAY_1 = new StaticEntity(13 * 64, 7 * 64, Sprite.VOLCANO_UNDERLAY_1);
		StaticEntity.LAVA = new StaticEntity(13 * 64 + 128, 7 * 64 + 445, 320, 131, 0, 0, new Animation(new Sprite[] {
				Sprite.LAVA_1, Sprite.LAVA_2, Sprite.LAVA_3, Sprite.LAVA_2, Sprite.LAVA_1, Sprite.LAVA_4 }, 1));
		StaticEntity.FARM_UNDERLAY_1 = new StaticEntity(8 * 64, 17 * 64, Sprite.FARM_UNDERLAY_1);
		StaticEntity.FOUNTAIN = new StaticEntity(716, 856, 320, 126, 0, -130,
				new Animation(new Sprite[] { Sprite.FOUNTAIN_1, Sprite.FOUNTAIN_2, Sprite.FOUNTAIN_3 }, 9));

		StaticEntity.ROOM_ONION_UNDERLAY = new StaticEntity(9 * 64, 4 * 64, Sprite.ROOM_ONION_UNDERLAY);

		StaticEntity.W_UNDERLAY_1 = new StaticEntity(0 * 2, 98 * 2, Sprite.W_UNDERLAY_1);
		StaticEntity.W_UNDERLAY_2 = new StaticEntity(77 * 2, 65 * 2, Sprite.W_UNDERLAY_2);
		StaticEntity.W_UNDERLAY_3 = new StaticEntity(139 * 2, 25 * 2, Sprite.W_UNDERLAY_3);
		StaticEntity.W_UNDERLAY_4 = new StaticEntity(323 * 2, 0 * 2, Sprite.W_UNDERLAY_4);
		StaticEntity.W_UNDERLAY_5 = new StaticEntity(496 * 2, 0 * 2, Sprite.W_UNDERLAY_5);
		StaticEntity.W_UNDERLAY_6 = new StaticEntity(634 * 2, 21 * 2, Sprite.W_UNDERLAY_6);
		StaticEntity.W_OVERLAY_1 = new StaticEntity(0 * 2, 0 * 2, Sprite.W_OVERLAY_1);
		StaticEntity.W_OVERLAY_2 = new StaticEntity(99 * 2, 0 * 2, Sprite.W_OVERLAY_2);
		StaticEntity.W_OVERLAY_3 = new StaticEntity(161 * 2, 0 * 2, Sprite.W_OVERLAY_3);
		StaticEntity.W_OVERLAY_4 = new StaticEntity(535 * 2, 0 * 2, Sprite.W_OVERLAY_4);

		StaticEntity.NW_UNDERLAY_1 = new StaticEntity(97 * 2, 134 * 2, Sprite.NW_UNDERLAY_1);
		StaticEntity.NW_UNDERLAY_2 = new StaticEntity(200 * 2, 134 * 2, Sprite.NW_UNDERLAY_2);
		StaticEntity.NW_UNDERLAY_3 = new StaticEntity(368 * 2, 134 * 2, Sprite.NW_UNDERLAY_3);
		StaticEntity.NW_UNDERLAY_4 = new StaticEntity(490 * 2, 134 * 2, Sprite.NW_UNDERLAY_4);
		StaticEntity.NW_UNDERLAY_5 = new StaticEntity(630 * 2, 189 * 2, Sprite.NW_UNDERLAY_5);
		StaticEntity.NW_UNDERLAY_6 = new StaticEntity(776 * 2, 105 * 2, Sprite.NW_UNDERLAY_6);
		StaticEntity.NW_UNDERLAY_7 = new StaticEntity(933 * 2, 36 * 2, Sprite.NW_UNDERLAY_7);
		StaticEntity.NW_UNDERLAY_8 = new StaticEntity(819 * 2, 251 * 2, Sprite.NW_UNDERLAY_8);
		StaticEntity.NW_UNDERLAY_9 = new StaticEntity(431 * 2, 365 * 2, Sprite.NW_UNDERLAY_9);
		StaticEntity.NW_UNDERLAY_10 = new StaticEntity(207 * 2, 476 * 2, Sprite.NW_UNDERLAY_10);
		StaticEntity.NW_UNDERLAY_11 = new StaticEntity(168 * 2, 730 * 2, Sprite.NW_UNDERLAY_11);
		StaticEntity.NW_UNDERLAY_12 = new StaticEntity(341 * 2, 688 * 2, Sprite.NW_UNDERLAY_12);
		StaticEntity.NW_UNDERLAY_13 = new StaticEntity(427 * 2, 637 * 2, Sprite.NW_UNDERLAY_13);
		StaticEntity.NW_UNDERLAY_14 = new StaticEntity(466 * 2, 637 * 2, Sprite.NW_UNDERLAY_14);
		StaticEntity.NW_UNDERLAY_15 = new StaticEntity(600 * 2, 516 * 2, Sprite.NW_UNDERLAY_15);
		StaticEntity.NW_UNDERLAY_16 = new StaticEntity(663 * 2, 544 * 2, Sprite.NW_UNDERLAY_16);
		StaticEntity.NW_UNDERLAY_17 = new StaticEntity(789 * 2, 515 * 2, Sprite.NW_UNDERLAY_17);
		StaticEntity.NW_UNDERLAY_18 = new StaticEntity(804 * 2, 544 * 2, Sprite.NW_UNDERLAY_18);
		StaticEntity.NW_UNDERLAY_19 = new StaticEntity(663 * 2, 685 * 2, Sprite.NW_UNDERLAY_19);
		StaticEntity.NW_UNDERLAY_20 = new StaticEntity(888 * 2, 677 * 2, Sprite.NW_UNDERLAY_20);
		StaticEntity.NW_UNDERLAY_21 = new StaticEntity(887 * 2, 778 * 2, Sprite.NW_UNDERLAY_21);
		StaticEntity.NW_UNDERLAY_22 = new StaticEntity(627 * 2, 824 * 2, Sprite.NW_UNDERLAY_22);
		StaticEntity.NW_UNDERLAY_23 = new StaticEntity(505 * 2, 952 * 2, Sprite.NW_UNDERLAY_23);
		StaticEntity.NW_UNDERLAY_24 = new StaticEntity(248 * 2, 602 * 2, Sprite.NW_UNDERLAY_24);
		StaticEntity.NW_UNDERLAY_25 = new StaticEntity(265 * 2, 730 * 2, Sprite.NW_UNDERLAY_25);
		StaticEntity.NW_UNDERLAY_26 = new StaticEntity(168 * 2, 584 * 2, Sprite.NW_UNDERLAY_26);
		StaticEntity.NW_OVERLAY_1 = new StaticEntity(0 * 2, 0 * 2, Sprite.NW_OVERLAY_1);
		StaticEntity.NW_OVERLAY_2 = new StaticEntity(0 * 2, 203 * 2, Sprite.NW_OVERLAY_2);
		StaticEntity.NW_OVERLAY_3 = new StaticEntity(162 * 2, 389 * 2, Sprite.NW_OVERLAY_3);
		StaticEntity.NW_OVERLAY_4 = new StaticEntity(162 * 2, 644 * 2, Sprite.NW_OVERLAY_4);
		StaticEntity.NW_OVERLAY_5 = new StaticEntity(162 * 2, 724 * 2, Sprite.NW_OVERLAY_5);
		StaticEntity.NW_OVERLAY_6 = new StaticEntity(326 * 2, 431 * 2, Sprite.NW_OVERLAY_6);
		StaticEntity.NW_OVERLAY_7 = new StaticEntity(402 * 2, 222 * 2, Sprite.NW_OVERLAY_7);
		StaticEntity.NW_OVERLAY_8 = new StaticEntity(611 * 2, 431 * 2, Sprite.NW_OVERLAY_8);
		StaticEntity.NW_OVERLAY_9 = new StaticEntity(632 * 2, 317 * 2, Sprite.NW_OVERLAY_9);
		StaticEntity.NW_OVERLAY_10 = new StaticEntity(813 * 2, 203 * 2, Sprite.NW_OVERLAY_10);
		StaticEntity.NW_OVERLAY_11 = new StaticEntity(300 * 2, 786 * 2, Sprite.NW_OVERLAY_11);
		StaticEntity.NW_OVERLAY_12 = new StaticEntity(472 * 2, 740 * 2, Sprite.NW_OVERLAY_12);
		StaticEntity.NW_OVERLAY_13 = new StaticEntity(637 * 2, 841 * 2, Sprite.NW_OVERLAY_13);

		StaticEntity.N_UNDERLAY_1 = new StaticEntity(0, 0, Sprite.N_UNDERLAY_1);
		StaticEntity.N_UNDERLAY_2 = new StaticEntity(503, 91, Sprite.N_UNDERLAY_2);
		StaticEntity.N_UNDERLAY_3 = new StaticEntity(1653, 0, Sprite.N_UNDERLAY_3);
		StaticEntity.N_UNDERLAY_4 = new StaticEntity(0, 917, Sprite.N_UNDERLAY_4);
		StaticEntity.N_OVERLAY_1 = new StaticEntity(0, 0, Sprite.N_OVERLAY_1);
		StaticEntity.N_OVERLAY_2 = new StaticEntity(715, 0, Sprite.N_OVERLAY_2);
		StaticEntity.N_OVERLAY_3 = new StaticEntity(1309, 0, Sprite.N_OVERLAY_3);
		StaticEntity.N_OVERLAY_4 = new StaticEntity(1279, 981, Sprite.N_OVERLAY_4);
		StaticEntity.N_OVERLAY_5 = new StaticEntity(1156, 1465, Sprite.N_OVERLAY_5);
		StaticEntity.N_OVERLAY_6 = new StaticEntity(715, 1727, Sprite.N_OVERLAY_6);

		StaticEntity.NE_UNDERLAY_1 = new StaticEntity(0, 0, Sprite.NE_UNDERLAY_1);
		StaticEntity.NE_UNDERLAY_2 = new StaticEntity(0, 300 * 2, Sprite.NE_UNDERLAY_2);
		StaticEntity.NE_UNDERLAY_3 = new StaticEntity(31 * 2, 421 * 2, Sprite.NE_UNDERLAY_3);
		StaticEntity.NE_UNDERLAY_4 = new StaticEntity(54 * 2, 580 * 2, Sprite.NE_UNDERLAY_4);
		StaticEntity.NE_UNDERLAY_5 = new StaticEntity(794 * 2, 0, Sprite.NE_UNDERLAY_5);
		StaticEntity.NE_OVERLAY_1 = new StaticEntity(0, 0, Sprite.NE_OVERLAY_1);
		StaticEntity.NE_OVERLAY_2 = new StaticEntity(0, 365 * 2, Sprite.NE_OVERLAY_2);
		StaticEntity.NE_OVERLAY_3 = new StaticEntity(775 * 2, 365 * 2, Sprite.NE_OVERLAY_3);

		StaticEntity.SE_UNDERLAY_1 = new StaticEntity(0, 0, Sprite.SE_UNDERLAY_1);
		StaticEntity.SE_OVERLAY_1 = new StaticEntity(0, 0, Sprite.SE_OVERLAY_1);

		StaticEntity.E_OVERLAY_1 = new StaticEntity(0, 0, Sprite.E_OVERLAY_1);
		StaticEntity.E_OVERLAY_2 = new StaticEntity(0, 1060, Sprite.E_OVERLAY_2);
		StaticEntity.E_OVERLAY_3 = new StaticEntity(282, 1888, Sprite.E_OVERLAY_3);
		StaticEntity.E_OVERLAY_4 = new StaticEntity(1378, 0, Sprite.E_OVERLAY_4);
		StaticEntity.E_UNDERLAY_1 = new StaticEntity(0, 160, Sprite.E_UNDERLAY_1);
		StaticEntity.E_UNDERLAY_2 = new StaticEntity(1579, 0, Sprite.E_UNDERLAY_2);

		StaticEntity.C_UNDERLAY_1 = new StaticEntity(0, 48, Sprite.C_UNDERLAY_1);
		StaticEntity.C_UNDERLAY_2 = new StaticEntity(1706, 276, Sprite.C_UNDERLAY_2);
		StaticEntity.C_UNDERLAY_3 = new StaticEntity(1472, 1894, Sprite.C_UNDERLAY_3);
		StaticEntity.C_OVERLAY_1 = new StaticEntity(0, 0, Sprite.C_OVERLAY_1);
		StaticEntity.C_OVERLAY_2 = new StaticEntity(1772, 0, Sprite.C_OVERLAY_2);
		StaticEntity.C_OVERLAY_3 = new StaticEntity(1832, 1010, Sprite.C_OVERLAY_3);
		StaticEntity.C_OVERLAY_4 = new StaticEntity(1424, 1788, Sprite.C_OVERLAY_4);

		StaticEntity.S_OVERLAY_1 = new StaticEntity(262, 336, Sprite.S_OVERLAY_1);
		StaticEntity.S_OVERLAY_2 = new StaticEntity(1258, 0, Sprite.S_OVERLAY_2);
		StaticEntity.S_OVERLAY_3 = new StaticEntity(1812, 858, Sprite.S_OVERLAY_3);
		StaticEntity.S_UNDERLAY_1 = new StaticEntity(314, 606, Sprite.S_UNDERLAY_1);
		StaticEntity.S_UNDERLAY_2 = new StaticEntity(1848, 1012, Sprite.S_UNDERLAY_2);

		StaticEntity.POS_MUSHROOM_HOUSE = new int[] { 4 * 64, 3 * 64, -64, -64 };
		StaticEntity.POS_CENTER_STORE = new int[] { 6 * 64, 3 * 64, 0, -64 };
		StaticEntity.POS_WOODS_STORE = new int[] { 4 * 64, 3 * 64, -64, -64 };
		StaticEntity.POS_STALL = new int[] { 6 * 64, 2 * 64, 0, -64 * 2 };
		StaticEntity.POS_SHROOM_DOOR = new int[] { 64, 64, -32, -64 };
		StaticEntity.POS_SHROOM = new int[] { 128, 64, 0, -27 };
		StaticEntity.POS_TREE_HOUSE = new int[] { 4 * 64, 3 * 64, -64, -64 };
		StaticEntity.POS_TREE = new int[] { 32, 32, -48, -160 };
		StaticEntity.POLE = new int[] { 16, 16, 0, -48 };
	}

	private static void loadSprites() {
		// tiles
		Sprite.DIRT_N = new Sprite(0, 0, Tile.TILE_S, Tile.TILE_S, SpriteSheet.TILES, "tile");
		Sprite.DIRT = new Sprite(0, 1 * 64, Tile.TILE_S, Tile.TILE_S, SpriteSheet.TILES, "tile");
		Sprite.DIRT_W = new Sprite(0, 2 * 64, Tile.TILE_S, Tile.TILE_S, SpriteSheet.TILES, "tile");
		Sprite.DIRT_E = new Sprite(0, 3 * 64, Tile.TILE_S, Tile.TILE_S, SpriteSheet.TILES, "tile");
		Sprite.DIRT_NW_CRUST = new Sprite(0, 4 * 64, Tile.TILE_S, Tile.TILE_S, SpriteSheet.TILES, "tile");
		Sprite.DIRT_NE_CRUST = new Sprite(0, 5 * 64, Tile.TILE_S, Tile.TILE_S, SpriteSheet.TILES, "tile");
		Sprite.DIRT_NE = new Sprite(0, 6 * 64, Tile.TILE_S, Tile.TILE_S, SpriteSheet.TILES, "tile");
		Sprite.DIRT_NW = new Sprite(0, 7 * 64, Tile.TILE_S, Tile.TILE_S, SpriteSheet.TILES, "tile");
		Sprite.BRUSH = new Sprite(0, 8 * 64, Tile.TILE_S, Tile.TILE_S, SpriteSheet.TILES, "tile");
		Sprite.DIRT_STAIRS = new Sprite(0, 9 * 64, Tile.TILE_S, Tile.TILE_S, SpriteSheet.TILES, "tile");

		Sprite.MushRoomFloor = new Sprite(2 * 64, 0, Tile.TILE_S, Tile.TILE_S, SpriteSheet.TILES, "tile");
		Sprite.FLOOR = new Sprite(2 * 64, 1 * 64, Tile.TILE_S, Tile.TILE_S, SpriteSheet.TILES, "tile");
		Sprite.WALL_W = new Sprite(2 * 64, 2 * 64, Tile.TILE_S, Tile.TILE_S, SpriteSheet.TILES, "tile");
		Sprite.WALL_E = new Sprite(2 * 64, 3 * 64, Tile.TILE_S, Tile.TILE_S, SpriteSheet.TILES, "tile");
		Sprite.CEILING = new Sprite(2 * 64, 4 * 64, Tile.TILE_S, Tile.TILE_S, SpriteSheet.TILES, "tile");
		Sprite.WALL_SW_CRUST = new Sprite(2 * 64, 5 * 64, Tile.TILE_S, Tile.TILE_S, SpriteSheet.TILES, "tile");
		Sprite.WALL_SE_CRUST = new Sprite(2 * 64, 6 * 64, Tile.TILE_S, Tile.TILE_S, SpriteSheet.TILES, "tile");
		Sprite.WALL_SW = new Sprite(2 * 64, 7 * 64, Tile.TILE_S, Tile.TILE_S, SpriteSheet.TILES, "tile");
		Sprite.WALL_SE = new Sprite(2 * 64, 8 * 64, Tile.TILE_S, Tile.TILE_S, SpriteSheet.TILES, "tile");
		Sprite.VOID = new Sprite(2 * 64, 9 * 64, Tile.TILE_S, Tile.TILE_S, SpriteSheet.TILES, "tile");

		Sprite.FOUNTAIN_1 = new Sprite(0, 0, 320, 256, SpriteSheet.FOUNTAIN, "fountain");
		Sprite.FOUNTAIN_2 = new Sprite(0, 256, 320, 256, SpriteSheet.FOUNTAIN, "fountain");
		Sprite.FOUNTAIN_3 = new Sprite(0, 256 * 2, 320, 256, SpriteSheet.FOUNTAIN, "fountain");

		Sprite.CAVE_UNDERLAY_1 = new Sprite(0, 0, 448, 320, SpriteSheet.CAVE_UNDERLAY, "lay");
		Sprite.VOLCANO_UNDERLAY_1 = new Sprite(0, 0, 512, 640, SpriteSheet.VOLCANO_UNDERLAY, "lay");
		Sprite.LAVA_1 = new Sprite(0, 0, 320, 131, SpriteSheet.LAVA, "lay");
		Sprite.LAVA_2 = new Sprite(0, 131, 320, 131, SpriteSheet.LAVA, "lay");
		Sprite.LAVA_3 = new Sprite(0, 131 * 2, 320, 131, SpriteSheet.LAVA, "lay");
		Sprite.LAVA_4 = new Sprite(0, 131 * 3, 320, 131, SpriteSheet.LAVA, "lay");
		Sprite.FARM_UNDERLAY_1 = new Sprite(0, 0, 704, 512, SpriteSheet.FARM_UNDERLAY, "lay");

		Sprite.ROOM_ONION_UNDERLAY = new Sprite(0, 0, 576, 640, SpriteSheet.ROOM_ONION_UNDERLAY, "lay");

		Sprite.W_UNDERLAY_1 = new Sprite(0, 196, 154, 764, SpriteSheet.MAIN_W_UNDERLAY, "lay");
		Sprite.W_UNDERLAY_2 = new Sprite(156, 130, 124, 206, SpriteSheet.MAIN_W_UNDERLAY, "lay");
		Sprite.W_UNDERLAY_3 = new Sprite(278, 50, 368, 162, SpriteSheet.MAIN_W_UNDERLAY, "lay");
		Sprite.W_UNDERLAY_4 = new Sprite(646, 0, 268, 150, SpriteSheet.MAIN_W_UNDERLAY, "lay");
		Sprite.W_UNDERLAY_5 = new Sprite(992, 0, 276, 146, SpriteSheet.MAIN_W_UNDERLAY, "lay");
		Sprite.W_UNDERLAY_6 = new Sprite(1268, 42, 780, 176, SpriteSheet.MAIN_W_UNDERLAY, "lay");
		Sprite.W_OVERLAY_1 = new Sprite(0, 0, 198, 856, SpriteSheet.MAIN_W_OVERLAY, "lay");
		Sprite.W_OVERLAY_2 = new Sprite(198, 0, 124, 216, SpriteSheet.MAIN_W_OVERLAY, "lay");
		Sprite.W_OVERLAY_3 = new Sprite(322, 0, 504, 122, SpriteSheet.MAIN_W_OVERLAY, "lay");
		Sprite.W_OVERLAY_4 = new Sprite(1070, 0, 980, 138, SpriteSheet.MAIN_W_OVERLAY, "lay");

		Sprite.NW_UNDERLAY_1 = new Sprite(194, 268, 206, 544, SpriteSheet.MAIN_NW_UNDERLAY, "lay");
		Sprite.NW_UNDERLAY_2 = new Sprite(400, 268, 336, 334, SpriteSheet.MAIN_NW_UNDERLAY, "lay");
		Sprite.NW_UNDERLAY_3 = new Sprite(736, 268, 244, 262, SpriteSheet.MAIN_NW_UNDERLAY, "lay");
		Sprite.NW_UNDERLAY_4 = new Sprite(980, 268, 280, 202, SpriteSheet.MAIN_NW_UNDERLAY, "lay");
		Sprite.NW_UNDERLAY_5 = new Sprite(1260, 378, 292, 164, SpriteSheet.MAIN_NW_UNDERLAY, "lay");
		Sprite.NW_UNDERLAY_6 = new Sprite(1552, 210, 314, 254, SpriteSheet.MAIN_NW_UNDERLAY, "lay");
		Sprite.NW_UNDERLAY_7 = new Sprite(1866, 72, 182, 186, SpriteSheet.MAIN_NW_UNDERLAY, "lay");
		Sprite.NW_UNDERLAY_8 = new Sprite(1638, 502, 52, 70, SpriteSheet.MAIN_NW_UNDERLAY, "lay");
		Sprite.NW_UNDERLAY_9 = new Sprite(862, 730, 100, 88, SpriteSheet.MAIN_NW_UNDERLAY, "lay");
		Sprite.NW_UNDERLAY_10 = new Sprite(414, 952, 114, 156, SpriteSheet.MAIN_NW_UNDERLAY, "lay");
		Sprite.NW_UNDERLAY_11 = new Sprite(336, 1460, 152, 134, SpriteSheet.MAIN_NW_UNDERLAY, "lay");
		Sprite.NW_UNDERLAY_12 = new Sprite(682, 1376, 176, 172, SpriteSheet.MAIN_NW_UNDERLAY, "lay");
		Sprite.NW_UNDERLAY_13 = new Sprite(854, 1274, 78, 236, SpriteSheet.MAIN_NW_UNDERLAY, "lay");
		Sprite.NW_UNDERLAY_14 = new Sprite(932, 1274, 240, 152, SpriteSheet.MAIN_NW_UNDERLAY, "lay");
		Sprite.NW_UNDERLAY_15 = new Sprite(1200, 1032, 126, 198, SpriteSheet.MAIN_NW_UNDERLAY, "lay");
		Sprite.NW_UNDERLAY_16 = new Sprite(1326, 1088, 282, 194, SpriteSheet.MAIN_NW_UNDERLAY, "lay");
		Sprite.NW_UNDERLAY_17 = new Sprite(1578, 1030, 126, 58, SpriteSheet.MAIN_NW_UNDERLAY, "lay");
		Sprite.NW_UNDERLAY_18 = new Sprite(1608, 1088, 224, 148, SpriteSheet.MAIN_NW_UNDERLAY, "lay");
		Sprite.NW_UNDERLAY_19 = new Sprite(1326, 1370, 298, 62, SpriteSheet.MAIN_NW_UNDERLAY, "lay");
		Sprite.NW_UNDERLAY_20 = new Sprite(1776, 1354, 88, 70, SpriteSheet.MAIN_NW_UNDERLAY, "lay");
		Sprite.NW_UNDERLAY_21 = new Sprite(1174, 1556, 88, 56, SpriteSheet.MAIN_NW_UNDERLAY, "lay");
		Sprite.NW_UNDERLAY_22 = new Sprite(1254, 1648, 88, 66, SpriteSheet.MAIN_NW_UNDERLAY, "lay");
		Sprite.NW_UNDERLAY_23 = new Sprite(1010, 1904, 74, 154, SpriteSheet.MAIN_NW_UNDERLAY, "lay");
		Sprite.NW_UNDERLAY_24 = new Sprite(496, 1204, 186, 228, SpriteSheet.MAIN_NW_UNDERLAY, "lay");
		Sprite.NW_UNDERLAY_25 = new Sprite(530, 1460, 152, 134, SpriteSheet.MAIN_NW_UNDERLAY, "lay");
		Sprite.NW_UNDERLAY_26 = new Sprite(336, 1168, 72, 82, SpriteSheet.MAIN_NW_UNDERLAY, "lay");
		Sprite.NW_OVERLAY_1 = new Sprite(0, 0, 2048, 406, SpriteSheet.MAIN_NW_OVERLAY, "lay");
		Sprite.NW_OVERLAY_2 = new Sprite(0, 406, 324, 1642, SpriteSheet.MAIN_NW_OVERLAY, "lay");
		Sprite.NW_OVERLAY_3 = new Sprite(324, 778, 328, 510, SpriteSheet.MAIN_NW_OVERLAY, "lay");
		Sprite.NW_OVERLAY_4 = new Sprite(324, 1288, 142, 160, SpriteSheet.MAIN_NW_OVERLAY, "lay");
		Sprite.NW_OVERLAY_5 = new Sprite(324, 1448, 276, 600, SpriteSheet.MAIN_NW_OVERLAY, "lay");
		Sprite.NW_OVERLAY_6 = new Sprite(652, 862, 570, 574, SpriteSheet.MAIN_NW_OVERLAY, "lay");
		Sprite.NW_OVERLAY_7 = new Sprite(804, 444, 460, 418, SpriteSheet.MAIN_NW_OVERLAY, "lay");
		Sprite.NW_OVERLAY_8 = new Sprite(1222, 862, 42, 238, SpriteSheet.MAIN_NW_OVERLAY, "lay");
		Sprite.NW_OVERLAY_9 = new Sprite(1264, 634, 362, 506, SpriteSheet.MAIN_NW_OVERLAY, "lay");
		Sprite.NW_OVERLAY_10 = new Sprite(1626, 406, 422, 1642, SpriteSheet.MAIN_NW_OVERLAY, "lay");
		Sprite.NW_OVERLAY_11 = new Sprite(600, 1572, 330, 476, SpriteSheet.MAIN_NW_OVERLAY, "lay");
		Sprite.NW_OVERLAY_12 = new Sprite(944, 1480, 330, 568, SpriteSheet.MAIN_NW_OVERLAY, "lay");
		Sprite.NW_OVERLAY_13 = new Sprite(1274, 1682, 352, 366, SpriteSheet.MAIN_NW_OVERLAY, "lay");

		Sprite.N_UNDERLAY_1 = new Sprite(0, 0, 400, 918, SpriteSheet.MAIN_N_UNDERLAY, "lay");
		Sprite.N_UNDERLAY_2 = new Sprite(503, 91, 1019, 827, SpriteSheet.MAIN_N_UNDERLAY, "lay");
		Sprite.N_UNDERLAY_3 = new Sprite(1653, 0, 395, 918, SpriteSheet.MAIN_N_UNDERLAY, "lay");
		Sprite.N_UNDERLAY_4 = new Sprite(0, 917, 2048, 824, SpriteSheet.MAIN_N_UNDERLAY, "lay");
		Sprite.N_OVERLAY_1 = new Sprite(0, 0, 715, 2048, SpriteSheet.MAIN_N_OVERLAY, "lay");
		Sprite.N_OVERLAY_2 = new Sprite(715, 0, 594, 197, SpriteSheet.MAIN_N_OVERLAY, "lay");
		Sprite.N_OVERLAY_3 = new Sprite(1309, 0, 739, 2048, SpriteSheet.MAIN_N_OVERLAY, "lay");
		Sprite.N_OVERLAY_4 = new Sprite(1279, 981, 30, 122, SpriteSheet.MAIN_N_OVERLAY, "lay");
		Sprite.N_OVERLAY_5 = new Sprite(1156, 1465, 153, 170, SpriteSheet.MAIN_N_OVERLAY, "lay");
		Sprite.N_OVERLAY_6 = new Sprite(715, 1727, 594, 321, SpriteSheet.MAIN_N_OVERLAY, "lay");

		Sprite.NE_UNDERLAY_1 = new Sprite(0, 0, 794 * 2, 135 * 2, SpriteSheet.MAIN_NE_UNDERLAY, "lay");
		Sprite.NE_UNDERLAY_2 = new Sprite(0, 300 * 2, 794 * 2, 121 * 2, SpriteSheet.MAIN_NE_UNDERLAY, "lay");
		Sprite.NE_UNDERLAY_3 = new Sprite(31 * 2, 421 * 2, 70 * 2, 35 * 2, SpriteSheet.MAIN_NE_UNDERLAY, "lay");
		Sprite.NE_UNDERLAY_4 = new Sprite(54 * 2, 580 * 2, 48 * 2, 182 * 2, SpriteSheet.MAIN_NE_UNDERLAY, "lay");
		Sprite.NE_UNDERLAY_5 = new Sprite(794 * 2, 0, 230 * 2, 932 * 2, SpriteSheet.MAIN_NE_UNDERLAY, "lay");
		Sprite.NE_OVERLAY_1 = new Sprite(0, 0, 1024 * 2, 365 * 2, SpriteSheet.MAIN_NE_OVERLAY, "lay");
		Sprite.NE_OVERLAY_2 = new Sprite(0, 365 * 2, 126 * 2, 659 * 2, SpriteSheet.MAIN_NE_OVERLAY, "lay");
		Sprite.NE_OVERLAY_3 = new Sprite(775 * 2, 365 * 2, 249 * 2, 659 * 2, SpriteSheet.MAIN_NE_OVERLAY, "lay");

		Sprite.SE_UNDERLAY_1 = new Sprite(0, 0, 2048, 1152, SpriteSheet.MAIN_SE_UNDERLAY, "lay");
		Sprite.SE_OVERLAY_1 = new Sprite(0, 0, 2048, 1004, SpriteSheet.MAIN_SE_OVERLAY, "lay");

		Sprite.E_OVERLAY_1 = new Sprite(0, 0, 246, 892, SpriteSheet.MAIN_E_OVERLAY, "lay");
		Sprite.E_OVERLAY_2 = new Sprite(0, 1060, 282, 988, SpriteSheet.MAIN_E_OVERLAY, "lay");
		Sprite.E_OVERLAY_3 = new Sprite(282, 1888, 1096, 160, SpriteSheet.MAIN_E_OVERLAY, "lay");
		Sprite.E_OVERLAY_4 = new Sprite(1378, 0, 670, 2048, SpriteSheet.MAIN_E_OVERLAY, "lay");
		Sprite.E_UNDERLAY_1 = new Sprite(0, 160, 206, 1386, SpriteSheet.MAIN_E_UNDERLAY, "lay");
		Sprite.E_UNDERLAY_2 = new Sprite(1579, 0, 469, 1759, SpriteSheet.MAIN_E_UNDERLAY, "lay");

		Sprite.C_UNDERLAY_1 = new Sprite(0, 48, 1862, 288, SpriteSheet.MAIN_C_UNDERLAY, "lay");
		Sprite.C_UNDERLAY_2 = new Sprite(1706, 276, 342, 698, SpriteSheet.MAIN_C_UNDERLAY, "lay");
		Sprite.C_UNDERLAY_3 = new Sprite(1472, 1894, 120, 154, SpriteSheet.MAIN_C_UNDERLAY, "lay");
		Sprite.C_OVERLAY_1 = new Sprite(0, 0, 1772, 212, SpriteSheet.MAIN_C_OVERLAY, "lay");
		Sprite.C_OVERLAY_2 = new Sprite(1772, 0, 276, 854, SpriteSheet.MAIN_C_OVERLAY, "lay");
		Sprite.C_OVERLAY_3 = new Sprite(1832, 1010, 216, 778, SpriteSheet.MAIN_C_OVERLAY, "lay");
		Sprite.C_OVERLAY_4 = new Sprite(1424, 1788, 624, 260, SpriteSheet.MAIN_C_OVERLAY, "lay");

		Sprite.S_OVERLAY_1 = new Sprite(262, 336, 996, 456, SpriteSheet.MAIN_S_OVERLAY, "lay");
		Sprite.S_OVERLAY_2 = new Sprite(1258, 0, 790, 854, SpriteSheet.MAIN_S_OVERLAY, "lay");
		Sprite.S_OVERLAY_3 = new Sprite(1812, 858, 194, 231, SpriteSheet.MAIN_S_OVERLAY, "lay");
		Sprite.S_UNDERLAY_1 = new Sprite(314, 606, 1734, 372, SpriteSheet.MAIN_S_UNDERLAY, "lay");
		Sprite.S_UNDERLAY_2 = new Sprite(1848, 1012, 200, 140, SpriteSheet.MAIN_S_UNDERLAY, "lay");

		// water
		Sprite.CLIFF = new Sprite[] { //
				new Sprite(1 * 64, 0 * 64, Tile.TILE_S, Tile.TILE_S, SpriteSheet.TILES, "tile"),
				new Sprite(1 * 64, 1 * 64, Tile.TILE_S, Tile.TILE_S, SpriteSheet.TILES, "tile"),
				new Sprite(1 * 64, 2 * 64, Tile.TILE_S, Tile.TILE_S, SpriteSheet.TILES, "tile"),
				new Sprite(1 * 64, 3 * 64, Tile.TILE_S, Tile.TILE_S, SpriteSheet.TILES, "tile"), };

		Sprite.WATER = new Sprite[] { //
				new Sprite(1 * 64, 4 * 64, Tile.TILE_S, Tile.TILE_S, SpriteSheet.TILES, "tile"),
				new Sprite(1 * 64, 5 * 64, Tile.TILE_S, Tile.TILE_S, SpriteSheet.TILES, "tile"),
				new Sprite(1 * 64, 6 * 64, Tile.TILE_S, Tile.TILE_S, SpriteSheet.TILES, "tile"),
				new Sprite(1 * 64, 7 * 64, Tile.TILE_S, Tile.TILE_S, SpriteSheet.TILES, "tile"), };

		Sprite.WATER_E = new Sprite[] { //
				new Sprite(1 * 64, 8 * 64, Tile.TILE_S, Tile.TILE_S, SpriteSheet.TILES, "tile"),
				new Sprite(1 * 64, 9 * 64, Tile.TILE_S, Tile.TILE_S, SpriteSheet.TILES, "tile"),
				new Sprite(1 * 64, 10 * 64, Tile.TILE_S, Tile.TILE_S, SpriteSheet.TILES, "tile"),
				new Sprite(1 * 64, 11 * 64, Tile.TILE_S, Tile.TILE_S, SpriteSheet.TILES, "tile"), };

		Sprite.WATER_W = new Sprite[] { //
				new Sprite(1 * 64, 12 * 64, Tile.TILE_S, Tile.TILE_S, SpriteSheet.TILES, "tile"),
				new Sprite(1 * 64, 13 * 64, Tile.TILE_S, Tile.TILE_S, SpriteSheet.TILES, "tile"),
				new Sprite(1 * 64, 14 * 64, Tile.TILE_S, Tile.TILE_S, SpriteSheet.TILES, "tile"),
				new Sprite(1 * 64, 15 * 64, Tile.TILE_S, Tile.TILE_S, SpriteSheet.TILES, "tile"), };

		// player
		Sprite.PLAYER_IDLE_F = new Sprite[] {
				new Sprite(0, 1 * 64, Entity.PLAYER_S, Entity.PLAYER_S, SpriteSheet.PLAYER, ""),
				new Sprite(0, 0, Entity.PLAYER_S, Entity.PLAYER_S, SpriteSheet.PLAYER, ""),
				new Sprite(0, 1 * 64, Entity.PLAYER_S, Entity.PLAYER_S, SpriteSheet.PLAYER, ""),
				new Sprite(0, 2 * 64, Entity.PLAYER_S, Entity.PLAYER_S, SpriteSheet.PLAYER, ""), };

		Sprite.PLAYER_IDLE_R = new Sprite[] {
				new Sprite(0, 4 * 64, Entity.PLAYER_S, Entity.PLAYER_S, SpriteSheet.PLAYER, ""),
				new Sprite(0, 3 * 64, Entity.PLAYER_S, Entity.PLAYER_S, SpriteSheet.PLAYER, ""),
				new Sprite(0, 4 * 64, Entity.PLAYER_S, Entity.PLAYER_S, SpriteSheet.PLAYER, ""),
				new Sprite(0, 5 * 64, Entity.PLAYER_S, Entity.PLAYER_S, SpriteSheet.PLAYER, ""), };

		Sprite.PLAYER_IDLE_L = new Sprite[] {
				new Sprite(0, 7 * 64, Entity.PLAYER_S, Entity.PLAYER_S, SpriteSheet.PLAYER, ""),
				new Sprite(0, 6 * 64, Entity.PLAYER_S, Entity.PLAYER_S, SpriteSheet.PLAYER, ""),
				new Sprite(0, 7 * 64, Entity.PLAYER_S, Entity.PLAYER_S, SpriteSheet.PLAYER, ""),
				new Sprite(0, 8 * 64, Entity.PLAYER_S, Entity.PLAYER_S, SpriteSheet.PLAYER, ""), };

		Sprite.PLAYER_IDLE_B = new Sprite[] {
				new Sprite(0, 10 * 64, Entity.PLAYER_S, Entity.PLAYER_S, SpriteSheet.PLAYER, ""),
				new Sprite(0, 9 * 64, Entity.PLAYER_S, Entity.PLAYER_S, SpriteSheet.PLAYER, ""),
				new Sprite(0, 10 * 64, Entity.PLAYER_S, Entity.PLAYER_S, SpriteSheet.PLAYER, ""),
				new Sprite(0, 11 * 64, Entity.PLAYER_S, Entity.PLAYER_S, SpriteSheet.PLAYER, ""), };

		Sprite.PLAYER_WALK_F = new Sprite[] {
				new Sprite(1 * 64, 1 * 64, Entity.PLAYER_S, Entity.PLAYER_S, SpriteSheet.PLAYER, ""),
				new Sprite(1 * 64, 0, Entity.PLAYER_S, Entity.PLAYER_S, SpriteSheet.PLAYER, ""),
				new Sprite(1 * 64, 1 * 64, Entity.PLAYER_S, Entity.PLAYER_S, SpriteSheet.PLAYER, ""),
				new Sprite(1 * 64, 2 * 64, Entity.PLAYER_S, Entity.PLAYER_S, SpriteSheet.PLAYER, ""), };

		Sprite.PLAYER_WALK_R = new Sprite[] {
				new Sprite(1 * 64, 4 * 64, Entity.PLAYER_S, Entity.PLAYER_S, SpriteSheet.PLAYER, ""),
				new Sprite(1 * 64, 3 * 64, Entity.PLAYER_S, Entity.PLAYER_S, SpriteSheet.PLAYER, ""),
				new Sprite(1 * 64, 4 * 64, Entity.PLAYER_S, Entity.PLAYER_S, SpriteSheet.PLAYER, ""),
				new Sprite(1 * 64, 5 * 64, Entity.PLAYER_S, Entity.PLAYER_S, SpriteSheet.PLAYER, ""), };

		Sprite.PLAYER_WALK_L = new Sprite[] {
				new Sprite(1 * 64, 7 * 64, Entity.PLAYER_S, Entity.PLAYER_S, SpriteSheet.PLAYER, ""),
				new Sprite(1 * 64, 6 * 64, Entity.PLAYER_S, Entity.PLAYER_S, SpriteSheet.PLAYER, ""),
				new Sprite(1 * 64, 7 * 64, Entity.PLAYER_S, Entity.PLAYER_S, SpriteSheet.PLAYER, ""),
				new Sprite(1 * 64, 8 * 64, Entity.PLAYER_S, Entity.PLAYER_S, SpriteSheet.PLAYER, ""), };

		Sprite.PLAYER_WALK_B = new Sprite[] {
				new Sprite(1 * 64, 10 * 64, Entity.PLAYER_S, Entity.PLAYER_S, SpriteSheet.PLAYER, ""),
				new Sprite(1 * 64, 9 * 64, Entity.PLAYER_S, Entity.PLAYER_S, SpriteSheet.PLAYER, ""),
				new Sprite(1 * 64, 10 * 64, Entity.PLAYER_S, Entity.PLAYER_S, SpriteSheet.PLAYER, ""),
				new Sprite(1 * 64, 11 * 64, Entity.PLAYER_S, Entity.PLAYER_S, SpriteSheet.PLAYER, ""), };

		// garlic1
		Sprite.GARLIC1_IDLE_R = new Sprite[] {
				new Sprite(0, 64, Entity.GARLIC1_W, Entity.GARLIC1_H, SpriteSheet.GARLIC, ""),
				new Sprite(0, 0, Entity.GARLIC1_W, Entity.GARLIC1_H, SpriteSheet.GARLIC, ""),
				new Sprite(0, 64, Entity.GARLIC1_W, Entity.GARLIC1_H, SpriteSheet.GARLIC, ""),
				new Sprite(0, 128, Entity.GARLIC1_W, Entity.GARLIC1_H, SpriteSheet.GARLIC, ""), };

		Sprite.GARLIC1_IDLE_L = new Sprite[] {
				new Sprite(0, 192 + 64, Entity.GARLIC1_W, Entity.GARLIC1_H, SpriteSheet.GARLIC, ""),
				new Sprite(0, 192, Entity.GARLIC1_W, Entity.GARLIC1_H, SpriteSheet.GARLIC, ""),
				new Sprite(0, 192 + 64, Entity.GARLIC1_W, Entity.GARLIC1_H, SpriteSheet.GARLIC, ""),
				new Sprite(0, 192 + 128, Entity.GARLIC1_W, Entity.GARLIC1_H, SpriteSheet.GARLIC, ""), };

		Sprite.GARLIC1_WALK_R = new Sprite[] {
				new Sprite(32, 64, Entity.GARLIC1_W, Entity.GARLIC1_H, SpriteSheet.GARLIC, ""),
				new Sprite(32, 0, Entity.GARLIC1_W, Entity.GARLIC1_H, SpriteSheet.GARLIC, ""),
				new Sprite(32, 64, Entity.GARLIC1_W, Entity.GARLIC1_H, SpriteSheet.GARLIC, ""),
				new Sprite(32, 128, Entity.GARLIC1_W, Entity.GARLIC1_H, SpriteSheet.GARLIC, ""), };

		Sprite.GARLIC1_WALK_L = new Sprite[] {
				new Sprite(32, 192 + 64, Entity.GARLIC1_W, Entity.GARLIC1_H, SpriteSheet.GARLIC, ""),
				new Sprite(32, 192, Entity.GARLIC1_W, Entity.GARLIC1_H, SpriteSheet.GARLIC, ""),
				new Sprite(32, 192 + 64, Entity.GARLIC1_W, Entity.GARLIC1_H, SpriteSheet.GARLIC, ""),
				new Sprite(32, 192 + 128, Entity.GARLIC1_W, Entity.GARLIC1_H, SpriteSheet.GARLIC, ""), };

		// garlic
		Sprite.EGGPLANT_IDLE_F = new Sprite[] {
				new Sprite(0, 128, Entity.EGGPLANT_W, Entity.EGGPLANT_H, SpriteSheet.EGGPLANT, ""),
				new Sprite(0, 0, Entity.EGGPLANT_W, Entity.EGGPLANT_H, SpriteSheet.EGGPLANT, ""),
				new Sprite(0, 128, Entity.EGGPLANT_W, Entity.EGGPLANT_H, SpriteSheet.EGGPLANT, ""),
				new Sprite(0, 128 * 2, Entity.EGGPLANT_W, Entity.EGGPLANT_H, SpriteSheet.EGGPLANT, ""), };

		// corn
		Sprite.CORN_SLEEP_CHAIR = new Sprite[] { new Sprite(0, 0, Entity.CORN_W, Entity.CORN_H, SpriteSheet.CORN, ""),
				new Sprite(0, 128, Entity.CORN_W, Entity.CORN_H, SpriteSheet.CORN, ""),
				new Sprite(0, 128 * 2, Entity.CORN_W, Entity.CORN_H, SpriteSheet.CORN, ""), };
		Sprite.CORN_AWAKE_CHAIR = new Sprite[] {
				new Sprite(0, 384 + 0, Entity.CORN_W, Entity.CORN_H, SpriteSheet.CORN, ""),
				new Sprite(0, 384 + 128, Entity.CORN_W, Entity.CORN_H, SpriteSheet.CORN, ""),
				new Sprite(0, 384 + 128 * 2, Entity.CORN_W, Entity.CORN_H, SpriteSheet.CORN, ""), };

		// plants
		Sprite.SHROOM_DOOR = new Sprite(0, 0, 128, 128, SpriteSheet.PLANTS, "plant");
		Sprite.SHROOM_1 = new Sprite(0, 128, 128, 91, SpriteSheet.PLANTS, "plant");
		Sprite.SHROOM_2 = new Sprite(0, 128 + 91, 128, 91, SpriteSheet.PLANTS, "plant");
		Sprite.SHROOM_3 = new Sprite(0, 128 + 91 * 2, 128, 91, SpriteSheet.PLANTS, "plant");
		Sprite.TREE = new Sprite(128, 0, 128, 192, SpriteSheet.PLANTS, "plant");

		// buildings
		Sprite.HOUSE_GARLIC = new Sprite(0, 0, 384, 256, SpriteSheet.BUILDINGS, "building");
		Sprite.HOUSE_ONION = new Sprite(0, 256, 384, 256, SpriteSheet.BUILDINGS, "building");
		Sprite.HOUSE_CARROT = new Sprite(0, 256 * 2, 384, 256, SpriteSheet.BUILDINGS, "building");
		Sprite.HOUSE_CORN = new Sprite(0, 256 * 3, 384, 256, SpriteSheet.BUILDINGS, "building");
		Sprite.HOUSE_LETTUCE = new Sprite(0, 256 * 4, 384, 256, SpriteSheet.BUILDINGS, "building");
		Sprite.HOUSE_EGGPLANT = new Sprite(0, 256 * 5, 384, 256, SpriteSheet.BUILDINGS, "building");

		Sprite.HOUSE_CHERRY = new Sprite(384, 0, 384, 288, SpriteSheet.BUILDINGS, "building");
		Sprite.HOUSE_WATERMELON = new Sprite(384, 288, 384, 288, SpriteSheet.BUILDINGS, "building");
		Sprite.HOUSE_PEAR = new Sprite(384, 288 * 2, 384, 288, SpriteSheet.BUILDINGS, "building");
		Sprite.HOUSE_RASPBERRY = new Sprite(384, 288 * 3, 384, 288, SpriteSheet.BUILDINGS, "building");
		Sprite.HOUSE_BANANA = new Sprite(384, 288 * 4, 384, 288, SpriteSheet.BUILDINGS, "building");
		Sprite.HOUSE_MANGO = new Sprite(384, 288 * 5, 384, 288, SpriteSheet.BUILDINGS, "building");

		Sprite.STALL_MUSH = new Sprite(384 * 2, 0, 384, 256, SpriteSheet.BUILDINGS, "building");
		Sprite.STALL_FISH = new Sprite(384 * 2, 256, 384, 256, SpriteSheet.BUILDINGS, "building");
		Sprite.SCHOOL = new Sprite(384 * 2, 256 * 2, 384, 256, SpriteSheet.BUILDINGS, "building");
		Sprite.GYM = new Sprite(384 * 2, 256 * 3, 384, 256, SpriteSheet.BUILDINGS, "building");
		Sprite.LIBRARY = new Sprite(384 * 2, 256 * 4, 384, 256, SpriteSheet.BUILDINGS, "building");
		Sprite.STOCKHOUSE = new Sprite(384 * 2, 256 * 5, 384, 256, SpriteSheet.BUILDINGS, "building");
		Sprite.CLOTHES = new Sprite(384 * 2, 256 * 6, 384, 256, SpriteSheet.BUILDINGS, "building");
		Sprite.SALON = new Sprite(384 * 2, 256 * 7, 384, 256, SpriteSheet.BUILDINGS, "building");

		// menu
		Sprite.mainTextBG = new Sprite(0, 96, 480, 135, SpriteSheet.menuETC, "nonGrid");
		Sprite.menuMainBG = new Sprite(0, 256, 480 / 3, 270, SpriteSheet.menuETC, "nonGrid");
		Sprite.menuMainBGUnselected = new Sprite(480, 256, 480 / 3, 270, SpriteSheet.menuETC, "nonGrid");
		Sprite.menuStatsBG = new Sprite(480 / 3, 256, 480 / 3 * 2, 270, SpriteSheet.menuETC, "nonGrid");

		// items
		Sprite.unselected = new Sprite(0, 0, 64, 64, SpriteSheet.ITEMS, "item");
		Sprite.selected = new Sprite(0, 64, 64, 64, SpriteSheet.ITEMS, "item");
		Sprite.mushCube = new Sprite(0, 64 * 2, 64, 64, SpriteSheet.ITEMS, "item");
		Sprite.jellyDisc = new Sprite(0, 64 * 4, 64, 64, SpriteSheet.ITEMS, "item");
		Sprite.rubbish = new Sprite(0, 64 * 3, 64, 64, SpriteSheet.ITEMS, "item");

		Sprite.fruitchip = new Sprite(64, 0, 32, 32, SpriteSheet.ITEMS, "item");
		Sprite.pole = new Sprite(64, 32, 320, 258, SpriteSheet.ITEMS, "item");
		Sprite.groundPole = new Sprite(368, 32, 16, 64, SpriteSheet.ITEMS, "item");
		Sprite.noPole = new Sprite(384, 32, 16, 64, SpriteSheet.ITEMS, "item");

		// projectiles
		Sprite.spray = new Sprite(0, 0, 16, 16, SpriteSheet.PROJECTILES, "projectile");
		Sprite.stink = new Sprite(0, 16, 16, 16, SpriteSheet.PROJECTILES, "projectile");

		Sprite.FISHING_SPOT = new Sprite[] { new Sprite(16, 0, 64, 48, SpriteSheet.PROJECTILES, ""),
				new Sprite(16, 48, 64, 48, SpriteSheet.PROJECTILES, ""),
				new Sprite(16, 48 * 2, 64, 48, SpriteSheet.PROJECTILES, ""), };
	}

	private static void loadSpriteSheet() {

		SpriteSheet.TILES = new SpriteSheet("tiles");

		SpriteSheet.GARLIC = new SpriteSheet("garlic");
		SpriteSheet.EGGPLANT = new SpriteSheet("eggplant");
		SpriteSheet.CORN = new SpriteSheet("corn");

		SpriteSheet.PLAYER = new SpriteSheet("player");
		SpriteSheet.PLANTS = new SpriteSheet("plants");
		SpriteSheet.BUILDINGS = new SpriteSheet("buildings");
		SpriteSheet.FOUNTAIN = new SpriteSheet("fountain");

		SpriteSheet.ROOM_ONION = new SpriteSheet("roomOnion");
		SpriteSheet.ROOM_ONION_UNDERLAY = new SpriteSheet("roomOnion_underlay");
		// SpriteSheet.ROOM_GARLIC = new SpriteSheet("roomGarlic");
		// SpriteSheet.ROOM_LETTUCE = new SpriteSheet("roomLettuce");
		// SpriteSheet.ROOM_CARROT = new SpriteSheet("roomCarrot");
		// SpriteSheet.ROOM_EGGPLANT = new SpriteSheet("roomEggplant");
		// SpriteSheet.ROOM_CORN = new SpriteSheet("roomCorn");

		// SpriteSheet.ROOM_WATERMELON = new SpriteSheet("roomWatermelon");
		// SpriteSheet.ROOM_CHERRY = new SpriteSheet("roomCherry");
		// SpriteSheet.ROOM_PEAR = new SpriteSheet("roomPear");
		// SpriteSheet.ROOM_RASPBERRY = new SpriteSheet("roomRaspberry");
		// SpriteSheet.ROOM_BANANA = new SpriteSheet("roomBanana");
		// SpriteSheet.ROOM_MANGO = new SpriteSheet("roomMango");

		SpriteSheet.ROOM_GYM = new SpriteSheet("roomGym");
		SpriteSheet.ROOM_SCHOOL = new SpriteSheet("roomSchool");
		SpriteSheet.ROOM_LIBRARY = new SpriteSheet("roomLibrary");
		SpriteSheet.ROOM_STORAGE = new SpriteSheet("roomStorage");
		SpriteSheet.ROOM_CLOTHES = new SpriteSheet("roomClothes");
		SpriteSheet.ROOM_SALON = new SpriteSheet("roomSalon");

		SpriteSheet.CAVE = new SpriteSheet("cave");
		SpriteSheet.CAVE_UNDERLAY = new SpriteSheet("cave_underlay");
		SpriteSheet.VOLCANO = new SpriteSheet("volcano");
		SpriteSheet.VOLCANO_UNDERLAY = new SpriteSheet("volcano_underlay");
		SpriteSheet.LAVA = new SpriteSheet("lava");
		SpriteSheet.FARM = new SpriteSheet("farm");
		SpriteSheet.FARM_UNDERLAY = new SpriteSheet("farm_underlay");

		SpriteSheet.MAIN_NW = new SpriteSheet("mainNW");
		SpriteSheet.MAIN_N = new SpriteSheet("mainN");
		SpriteSheet.MAIN_NE = new SpriteSheet("mainNE");
		SpriteSheet.MAIN_E = new SpriteSheet("mainE");
		SpriteSheet.MAIN_SE = new SpriteSheet("mainSE");
		SpriteSheet.MAIN_S = new SpriteSheet("mainS");
		SpriteSheet.MAIN_SW = new SpriteSheet("mainSW");
		SpriteSheet.MAIN_W = new SpriteSheet("mainW");
		SpriteSheet.MAIN_C = new SpriteSheet("mainC");

		SpriteSheet.MAIN_NW_OVERLAY = new SpriteSheet("mainNW_overlay");
		SpriteSheet.MAIN_NW_UNDERLAY = new SpriteSheet("mainNW_underlay");
		SpriteSheet.MAIN_N_OVERLAY = new SpriteSheet("mainN_overlay");
		SpriteSheet.MAIN_N_UNDERLAY = new SpriteSheet("mainN_underlay");
		SpriteSheet.MAIN_W_OVERLAY = new SpriteSheet("mainW_overlay");
		SpriteSheet.MAIN_W_UNDERLAY = new SpriteSheet("mainW_underlay");
		SpriteSheet.MAIN_C_OVERLAY = new SpriteSheet("mainC_overlay");
		SpriteSheet.MAIN_C_UNDERLAY = new SpriteSheet("mainC_underlay");
		SpriteSheet.MAIN_NE_OVERLAY = new SpriteSheet("mainNE_overlay");
		SpriteSheet.MAIN_NE_UNDERLAY = new SpriteSheet("mainNE_underlay");
		SpriteSheet.MAIN_E_OVERLAY = new SpriteSheet("mainE_overlay");
		SpriteSheet.MAIN_E_UNDERLAY = new SpriteSheet("mainE_underlay");
		SpriteSheet.MAIN_SE_OVERLAY = new SpriteSheet("mainSE_overlay");
		SpriteSheet.MAIN_SE_UNDERLAY = new SpriteSheet("mainSE_underlay");
		SpriteSheet.MAIN_S_OVERLAY = new SpriteSheet("mainS_overlay");
		SpriteSheet.MAIN_S_UNDERLAY = new SpriteSheet("mainS_underlay");

		SpriteSheet.menuETC = new SpriteSheet("menuETC");
		SpriteSheet.ITEMS = new SpriteSheet("items");
		SpriteSheet.PROJECTILES = new SpriteSheet("projectiles");
	}

}