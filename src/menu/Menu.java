package menu;

import render.Camera;
import render.Sprite;
import render.SpriteSheet;
import render.TextEngine;
import world.World;

public class Menu {

	private static Menu menu;

	private static boolean showMain;
	private static boolean showStats;
	private static boolean showFriends;
	private static boolean showInventory;
	private static boolean showSettings;

	private int posMain;
	private int posInventory;
	private int posFriends;
	private int posSettings;

	private TextEngine main;
	private TextEngine stats;
	private TextEngine inventory;
	private TextEngine friends;
	private TextEngine settings;

	private int x, y, w, h;
	private int[] pixels;

	public static void init() {
		menu = new Menu(32, 32, 480, 480 / 16 * 9);
	}

	public Menu(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		main = new TextEngine(0, 0, w / 3, h, Sprite.menuMainBG);
		stats = new TextEngine(w / 3, 0, w / 3 * 2, h);
		inventory = new TextEngine(w / 3, 0, w / 3 * 2, h);
		friends = new TextEngine(w / 3, 0, w / 3 * 2, h);
		settings = new TextEngine(w / 3, 0, w / 3 * 2, h);
		main.renderString(new String[] { "&Stats", "", "Bag", "", "Citizens", "", "Settings", "",
				" " + World.world().player().getInv().fruitchips() });
		stats.renderString(new String[] { "", "", "", "", "", "", "", "", "", "", "You are an Onion." });
		inventory.renderString(new String[] { "", "", "", "", "", "", "", "", "", "", "Stuff you have." });
		friends.renderString(new String[] { "", "", "", "", "", "", "", "", "", "", "Your friends." });
		settings.renderString(new String[] { "", "", "", "", "", "Music", "SFX", "Quit" });

		pixels = new int[w * h];

		showStats = true; // set true to show for first use
		showFriends = false;
		showInventory = false;
		showSettings = false;
		showMain = true; // set true to make main current
		menu = this;
		posMain = 0;
		posInventory = 0;
		posFriends = 0;
		posSettings = 3;
		updatePixels();
	}

	public void updatePixels() {
		String[] mainS = new String[] { "Stats", "", "Bag", "", "Citizens", "", "Settings", "", "",
				"   " + World.world().player().getInv().fruitchips(), };
		mainS[posMain] = "&" + mainS[posMain];
		main.renderString(mainS);
		for (int i = 0; i < main.w(); i++) {
			for (int j = 0; j < main.h(); j++) {
				pixels[i + j * w] = main.pixels()[i + j * main.w()];
			}
		}
		for (int i = 0; i < 32; i++) {
			for (int j = 0; j < 32; j++) {
				int pixel = Sprite.fruitchip.pixels(64 + i + j * SpriteSheet.ITEMS.w());
				if (((pixel >> 24) & 0xff) != 0) pixels[30 + i + (200 + j) * w] = pixel;
			}
		}
		for (int i = 0; i < stats.w(); i++) {
			for (int j = 0; j < stats.h(); j++) {
				int pixel = Sprite.menuStatsBG.pixels(
						(i + Sprite.menuStatsBG.pathX()) + (j + Sprite.menuStatsBG.pathY()) * SpriteSheet.menuETC.w());
				pixels[(i + stats.x()) + j * w] = pixel;
			}
		}
		if (showStats) {
			stats.renderString(new String[] { "", "", "", "", "", "", "", "", "", "", "You are an Onion." });
			for (int i = 0; i < stats.w(); i++) {
				for (int j = 0; j < stats.h(); j++) {
					if (stats.pixels()[i + j * stats.w()] >> 24 != 0)
						pixels[i + stats.x() + j * w] = stats.pixels()[i + j * stats.w()];
				}
			}
		} else if (showInventory) {
			Inventory temp = World.world().player().getInv();

			String mushCount = "";
			String fishCount = "";
			mushCount = temp.mushroomCount() + "";
			fishCount = temp.fishCount() + "";
			inventory.renderString(new String[] { "" + mushCount + "   " + fishCount, "", "", "", "", "", "", "", "",
					"", temp.getItems()[posInventory].getDescription() });

			temp.renderItems(pixels, w, h, inventory.x() + 10, 10, posInventory);
			for (int i = 0; i < inventory.w(); i++) {
				for (int j = 0; j < inventory.h(); j++) {
					if (inventory.pixels()[i + j * inventory.w()] >> 24 != 0)
						pixels[i + inventory.x() + j * w] = inventory.pixels()[i + j * inventory.w()];
				}
			}

		} else if (showFriends) {
			friends.renderString(new String[] { "", "", "", "", "", "", "", "", "", "", "Your friends." });
			for (int i = 0; i < friends.w(); i++) {
				for (int j = 0; j < friends.h(); j++) {
					if (friends.pixels()[i + j * friends.w()] >> 24 != 0)
						pixels[i + friends.x() + j * w] = friends.pixels()[i + j * friends.w()];
				}
			}
		} else if (showSettings) {
			String[] settingsS = new String[] { "", "", "", "     Music", "", "     SFX", "", "     Quit" };
			settingsS[posSettings] = "&" + settingsS[posSettings];
			settings.renderString(settingsS);
			for (int i = 0; i < settings.w(); i++) {
				for (int j = 0; j < settings.h(); j++) {
					if (settings.pixels()[i + j * settings.w()] >> 24 != 0)
						pixels[i + settings.x() + j * w] = settings.pixels()[i + j * settings.w()];
				}
			}
		}
	}

	public void pressed(String dir) {
		if (showMain) {
			switch (dir) {
			case "W":
				posMain -= 2;
				if (posMain < 0) posMain = 0;
				break;
			case "A":
				break;
			case "S":
				posMain += 2;
				if (posMain > 6) posMain = 6;
				break;
			case "D":
				select();
				break;
			}
		} else if (showSettings) {
			switch (dir) {
			case "W":
				posSettings -= 2;
				if (posSettings < 3) posSettings = 3;
				break;
			case "A":
				goBack();
				break;
			case "S":
				posSettings += 2;
				if (posSettings > 7) posSettings = 7;
				break;
			case "D":
				select();
				break;
			}
		} else if (showInventory) {
			switch (dir) {
			case "W":
				posInventory -= 4;
				if (posInventory < 0) posInventory = 0;
				break;
			case "A":
				posInventory -= 1;
				if (posInventory < 0) posInventory = 0;
				break;
			case "S":
				posInventory += 4;
				if (posInventory >= World.world().player().getInv().getItems().length)
					posInventory = World.world().player().getInv().getItems().length - 1;
				break;
			case "D":
				posInventory += 1;
				if (posInventory >= World.world().player().getInv().getItems().length)
					posInventory = World.world().player().getInv().getItems().length - 1;
				break;
			}
		}
		/*
		System.out.println(
		showMain + " " + showStats + " " + showInventory + " " + showFriends + " " + showSettings + " ");
		*/
		updatePixels();
	}

	public void select() {
		if (showMain) {
			switch (posMain) {
			case 0:
				showMain = true;
				showStats = true;
				showFriends = false;
				showSettings = false;
				showInventory = false;
				break;
			case 2:
				showMain = false;
				showStats = false;
				showFriends = false;
				showSettings = false;
				showInventory = true;
				break;
			case 4:
				showMain = false;
				showStats = false;
				showInventory = false;
				showSettings = false;
				showFriends = true;
				break;
			case 6:
				showMain = false;
				showStats = false;
				showInventory = false;
				showFriends = false;
				showSettings = true;
				break;
			}
		}
		if (!showMain) main.setBG(Sprite.menuMainBGUnselected);
		updatePixels();
	}

	public void goBack() {
		if (showMain) {
			Camera.camera().showMenu(false);
		} else {
			showMain = true;
		}
		if (showMain) main.setBG(Sprite.menuMainBG);
		updatePixels();
	}

	public int[] pixels() {
		return pixels;
	}

	public boolean showMain() {
		return showMain;
	}

	public boolean showStats() {
		return showStats;
	}

	public boolean showfriends() {
		return showFriends;
	}

	public boolean showInventory() {
		return showInventory;
	}

	public boolean showSettings() {
		return showSettings;
	}

	public void showMain(boolean show) {
		showMain = show;
	}

	public void showStats(boolean show) {
		showStats = show;
	}

	public void showfriends(boolean show) {
		showFriends = show;
	}

	public void showInventory(boolean show) {
		showInventory = show;
	}

	public void showSettings(boolean show) {
		showSettings = show;
	}

	public static Menu menu() {
		return menu;
	}

	public int x() {
		return x;
	}

	public int y() {
		return y;
	}

	public int w() {
		return w;
	}

	public int h() {
		return h;
	}

}
