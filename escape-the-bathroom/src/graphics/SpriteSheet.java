package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	public static SpriteSheet bathroom = new SpriteSheet("bathroom");
	public static SpriteSheet items = new SpriteSheet("items");
	public static SpriteSheet splash = new SpriteSheet("splash");

	private BufferedImage image;
	private String path;
	private int w, h;
	private int[] pixels;

	public SpriteSheet(String path) {
		this.path = path;
		loadSheet();
	}

	private void loadSheet() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource("/" + path + ".png"));
			w = image.getWidth();
			h = image.getHeight();
			pixels = new int[w * h];
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int w() {
		return w;
	}

	public int h() {
		return h;
	}

	public String name() {
		return path;
	}

	public int[] pixels() {
		return pixels;
	}

	public BufferedImage image() {
		return image;
	}

}