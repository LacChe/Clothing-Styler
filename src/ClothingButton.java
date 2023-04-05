import java.awt.Color;

public class ClothingButton extends Button {

	public ClothingButton(int x, int y, int w, int h, Sprite s) {
		super(x, y, w, h, s);
	}

	public ClothingButton(int x, int y, int w, int h, String s, Color c) {
		super(x, y, w, h, s, c);
	}

	protected void action(boolean l) {
		super.action(l);
	}
	
}
