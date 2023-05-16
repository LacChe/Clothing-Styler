package world;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import entity.Corn;
import entity.DroppingSack;
import entity.Eggplant;
import entity.Entity;
import entity.Event;
import entity.FishingSpot;
import entity.Garlic1;
import entity.Player;
import entity.Projectile;
import entity.StaticEntity;
import render.Animation;
import render.Camera;
import render.Sprite;
import render.SpriteSheet;

public class World {

	private static World currentWorld;

	public static World ROOM_ONION;
	// public static World ROOM_GARLIC;
	// public static World ROOM_LETTUCE;
	// public static World ROOM_CARROT;
	// public static World ROOM_EGGPLANT;
	// public static World ROOM_CORN;

	// public static World ROOM_WATERMELON;
	// public static World ROOM_CHERRY;
	// public static World ROOM_PEAR;
	// public static World ROOM_RASPBERRY;
	// public static World ROOM_BANANA;
	// public static World ROOM_MANGO;

	public static World ROOM_CLOTHES;
	public static World ROOM_SALON;

	public static World ROOM_GYM;
	public static World ROOM_SCHOOL;
	public static World ROOM_LIBRARY;
	public static World ROOM_STORAGE;

	public static World CAVE;
	public static World VOLCANO;
	public static World FARM;

	public static World MAIN_NW;
	public static World MAIN_N;
	public static World MAIN_NE;
	public static World MAIN_E;
	public static World MAIN_SE;
	public static World MAIN_S;
	public static World MAIN_SW;
	public static World MAIN_W;
	public static World MAIN_C;

	private ArrayList<Entity> entities;

	private ArrayList<StaticEntity> underlay;
	private ArrayList<StaticEntity> overlay;

	private ArrayList<Projectile> projectiles;

	private FishingSpot[] fishingSpots;

	private boolean addSack = false;

	private Tile[] tiles;
	private int w, h;

	private int[] pixels;

	public World(SpriteSheet tileSetter) {
		this.w = tileSetter.w() / 2;
		this.h = tileSetter.h();
		entities = new ArrayList<Entity>();
		overlay = new ArrayList<StaticEntity>();
		underlay = new ArrayList<StaticEntity>();
		projectiles = new ArrayList<Projectile>();

		tiles = new Tile[w * h];
		pixels = new int[w * h * Tile.TILE_S * Tile.TILE_S];

		entities().add(new Player());
		if (tileSetter.name().equals("mainNW")) {
			underlay.add(StaticEntity.NW_UNDERLAY_1);
			underlay.add(StaticEntity.NW_UNDERLAY_2);
			underlay.add(StaticEntity.NW_UNDERLAY_3);
			underlay.add(StaticEntity.NW_UNDERLAY_4);
			underlay.add(StaticEntity.NW_UNDERLAY_5);
			underlay.add(StaticEntity.NW_UNDERLAY_6);
			underlay.add(StaticEntity.NW_UNDERLAY_7);
			underlay.add(StaticEntity.NW_UNDERLAY_8);
			underlay.add(StaticEntity.NW_UNDERLAY_9);
			underlay.add(StaticEntity.NW_UNDERLAY_10);
			underlay.add(StaticEntity.NW_UNDERLAY_11);
			underlay.add(StaticEntity.NW_UNDERLAY_12);
			underlay.add(StaticEntity.NW_UNDERLAY_13);
			underlay.add(StaticEntity.NW_UNDERLAY_14);
			underlay.add(StaticEntity.NW_UNDERLAY_15);
			underlay.add(StaticEntity.NW_UNDERLAY_16);
			underlay.add(StaticEntity.NW_UNDERLAY_17);
			underlay.add(StaticEntity.NW_UNDERLAY_18);
			underlay.add(StaticEntity.NW_UNDERLAY_19);
			underlay.add(StaticEntity.NW_UNDERLAY_20);
			underlay.add(StaticEntity.NW_UNDERLAY_21);
			underlay.add(StaticEntity.NW_UNDERLAY_22);
			underlay.add(StaticEntity.NW_UNDERLAY_23);
			underlay.add(StaticEntity.NW_UNDERLAY_24);
			underlay.add(StaticEntity.NW_UNDERLAY_25);
			underlay.add(StaticEntity.NW_UNDERLAY_26);
			overlay.add(StaticEntity.NW_OVERLAY_1);
			overlay.add(StaticEntity.NW_OVERLAY_2);
			overlay.add(StaticEntity.NW_OVERLAY_3);
			overlay.add(StaticEntity.NW_OVERLAY_4);
			overlay.add(StaticEntity.NW_OVERLAY_5);
			overlay.add(StaticEntity.NW_OVERLAY_6);
			overlay.add(StaticEntity.NW_OVERLAY_7);
			overlay.add(StaticEntity.NW_OVERLAY_8);
			overlay.add(StaticEntity.NW_OVERLAY_9);
			overlay.add(StaticEntity.NW_OVERLAY_10);
			overlay.add(StaticEntity.NW_OVERLAY_11);
			overlay.add(StaticEntity.NW_OVERLAY_12);
			overlay.add(StaticEntity.NW_OVERLAY_13);

			entities().add(new Garlic1(23 * Tile.TILE_S, 24 * Tile.TILE_S));

			entities.add(new Event(31 * Tile.TILE_S + 25, 3 * Tile.TILE_S, Tile.TILE_S, Tile.TILE_S, 2));
			entities.add(
					new Event(14 * Tile.TILE_S, 31 * Tile.TILE_S + Tile.TILE_S / 2, Tile.TILE_S * 2, Tile.TILE_S, 4));
			entities.add(new Event(8 * Tile.TILE_S, 8 * Tile.TILE_S, Tile.TILE_S, Tile.TILE_S, 30));
			entities.add(new Event(22 * Tile.TILE_S, 21 * Tile.TILE_S, Tile.TILE_S, Tile.TILE_S, 0));
		} else if (tileSetter.name().equals("mainN")) {
			underlay.add(StaticEntity.N_UNDERLAY_1);
			underlay.add(StaticEntity.N_UNDERLAY_2);
			underlay.add(StaticEntity.N_UNDERLAY_3);
			underlay.add(StaticEntity.N_UNDERLAY_4);
			overlay.add(StaticEntity.N_OVERLAY_1);
			overlay.add(StaticEntity.N_OVERLAY_2);
			overlay.add(StaticEntity.N_OVERLAY_3);
			overlay.add(StaticEntity.N_OVERLAY_4);
			overlay.add(StaticEntity.N_OVERLAY_5);
			overlay.add(StaticEntity.N_OVERLAY_6);
			entities.add(new Event(0 * Tile.TILE_S, 3 * Tile.TILE_S, Tile.TILE_S, Tile.TILE_S, 3));
			entities.add(new Event(31 * Tile.TILE_S + 25, 3 * Tile.TILE_S, Tile.TILE_S, Tile.TILE_S, 6));
			entities.add(new Event(19 * Tile.TILE_S, 14 * Tile.TILE_S, Tile.TILE_S, Tile.TILE_S, 22));
			entities.add(new Event(13 * Tile.TILE_S, 5 * Tile.TILE_S, Tile.TILE_S, Tile.TILE_S, 24));
		} else if (tileSetter.name().equals("mainNE")) {
			underlay.add(StaticEntity.NE_UNDERLAY_1);
			underlay.add(StaticEntity.NE_UNDERLAY_2);
			underlay.add(StaticEntity.NE_UNDERLAY_3);
			underlay.add(StaticEntity.NE_UNDERLAY_4);
			underlay.add(StaticEntity.NE_UNDERLAY_5);
			overlay.add(StaticEntity.NE_OVERLAY_1);
			overlay.add(StaticEntity.NE_OVERLAY_2);
			overlay.add(StaticEntity.NE_OVERLAY_3);
			entities.add(new Event(0 * Tile.TILE_S, 3 * Tile.TILE_S, Tile.TILE_S, Tile.TILE_S, 7));
			entities.add(new Event(0 * Tile.TILE_S, 31 * Tile.TILE_S, 32 * Tile.TILE_S, Tile.TILE_S, 8));
			entities.add(new Event(355 + 128, 1016 + 128, Tile.TILE_S, Tile.TILE_S, 48));
			entities.add(new Event(1080 + 128, 1018 + 128, Tile.TILE_S, Tile.TILE_S, 50));
			entities.add(new Event(355 + 128, 1652 + 128, Tile.TILE_S, Tile.TILE_S, 52));
			entities.add(new Event(1140 + 128, 1652 + 128, Tile.TILE_S, Tile.TILE_S, 54));
		} else if (tileSetter.name().equals("mainE")) {
			overlay.add(StaticEntity.E_OVERLAY_1);
			overlay.add(StaticEntity.E_OVERLAY_2);
			overlay.add(StaticEntity.E_OVERLAY_3);
			overlay.add(StaticEntity.E_OVERLAY_4);
			underlay.add(StaticEntity.E_UNDERLAY_1);
			underlay.add(StaticEntity.E_UNDERLAY_2);
			entities.add(StaticEntity.FOUNTAIN);

			entities.add(new Event(0 * Tile.TILE_S, 0 * Tile.TILE_S, Tile.TILE_S, 31 * Tile.TILE_S, 13));
			entities.add(new Event(0 * Tile.TILE_S, 0 * Tile.TILE_S, 32 * Tile.TILE_S, Tile.TILE_S, 9));
			entities.add(new Event(0 * Tile.TILE_S, 31 * Tile.TILE_S, 32 * Tile.TILE_S, Tile.TILE_S, 14));
			entities.add(new Event(427 + 128, 218 + 128, Tile.TILE_S, Tile.TILE_S, 56));
			entities.add(new Event(1063 + 128, 463 + 128, Tile.TILE_S, Tile.TILE_S, 58));
			entities.add(new Event(392 + 128, 1336 + 128, Tile.TILE_S, Tile.TILE_S, 62));
			entities.add(new Event(1025 + 128, 1201 + 128, Tile.TILE_S, Tile.TILE_S, 60));
		} else if (tileSetter.name().equals("mainSE")) {
			underlay.add(StaticEntity.SE_UNDERLAY_1);
			overlay.add(StaticEntity.SE_OVERLAY_1);
			entities.add(new Event(0 * Tile.TILE_S, 0 * Tile.TILE_S, Tile.TILE_S, 32 * Tile.TILE_S, 16));
			entities.add(new Event(0 * Tile.TILE_S, 0 * Tile.TILE_S, 32 * Tile.TILE_S, Tile.TILE_S, 15));
		} else if (tileSetter.name().equals("mainS")) {
			overlay.add(StaticEntity.S_OVERLAY_1);
			overlay.add(StaticEntity.S_OVERLAY_2);
			overlay.add(StaticEntity.S_OVERLAY_3);
			underlay.add(StaticEntity.S_UNDERLAY_1);
			underlay.add(StaticEntity.S_UNDERLAY_2);
			entities.add(new Event(31 * Tile.TILE_S, 0 * Tile.TILE_S, Tile.TILE_S, 32 * Tile.TILE_S, 17));
		} else if (tileSetter.name().equals("mainSW")) {

			entities.add(new Corn(20 * Tile.TILE_S, 16 * Tile.TILE_S));
			entities.add(new Event(20 * Tile.TILE_S, 16 * Tile.TILE_S, Tile.TILE_S, Tile.TILE_S, 71));

			fishingSpots = new FishingSpot[3];
			fishingSpots[0] = new FishingSpot(17 * Tile.TILE_S, 16 * Tile.TILE_S,
					new Animation(Sprite.FISHING_SPOT, 1));
			entities.add(new Event(17 * Tile.TILE_S, 16 * Tile.TILE_S, 64, 48, 67));
			fishingSpots[1] = new FishingSpot(15 * Tile.TILE_S, 13 * Tile.TILE_S,
					new Animation(Sprite.FISHING_SPOT, 1));
			entities.add(new Event(15 * Tile.TILE_S, 13 * Tile.TILE_S, 64, 48, 68));
			fishingSpots[2] = new FishingSpot(12 * Tile.TILE_S, 13 * Tile.TILE_S,
					new Animation(Sprite.FISHING_SPOT, 1));
			entities.add(new Event(12 * Tile.TILE_S, 13 * Tile.TILE_S, 64, 48, 69));

			entities.add(new StaticEntity(24 * Tile.TILE_S, 14 * Tile.TILE_S, StaticEntity.POLE, Sprite.groundPole));
			entities.add(new Event(24 * Tile.TILE_S, 14 * Tile.TILE_S - 48, 16, 64, 66));
			entities.add(new Event(0 * Tile.TILE_S, 0 * Tile.TILE_S, 32 * Tile.TILE_S, Tile.TILE_S, 21));
		} else if (tileSetter.name().equals("mainW")) {
			underlay.add(StaticEntity.W_UNDERLAY_1);
			underlay.add(StaticEntity.W_UNDERLAY_2);
			underlay.add(StaticEntity.W_UNDERLAY_3);
			underlay.add(StaticEntity.W_UNDERLAY_4);
			underlay.add(StaticEntity.W_UNDERLAY_5);
			underlay.add(StaticEntity.W_UNDERLAY_6);
			overlay.add(StaticEntity.W_OVERLAY_1);
			overlay.add(StaticEntity.W_OVERLAY_2);
			overlay.add(StaticEntity.W_OVERLAY_3);
			overlay.add(StaticEntity.W_OVERLAY_4);
			entities.add(new Event(0 * Tile.TILE_S, 31 * Tile.TILE_S, 32 * Tile.TILE_S, Tile.TILE_S, 20));
			entities.add(new Event(31 * Tile.TILE_S, 0 * Tile.TILE_S, Tile.TILE_S, 30 * Tile.TILE_S, 10));
			entities.add(new Event(14 * Tile.TILE_S, 0 * Tile.TILE_S, Tile.TILE_S * 2, Tile.TILE_S, 5));
			entities.add(new Event(19 * Tile.TILE_S - 32, 16 * Tile.TILE_S, Tile.TILE_S, Tile.TILE_S, 27));
			entities.add(new Event(654 + 64, 844 + 128, Tile.TILE_S, Tile.TILE_S, 32));
			entities.add(new Event(1368 + 64, 632 + 128, Tile.TILE_S, Tile.TILE_S, 34));
			entities.add(new Event(789 + 64, 1346 + 128, Tile.TILE_S, Tile.TILE_S, 36));
			entities.add(new Event(1507 + 64, 1578 + 128, Tile.TILE_S, Tile.TILE_S, 38));
		} else if (tileSetter.name().equals("mainC")) {
			overlay.add(StaticEntity.C_OVERLAY_1);
			overlay.add(StaticEntity.C_OVERLAY_2);
			overlay.add(StaticEntity.C_OVERLAY_3);
			overlay.add(StaticEntity.C_OVERLAY_4);
			underlay.add(StaticEntity.C_UNDERLAY_1);
			underlay.add(StaticEntity.C_UNDERLAY_2);
			underlay.add(StaticEntity.C_UNDERLAY_3);

			entities.add(new Event(1080 + 204 - 96, 893 + 133 - 32, Tile.TILE_S, Tile.TILE_S, 65));
			entities.add(new Event(448 + 182 - 96, 890 + 143 - 32, Tile.TILE_S, Tile.TILE_S, 64));

			entities.add(new Event(0 * Tile.TILE_S, 0 * Tile.TILE_S, Tile.TILE_S, 31 * Tile.TILE_S, 11));
			entities.add(new Event(31 * Tile.TILE_S, 0 * Tile.TILE_S, Tile.TILE_S, 30 * Tile.TILE_S, 12));
			entities.add(new Event(142 + 128, 332 + 128, Tile.TILE_S, Tile.TILE_S, 40));
			entities.add(new Event(1351 + 128, 391 + 128, Tile.TILE_S, Tile.TILE_S, 42));
			entities.add(new Event(273 + 128, 1520 + 128, Tile.TILE_S, Tile.TILE_S, 46));
			entities.add(new Event(1353 + 128, 1398 + 128, Tile.TILE_S, Tile.TILE_S, 44));
		} else if (tileSetter.name().equals("roomOnion")) {
			underlay.add(StaticEntity.ROOM_ONION_UNDERLAY);
			entities.add(new Event(12 * Tile.TILE_S, 14 * Tile.TILE_S, Tile.TILE_S, Tile.TILE_S, 1));
		} else if (tileSetter.name().equals("roomGarlic")) {
			entities.add(new Event(12 * Tile.TILE_S, 14 * Tile.TILE_S, Tile.TILE_S, Tile.TILE_S, 31));
		} else if (tileSetter.name().equals("roomCarrot")) {
			entities.add(new Event(12 * Tile.TILE_S, 14 * Tile.TILE_S, Tile.TILE_S, Tile.TILE_S, 33));
		} else if (tileSetter.name().equals("roomLettuce")) {
			entities.add(new Event(12 * Tile.TILE_S, 14 * Tile.TILE_S, Tile.TILE_S, Tile.TILE_S, 37));
		} else if (tileSetter.name().equals("roomEggplant")) {
			entities.add(new Event(12 * Tile.TILE_S, 14 * Tile.TILE_S, Tile.TILE_S, Tile.TILE_S, 35));
		} else if (tileSetter.name().equals("roomCorn")) {
			entities.add(new Event(12 * Tile.TILE_S, 14 * Tile.TILE_S, Tile.TILE_S, Tile.TILE_S, 39));
		} else if (tileSetter.name().equals("roomCherry")) {
			entities.add(new Event(14 * Tile.TILE_S, 14 * Tile.TILE_S, Tile.TILE_S, Tile.TILE_S, 49));
		} else if (tileSetter.name().equals("roomWatermelon")) {
			entities.add(new Event(14 * Tile.TILE_S, 14 * Tile.TILE_S, Tile.TILE_S, Tile.TILE_S, 51));
		} else if (tileSetter.name().equals("roomPear")) {
			entities.add(new Event(14 * Tile.TILE_S, 14 * Tile.TILE_S, Tile.TILE_S, Tile.TILE_S, 57));
		} else if (tileSetter.name().equals("roomRaspberry")) {
			entities.add(new Event(14 * Tile.TILE_S, 14 * Tile.TILE_S, Tile.TILE_S, Tile.TILE_S, 59));
		} else if (tileSetter.name().equals("roomMango")) {
			entities.add(new Event(14 * Tile.TILE_S, 14 * Tile.TILE_S, Tile.TILE_S, Tile.TILE_S, 61));
		} else if (tileSetter.name().equals("roomBanana")) {
			entities.add(new Event(14 * Tile.TILE_S, 14 * Tile.TILE_S, Tile.TILE_S, Tile.TILE_S, 63));
		} else if (tileSetter.name().equals("roomClothes")) {
			entities.add(new Event(14 * Tile.TILE_S, 14 * Tile.TILE_S, Tile.TILE_S, Tile.TILE_S, 53));
		} else if (tileSetter.name().equals("roomSalon")) {
			entities.add(new Event(14 * Tile.TILE_S, 14 * Tile.TILE_S, Tile.TILE_S, Tile.TILE_S, 55));
		} else if (tileSetter.name().equals("roomGym")) {
			entities.add(new Event(12 * Tile.TILE_S, 14 * Tile.TILE_S, Tile.TILE_S, Tile.TILE_S, 41));
		} else if (tileSetter.name().equals("roomSchool")) {
			entities.add(new Event(12 * Tile.TILE_S, 14 * Tile.TILE_S, Tile.TILE_S, Tile.TILE_S, 43));
		} else if (tileSetter.name().equals("roomLibrary")) {
			entities.add(new Event(12 * Tile.TILE_S, 14 * Tile.TILE_S, Tile.TILE_S, Tile.TILE_S, 45));
		} else if (tileSetter.name().equals("roomStorage")) {
			entities.add(new Event(12 * Tile.TILE_S, 14 * Tile.TILE_S, Tile.TILE_S, Tile.TILE_S, 47));
		} else if (tileSetter.name().equals("cave")) {
			underlay.add(StaticEntity.CAVE_UNDERLAY_1);
			entities.add(new Event(16 * Tile.TILE_S, 16 * Tile.TILE_S, Tile.TILE_S, Tile.TILE_S, 23));
		} else if (tileSetter.name().equals("volcano")) {
			underlay.add(StaticEntity.VOLCANO_UNDERLAY_1);
			underlay.add(StaticEntity.LAVA);
			entities.add(new Event(16 * Tile.TILE_S, 16 * Tile.TILE_S, Tile.TILE_S, Tile.TILE_S, 25));
			entities.add(new Event(15 * Tile.TILE_S, 10 * Tile.TILE_S, Tile.TILE_S, Tile.TILE_S, 26));
		} else if (tileSetter.name().equals("farm")) {
			underlay.add(StaticEntity.FARM_UNDERLAY_1);

			entities.add(new Eggplant(9 * Tile.TILE_S, 19 * Tile.TILE_S));
			entities.add(new Event(9 * Tile.TILE_S, 19 * Tile.TILE_S, Tile.TILE_S, Tile.TILE_S, 70));
			entities.add(new Event(16 * Tile.TILE_S, 15 * Tile.TILE_S, Tile.TILE_S, Tile.TILE_S, 28));
			entities.add(new Event(10 * Tile.TILE_S, 20 * Tile.TILE_S, Tile.TILE_S * 3, Tile.TILE_S, 29));
			entities.add(new Event(14 * Tile.TILE_S, 20 * Tile.TILE_S, Tile.TILE_S * 3, Tile.TILE_S, 29));
			entities.add(new Event(10 * Tile.TILE_S, 24 * Tile.TILE_S, Tile.TILE_S * 3, Tile.TILE_S, 29));
			entities.add(new Event(14 * Tile.TILE_S, 24 * Tile.TILE_S, Tile.TILE_S * 3, Tile.TILE_S, 29));
			entities.add(new Event(9 * Tile.TILE_S, 21 * Tile.TILE_S, Tile.TILE_S * 4, Tile.TILE_S, 29));
			entities.add(new Event(14 * Tile.TILE_S, 21 * Tile.TILE_S, Tile.TILE_S * 4, Tile.TILE_S, 29));
			entities.add(new Event(9 * Tile.TILE_S, 23 * Tile.TILE_S, Tile.TILE_S * 4, Tile.TILE_S, 29));
			entities.add(new Event(14 * Tile.TILE_S, 23 * Tile.TILE_S, Tile.TILE_S * 4, Tile.TILE_S, 29));
		}

		setEntities(tileSetter.pixels());
		sortEntities();

		setTiles(tileSetter.pixels());
	}

	public World(int w, int h) {
		this.w = w;
		this.h = h;
		entities = new ArrayList<Entity>();
		tiles = new Tile[w * h];
		pixels = new int[w * h * Tile.TILE_S * Tile.TILE_S];
		initDefault();
	}

	public void update() {
		if (addSack) {
			addSack = false;
			entities.add(new DroppingSack(15 * Tile.TILE_S, 10 * Tile.TILE_S));
		}
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).getClass().getName().equals("entity.DroppingSack")
					&& ((DroppingSack) entities.get(i)).complete())
				entities.remove(i);
		}
		sortEntities();
		for (Entity e : entities) {
			e.update(this);
		}
		if (fishingSpots != null) {
			for (FishingSpot f : fishingSpots) {
				f.update(this);
			}
		}
		int remove = -1;
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update(this);
			if (projectiles.get(i).duration() <= 0) remove = i;
		}
		if (remove != -1) projectiles.remove(remove);
		remove = -1;
	}

	public void render(Camera c) {
		int boxOffX = c.offX(), boxOffY = c.offY();
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				if (i >= c.offX() / Tile.TILE_S && j >= c.offY() / Tile.TILE_S
						&& i < (c.offX() + c.w()) / Tile.TILE_S + 1 && j < (c.offY() + c.h()) / Tile.TILE_S + 1)
					if (tiles[i + j * w] != Tile.BRUSH) tiles[i + j * w].render(pixels, w * Tile.TILE_S,
							h * Tile.TILE_S, i * Tile.TILE_S, j * Tile.TILE_S);
			}
		}

		// underlay
		for (StaticEntity s : underlay) {
			s.render(pixels, w * Tile.TILE_S, h * Tile.TILE_S);
		}

		if (fishingSpots != null) {
			for (FishingSpot f : fishingSpots) {
				f.render(pixels, w * Tile.TILE_S, h * Tile.TILE_S);
			}
		}

		for (Entity e : entities) {
			// e.render(pixels, w * Tile.TILE_S, h * Tile.TILE_S); // test for speed
			if (e.sprite() == null) {
				e.render(pixels, w * Tile.TILE_S, h * Tile.TILE_S);
			} else if (e.x() + e.sXOff() <= c.offX() + c.w() && e.y() + e.sYOff() <= c.offY() + c.h()
					&& e.x() + e.sprite().w() + e.sXOff() >= c.offX()
					&& e.y() + +e.sprite().h() + e.sYOff() >= c.offY()) {
				e.render(pixels, w * Tile.TILE_S, h * Tile.TILE_S);
			}
		}

		try {
			for (Projectile p : projectiles) {
				p.render(pixels, w * Tile.TILE_S, h * Tile.TILE_S);
			}
		} catch (ConcurrentModificationException exc) {

		}

		for (StaticEntity s : overlay) {
			s.render(pixels, w * Tile.TILE_S, h * Tile.TILE_S);
		}

		// send to camera
		for (int i = 0; i < c.w(); i++) {
			for (int j = 0; j < c.h(); j++) {
				c.pixels()[i + (j) * c.w()] = pixels[i + boxOffX + (j + boxOffY) * w * Tile.TILE_S];
			}
		}
	}

	private void sortEntities() {
		for (int i = 0; i < entities.size() - 1; i++) {
			Entity e1 = entities.get(i);
			Entity e2 = entities.get(i + 1);
			if (e1.y() + e1.h() > e2.y() + e2.h()) {
				entities.set(i, e2);
				entities.set(i + 1, e1);
			}
		}
	}

	private void setEntities(int[] tilesRGB) {
		int[] pE = new int[w * h];
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				pE[x + y * w] = tilesRGB[x + w + y * w * 2];
			}
		}
		int count = (tilesRGB[w] & 0xff);
		if (count <= 0) return;
		for (int i = 1; i < pE.length; i += 3) {
			if (count <= 0) return;
			int setX = (pE[i + 1] >> 16 & 0xff) * 10000 + (pE[i + 1] >> 8 & 0xff) * 100 + (pE[i + 1] & 0xff);
			int setY = (pE[i + 2] >> 16 & 0xff) * 10000 + (pE[i + 2] >> 8 & 0xff) * 100 + (pE[i + 2] & 0xff);
			switch (pE[i] & 0xff) {
			case 0:// onion house
				entities.add(new StaticEntity(setX, setY, StaticEntity.POS_MUSHROOM_HOUSE, Sprite.HOUSE_ONION));
				break;
			case 1:// garlic house
				entities.add(new StaticEntity(setX, setY, StaticEntity.POS_MUSHROOM_HOUSE, Sprite.HOUSE_GARLIC));
				break;
			case 2:// carrot house
				entities.add(new StaticEntity(setX, setY, StaticEntity.POS_MUSHROOM_HOUSE, Sprite.HOUSE_CARROT));
				break;
			case 3:// lettuce house
				entities.add(new StaticEntity(setX, setY, StaticEntity.POS_MUSHROOM_HOUSE, Sprite.HOUSE_LETTUCE));
				break;
			case 4:// egg plant house
				entities.add(new StaticEntity(setX, setY, StaticEntity.POS_MUSHROOM_HOUSE, Sprite.HOUSE_EGGPLANT));
				break;
			case 5:// corn house
				entities.add(new StaticEntity(setX, setY, StaticEntity.POS_MUSHROOM_HOUSE, Sprite.HOUSE_CORN));
				break;
			case 6:// mushroom door
				entities.add(new StaticEntity(setX, setY, StaticEntity.POS_SHROOM_DOOR, Sprite.SHROOM_DOOR));
				break;
			case 7:// mushroom 1
				entities.add(new StaticEntity(setX, setY, StaticEntity.POS_SHROOM, Sprite.SHROOM_1));
				break;
			case 8:// mushroom 2
				entities.add(new StaticEntity(setX, setY, StaticEntity.POS_SHROOM, Sprite.SHROOM_2));
				break;
			case 9:// mushroom 3
				entities.add(new StaticEntity(setX, setY, StaticEntity.POS_SHROOM, Sprite.SHROOM_3));
				break;
			case 10:// cherry house
				entities.add(new StaticEntity(setX, setY, StaticEntity.POS_TREE_HOUSE, Sprite.HOUSE_CHERRY));
				break;
			case 11:// water melon house
				entities.add(new StaticEntity(setX, setY, StaticEntity.POS_TREE_HOUSE, Sprite.HOUSE_WATERMELON));
				break;
			case 12:// pear house
				entities.add(new StaticEntity(setX, setY, StaticEntity.POS_TREE_HOUSE, Sprite.HOUSE_PEAR));
				break;
			case 13:// raspberry house
				entities.add(new StaticEntity(setX, setY, StaticEntity.POS_TREE_HOUSE, Sprite.HOUSE_RASPBERRY));
				break;
			case 14:// banana house
				entities.add(new StaticEntity(setX, setY, StaticEntity.POS_TREE_HOUSE, Sprite.HOUSE_BANANA));
				break;
			case 15:// mango house
				entities.add(new StaticEntity(setX, setY, StaticEntity.POS_TREE_HOUSE, Sprite.HOUSE_MANGO));
				break;
			case 16:// tree
				entities.add(new StaticEntity(setX, setY, StaticEntity.POS_TREE, Sprite.TREE));
				break;
			case 17:// mushstall
				entities.add(new StaticEntity(setX, setY, StaticEntity.POS_STALL, Sprite.STALL_MUSH));
				break;
			case 18:// fishstall
				entities.add(new StaticEntity(setX, setY, StaticEntity.POS_STALL, Sprite.STALL_FISH));
				break;
			case 19:// school
				entities.add(new StaticEntity(setX, setY, StaticEntity.POS_CENTER_STORE, Sprite.SCHOOL));
				break;
			case 20:// gym
				entities.add(new StaticEntity(setX, setY, StaticEntity.POS_CENTER_STORE, Sprite.GYM));
				break;
			case 21:// library
				entities.add(new StaticEntity(setX, setY, StaticEntity.POS_CENTER_STORE, Sprite.LIBRARY));
				break;
			case 22:// stockhouse
				entities.add(new StaticEntity(setX, setY, StaticEntity.POS_CENTER_STORE, Sprite.STOCKHOUSE));
				break;
			case 23:// clothes
				entities.add(new StaticEntity(setX, setY, StaticEntity.POS_WOODS_STORE, Sprite.CLOTHES));
				break;
			case 24:// salon
				entities.add(new StaticEntity(setX, setY, StaticEntity.POS_WOODS_STORE, Sprite.SALON));
				break;
			case 25://
				break;
			case 26://
				break;
			case 27://
				break;
			case 28://
				break;
			case 29://
				break;
			default:
			}
			count--;
		}

	}

	private void setTiles(int[] tilesRGB) {
		int i = 0;
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				i = x + y * w;
				switch ((tilesRGB[x + y * w * 2] >> 8) & 0xff) {// green
				case 0:
					switch (tilesRGB[x + y * w * 2] & 0xff) {// blue
					case 0:
						tiles[i] = Tile.DIRT_N;
						break;
					case 10:
						tiles[i] = Tile.DIRT;
						break;
					case 20:
						tiles[i] = Tile.DIRT_W;
						break;
					case 30:
						tiles[i] = Tile.DIRT_E;
						break;
					case 40:
						tiles[i] = Tile.DIRT_NW_CRUST;
						break;
					case 50:
						tiles[i] = Tile.DIRT_NE_CRUST;
						break;
					case 60:
						tiles[i] = Tile.DIRT_NE;
						break;
					case 70:
						tiles[i] = Tile.DIRT_NW;
						break;
					case 80:
						tiles[i] = Tile.BRUSH;
						break;
					case 90:
						tiles[i] = Tile.DIRT_STAIRS;
						break;
					case 100:
						tiles[i] = Tile.CLIFF;
						break;
					case 110:
						tiles[i] = Tile.WATER;
						break;
					case 120:
						tiles[i] = Tile.WATER_E;
						break;
					case 130:
						tiles[i] = Tile.WATER_W;
						break;
					default:
						tiles[i] = Tile.DIRT;
					}
					break;
				case 10:
					switch (tilesRGB[x + y * w * 2] & 0xff) {// blue
					case 0:
						tiles[i] = Tile.MushRoomFloor;
						break;
					case 10:
						tiles[i] = Tile.FLOOR;
						break;
					case 20:
						tiles[i] = Tile.WALL_W;
						break;
					case 30:
						tiles[i] = Tile.WALL_E;
						break;
					case 40:
						tiles[i] = Tile.CEILING;
						break;
					case 50:
						tiles[i] = Tile.WALL_SW_CRUST;
						break;
					case 60:
						tiles[i] = Tile.WALL_SE_CRUST;
						break;
					case 70:
						tiles[i] = Tile.WALL_SW;
						break;
					case 80:
						tiles[i] = Tile.WALL_SE;
						break;
					case 90:
						tiles[i] = Tile.VOID;
						break;
					default:
						tiles[i] = Tile.FLOOR;
					}
					break;
				default:
				}
			}
		}
	}

	private void initDefault() {
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				if (i == 0 || j == 0 || i == w - 1 || j == h - 1 || j == 1 || j == h - 2) {
					tiles[i + j * w] = Tile.WATER;
				} else if (i == 1) {
					tiles[i + j * w] = Tile.WATER_E;
				} else if (i == w - 2) {
					tiles[i + j * w] = Tile.WATER_W;
				} else if ((i > 0 && i < w - 1) && j == h - 3) {
					tiles[i + j * w] = Tile.CLIFF;
				} else if ((i > 2 && i < w - 3) && j == 2) {
					tiles[i + j * w] = Tile.DIRT_N;
				} else if (i == 2 && j == 2) {
					tiles[i + j * w] = Tile.DIRT_NW_CRUST;
				} else if (i == w - 3 && j == 2) {//
					tiles[i + j * w] = Tile.DIRT_NE_CRUST;
				} else if ((j > 2 && j < h - 3) && i == 2) {
					tiles[i + j * w] = Tile.DIRT_W;
				} else if ((j > 2 && j < h - 3) && i == w - 3) {
					tiles[i + j * w] = Tile.DIRT_E;
				} else tiles[i + j * w] = Tile.DIRT;
			}
		}
	}

	public int w() {
		return w;
	}

	public int h() {
		return h;
	}

	public ArrayList<Entity> entities() {
		return entities;
	}

	public Tile[] tiles() {
		return tiles;
	}

	public Player player() {
		Player p = null;
		for (Entity e : entities) {
			if (e.getClass().getName().equals("entity.Player")) p = (Player) e;
		}
		return p;
	}

	public void setPlayer(Player p) {
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).getClass().getName().equals("entity.Player")) entities.set(i, p);
		}
	}

	public void addSpray(int angle) {
		projectiles.add(new Projectile(Sprite.spray).setAngle(angle));
	}

	public void addStink(int angle) {
		projectiles.add(new Projectile(Sprite.stink).setAngle(angle));
	}

	public ArrayList<Projectile> projectiles() {
		return projectiles;
	}

	public static void world(World w) {
		currentWorld = w;
	}

	public static World world() {
		return currentWorld;
	}

	public FishingSpot[] fishingSpots() {
		return fishingSpots;
	}

	public void addDroppingSack() {
		addSack = true;
	}

}