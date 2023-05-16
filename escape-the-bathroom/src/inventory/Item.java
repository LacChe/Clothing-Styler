package inventory;

import audio.Audio;
import boot.Button;
import boot.Transform;
import graphics.Sprite;
import menu.Menu;

public class Item extends Button {

	private boolean inBag = false;
	private boolean inHand = false;
	private boolean used = false;
	private Sprite sprite;

	private double hoverScale = 1.0;
	private double hoverScaleMax = 1.25;

	public Item(String n, Transform t, int w, int h, Sprite s) {
		super(n, t, w, h);
		name = n;
		sprite = s;
		transform = t;
	}

	public Item(String n, Transform t, int w, int h, Sprite s, String attr) {
		super(n, t, w, h);
		name = n;
		sprite = s;
		transform = t;
		if (attr == "inBag") inBag = true;
	}

	public void render() {
		if (transform != null) {
			if (hover && hoverScale < hoverScaleMax) hoverScale += 0.02;
			else if (!hover && hoverScale > 1.0) hoverScale -= 0.02;
			sprite.render(new Transform(transform.position[0] - w * ((hoverScale - 1) / 2),
					transform.position[1] - h * ((hoverScale - 1) / 2), transform.scale[0] * hoverScale,
					transform.scale[1] * hoverScale));
		}
	}

	public void clicked() {
		Item combineItem = null;
		if (Menu.showSplash && name == "start") {
			Menu.showSplash = false;
		} else {
			if ((combineItem = Inventory.holding()) != null && inBag) {
				if (name == "megaphone" && combineItem.name == "brick") {
					Audio.play(Audio.combineItem);
					Inventory.addItem(new Item("wires", new Transform(0, 0, 1.5, 1.5), 32, 32, Sprite.wires, "inBag"));
					Inventory.addItem(
							new Item("plastic", new Transform(0, 0, 1.5, 1.5), 32, 32, Sprite.plastic, "inBag"));
					used = true;
					Inventory.unHold();
				}
				if ((name == "wires" && combineItem.name == "bandage")
						|| (combineItem.name == "wires" && name == "bandage")) {
					Audio.play(Audio.combineItem);
					Inventory.addItem(new Item("bandageWire", new Transform(0, 0, 1.5, 1.5), 32, 32, Sprite.bandageWire,
							"inBag"));
					Inventory.holding().used(true);
					used = true;
					Inventory.unHold();
				}
				if ((name == "bandageWire" && combineItem.name == "battery")
						|| (combineItem.name == "bandageWire" && name == "battery")) {
					Audio.play(Audio.combineItem);
					Inventory.addItem(
							new Item("component", new Transform(0, 0, 1.5, 1.5), 32, 32, Sprite.component, "inBag"));
					Inventory.holding().used(true);
					used = true;
					Inventory.unHold();
				}
			}
			if (combineItem == null || combineItem.name == name) {
				if (inBag && !inHand) {
					inHand = true;
				} else if (inBag && inHand) {
					inHand = false;
				} else if (!inBag && name != "brick") {
					setInBag();
					Inventory.addItem(this);
				}
			}
			if (name == "brick" && combineItem != null && combineItem.name == "broom") {
				setInBag();
				Inventory.addItem(this);
				Inventory.unHold();
			}
		}
	}

	public void setInBag() {
		inBag = true;
		if (w > 32 * transform.scale[0]) {
			double scaleX = 32 * transform.scale[0] / w;
			w *= scaleX;
			h *= scaleX;
			transform = new Transform(transform.position[0], transform.position[1], transform.scale[0] * scaleX,
					transform.scale[1] * scaleX);
		}
		if (h > 32 * transform.scale[1]) {
			double scaleY = 32 * transform.scale[1] / h;
			w *= scaleY;
			h *= scaleY;
			transform = new Transform(transform.position[0], transform.position[1] - w / 2, transform.scale[0] * scaleY,
					transform.scale[1] * scaleY);
		}
	}

	public void setTransform(Transform t) {
		transform = t;
	}

	public boolean used() {
		return used;
	}

	public void used(boolean u) {
		used = u;
	}

	public boolean inBag() {
		return inBag;
	}

	public boolean inHand() {
		return inHand;
	}

	public void inHand(boolean h) {
		inHand = h;
	}

	public Sprite sprite() {
		return sprite;
	}

}