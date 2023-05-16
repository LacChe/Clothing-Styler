package entity;

import io.Keyboard;
import menu.Inventory;
import render.Animation;
import render.Camera;
import render.Sprite;
import world.Tile;
import world.World;

public class Player extends Entity {

	private boolean activate = false;
	private boolean holdingPole = false;

	private Inventory inv;

	public Player() {
		super(22 * Tile.TILE_S, 23 * Tile.TILE_S, 50, 24, -7, -39, new Animation[] { //
				new Animation(Sprite.PLAYER_IDLE_F, 3), //
				new Animation(Sprite.PLAYER_IDLE_R, 3), //
				new Animation(Sprite.PLAYER_IDLE_L, 3), //
				new Animation(Sprite.PLAYER_IDLE_B, 3), //
				new Animation(Sprite.PLAYER_WALK_F, 6), //
				new Animation(Sprite.PLAYER_WALK_R, 6), //
				new Animation(Sprite.PLAYER_WALK_L, 6), //
				new Animation(Sprite.PLAYER_WALK_B, 6),//
		});
		inv = new Inventory();
		angle = (float) (Math.PI * -0);
	}

	public void update(World world) {
		if (Keyboard.Shift) speed = speedMin * 2;
		else speed = speedMin;
		if (World.world().tiles()[x / Tile.TILE_S + y / Tile.TILE_S * World.world().w()] == Tile.DIRT_STAIRS)
			speed /= 2;
		// all movable entities have this
		if (!Camera.camera().showText() && !Camera.camera().showMenu()) {
			for (int i = 0; i < speed; i++) {
				move(world);
				activate(world);
				super.update(world);
			}
		}
	}

	public void move(World world) {
		if (Keyboard.W) { // north
			facingDir = 3;
			currentAnim = ANIM_WALK_B;
			preY = Math.max(0, y - 1);
		}
		if (Keyboard.S) { // south
			facingDir = 0;
			currentAnim = ANIM_WALK_F;
			preY = Math.min(world.h() * Tile.TILE_S - h, y + 1);
		}
		if (Keyboard.A) { // west
			facingDir = 2;
			currentAnim = ANIM_WALK_L;
			preX = Math.max(0, x - 1);
		}
		if (Keyboard.D) { // east
			facingDir = 1;
			currentAnim = ANIM_WALK_R;
			preX = Math.min(world.w() * Tile.TILE_S - w, x + 1);
		}
		if (!Keyboard.W && !Keyboard.A && !Keyboard.S && !Keyboard.D) {
			if (facingDir == 0) currentAnim = ANIM_IDLE_F;
			if (facingDir == 1) currentAnim = ANIM_IDLE_R;
			if (facingDir == 2) currentAnim = ANIM_IDLE_L;
			if (facingDir == 3) currentAnim = ANIM_IDLE_B;
		}
	}

	public void activate(World world) {
		for (int e = 0; e < world.entities().size(); e++) {
			Entity e2 = world.entities().get(e);
			if (e2.getClass().getName().equals("entity.Event")) {
				if (!((Event) e2).needsAction()) {
					if (x + w / 2 >= e2.x() && x + w / 2 < e2.x() + e2.w() && y + h / 2 >= e2.y()
							&& y + h / 2 < e2.y() + e2.h()) {
						e2.action();
					}
				} else if (activate == true) {
					if (facingDir == 2 || facingDir == 1) {
						int preXX = preX - 1;
						if (facingDir == 1) preXX += (w + 2);
						if (preXX >= e2.x && preXX < e2.x + e2.w) {
							if (y + h / 2 >= e2.y && y + h / 2 < e2.y + e2.h) {
								e2.action();
							}
						}
					} else if (facingDir == 3 || facingDir == 0) {
						int preYY = preY - 1;
						if (facingDir == 0) preYY += (h + 2);
						if (preYY >= e2.y && preYY < e2.y + e2.h) {
							if (x + w / 2 >= e2.x && x + w / 2 < e2.x + e2.w) {
								e2.action();
							}
						}
					}
				}
			}
		}
		activate = false;
	}

	public void render(int[] worldPixels, int worldW, int worldH) {
		if (holdingPole) {
			Sprite.pole.render(worldPixels, worldW, worldH, x - 64 * 5 + 15, y + sYOff, angle);
		}
		super.render(worldPixels, worldW, worldH);
	}

	public void setActivate() {
		activate = true;
	}

	public void setDirection(int i) {
		facingDir = i;
	}

	public Inventory getInv() {
		return inv;
	}

	public boolean holdingPole() {
		return holdingPole;
	}

	public void holdingPole(boolean b) {
		holdingPole = b;
	}

}