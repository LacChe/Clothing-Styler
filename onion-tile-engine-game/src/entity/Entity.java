package entity;

import render.Animation;
import render.Sprite;
import world.Tile;
import world.World;

public class Entity {

	public static int PLAYER_S;
	public static int GARLIC1_W;
	public static int GARLIC1_H;
	public static int EGGPLANT_W;
	public static int EGGPLANT_H;
	public static int CORN_W;
	public static int CORN_H;

	public static int ANIM_IDLE_F;
	public static int ANIM_IDLE_R;
	public static int ANIM_IDLE_L;
	public static int ANIM_IDLE_B;
	public static int ANIM_WALK_F;
	public static int ANIM_WALK_R;
	public static int ANIM_WALK_L;
	public static int ANIM_WALK_B;

	protected int x, y, w, h, speedMin = 5, speed = 5, facingDir = 0;
	protected Animation[] animations;
	protected int sXOff, sYOff, sW, sH;
	protected int currentAnim;
	protected float angle;

	protected boolean solid = true;

	protected int preX, preY;

	public Entity(int x, int y, int w, int h, int sXOff, int sYOff, Animation[] animations) {
		this.x = preX = x;
		this.y = preY = y;
		this.w = w;
		this.h = h;
		this.angle = 0;

		currentAnim = ANIM_IDLE_F;
		this.animations = animations;
		this.sXOff = sXOff;
		this.sYOff = sYOff;

		if (animations.length > 0) {
			this.sW = animations[0].currentSprite().w();
			this.sH = animations[0].currentSprite().h();
		}
	}

	public Entity(int x, int y, int w, int h, int sXOff, int sYOff, Animation animation) {
		this.x = preX = x;
		this.y = preY = y;
		this.w = w;
		this.h = h;
		this.angle = 0;

		currentAnim = ANIM_IDLE_F;
		this.animations = new Animation[] { animation };
		this.sXOff = sXOff;
		this.sYOff = sYOff;

		if (animations.length > 0) {
			this.sW = animations[0].currentSprite().w();
			this.sH = animations[0].currentSprite().h();
		}
	}

	public Entity(int x, int y, int w, int h, int sXOff, int sYOff, Sprite sprite) {
		this.x = preX = x;
		this.y = preY = y;
		this.w = w;
		this.h = h;
		this.angle = 0;

		currentAnim = ANIM_IDLE_F;
		this.animations = new Animation[] { new Animation(sprite) };
		this.sXOff = sXOff;
		this.sYOff = sYOff;

		if (animations.length > 0) {
			this.sW = animations[0].currentSprite().w();
			this.sH = animations[0].currentSprite().h();
		}
	}

	public void setAnimation(int index, Animation anim) {
		animations[index] = anim;
	}

	public void update(World world) {
		if (!collideWithTile(world, preX, 0) && !collideWithEntity(world, preX, 0)) {
			x = preX;
		} else preX = x;
		if (!collideWithTile(world, 0, preY) && !collideWithEntity(world, 0, preY)) {
			y = preY;
		} else preY = y;
	}

	public void render(int[] worldPixels, int worldW, int worldH) {
		if (animations.length > 0) {
			animations[currentAnim].currentSprite().render(worldPixels, worldW, worldH, x + sXOff, y + sYOff, angle);
		}
	}

	public void activate(World world) {

	}

	public void action() {

	}

	private boolean collideWithEntity(World world, int preX, int preY) {
		for (int e = 0; e < world.entities().size(); e++) {
			Entity e2 = world.entities().get(e);
			if (e2.solid()) {
				if (preX != 0) {
					int preXX = preX;
					if (preX > x) preXX += w;
					if (preXX >= e2.x && preXX < e2.x + e2.w) {
						for (int i = 0; i < h; i += 1) {
							if (y + i >= e2.y && y + i < e2.y + e2.h) return true;
						}
					}
				} else if (preY != 0) {
					int preYY = preY;
					if (preY > y) preYY += h;
					if (preYY >= e2.y && preYY < e2.y + e2.h) {
						for (int i = 0; i < w; i += 1) {
							if (x + i >= e2.x && x + i < e2.x + e2.w) return true;
						}
					}
				}
			}
		}
		return false;
	}

	private boolean collideWithTile(World world, int preX, int preY) {
		if ((preX < x && preX < 0) || (preX > x && preX + w > world.w() * Tile.TILE_S) || (preY < y && preY < 0)
				|| (preY > y && preY + h > world.h() * Tile.TILE_S))
			return true;
		Tile[] tiles = new Tile[] { Tile.DIRT };
		if (preX != 0) {
			tiles = new Tile[w / Tile.TILE_S + 2];
			int preXX = preX;
			if (preX > x) preXX += w;
			for (int i = 0, index = 0; i < h; i += Tile.TILE_S, index++) {
				if (world.tiles().length <= preXX / Tile.TILE_S + (y + i) / Tile.TILE_S * world.w()) return true;
				tiles[index] = world.tiles()[preXX / Tile.TILE_S + (y + i) / Tile.TILE_S * world.w()];
			}
			if (preXX / Tile.TILE_S + (y + h) / Tile.TILE_S * world.w() < world.tiles().length) {
				tiles[tiles.length - 1] = world.tiles()[preXX / Tile.TILE_S + (y + h) / Tile.TILE_S * world.w()];
			}
		} else if (preY != 0) {
			tiles = new Tile[h / Tile.TILE_S + 2];
			int preYY = preY;
			if (preY > y) preYY += h;
			for (int i = 0, index = 0; i < w; i += Tile.TILE_S, index++) {
				if (world.tiles().length <= (x + i) / Tile.TILE_S + preYY / Tile.TILE_S * world.w()) return true;
				tiles[index] = world.tiles()[(x + i) / Tile.TILE_S + preYY / Tile.TILE_S * world.w()];
			}
			if ((x + w) / Tile.TILE_S + preYY / Tile.TILE_S * world.w() < world.tiles().length) {
				tiles[tiles.length - 1] = world.tiles()[(x + w) / Tile.TILE_S + preYY / Tile.TILE_S * world.w()];
			}
		}
		boolean collide = false;
		for (Tile t : tiles) {
			if (t != null && !t.passable()) {
				collide = true;
			}
		}
		return collide;
	}

	public int x() {
		return x;
	}

	public int y() {
		return y;
	}

	public void x(int x) {
		this.preX = x;
		this.x = x;

	}

	public void y(int y) {
		this.preY = y;
		this.y = y;
	}

	public int w() {
		return w;
	}

	public int h() {
		return h;
	}

	public int sXOff() {
		return sXOff;
	}

	public int sYOff() {
		return sYOff;
	}

	public Sprite sprite() {
		if (animations.length > 0) return animations[0].currentSprite();
		return null;
	}

	public boolean solid() {
		return solid;
	}

}