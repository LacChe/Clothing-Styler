package entity;

import render.Animation;
import render.Sprite;
import world.World;

public class StaticEntity extends Entity {

	public static StaticEntity CAVE_UNDERLAY_1;
	public static StaticEntity VOLCANO_UNDERLAY_1;
	public static StaticEntity LAVA;
	public static StaticEntity FARM_UNDERLAY_1;
	public static StaticEntity FOUNTAIN;

	public static StaticEntity ROOM_ONION_UNDERLAY;

	public static StaticEntity W_UNDERLAY_1;
	public static StaticEntity W_UNDERLAY_2;
	public static StaticEntity W_UNDERLAY_3;
	public static StaticEntity W_UNDERLAY_4;
	public static StaticEntity W_UNDERLAY_5;
	public static StaticEntity W_UNDERLAY_6;
	public static StaticEntity W_OVERLAY_1;
	public static StaticEntity W_OVERLAY_2;
	public static StaticEntity W_OVERLAY_3;
	public static StaticEntity W_OVERLAY_4;

	public static StaticEntity NW_UNDERLAY_1;
	public static StaticEntity NW_UNDERLAY_2;
	public static StaticEntity NW_UNDERLAY_3;
	public static StaticEntity NW_UNDERLAY_4;
	public static StaticEntity NW_UNDERLAY_5;
	public static StaticEntity NW_UNDERLAY_6;
	public static StaticEntity NW_UNDERLAY_7;
	public static StaticEntity NW_UNDERLAY_8;
	public static StaticEntity NW_UNDERLAY_9;
	public static StaticEntity NW_UNDERLAY_10;
	public static StaticEntity NW_UNDERLAY_11;
	public static StaticEntity NW_UNDERLAY_12;
	public static StaticEntity NW_UNDERLAY_13;
	public static StaticEntity NW_UNDERLAY_14;
	public static StaticEntity NW_UNDERLAY_15;
	public static StaticEntity NW_UNDERLAY_16;
	public static StaticEntity NW_UNDERLAY_17;
	public static StaticEntity NW_UNDERLAY_18;
	public static StaticEntity NW_UNDERLAY_19;
	public static StaticEntity NW_UNDERLAY_20;
	public static StaticEntity NW_UNDERLAY_21;
	public static StaticEntity NW_UNDERLAY_22;
	public static StaticEntity NW_UNDERLAY_23;
	public static StaticEntity NW_UNDERLAY_24;
	public static StaticEntity NW_UNDERLAY_25;
	public static StaticEntity NW_UNDERLAY_26;
	public static StaticEntity NW_OVERLAY_1;
	public static StaticEntity NW_OVERLAY_2;
	public static StaticEntity NW_OVERLAY_3;
	public static StaticEntity NW_OVERLAY_4;
	public static StaticEntity NW_OVERLAY_5;
	public static StaticEntity NW_OVERLAY_6;
	public static StaticEntity NW_OVERLAY_7;
	public static StaticEntity NW_OVERLAY_8;
	public static StaticEntity NW_OVERLAY_9;
	public static StaticEntity NW_OVERLAY_10;
	public static StaticEntity NW_OVERLAY_11;
	public static StaticEntity NW_OVERLAY_12;
	public static StaticEntity NW_OVERLAY_13;

	public static StaticEntity N_UNDERLAY_1;
	public static StaticEntity N_UNDERLAY_2;
	public static StaticEntity N_UNDERLAY_3;
	public static StaticEntity N_UNDERLAY_4;
	public static StaticEntity N_OVERLAY_1;
	public static StaticEntity N_OVERLAY_2;
	public static StaticEntity N_OVERLAY_3;
	public static StaticEntity N_OVERLAY_4;
	public static StaticEntity N_OVERLAY_5;
	public static StaticEntity N_OVERLAY_6;

	public static StaticEntity NE_UNDERLAY_1;
	public static StaticEntity NE_UNDERLAY_2;
	public static StaticEntity NE_UNDERLAY_3;
	public static StaticEntity NE_UNDERLAY_4;
	public static StaticEntity NE_UNDERLAY_5;
	public static StaticEntity NE_OVERLAY_1;
	public static StaticEntity NE_OVERLAY_2;
	public static StaticEntity NE_OVERLAY_3;

	public static StaticEntity SE_UNDERLAY_1;
	public static StaticEntity SE_OVERLAY_1;

	public static StaticEntity E_OVERLAY_1;
	public static StaticEntity E_OVERLAY_2;
	public static StaticEntity E_OVERLAY_3;
	public static StaticEntity E_OVERLAY_4;
	public static StaticEntity E_UNDERLAY_1;
	public static StaticEntity E_UNDERLAY_2;

	public static StaticEntity C_UNDERLAY_1;
	public static StaticEntity C_UNDERLAY_2;
	public static StaticEntity C_UNDERLAY_3;
	public static StaticEntity C_OVERLAY_1;
	public static StaticEntity C_OVERLAY_2;
	public static StaticEntity C_OVERLAY_3;
	public static StaticEntity C_OVERLAY_4;

	public static StaticEntity S_OVERLAY_1;
	public static StaticEntity S_OVERLAY_2;
	public static StaticEntity S_OVERLAY_3;
	public static StaticEntity S_UNDERLAY_1;
	public static StaticEntity S_UNDERLAY_2;

	public static int[] POS_MUSHROOM_HOUSE;
	public static int[] POS_CENTER_STORE;
	public static int[] POS_WOODS_STORE;
	public static int[] POS_STALL;
	public static int[] POS_SHROOM_DOOR;
	public static int[] POS_SHROOM;
	public static int[] POS_TREE_HOUSE;
	public static int[] POS_TREE;
	public static int[] POLE;

	public StaticEntity(int x, int y, int w, int h, int sXOff, int sYOff, Animation[] animations) {
		super(x, y, w, h, sXOff, sYOff, animations);
	}

	public StaticEntity(int x, int y, int[] i, Animation[] animations) {
		super(x, y, i[0], i[1], i[2], i[3], animations);
	}

	public StaticEntity(int x, int y, int w, int h, int sXOff, int sYOff, Animation animation) {
		super(x, y, w, h, sXOff, sYOff, animation);
	}

	public StaticEntity(int x, int y, int[] i, Animation animation) {
		super(x, y, i[0], i[1], i[2], i[3], animation);
	}

	public StaticEntity(int x, int y, Sprite s) {
		super(x, y, s.w(), s.h(), 0, 0, s);
	}

	public StaticEntity(int x, int y, int[] i, Sprite sprite) {
		super(x, y, i[0], i[1], i[2], i[3], sprite);
	}

	@Override
	public void update(World world) {
		if (animations != null && animations.length > 0) {
			if (animations[currentAnim].currentSprite() == Sprite.groundPole && World.world().player().holdingPole())
				animations[currentAnim].currentSprite(Sprite.noPole);
			if (animations[currentAnim].currentSprite() == Sprite.noPole && !World.world().player().holdingPole())
				animations[currentAnim].currentSprite(Sprite.groundPole);
		}
	}

}