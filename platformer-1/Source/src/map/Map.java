package map;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import boot.Boot;
import boot.Camera;
import tile.Tile;
import tile.TileType;

public class Map implements Serializable {

	private static final long serialVersionUID = 1L;

	private int gridID = -1;
	private long time = 0;
	private int X, Y, OffX, OffY; // size of map, depends on txt file //
	private String fileName = "null"; // which file

	private Tile[][] tiles;

	private String[][] grid;

	private boolean showOutline = true, frozen = true;

	private static int freezeLimit = 5, froze = 0;

	private static ArrayList<Map> maps = new ArrayList<Map>();

	private static int rightMost = 80, bottomMost = 80;

	// constructor for map
	public Map(String n) {
		fileName = n;
		init();
	}

	public static void staticUpdate() {
		for (int i = 0; i < maps.size(); i++) {
			maps.get(i).update();
		}
	}

	public static void staticDraw(Graphics2D g) {
		for (int i = 0; i < maps.size(); i++) {

			if (maps.get(i) != null)
				if (Camera.inView(maps.get(i).OffX * 8, maps.get(i).OffY * 8, maps.get(i).X * 8, maps.get(i).Y * 8)) {
					maps.get(i).draw();

					// draw outline
					if (maps.get(i).showOutline)
						g.drawRect(maps.get(i).OffX * 8 - 1 + 8 + -Camera.getX(),
								maps.get(i).OffY * 8 - 1 + 8 + -Camera.getY(), maps.get(i).X * 8 + 1,
								maps.get(i).Y * 8 + 1);
				}

		}

		g.drawRect(7 + -Camera.getX(), 7 + -Camera.getY(), rightMost * 8 + 1, bottomMost * 8 + 1);

	}

	public void update() {
		if (!frozen) {
			time++;
		}
	}

	public void draw() {
		// draw tiles
		for (int i = 0; i < X; i++) {
			for (int j = 0; j < Y; j++) {
				if (tiles[i][j] != null) {
					tiles[i][j].draw(OffX + 1, OffY + 1);
				}
			}
		}
	}

	// start map
	public void init() {
		read();

		tiles = new Tile[X][Y];

		for (int i = 0; i < X; i++) {
			for (int j = 0; j < Y; j++) {
				// System.out.println(i + "," + j);
				if (grid[i][j].equals(" 0")) {
					tiles[i][j] = new Tile(i, j, TileType.Grass, gridID);
				} else if (grid[i][j].equals(" 1")) {
					tiles[i][j] = new Tile(i, j, TileType.Block, gridID);
				} else {
					tiles[i][j] = new Tile(i, j, TileType.Null, gridID);
				}
				Boot.getBoot().repaint();
			}
		}
		if (froze <= freezeLimit) {
			frozen = false;
			froze++;
		}
		refreshBounds();
	}

	// read file get txt
	public void read() {
		try {
			String in, line;
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			// WIDTH = Integer.parseInt(bufferedReader.readLine());
			// gets width height from first two lines of file

			in = bufferedReader.readLine();
			gridID = Integer.parseInt(in);

			in = bufferedReader.readLine();
			OffX = Integer.parseInt(in.substring(0, 4));
			OffY = Integer.parseInt(in.substring(5, 9));

			in = bufferedReader.readLine();
			X = Integer.parseInt(in.substring(0, 4));
			Y = Integer.parseInt(in.substring(5, 9));

			grid = new String[X][Y];
			for (int i = 0; i < Y; i++) {
				for (int j = 0; j < X; j++) {
					grid[j][i] = " ,";
				}
			}

			// unused yet
			int numGroups = Integer.parseInt(bufferedReader.readLine());

			// line = bufferedReader.readLine();
			line = "";

			// start map
			while (bufferedReader.ready() && true) {
				for (int g = 0; g < numGroups; g++) {

					in = bufferedReader.readLine();
					int offX = Integer.parseInt(in.substring(0, 4));
					int offY = Integer.parseInt(in.substring(5, 9));

					in = bufferedReader.readLine();
					int gX = Integer.parseInt(in.substring(0, 4));
					int gY = Integer.parseInt(in.substring(5, 9));

					for (int i = 0; i < gY; i++) {

						line = bufferedReader.readLine();

						for (int j = 0; j < gX; j++) {

							grid[offX + j][offY + i] = (line.substring(j * 2, (j + 1) * 2));

						}

					}

				}
				bufferedReader.close();
			}
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
		}
	}

	public Tile getTile(int x, int y) {
		if (x >= tiles.length || x < 0 || y >= tiles[0].length || y < 0) {
			return new Tile(x, y, TileType.Null, -1);
		}
		return tiles[x][y];
	}

	public int getOffX() {
		return OffX;
	}

	public int getOffY() {
		return OffY;
	}

	public int getX() {
		return X;
	}

	public int getY() {
		return Y;
	}

	public int getID() {
		return gridID;
	}

	public long getTime() {
		return time;
	}

	public String getFileName() {
		return fileName;
	}

	public boolean frozen() {
		return frozen;
	}

	public boolean freeze(boolean f) {
		if (!frozen && f && froze < freezeLimit) {
			froze++;
			frozen = true;
		} else if (frozen && !f) {
			froze--;
			frozen = false;
		}
		return false;
	}

	public static ArrayList<Map> getMaps() {
		return maps;
	}

	public static void setMaps(ArrayList<Map> m) {
		maps = m;
	}

	public static int rightMost() {
		return rightMost;
	}

	public static int bottomMost() {
		return bottomMost;
	}

	public static void refreshBounds() {
		int x = rightMost, y = bottomMost;
		for (Map m : maps) {
			int xx = m.OffX + m.X, yy = m.OffY + m.Y;
			if (xx > x)
				x = xx;
			if (yy > y)
				y = yy;
		}
		rightMost = x;
		bottomMost = y;
	}

}
