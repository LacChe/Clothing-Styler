package entity;

import render.Animation;
import render.Sprite;
import world.Tile;
import world.World;

public class Garlic1 extends Entity {

	int tempTimer = 100;

	public Garlic1(int x, int y) {
		super(x, y, 32, 24, 0, -39, new Animation[] { //
				new Animation(Sprite.GARLIC1_IDLE_L, 3), // for initializing
				new Animation(Sprite.GARLIC1_IDLE_R, 3), //
				new Animation(Sprite.GARLIC1_IDLE_L, 3), //
				null, //
				null, //
				new Animation(Sprite.GARLIC1_WALK_R, 6), //
				new Animation(Sprite.GARLIC1_WALK_L, 6), //
		});
		facingDir = 1;
		currentAnim = ANIM_IDLE_R;
	}

	public void update(World world) {
		speed = speedMin / 2;
		if (World.world().tiles()[x / Tile.TILE_S + y / Tile.TILE_S * World.world().w()] == Tile.DIRT_STAIRS)
			speed /= 4;
		for (int i = 0; i < speed; i++) {
			move(world);
			super.update(world);
		}
	}

	public void move(World world) {

		tempTimer--;
		if (tempTimer <= 0) {
			tempTimer = 100;
			if (currentAnim == ANIM_IDLE_R) currentAnim = ANIM_IDLE_L;
			else if (currentAnim == ANIM_IDLE_L) currentAnim = ANIM_WALK_L;
			else if (currentAnim == ANIM_WALK_L) currentAnim = ANIM_WALK_R;
			else if (currentAnim == ANIM_WALK_R) currentAnim = ANIM_IDLE_R;
		}

		if (currentAnim == ANIM_IDLE_L) { //
			facingDir = 2;
			currentAnim = ANIM_IDLE_L;
			// preY = Math.max(0, y - 1);
		}
		if (currentAnim == ANIM_IDLE_R) { //
			facingDir = 1;
			currentAnim = ANIM_IDLE_R;
			// preY = Math.min(world.h() * Tile.TILE_S - h, y + 1);
		}
		if (currentAnim == ANIM_WALK_L) { //
			facingDir = 2;
			currentAnim = ANIM_WALK_L;
			preX = Math.max(0, x - 1);
		}
		if (currentAnim == ANIM_WALK_R) { //
			facingDir = 1;
			currentAnim = ANIM_WALK_R;
			preX = Math.min(world.w() * Tile.TILE_S - w, x + 1);
		}
	}

}