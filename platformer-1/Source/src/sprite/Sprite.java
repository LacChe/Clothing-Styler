package sprite;

import java.awt.Image;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import boot.Boot;
import boot.Camera;
import map.Map;

public abstract class Sprite implements Serializable {

	protected static ArrayList<Sprite> sprites = new ArrayList<Sprite>();

	protected static final long serialVersionUID = 2147286267036451855L;

	protected int x, y;// pos of tile in map array
	protected int w, h;
	protected transient Image img;// tile texture
	protected String imgName = "/res/sprite.png";

	protected int gridID = -1;
	protected Map map;

	protected long time = 0;

	protected int xVelocity, yVelocity;

	protected boolean falling = true;

	protected boolean sprinting = false;

	public Sprite(int x, int y) {
		this.x = x;
		this.y = y;

		this.w = 8;
		this.h = 8;

		xVelocity = 0;
		yVelocity = 0;

		map = null;

	}

	public static void staticUpdate() {
		for (int i = 0; i < sprites.size(); i++) {
			sprites.get(i).update();
		}
	}

	public static void staticDraw() {
		for (int i = 0; i < sprites.size(); i++) {
			sprites.get(i).draw(Map.getMaps().get(i).getOffY(), Map.getMaps().get(i).getOffX());
		}
	}

	public void update() {

		updateLocale();

		// update surrounding

		// check falling
		if (collide(0, 1))
			falling = false;
		else
			falling = true;

		// move
		for (int i = 0; i < Math.abs(yVelocity); i++) {
			if (!collide(0, yVelocity / Math.abs(yVelocity)))
				y += yVelocity / Math.abs(yVelocity);
			else
				yVelocity = 0;
		}
		if (!falling) {
			if (xVelocity == 0)
				xVelocity += (int) (Math.random() * 3) - 1;
			for (int i = 0; i < Math.abs(xVelocity); i++) {
				if (!collide(xVelocity / Math.abs(xVelocity), 0))
					x += xVelocity / Math.abs(xVelocity);
				else {
					xVelocity = 0;
				}
			}
		} else
			adjustVelocity(0, 1);
	}

	// draw sprite
	public void draw(int OffX, int OffY) {
		if (img == null)
			try {
				this.img = ImageIO.read(getClass().getResourceAsStream(imgName));
			} catch (IOException e) {
				e.printStackTrace();
			}

		Boot.getOffScreenGraphics().drawImage(img, x + 8 + Camera.getX(), y + 8 + Camera.getY(), Boot.getBoot());
	}

	public void adjustVelocity(int i, int j) {
		xVelocity += i;
		if (xVelocity < -10)
			xVelocity = -10;
		if (xVelocity > 10)
			xVelocity = 10;

		yVelocity += j;
		if (yVelocity < -10)
			yVelocity = -10;
		if (yVelocity > 10)
			yVelocity = 10;
	}

	public boolean collide(int x, int y) {
		boolean collide = false;

		if (gridID == -1)
			return collide;

		int offX = Map.getMaps().get(gridID).getOffX();
		int offY = Map.getMaps().get(gridID).getOffY();

		// fix later
		if (x > 0)
			collide = (Map.getMaps().get(gridID).getTile((this.x) / 8 + 1 - offX, (this.y + y) / 8 - offY).solid()
					|| Map.getMaps().get(gridID).getTile((this.x) / 8 + 1 - offX, (this.y + y + (h - 1)) / 8 - offY)
							.solid());
		if (x < 0)
			collide = (Map.getMaps().get(gridID).getTile((this.x + x) / 8 - offX, (this.y + y) / 8 - offY).solid()
					|| Map.getMaps().get(gridID).getTile((this.x + x) / 8 - offX, (this.y + y + (h - 1)) / 8 - offY)
							.solid());
		if (y > 0)
			collide = (Map.getMaps().get(gridID).getTile((this.x + x) / 8 - offX, (this.y) / 8 + 1 - offY).solid()
					|| Map.getMaps().get(gridID).getTile((this.x + (w - 1)) / 8 - offX, (this.y) / 8 + 1 - offY)
							.solid());
		if (y < 0)
			collide = (Map.getMaps().get(gridID).getTile((this.x + x) / 8 - offX, (this.y + y) / 8 - offY).solid()
					|| Map.getMaps().get(gridID).getTile((this.x + (w - 1)) / 8 - offX, (this.y) / 8 - offY).solid());
		return collide;
	}

	public int gridID() {
		return gridID;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getXVelocity() {
		return xVelocity;
	}

	public int getYVelocity() {
		return yVelocity;
	}

	public boolean sprint() {
		return sprinting;
	}

	public void sprint(boolean s) {
		sprinting = s;
	}

	public void setXVelocity(int i) {
		xVelocity = i;
	}

	public void setYVelocity(int i) {
		yVelocity = i;
	}

	public static ArrayList<Sprite> getSprites() {
		return sprites;
	}

	public static void setSprites(ArrayList<Sprite> s) {
		sprites = s;
	}

	public void updateLocale() {
		gridID = -1;
		for (int i = 0; i < Map.getMaps().size(); i++) {
			if (Map.getMaps().get(i).getOffX() * 8 <= x + 16 && //
					Map.getMaps().get(i).getOffY() * 8 <= y + 16 && //
					(Map.getMaps().get(i).getOffX() + Map.getMaps().get(i).getX()) * 8 >= x + w - 16 && //
					(Map.getMaps().get(i).getOffY() + Map.getMaps().get(i).getY()) * 8 >= y + h - 16) {
				gridID = i;
				break;
			}
		}
	}

}