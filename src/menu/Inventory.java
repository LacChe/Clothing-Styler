package menu;

import render.Sprite;
import render.SpriteSheet;

public class Inventory {

	private Item[] items = new Item[4 * 3];
	private int itemCount = 3, mushroomCount = 1, fishCount = 1, fruitchips = 0;

	public Inventory() {
		// test
		items[0] = Item.mushCube;
		items[1] = Item.jellyDisc;
		itemCount = 2;
		for (int i = 2; i < items.length; i++) {
			items[i] = Item.empty;
		}
	}

	public void renderItems(int[] mainPixels, int mainW, int mainH, int offX, int offY, int selected) {
		for (int ind = 0; ind < items.length; ind++) {
			int offXBox = ind % 4;
			int offYBox = ind / 4;
			if (ind == selected) {
				for (int i = 0; i < Sprite.selected.w(); i++) {
					for (int j = 0; j < Sprite.selected.h(); j++) {
						if (((Sprite.selected.pixels((i + Sprite.selected.pathX())
								+ (j + Sprite.selected.pathY()) * SpriteSheet.ITEMS.w()) >> 24) & 0xff) != 0)
							mainPixels[(i + offX + offXBox * 70) + (j + offY + offYBox * 70) * mainW] = Sprite.selected
									.pixels((i + Sprite.selected.pathX())
											+ (j + Sprite.selected.pathY()) * SpriteSheet.ITEMS.w());
					}
				}
			}
			items[ind].render(mainPixels, mainW, mainH, offX + offXBox * 70, offY + offYBox * 70);
		}
	}

	public Item[] getItems() {
		return items;
	}

	public boolean remove(Item item) {
		int removePos = -1;
		for (int i = 0; i < items.length; i++) {
			if (items[i] == item) removePos = i;
		}
		if (removePos == -1) return false;
		for (int i = removePos; i < items.length - 1; i++) {
			items[i] = items[i + 1];
		}
		items[items.length - 1] = Item.empty;
		return true;
	}

	public int mushroomCount() {
		return mushroomCount;
	}

	public void mushroomCount(int m) {
		mushroomCount = m;
	}

	public void addMushroom() {
		mushroomCount++;
	}

	public int fishCount() {
		return fishCount;
	}

	public void fishCount(int f) {
		fishCount = f;
	}

	public void addFish() {
		fishCount++;
	}

	public int fruitchips() {
		return fruitchips;
	}

	public void fruitchips(int f) {
		fruitchips = f;
	}

	public void addFruitchips(int f) {
		fruitchips += f;
	}

	public boolean addRubbish() {
		for (int i = 0; i < items.length; i++) {
			if (items[i] == Item.rubbish) return false;
		}
		items[itemCount] = Item.rubbish;
		itemCount++;
		return true;
	}
}