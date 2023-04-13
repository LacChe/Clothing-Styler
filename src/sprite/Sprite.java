package sprite;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Sprite {// ABSTRACT

	protected int x, y;// where sprite starts on map
	protected Image[] imgs;// animation frames
	protected int offx, offy;// where sprite moves on map
	protected int frame;// current image
	protected boolean walkable = false, climbable = false;// can walk or climb
															// here
	protected boolean falling = false;// falling or not

	// constructor for sprite
	public Sprite(String[] fileName) {
		imgs = new Image[fileName.length];
		for (int i = 0; i < fileName.length; i++) {
			try {
				imgs[i] = ImageIO.read(getClass().getResourceAsStream(
						"/res/" + fileName[i] + ".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		frame = 0;
	}

	public abstract void render();// draw sprite

	public abstract void update(int i);// update pos

	public abstract void fall();// go down until walkable

	public abstract boolean isFalling();// passes falling or not

	public abstract void setOffx(int x);// set x pos

	public abstract void setOffy(int y);// set y pos

	public abstract int getOffx();// passes x pos

	public abstract int getOffy();// passes y pos

}