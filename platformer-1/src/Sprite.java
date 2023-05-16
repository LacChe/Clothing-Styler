import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public abstract class Sprite {// ABSTRACT

	protected int x, y, z, w, h, team;// where sprite is on map
	protected Image[] imgs;// animation frames
	protected int frame;// current image
	protected int dFrame;
	protected String name;
	protected Rectangle space;
	protected ArrayList<Animation> animations = new ArrayList<Animation>();

	protected Map m;

	protected int xVel = 0, yVel = 0;

	// drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int
	// sy1, int sx2, int sy2, ImageObserver observer)

	// prev
	protected int px, py, pz;// where sprite is on map

	// constructor for sprite
	public Sprite(String[] fileName, int i, int j) {
		x = i * 8;
		y = j * 8;
		z = 0;
		setImgs(fileName);
		frame = 0;
		space = new Rectangle(x, y, w, h);
	}

	public Sprite(String fileName, int i, int j) {
		x = i * 8;
		y = j * 8;
		setImgs(fileName);
		frame = 0;
		space = new Rectangle(x, y, w, h);
	}

	public Sprite(int i, int j) {
		x = i * 8;
		y = j * 8;
		frame = 0;
		space = new Rectangle(x, y, w, h);
	}

	public void draw(Graphics2D g) {
		Boot.getOffScreenGraphics().drawImage(imgs[frame], x + 11, y + 11, Boot.getBoot());// draw
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

	public void setImgs(String fileName) {
		imgs = new Image[1];
		try {
			imgs[0] = ImageIO.read(getClass().getResourceAsStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updateSpace() {
		space = new Rectangle(x, y, w, h);
	}

	public void updateMap() {
		m = Boot.getMaps().get(z);
	}

	// update pos
	public void update() {
		updateSpace();
		updateMap();
	}

	public abstract String is();

	public abstract int getXVel();

	public abstract void setXVel(int x);

	public abstract int getYVel();

	public abstract void setYVel(int y);

	public abstract int getX();

	public abstract void setX(int x);

	public abstract int getY();

	public abstract void setY(int y);

	public abstract int getZ();

	public abstract void setZ(int z);

	public abstract int getPX();

	public abstract void setPX(int x);

	public abstract int getPY();

	public abstract void setPY(int y);

	public abstract int getPZ();

	public abstract void setPZ(int z);

	public abstract int getFrame();

	public abstract void setFrame(int i);

	public abstract void record();
}