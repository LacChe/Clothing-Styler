package graphics;

import boot.Boot;
import boot.Transform;

public class Sprite {

	public static Sprite room1 = new Sprite(0, 0, 600, 600, SpriteSheet.bathroom);
	public static Sprite room2 = new Sprite(600, 0, 600, 600, SpriteSheet.bathroom);
	public static Sprite room3 = new Sprite(600 * 2, 0, 600, 600, SpriteSheet.bathroom);
	public static Sprite room4 = new Sprite(600 * 3, 0, 600, 600, SpriteSheet.bathroom);
	public static Sprite room5 = new Sprite(600 * 4, 0, 600, 600, SpriteSheet.bathroom);
	public static Sprite room6 = new Sprite(600 * 5, 0, 600, 600, SpriteSheet.bathroom);

	public static Sprite megaphone = new Sprite(0, 0, 32, 32, SpriteSheet.items);
	public static Sprite wires = new Sprite(0, 32, 32, 32, SpriteSheet.items);
	public static Sprite broom = new Sprite(32, 0, 32, 64, SpriteSheet.items);
	public static Sprite brick = new Sprite(64, 0, 32, 32, SpriteSheet.items);
	public static Sprite plastic = new Sprite(64, 32, 32, 32, SpriteSheet.items);
	public static Sprite battery = new Sprite(96, 0, 32, 32, SpriteSheet.items);
	public static Sprite bandage = new Sprite(96, 32, 32, 32, SpriteSheet.items);
	public static Sprite bandageWire = new Sprite(128, 0, 32, 32, SpriteSheet.items);
	public static Sprite component = new Sprite(128, 32, 32, 32, SpriteSheet.items);

	public static Sprite title = new Sprite(0, 0, 250, 170, SpriteSheet.splash);
	public static Sprite start = new Sprite(0, 180, 130, 50, SpriteSheet.splash);
	public static Sprite win = new Sprite(140, 180, 100, 40, SpriteSheet.splash);

	private int pathX, pathY, w, h;
	private SpriteSheet sheet;

	public Sprite(int pathX, int pathY, int w, int h, SpriteSheet sheet) {
		this.pathX = pathX;
		this.pathY = pathY;
		this.w = w;
		this.h = h;
		this.sheet = sheet;
	}

	public void render(int[] pixels, int offX, int offY) {
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				if (((pixels((pathX + x) + (pathY + y) * sheet.w()) >> 24) & 0xff) != 0) {
					pixels[x + offX + (y + offY) * Boot.WIDTH] = pixels((pathX + x) + (pathY + y) * sheet.w());
				}
			}
		}
	}

	public void render(Transform transform) {
		int offX = (int) transform.position[0];
		int offY = (int) transform.position[1];
		double scaleX = Math.abs(transform.scale[0]);
		double scaleY = transform.scale[1];
		double rotation = transform.rotation[0];

		double c = Math.cos(-rotation), s = Math.sin(-rotation);
		int x0 = (int) (w * scaleX / 2), y0 = (int) (h * scaleY / 2), x1 = (int) (w * scaleX / 2),
				y1 = (int) (h * scaleY / 2), xp, yp;

		for (int x = 0; x < w * scaleX; x++) {
			for (int y = 0; y < h * scaleY; y++) {
				xp = x;
				// flipX
				if (transform.scale[0] < 0) {
					xp = (int) (w * scaleX - x);
				}
				yp = y;

				// calculate rotation
				if (rotation != 0) {
					xp = x - x0;
					yp = y - y0;
					int xx = (int) ((double) (xp) * c - (double) (yp) * s);
					int yy = (int) ((double) (xp) * s + (double) (yp) * c);
					xp = xx + x1;
					yp = yy + y1;
				}

				if (x + offX + (y + offY) * Boot.WIDTH >= 0
						&& x + offX + (y + offY) * Boot.WIDTH < Camera.pixels().length
						&& (pathX + (int) (xp / scaleX)) + (pathY + (int) (yp / scaleY)) * sheet.w() >= 0
						&& (pathX + (int) (xp / scaleX))
								+ (pathY + (int) (yp / scaleY)) * sheet.w() < sheet.pixels().length
						&& ((pixels((pathX + (int) (xp / scaleX)) + (pathY + (int) (yp / scaleY)) * sheet.w()) >> 24)
								& 0xff) != 0) {
					Camera.pixels()[x + offX + (y + offY) * Boot.WIDTH] = pixels(
							(pathX + (int) (xp / scaleX)) + (pathY + (int) (yp / scaleY)) * sheet.w());
				}
			}
		}
	}

	public int w() {
		return w;
	}

	public int h() {
		return h;
	}

	public int pathX() {
		return pathX;
	}

	public int pathY() {
		return pathY;
	}

	public int pixels(int i) {
		if (((sheet.pixels()[i] >> 24) & 0xff) == 0) return 0x00ffffff;
		return sheet.pixels()[i];
	}

}