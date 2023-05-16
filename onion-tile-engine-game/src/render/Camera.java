package render;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import io.Boot;
import menu.Menu;
import world.Tile;
import world.World;

public class Camera {

	private static Camera camera;

	private BufferedImage image;
	private TextEngine mainTE;
	private String[] text = new String[] { "Goodmorning Onion!", "   This is your house.", "      Have fun!" };

	private boolean showMainTE;
	private boolean showMenu;
	private boolean showWorld;

	private int w, h, offX, offY;
	private int[] pixels;

	private float alpha = 0;

	public Camera(World world, int w, int h) {
		this.w = w;
		this.h = h;
		this.offX = this.offY = 0;
		this.mainTE = new TextEngine(Boot.WIDTH / 4, Boot.HEIGHT * 3 / 4 - 20, Boot.WIDTH * 1 / 2, Boot.HEIGHT / 4,
				Sprite.mainTextBG);
		this.showMainTE = true;
		this.showMenu = false;
		this.showWorld = true;
		this.image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		this.pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		load();
		offX = world.player().x() + world.player().w() / 2 - w / 2;
		offY = world.player().y() + world.player().h() / 2 - h / 2;
		if (offX < 0) offX = 0;
		if (offY < 0) offY = 0;
		if (offX > world.w() * Tile.TILE_S - w) offX = world.w() * Tile.TILE_S - w;
		if (offY > world.h() * Tile.TILE_S - h) offY = world.h() * Tile.TILE_S - h;
		camera = this;
	}

	public void load() {
		for (int i = 0; i < w * h; i++) {
			pixels[i] = 0xff00ff;
		}
	}

	public void update(World world) {
		if (world.player() != null) correctCamera(world);
	}

	private void correctCamera(World world) {
		float lerp = 0.15f;
		int pX = world.player().x() + world.player().w() / 2 - w / 2;
		int pY = world.player().y() + world.player().h() / 2 - h / 2;
		if (offX != pX) offX += (pX - offX) * lerp;
		if (offY != pY) offY += (pY - offY) * lerp;
		if (offX < 0) offX = 0;
		if (offY < 0) offY = 0;
		if (offX > world.w() * Tile.TILE_S - w) offX = world.w() * Tile.TILE_S - w;
		if (offY > world.h() * Tile.TILE_S - h) offY = world.h() * Tile.TILE_S - h;
	}

	public void render() {
		if (showWorld) World.world().render(this);
		if (showMainTE) {
			mainTE.renderString(text);
			alpha = 0.75f;
			for (int i = 0; i < mainTE.w(); i++) {
				for (int j = 0; j < mainTE.h(); j++) {
					if (mainTE.pixels()[i + j * mainTE.w()] != 0) {
						int srcR = ((mainTE.pixels()[i + j * mainTE.w()] >> 16) & 0xff);
						int srcG = ((mainTE.pixels()[i + j * mainTE.w()] >> 8) & 0xff);
						int srcB = ((mainTE.pixels()[i + j * mainTE.w()] >> 0) & 0xff);
						int dstR = ((pixels[i + mainTE.x() + (j + mainTE.y()) * w] >> 16) & 0xff);
						int dstG = ((pixels[i + mainTE.x() + (j + mainTE.y()) * w] >> 8) & 0xff);
						int dstB = ((pixels[i + mainTE.x() + (j + mainTE.y()) * w] >> 0) & 0xff);
						int color = (((int) (srcR * alpha + dstR * (1 - alpha))) << 16)
								+ (((int) (srcG * alpha + dstG * (1 - alpha))) << 8)
								+ (((int) (srcB * alpha + dstB * (1 - alpha))));
						pixels[i + mainTE.x() + (j + mainTE.y()) * w] = color;
					}
				}
			}
		}
		if (showMenu) {
			alpha = 0.85f;
			for (int i = 0; i < Menu.menu().w(); i++) {
				for (int j = 0; j < Menu.menu().h(); j++) {
					if (Menu.menu().pixels()[i + j * Menu.menu().w()] != 0) {
						int srcR = ((Menu.menu().pixels()[i + j * Menu.menu().w()] >> 16) & 0xff);
						int srcG = ((Menu.menu().pixels()[i + j * Menu.menu().w()] >> 8) & 0xff);
						int srcB = ((Menu.menu().pixels()[i + j * Menu.menu().w()] >> 0) & 0xff);
						int dstR = ((pixels[i + Menu.menu().x() + (j + Menu.menu().y()) * w] >> 16) & 0xff);
						int dstG = ((pixels[i + Menu.menu().x() + (j + Menu.menu().y()) * w] >> 8) & 0xff);
						int dstB = ((pixels[i + Menu.menu().x() + (j + Menu.menu().y()) * w] >> 0) & 0xff);
						int color = (((int) (srcR * alpha + dstR * (1 - alpha))) << 16)
								+ (((int) (srcG * alpha + dstG * (1 - alpha))) << 8)
								+ (((int) (srcB * alpha + dstB * (1 - alpha))));
						pixels[i + Menu.menu().x() + (j + Menu.menu().y()) * w] = color;
					}
				}
			}

		}
	}

	public BufferedImage getImage() {
		return image;
	}

	public int w() {
		return w;
	}

	public int h() {
		return h;
	}

	public int offX() {
		return offX;
	}

	public int offY() {
		return offY;
	}

	public void offX(int x) {
		offX = x;
	}

	public void offY(int y) {
		offY = y;
	}

	public int[] pixels() {
		return pixels;
	}

	public static void camera(Camera c) {
		camera = c;
	}

	public static Camera camera() {
		return camera;
	}

	public boolean showText() {
		return showMainTE;
	}

	public void showText(boolean show) {
		showMainTE = show;
	}

	public void setText(String[] t) {
		text = t;
	}

	public void setText(String t) {
		text = new String[] { t };
	}

	public boolean showMenu() {
		return showMenu;
	}

	public void showMenu(boolean show) {
		showMenu = show;
	}

}