package inventory;

import java.util.ArrayList;

import audio.Audio;
import boot.Boot;
import boot.Transform;

public class Inventory {

	private static ArrayList<Item> items = new ArrayList<Item>();
	private static ArrayList<Item> updateItems = new ArrayList<Item>();

	private static double x = Boot.WIDTH / 2, y = 665;

	public static Item getItem(String name) {
		if (items.size() > 0) for (Item i : items)
			if (i.name() == name) return i;
		return null;
	}

	public static ArrayList<Item> items() {
		return items;
	}

	public static void addItem(Item i) {
		Audio.play(Audio.getItem);
		updateItems.add(i);
	}

	private static void updateItems() {
		for (Item i : updateItems) {
			items.add(i);
		}
		updateItems = new ArrayList<Item>();

		while (!cleanItems()) {
			// clean items
		}

		updatePos();

	}

	private static boolean cleanItems() {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).used()) {
				items.remove(i);
				return false;
			}
		}
		return true;
	}

	public static void render() {
		updateItems();
		for (int i = 0; i < items.size(); i++) {
			items.get(i).render();
			// items.get(i).renderButton(); // render button
		}
	}

	private static void updatePos() {
		for (int i = 0; i < items.size(); i++) {
			Transform t = new Transform(x - items.size() * 64 / 2 + i * 64, y - items.get(i).h() / 2, 1.5, 1.5);
			items.get(i).setTransform(t);
		}
	}

	public static void unHold() {
		for (Item i : Inventory.items())
			if (i.inHand()) {
				i.inHand(false);
			}
	}

	public static Item holding() {
		for (Item i : Inventory.items())
			if (i.inHand()) { return i; }
		return null;
	}

}