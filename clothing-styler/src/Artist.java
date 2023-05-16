import java.awt.Color;
import java.awt.Image;

public abstract class Artist {

	public static void draw() {
		Boot.getOffScreenGraphics().clearRect(0, 0, 1280, 720);

		Boot.getOffScreenGraphics().drawImage(BackSprite.backs.get(0).getImages()[0], 0, 0, 1280, 720, Boot.getBoot());

		Boot.getOffScreenGraphics().setColor(Color.GRAY);
		Boot.getOffScreenGraphics().drawRect(10 + (2 + 249) * 0, 10, 249, 660);
		Boot.getOffScreenGraphics().drawRect(10 + (2 + 249) * 1, 10, 249, 660);
		Boot.getOffScreenGraphics().drawRect(10 + (2 + 249) * 2, 10, 249, 660);
		Boot.getOffScreenGraphics().drawRect(10 + (2 + 249) * 3, 10, 249, 660);
		Boot.getOffScreenGraphics().setColor(Color.WHITE);
		Boot.getOffScreenGraphics().drawRect(10, 10, 1002, 660);
		Boot.getOffScreenGraphics().setColor(Color.PINK);
		Boot.getOffScreenGraphics().drawRect(10 + (2 + 249) * 4, 10, 249, 660);
		Boot.getOffScreenGraphics().drawString("Settings", 11 + (2 + 249) * 4, 21);

		Button.draw();
		Outfit.draw();

	}

	public static void drawButton(int x, int y, int w, int h, Color c, String s) {
		Boot.getOffScreenGraphics().setColor(c);
		Boot.getOffScreenGraphics().fillRect(x, y, w, h);
		Boot.getOffScreenGraphics().setColor(Color.BLACK);
		Boot.getOffScreenGraphics().drawString(s, x + 2, y + 12);
	}

	public static void drawButton(int x, int y, int w, int h, int sx, int sy, int sw, int sh, Sprite s) {
		Image a = s.getImages()[0];
		Boot.getOffScreenGraphics().drawImage(a, x, y, x + w, y + h, sx, sy, sx + sw, sy + sh, Boot.getBoot());
		// draw (img, dst, strt, obsv)
	}

	public static void drawClothe(int x, int y, int w, int h, int sx, int sy, int sw, int sh, Sprite s) {
		Image a = s.getImages()[0];
		Boot.getOffScreenGraphics().drawImage(a, x, y, x + w, y + h, sx, sy, sx + sw, sy + sh, Boot.getBoot());
	}

}
