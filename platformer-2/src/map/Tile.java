package map;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import boot.Boot;

public class Tile {
	private int x, y;// pos of tile in map array
	private Image img;// tile texture
	private TileType type;// type of tile
	private static TileType typeList[] = new TileType[] { null, TileType.Earth,
			TileType.Ground, TileType.Grass };

	// tile constructor
	public Tile(int x, int y, TileType type) {
		this.x = x;
		this.y = y;
		this.type = type;
		try {
			this.img = ImageIO.read(getClass().getResourceAsStream(
					type.fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// draw tile
	public void render(int a, int b) {
		Boot.getBuffer().drawImage(img, x * 16 - a, y * 16 - b, Boot.getBoot());
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

	// passes tiletypelist
	public static TileType[] getTypeList() {
		return typeList;
	}

	// checks if solid
	public boolean isSolid() {
		return type.solid;
	}

	// checks if walkable
	public boolean isWalkable() {
		return type.walkable;
	}
}
