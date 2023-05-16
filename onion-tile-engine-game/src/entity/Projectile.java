package entity;

import render.Sprite;
import world.Tile;
import world.World;

public class Projectile {

	private Sprite s;
	private float angle, rotation;
	private int degrees;
	private float x, y;
	private int duration;

	public Projectile(Sprite s) {
		this.s = s;
		this.x = World.world().player().x + World.world().player().w / 2;
		this.y = World.world().player().y + World.world().player().h / 2;
		this.angle = 0;
		this.degrees = 360;
		this.rotation = 0;
		this.duration = 80;
	}

	public void update(World world) {
		duration--;
		if (collide(world)) duration = 0;
		if (duration <= 0) return;

		float speed = 5;

		// calculate rotation
		degrees -= 5;
		if (degrees < 0) degrees = 360;
		rotation = (float) (Math.PI) / 180f * (float) degrees;

		// calculate x
		x = x + speed * (float) Math.cos((double) angle);

		// calculate y
		y = y + speed * (float) Math.sin((double) angle);

	}

	public void render(int[] worldPixels, int worldW, int worldH) {
		s.render(worldPixels, worldW, worldH, (int) x - 8, (int) y - 28, rotation);
	}

	public boolean collide(World world) {

		// with tile
		if ((int) x / Tile.TILE_S < 0 || (int) y / Tile.TILE_S < 0 || (int) x / Tile.TILE_S >= world.w()
				|| (int) y / Tile.TILE_S >= world.h())
			return true;
		Tile t2 = world.tiles()[(int) x / Tile.TILE_S + (int) y / Tile.TILE_S * world.w()];
		if (!t2.passable()) return true;

		// with entity
		for (int e = 0; e < world.entities().size(); e++) {
			Entity e2 = world.entities().get(e);
			if (e2.solid() && !e2.getClass().getName().equals("entity.Player"))
				if (x >= e2.x && x < e2.x + e2.w && y >= e2.y && y < e2.y + e2.h) {
				if (e2.getClass().getName().equals("entity.Corn")) Corn.wake();
				return true;
				}
		}

		return false;
	}

	public Projectile setAngle(float a) {
		angle = (float) (Math.PI) / 180f * (float) a;
		return this;
	}

	public int duration() {
		return duration;
	}

}
