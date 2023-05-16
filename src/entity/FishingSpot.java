package entity;

import render.Animation;
import world.World;

public class FishingSpot extends Entity {

	private int countDown = -1;
	private boolean empty = false;

	public FishingSpot(int x, int y, Animation animation) {
		super(x, y, 64, 48, -305, 208, animation);
		solid = false;
	}

	public void update(World world) {
		if (empty) {
			countDown--;
			if (countDown <= 0) {
				countDown = -1;
				empty = false;
			}
		}
	}

	public void render(int[] worldPixels, int worldW, int worldH) {
		if (!empty) if (animations.length > 0)
			animations[currentAnim].currentSprite().render(worldPixels, worldW, worldH, x + sXOff, y + sYOff, angle);
	}

	public boolean caught() {
		if (!empty) {
			empty = true;
			countDown = 200;
			World.world().player().getInv().addFish();
			return true;
		} else return false;
	}

}