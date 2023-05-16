package menu;

import boot.Transform;
import graphics.Sprite;
import inventory.Item;

public class Menu {

	public static boolean showSplash = true;
	public static Item start = new Item("start", new Transform(10, 400), 130, 50, Sprite.start);

	public static boolean win = false;

	public static void render() {
		Sprite.title.render(new Transform(10, 10));
		if (!win) {
			start.render();
		} else {
			Sprite.win.render(new Transform(10, 400));
		}
	}

}
