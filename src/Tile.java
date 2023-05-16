import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Tile {
	private int x, y;// pos of tile in map array
	private Image img;// tile texture
	private TileType type;// type of tile
	private boolean onscreen = true;
	private static TileType[] typeList = new TileType[] { TileType.Null, TileType.Dirt, TileType.Grass };

	// tile constructor
	public Tile(int x, int y, TileType type) {
		this.x = x;
		this.y = y;
		this.type = type;
		try {
			this.img = ImageIO.read(getClass().getResourceAsStream(type.fileName));
			// this.img = ImageIO.read(getClass().getResourceAsStream(
			// type.fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// draw tile
	public void draw() {
		Boot.getOffScreenGraphics().drawImage(img, x * 8 + 11, y * 8 + 11, Boot.getBoot());
	}

	// passes x
	public double getX() {
		return x;
	}

	// sets x
	public void setX(int x) {
		this.x = x;
	}

	// passes y
	public double getY() {
		return y;
	}

	// sets
	public void setY(int y) {
		this.y = y;
	}

	// passes tiletype
	public TileType getType() {
		return type;
	}

	// sets tiletype
	public void setType(int i) {
		this.type = typeList[i];
	}

	// sets isPassable
	public boolean isPassable() {
		return this.type.passable;
	}

	// sets isPassable
	public void setPassable(boolean b) {
		this.type.passable = b;
	}

	// passes tiletypelist
	public static TileType[] getTypeList() {
		return typeList;
	}

	public boolean isOnscreen() {
		return onscreen;
	}

	public void setOnscreen(boolean onscreen) {
		this.onscreen = onscreen;
	}

}
