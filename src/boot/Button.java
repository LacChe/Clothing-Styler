package boot;

import graphics.Camera;
import inventory.Inventory;
import menu.Menu;

public class Button {

	protected Transform transform;
	protected String name;
	protected double w, h;

	protected boolean hover = false;

	public Button(String n, Transform t, double w, double h) {
		name = n;
		transform = t;
		this.w = w * t.scale[0];
		this.h = h * t.scale[1];
	}

	public void clicked() {
		if (name == "doorHandle" && Inventory.holding() != null && Inventory.holding().name == "component") {
			Menu.showSplash = true;
			Menu.win = true;
		}
	}

	public void renderButton() {
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				if (i == 0 || j == 0 || i == w - 1 || j == h - 1) {
					if ((int) ((transform.position[0] + i) + ((transform.position[1] + j)) * Boot.WIDTH) < Camera
							.pixels().length
							&& (int) ((transform.position[0] + i) + ((transform.position[1] + j)) * Boot.WIDTH) >= 0)
						Camera.pixels()[(int) ((transform.position[0] + i)
								+ ((transform.position[1] + j)) * Boot.WIDTH)] = 0x000000;
				}
			}
		}
	}

	public double x() {
		return transform.position[0];
	}

	public double y() {
		return transform.position[1];
	}

	public double w() {
		return w;
	}

	public double h() {
		return h;
	}

	public void hover(boolean h) {
		hover = h;
	}

	public boolean hover() {
		return hover;
	}

	public String name() {
		return name;
	}

}