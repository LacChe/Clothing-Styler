package world;

import render.Animation;

public class Tile {

	public static int TILE_S = 64;

	public static Tile DIRT_N; // 000,000,000
	public static Tile DIRT; // 000,000,010
	public static Tile DIRT_W; // 000,000,020
	public static Tile DIRT_E;// 000,000,030
	public static Tile DIRT_NW_CRUST; // 000,000,040
	public static Tile DIRT_NE_CRUST; // 000,000,050
	public static Tile DIRT_NW;// 000,000,060
	public static Tile DIRT_NE;// 000,000,070
	public static Tile BRUSH; // 000,000,080
	public static Tile DIRT_STAIRS; // 000,000,090

	public static Tile CLIFF; // 000,000,100
	public static Tile WATER;// 000,000,110
	public static Tile WATER_E; // 000,000,120
	public static Tile WATER_W;// 000,000,130

	public static Tile MushRoomFloor; // 000,010,000
	public static Tile FLOOR;// 000,010,010
	public static Tile WALL_W;// 000,010,020
	public static Tile WALL_E;// 000,010,030
	public static Tile CEILING; // 000,010,040
	public static Tile WALL_SW_CRUST; // 000,010,050
	public static Tile WALL_SE_CRUST;// 000,010,060
	public static Tile WALL_SW;// 000,010,070
	public static Tile WALL_SE; // 000,010,080
	public static Tile VOID;// 000,010,090

	private Animation a;
	private boolean passable;

	public Tile(Animation a) {
		this.a = a;
		this.passable = true;
	}

	public void render(int[] worldPixels, int worldW, int worldH, int offX, int offY) {
		a.currentSprite().render(worldPixels, worldW, worldH, offX, offY, 0);
	}

	public Animation a() {
		return a;
	}

	public Tile setPassable(boolean p) {
		passable = p;
		return this;
	}

	public boolean passable() {
		return passable;
	}

}