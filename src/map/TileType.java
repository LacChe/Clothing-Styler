package map;

public enum TileType {

	Grass("grass", false, false), Earth("earth", true, false), Ground("ground",
			true, true);

	String fileName;
	boolean buildable;
	boolean walkable;
	boolean solid;
	int support;

	TileType(String fileName, boolean solid, boolean walkable) {
		this.fileName = "/res/" + fileName + ".png";
		this.solid = solid;
		this.walkable = walkable;
	}

}