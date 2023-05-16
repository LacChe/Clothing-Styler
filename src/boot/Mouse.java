package boot;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.MemoryImageSource;

import javax.swing.JFrame;

import graphics.Camera;
import inventory.Inventory;
import inventory.Item;
import menu.Menu;
import room.RoomChanger;
import room.RoomManager;

public class Mouse implements MouseListener {

	public static double x, y;

	public static boolean heldLeft = false;
	private static Toolkit toolkit = Toolkit.getDefaultToolkit();

	public static void init(JFrame f) {
		int[] pixels = new int[32 * 32];
		for (int i = 0; i < 32; i++) {
			for (int j = 0; j < 32; j++) {
				// pixels[i + j * 32] = Sprite.crossHair.pixels(i + (32 + j) *
				// SpriteSheet.ui.w());
			}
		}
		Image image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(32, 32, pixels, 0, 32));
		Cursor c = toolkit.createCustomCursor(image, new Point(16, 16), "img");
		f.setCursor(c);
	}

	public void update(JFrame f) {
		x = MouseInfo.getPointerInfo().getLocation().getX() - Boot.boot.frame().getLocationOnScreen().getX();
		y = MouseInfo.getPointerInfo().getLocation().getY() - Boot.boot.frame().getLocationOnScreen().getY() - 32;

		boolean inButton = false;
		if (Menu.showSplash && !Menu.win) {
			if (x > Menu.start.x() && x < Menu.start.x() + Menu.start.w() && y > Menu.start.y()
					&& y < Menu.start.y() + Menu.start.h()) {
				Menu.start.hover(true);
				Cursor c = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
				f.setCursor(c);
				inButton = true;
			} else {
				Menu.start.hover(false);
			}
		} else {
			for (Item i : Inventory.items()) {
				// System.out.println(r.x() + " " + x + " " + (r.x() + r.w()));
				// System.out.println(r.y() + " " + y + " " + (r.y() + r.h()));
				if (!i.used()) {
					if (x > i.x() && x < i.x() + i.w() && y > i.y() && y < i.y() + i.h()) {
						i.hover(true);
						Cursor c = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
						f.setCursor(c);
						inButton = true;
					} else {
						i.hover(false);
					}
				}
			}

			if (!Camera.changingRooms()) {
				for (RoomChanger r : RoomManager.currentRoom().roomChangers()) {
					// System.out.println(r.x() + " " + x + " " + (r.x() + r.w()));
					// System.out.println(r.y() + " " + y + " " + (r.y() + r.h()));
					if (x > r.x() && x < r.x() + r.w() && y > r.y() && y < r.y() + r.h()) {
						Cursor c = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
						f.setCursor(c);
						inButton = true;
					}
				}
				for (Item i : RoomManager.currentRoom().items()) {
					// System.out.println(r.x() + " " + x + " " + (r.x() + r.w()));
					// System.out.println(r.y() + " " + y + " " + (r.y() + r.h()));
					if (x > i.x() && x < i.x() + i.w() && y > i.y() && y < i.y() + i.h()) {
						i.hover(true);
						Cursor c = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
						f.setCursor(c);
						inButton = true;
					} else {
						i.hover(false);
					}
				}
				for (Button b : RoomManager.currentRoom().buttons()) {
					// System.out.println(r.x() + " " + x + " " + (r.x() + r.w()));
					// System.out.println(r.y() + " " + y + " " + (r.y() + r.h()));
					if (x > b.x() && x < b.x() + b.w() && y > b.y() && y < b.y() + b.h()) {
						b.hover(true);
						Cursor c = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
						f.setCursor(c);
						inButton = true;
					} else {
						b.hover(false);
					}
				}
			}
		}
		if (!inButton) {
			Cursor c = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
			f.setCursor(c);
		}
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		if (e.getButton() == 1) heldLeft = true;
	}

	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == 1) heldLeft = false;
		if (Menu.showSplash && !Menu.win) {
			if (x > Menu.start.x() && x < Menu.start.x() + Menu.start.w() && y > Menu.start.y()
					&& y < Menu.start.y() + Menu.start.h()) {
				Menu.start.clicked();
			}
		} else {
			for (Item i : Inventory.items())
				if (!i.used()) {
					if (x > i.x() && x < i.x() + i.w() && y > i.y() && y < i.y() + i.h()) {
						i.clicked();
					}
				}
			if (!Camera.changingRooms()) {
				for (RoomChanger r : RoomManager.currentRoom().roomChangers())
					if (x > r.x() && x < r.x() + r.w() && y > r.y() && y < r.y() + r.h()) r.clicked();
				for (Item i : RoomManager.currentRoom().items())
					if (x > i.x() && x < i.x() + i.w() && y > i.y() && y < i.y() + i.h() && !i.inBag()) i.clicked();
				for (Button b : RoomManager.currentRoom().buttons())
					if (x > b.x() && x < b.x() + b.w() && y > b.y() && y < b.y() + b.h()) b.clicked();
			}
		}
		// System.out.println(x + " " + y);
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

}
