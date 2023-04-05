package tile;

import java.awt.Image;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

import boot.Boot;
import boot.Camera;

public class Tile implements Serializable {

	private static final long serialVersionUID = 2897381605041054969L;

	private int gridID;
	private int x, y;// pos of tile in map array
	private transient Image img;// tile texture
	private TileType type;// type of tile

	private static TileType[] typeList = new TileType[] { TileType.Null, TileType.Block, TileType.Grass };

	// tile constructor
	public Tile(int x, int y, TileType type, int id) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.gridID = id;
		try {
			this.img = ImageIO.read(getClass().getResourceAsStream(type.fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// draw tile
	public void draw(int OffX, int OffY) {
		if (img == null)
			try {
				this.img = ImageIO.read(getClass().getResourceAsStream(type.fileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
		if (Camera.inView((OffX + x) * 8, (OffY + y) * 8, 8, 8))
			Boot.getOffScreenGraphics().drawImage(img, (OffX + x) * 8 + -Camera.getX(), (OffY + y) * 8 + -Camera.getY(),
					Boot.getBoot());
	}

	public boolean solid() {
		return type.solid;
	}

	public String type() {
		return type.name();
	}

	public int gridID() {
		return gridID;
	}

	public static TileType[] types() {
		return typeList;
	}

}