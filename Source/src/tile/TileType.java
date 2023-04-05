package tile;
public enum TileType {

	Null("null"), Block("block"), Grass("grass");

	String fileName;
	boolean solid = true; // passable or not

	TileType(String fileName) {
		this.fileName = "/res/" + fileName + ".png";
		switch (fileName) {
		case "null":
			solid = false;
			break;
		case "block":
			break;
		case "grass":
			solid = false;
			break;
		}
	}

}