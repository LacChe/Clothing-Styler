import java.awt.Color;
import java.util.ArrayList;

public class Button {
	// 1XXX Menu buttons
	// >=5XXX Clothing cycle hide/show

	protected static ArrayList<Button> buttonList = new ArrayList<Button>();

	protected int x, y, w, h;
	protected String s;
	protected Color c;
	protected Sprite sprite;
	protected boolean show;

	protected boolean showSprite = true;

	public Button() {
		x = 16;
		y = 16;
		w = 40;
		h = 16;
		s = "Button";
		c = Color.WHITE;
		show = true;
		buttonList.add(this);
	}

	public Button(int x, int y, int w, int h, Sprite s) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.setSprite(s);
		show = true;
		buttonList.add(this);
	}

	public Button(int x, int y, int w, int h, String s, Color c) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.s = s;
		this.c = c;
		show = true;
		buttonList.add(this);
	}

	public static void isClicked(int x, int y) {
		isLeftClicked(x, y);
		isRightClicked(x, y);
	}

	private static void isLeftClicked(int x, int y) {
		for (Button b : buttonList) {
			if (b.x < x && b.x + b.w > x && b.y < y && b.y + b.h > y)
				b.action(true);
		}
	}

	private static void isRightClicked(int x, int y) {
		for (Button b : buttonList) {
			if (b.x < x && b.x + b.w > x && b.y < y && b.y + b.h > y)
				b.action(false);
		}
	}

	protected void action(boolean l) {
		if (l) {

			if (this.getClass().toString().equals("class ClothingButton")) {
				if (this.c == Color.GRAY) {
					this.c = Color.WHITE;
					this.showSprite = true;
				} else {
					this.c = Color.GRAY;
					this.showSprite = false;
				}
			} else {

				System.err.println(this.getClass().toString());

			}
		} else {

		}
	}

	public static void draw() {
		for (Button b : buttonList) {
			if (b.show)
				b.drawButton();
		}
	}

	private void drawButton() {
		if (sprite != null) {
			Artist.drawButton(x, y, w, h, sprite.getSX(), sprite.getSY(), sprite.getSW(), sprite.getSH(), sprite);
		} else
			Artist.drawButton(x, y, w, h, c, s);
	}

	public static ArrayList<Button> getButtonsList() {
		return buttonList;
	}

	public static void setButtonsList(ArrayList<Button> buttonsList) {
		Button.buttonList = buttonsList;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public boolean showSprite() {
		return showSprite;
	}

}
