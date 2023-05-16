package render;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	public static SpriteSheet TILES;

	public static SpriteSheet GARLIC;
	public static SpriteSheet EGGPLANT;
	public static SpriteSheet CORN;

	public static SpriteSheet PLAYER;
	public static SpriteSheet PLANTS;
	public static SpriteSheet BUILDINGS;
	public static SpriteSheet FOUNTAIN;

	public static SpriteSheet ROOM_ONION;
	public static SpriteSheet ROOM_ONION_UNDERLAY;
	// public static SpriteSheet ROOM_GARLIC ;
	// public static SpriteSheet ROOM_LETTUCE;
	// public static SpriteSheet ROOM_CARROT ;
	// public static SpriteSheet ROOM_EGGPLANT;
	// public static SpriteSheet ROOM_CORN ;

	// public static SpriteSheet ROOM_WATERMELON;
	// public static SpriteSheet ROOM_CHERRY ;
	// public static SpriteSheet ROOM_PEAR ;
	// public static SpriteSheet ROOM_RASPBERRY ;
	// public static SpriteSheet ROOM_BANANA ;
	// public static SpriteSheet ROOM_MANGO ;

	public static SpriteSheet ROOM_GYM;
	public static SpriteSheet ROOM_SCHOOL;
	public static SpriteSheet ROOM_LIBRARY;
	public static SpriteSheet ROOM_STORAGE;
	public static SpriteSheet ROOM_CLOTHES;
	public static SpriteSheet ROOM_SALON;

	public static SpriteSheet CAVE;
	public static SpriteSheet CAVE_UNDERLAY;
	public static SpriteSheet VOLCANO;
	public static SpriteSheet VOLCANO_UNDERLAY;
	public static SpriteSheet LAVA;
	public static SpriteSheet FARM;
	public static SpriteSheet FARM_UNDERLAY;

	public static SpriteSheet MAIN_NW;
	public static SpriteSheet MAIN_N;
	public static SpriteSheet MAIN_NE;
	public static SpriteSheet MAIN_E;
	public static SpriteSheet MAIN_SE;
	public static SpriteSheet MAIN_S;
	public static SpriteSheet MAIN_SW;
	public static SpriteSheet MAIN_W;
	public static SpriteSheet MAIN_C;

	public static SpriteSheet MAIN_NW_OVERLAY;
	public static SpriteSheet MAIN_NW_UNDERLAY;
	public static SpriteSheet MAIN_N_OVERLAY;
	public static SpriteSheet MAIN_N_UNDERLAY;
	public static SpriteSheet MAIN_W_OVERLAY;
	public static SpriteSheet MAIN_W_UNDERLAY;
	public static SpriteSheet MAIN_C_OVERLAY;
	public static SpriteSheet MAIN_C_UNDERLAY;
	public static SpriteSheet MAIN_NE_OVERLAY;
	public static SpriteSheet MAIN_NE_UNDERLAY;
	public static SpriteSheet MAIN_E_OVERLAY;
	public static SpriteSheet MAIN_E_UNDERLAY;
	public static SpriteSheet MAIN_SE_OVERLAY;
	public static SpriteSheet MAIN_SE_UNDERLAY;
	public static SpriteSheet MAIN_S_OVERLAY;
	public static SpriteSheet MAIN_S_UNDERLAY;

	public static SpriteSheet menuETC;
	public static SpriteSheet ITEMS;
	public static SpriteSheet PROJECTILES;

	private BufferedImage image;
	private String path;
	private int w, h;
	private int[] pixels;

	public SpriteSheet(String path) {
		this.path = path;
		loadSheet();
	}

	private void loadSheet() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource("/" + path + ".png"));
			w = image.getWidth();
			h = image.getHeight();
			pixels = new int[w * h];
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int w() {
		return w;
	}

	public int h() {
		return h;
	}

	public String name() {
		return path;
	}

	public int[] pixels() {
		return pixels;
	}

	public BufferedImage image() {
		return image;
	}

}