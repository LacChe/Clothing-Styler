package graphics;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import boot.Boot;
import boot.Mouse;
import boot.Transform;
import inventory.Inventory;
import inventory.Item;
import menu.Menu;
import room.RoomManager;

public class Camera {

	private static BufferedImage image;
	private static int[] pixels;

	private static int changingRooms = 0;

	public static void init() {
		image = new BufferedImage(Boot.WIDTH, Boot.HEIGHT, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	}

	public static void update() {
	}

	public static void render() {
		for (int i = 0; i < pixels.length; i++)
			pixels[i] = 0x120707;

		if (Menu.showSplash) {
			Menu.render();
		} else {
			if (changingRooms > 0) {
				for (int x = 20; x < 620; x++)
					for (int y = 20; y < 620; y++)
						pixels[x + y * Boot.WIDTH] = 0x000000;
				changingRooms--;
			} else {
				RoomManager.render();
			}
			Inventory.render();
			Item heldItem = Inventory.holding();
			if (heldItem != null) {
				heldItem.sprite().render(new Transform(Mouse.x - heldItem.w() / 2, Mouse.y - heldItem.h() / 2));
			}
		}
	}

	public static int[] pixels() {
		return pixels;
	}

	public static BufferedImage image() {
		return image;
	}

	public static void changeRooms() {
		changingRooms = 50;
	}

	public static boolean changingRooms() {
		return changingRooms > 0;
	}

}