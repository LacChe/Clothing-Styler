package entity;

import render.Sprite;
import render.SpriteSheet;
import world.World;

public class DroppingSack extends Entity {

	private int dropDst = 0;
	private int sinkDst = 0;

	private boolean complete = false;

	public DroppingSack(int x, int y) {
		super(x, y, 64, 64, 0, 0, Sprite.rubbish);
	}

	public void update(World world) {
		if (dropDst < 3 * 64 + 10) dropDst += 5;
		else if (sinkDst < 64) sinkDst++;
		else complete = true;
	}

	public void render(int[] worldPixels, int worldW, int worldH) {
		if (animations.length > 0) {
			for (int i = 0; i < 64; i++) {
				for (int j = 0; j < 64 - sinkDst; j++) {
					int pixel = animations[currentAnim].currentSprite().pixels(
							(i + Sprite.rubbish.pathX()) + (j + Sprite.rubbish.pathY()) * SpriteSheet.ITEMS.w());
					if (((pixel >> 24) & 0xff) != 0)
						worldPixels[(i + x) + (j + y + dropDst + sinkDst) * worldW] = pixel;
				}
			}
		}
	}

	public boolean complete() {
		return complete;
	}

}