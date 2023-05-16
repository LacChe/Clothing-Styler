import java.awt.Color;
import java.util.ArrayList;

public class Outfit {

	private static ArrayList<Outfit> outfits = new ArrayList<Outfit>();

	public static final int[][][] SpriteSnap1 = new int[][][] { //
			{ { 11 + 125, 11 + 140 }, { 11 + 125, 11 + 160 }, { 11 + 125, 11 + 180 }, { 11 + 125, 11 + 220 },
					{ 11 + 125, 11 + 240 }, //
					{ 11 + 125, 11 + 240 }, { 11 + 125, 11 + 390 }, { 11 + 125, 11 + 390 }, { 11 + 125, 11 + 550 },
					{ 11 + 125, 11 + 550 } }, //
			{ { 11 + 375, 11 + 140 }, { 11 + 375, 11 + 160 }, { 11 + 375, 11 + 180 }, { 11 + 375, 11 + 220 },
					{ 11 + 375, 11 + 240 }, //
					{ 11 + 375, 11 + 240 }, { 11 + 375, 11 + 390 }, { 11 + 375, 11 + 390 }, { 11 + 375, 11 + 550 },
					{ 11 + 375, 11 + 550 } }, //
			{ { 11 + 625, 11 + 140 }, { 11 + 625, 11 + 160 }, { 11 + 625, 11 + 180 }, { 11 + 625, 11 + 220 },
					{ 11 + 625, 11 + 240 }, //
					{ 11 + 625, 11 + 240 }, { 11 + 625, 11 + 390 }, { 11 + 625, 11 + 390 }, { 11 + 625, 11 + 550 },
					{ 11 + 625, 11 + 550 } }, //
			{ { 11 + 875, 11 + 140 }, { 11 + 875, 11 + 160 }, { 11 + 875, 11 + 180 }, { 11 + 875, 11 + 220 },
					{ 11 + 875, 11 + 240 }, //
					{ 11 + 875, 11 + 240 }, { 11 + 875, 11 + 390 }, { 11 + 875, 11 + 390 }, { 11 + 875, 11 + 550 },
					{ 11 + 875, 11 + 550 } } };//

	private ClothingSprite[] sprites;
	private ClothingButton[] buttons;
	int angleCount;
	int itemCount;

	public Outfit(String fileName, String[] itemNames, int[][][] dimensions, int angleCount, int itemCount) {
		this.angleCount = angleCount;
		this.itemCount = itemCount;
		setSprites(new ClothingSprite[angleCount * itemCount]);
		setButtons(new ClothingButton[angleCount * itemCount]);

		addSprites(fileName, dimensions);
		addButtons(itemNames);

		outfits.add(this);
	}

	private void addSprites(String filename, int[][][] dimensions) {
		for (int i = 0; i < angleCount; i++) {
			for (int j = 0; j < itemCount; j++) {
				ClothingSprite s = new ClothingSprite(filename, SpriteSnap1[i][j][0] + dimensions[i][j][0],
						SpriteSnap1[i][j][1] + dimensions[i][j][1], dimensions[i][j][2], dimensions[i][j][3],
						dimensions[i][j][4], dimensions[i][j][5], dimensions[i][j][6], dimensions[i][j][7]);
				this.sprites[itemCount * i + j] = s;
			}
		}
	}

	private void addButtons(String[] itemNames) {
		for (int i = 0; i < angleCount; i++) {
			for (int j = 0; j < itemCount; j++) {

				int tx = 1020 + i * 60;
				int ty = 300 + j * 25;
				int tw = 55;
				int th = 16;

				ClothingButton b = new ClothingButton(tx, ty, tw, th, itemNames[j], Color.WHITE);
				// Button b = new Button(tx, ty, tw, th, this.sprites[itemCount * i + j]);
				this.buttons[itemCount * i + j] = b;
			}
		}
	}

	public static void draw() {
		for (Outfit o : outfits) {
			o.drawClothes();
		}
	}

	public void drawClothes() {
		if (buttons[1].showSprite() && sprites[1] != null) {
			Artist.drawClothe(sprites[1].x - sprites[1].w / 2, sprites[1].y, sprites[1].w, sprites[1].h, sprites[1].sx,
					sprites[1].sy, sprites[1].sw, sprites[1].sh, sprites[1]);
		}
		if (buttons[0].showSprite() && sprites[0] != null) {
			Artist.drawClothe(sprites[0].x - sprites[0].w / 2, sprites[0].y, sprites[0].w, sprites[0].h, sprites[0].sx,
					sprites[0].sy, sprites[0].sw, sprites[0].sh, sprites[0]);
		}
		if (buttons[8].showSprite() && sprites[8] != null) {
			Artist.drawClothe(sprites[8].x - sprites[8].w / 2, sprites[8].y, sprites[8].w, sprites[8].h, sprites[8].sx,
					sprites[8].sy, sprites[8].sw, sprites[8].sh, sprites[8]);
		}
		if (buttons[6].showSprite() && sprites[6] != null) {
			Artist.drawClothe(sprites[6].x - sprites[6].w / 2, sprites[6].y, sprites[6].w, sprites[6].h, sprites[6].sx,
					sprites[6].sy, sprites[6].sw, sprites[6].sh, sprites[6]);
		}
		if (buttons[7].showSprite() && sprites[7] != null) {
			Artist.drawClothe(sprites[7].x - sprites[7].w / 2, sprites[7].y, sprites[7].w, sprites[7].h, sprites[7].sx,
					sprites[7].sy, sprites[7].sw, sprites[7].sh, sprites[7]);
		}
		if (buttons[4].showSprite() && sprites[4] != null) {
			Artist.drawClothe(sprites[4].x - sprites[4].w / 2, sprites[4].y, sprites[4].w, sprites[4].h, sprites[4].sx,
					sprites[4].sy, sprites[4].sw, sprites[4].sh, sprites[4]);
		}
		if (buttons[9].showSprite() && sprites[9] != null) {
			Artist.drawClothe(sprites[9].x - sprites[9].w / 2, sprites[9].y, sprites[9].w, sprites[9].h, sprites[9].sx,
					sprites[9].sy, sprites[9].sw, sprites[9].sh, sprites[9]);
		}
		if (buttons[5].showSprite() && sprites[5] != null) {
			Artist.drawClothe(sprites[5].x - sprites[5].w / 2, sprites[5].y, sprites[5].w, sprites[5].h, sprites[5].sx,
					sprites[5].sy, sprites[5].sw, sprites[5].sh, sprites[5]);
		}
		if (buttons[3].showSprite() && sprites[3] != null) {
			Artist.drawClothe(sprites[3].x - sprites[3].w / 2, sprites[3].y, sprites[3].w, sprites[3].h, sprites[3].sx,
					sprites[3].sy, sprites[3].sw, sprites[3].sh, sprites[3]);
		}
		if (buttons[2].showSprite() && sprites[2] != null) {
			Artist.drawClothe(sprites[2].x - sprites[2].w / 2, sprites[2].y, sprites[2].w, sprites[2].h, sprites[2].sx,
					sprites[2].sy, sprites[2].sw, sprites[2].sh, sprites[2]);
		}
	}

	public Sprite[] getSprites() {
		return sprites;
	}

	public void setSprites(ClothingSprite[] sprites) {
		this.sprites = sprites;
	}

	public Button[] getButtons() {
		return buttons;
	}

	public void setButtons(ClothingButton[] b) {
		this.buttons = b;
	}

}
