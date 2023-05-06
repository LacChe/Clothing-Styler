package entity;

import render.Animation;
import render.Sprite;
import world.Flow;
import world.World;

public class Eggplant extends Entity {

	// case 70

	public Eggplant(int x, int y) {
		super(x, y, 64, 64, 0, -64, new Animation[] { //
				new Animation(Sprite.EGGPLANT_IDLE_F, 3), });
	}

	public static String[] getComment() {
		if (Flow.needDumping()) {
			if (World.world().player().getInv().addRubbish()) {
				return new String[] { "Hi Onion.", //
						"I have too much to do.", //
						"Can you dump this sack of", //
						"rubbish in the volcano", //
						"please." };
			} else {
				return new String[] { "Looks like you already", //
						"have a sack of rubbish.", //
						"Can you dump it in", //
						"the volcano please." };
			}
		} else if (Flow.justDumped()) {
			Flow.justDumped(false);
			Flow.canFarm(true);
			return new String[] { "Thanks for the help!", "Feel free to harvest these", "mushcubes." };
		}
		return new String[] { "Hi Onion." };
	}

}