package menu;

import render.Sprite;
import render.SpriteSheet;

public class Item {

	public static Item empty;
	public static Item mushCube;
	public static Item jellyDisc;
	public static Item rubbish;

	private Sprite s;
	private String description;

	public Item() {
		this.s = null;
		this.description = "";
	}

	public Item(Sprite s, String description) {
		this.s = s;
		this.description = description;
	}

	public void render(int[] mainPixels, int mainW, int mainH, int offX, int offY) {
		if (s != null) {
			for (int i = 0; i < s.w(); i++) {
				for (int j = 0; j < s.h(); j++) {
					if (((s.pixels((i + s.pathX()) + (j + s.pathY()) * SpriteSheet.ITEMS.w()) >> 24) & 0xff) != 0)
						mainPixels[(i + offX) + (j + offY) * mainW] = s
								.pixels((i + s.pathX()) + (j + s.pathY()) * SpriteSheet.ITEMS.w());
				}
			}
		}
	}

	public String getDescription() {
		return description;
	}

}
