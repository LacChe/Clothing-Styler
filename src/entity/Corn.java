package entity;

import render.Animation;
import render.Camera;
import render.Sprite;
import world.Flow;
import world.World;

public class Corn extends Entity {

	public static int ANIM_SLEEP_CHAIR = 8;
	public static int ANIM_AWAKE_CHAIR = 9;

	private static boolean awake = false;

	public Corn(int x, int y) {
		super(x, y, 64, 64, 0, -64, new Animation[] { //
				new Animation(Sprite.CORN_SLEEP_CHAIR, 3), // for initializing
				null, //
				null, //
				null, //
				null, //
				null, //
				null, //
				null, //
				new Animation(Sprite.CORN_SLEEP_CHAIR, 3), //
				new Animation(Sprite.CORN_AWAKE_CHAIR, 1), //
		});
		facingDir = 1;
		currentAnim = ANIM_SLEEP_CHAIR;
	}

	public void update(World world) {
		if (!awake) {
			currentAnim = ANIM_SLEEP_CHAIR;
		} else {
			currentAnim = ANIM_AWAKE_CHAIR;
		}
		super.update(world);
	}

	public static String[] getComment() {
		if (awake) {
			if (!Flow.justSoldMush() && !Flow.canFish()) {
				return new String[] { "Hi Onion.", //
						"Can you help Eggplant", //
						"sell some Mushcubes?", //
						"I am getting too old to", //
						"help with that job." };
			} else if (Flow.justSoldMush()) {
				Flow.justSoldMush(false);
				Flow.canFish(true);
				sleep();
				return new String[] { "Nice work.", "How about picking up that", "fishing pole and lay back",
						"for a bit." };
			}
		}
		return new String[] { "Hggggggggggggg..." };
	}

	public static void sleep() {
		awake = false;
	}

	public static void wake() {
		Camera.camera().setText(new String[] { "Oh!", "Hello Onion." });
		Camera.camera().showText(true);
		awake = true;
	}

}