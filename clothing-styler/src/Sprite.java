import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Sprite {// ABSTRACT

	protected int x, y, w, h;// where sprite is on map
	protected int sx, sy, sw, sh;// where sprite is on spritesheet
	protected Image[] imgs;// animation frames
	protected int frame;// current image
	protected int dFrame;
	protected String name;

	public Sprite(String[] fileName, int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		setImgs(fileName);
		frame = 0;
	}

	public Sprite(String fileName, int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		setImgs(fileName);
		frame = 0;
	}

	public Sprite(String fileName, int x, int y, int w, int h, int sx, int sy, int sw, int sh) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.sx = sx;
		this.sy = sy;
		this.sw = sw;
		this.sh = sh;
		setImgs(fileName);
		frame = 0;
	}

	public Sprite(int i, int j) {
		x = i;
		y = j;
		frame = 0;
	}

	public void setImgs(String[] fileName) {
		imgs = new Image[fileName.length];
		for (int n = 0; n < fileName.length; n++) {
			try {
				imgs[n] = ImageIO.read(getClass().getResourceAsStream(fileName[n]));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void setImg(String fileName) {
		imgs = new Image[1];
		try {
			imgs[frame] = ImageIO.read(getClass().getResourceAsStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setImgs(String fileName) {
		imgs = new Image[1];
		try {
			imgs[frame] = ImageIO.read(getClass().getResourceAsStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public abstract int getX();

	public abstract void setX(int x);

	public abstract int getY();

	public abstract void setY(int y);

	public abstract int getW();

	public abstract void setW(int w);

	public abstract int getH();

	public abstract void setH(int h);

	public abstract int getSX();

	public abstract void setSX(int sx);

	public abstract int getSY();

	public abstract void setSY(int sy);

	public abstract int getSW();

	public abstract void setSW(int sw);

	public abstract int getSH();

	public abstract void setSH(int sh);

	public abstract int getFrame();

	public abstract void setFrame(int i);

	public abstract Image[] getImages();
}