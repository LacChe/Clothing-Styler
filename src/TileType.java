public enum TileType {

	Null("null"), Dirt("dirt"), //
	Grass("grass");

	String fileName;
	boolean passable;

	TileType(String fileName) {
		this.fileName = "/res/" + fileName + ".png";
		switch (fileName) {
		case "null":
			this.passable = true;
			break;
		case "dirt":
			this.passable = false;
			break;
		case "grass":
			this.passable = true;
			break;
		}
	}

}