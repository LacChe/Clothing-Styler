import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Map {

	private int X, Y, Z = -1;// size of map, depends on txt file //
	private String fileName = "src/mapTestTer.txt";// which file
	private String backName;
	private BufferedImage back;// back ground
	private boolean ready = false;// can draw
	private Tile[][] tiles;
	private String[][] grid;
	private double loaded;
	private ArrayList<Creature> creatures;
	private ArrayList<Structure> structures;
	private Self A;
	private String selfType;
	private int selfX, selfY;

	// constructor for map

	public Map(int z) {
		Z = z;
		init();
	}

	// start map
	public void init() {
		structures = new ArrayList<Structure>();
		creatures = new ArrayList<Creature>();
		read();
		tiles = new Tile[X][Y];
		for (int i = 0; i < X; i++) {
			for (int j = 0; j < Y; j++) {
				// System.out.println(i + "," + j);
				if (grid[i][j].equals(" 0")) {
					tiles[i][j] = new Tile(i, j, TileType.Grass);
				} else if (grid[i][j].equals(" 1")) {
					tiles[i][j] = new Tile(i, j, TileType.Dirt);
				} else if (grid[i][j].equals(" .")) {
					tiles[i][j] = new Tile(i, j, TileType.Null);
				}
				loaded = ((i + 1.0) + ((j + 1.0) / Y)) / X;
				Boot.setLoaded(loaded);
				Boot.getBoot().repaint();
			}
		}
		try {
			back = ImageIO.read(getClass().getResourceAsStream(backName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public BufferedImage getTile() {
		return back;
	}

	public void setTile(BufferedImage tile) {
		this.back = tile;
	}

	public Tile[][] getTiles() {
		return tiles;
	}

	// draw each tile
	public void draw(Graphics2D g) {
		Boot.getOffScreenGraphics().drawImage(back, 11, 11, Boot.getBoot());
		drawSprites(g);
		for (int i = 0; i < X; i++) {
			for (int j = 0; j < Y; j++) {
				if (tiles[i][j] != null) {
					tiles[i][j].draw();
				}
				// System.out.print(grid[i][j]);
			}
			// System.out.print("\n");
		}
	}

	public void drawSprites(Graphics2D g) {
		if (structures != null) {
			for (Structure s : structures) {
				s.draw(g);
			}
		}
		if (creatures != null) {
			for (Creature s : creatures) {
				s.draw(g);
			}
		}
		if (A != null) {
			A.draw(g);
		}
	}

	// read file get txt
	public void read() {
		ready = false;
		try {
			String in;
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			// WIDTH = Integer.parseInt(bufferedReader.readLine());
			// gets width height from first two lines of file
			in = bufferedReader.readLine();
			X = Integer.parseInt(in.substring(0, 4));
			Y = Integer.parseInt(in.substring(5, 9));
			grid = new String[X][Y];
			in = bufferedReader.readLine();
			backName = "/res/" + in + ".png";
			in = bufferedReader.readLine();
			selfType = in.substring(0, 2);
			selfX = Integer.parseInt(in.substring(3, 7));
			selfY = Integer.parseInt(in.substring(8, 12));
			addSelf(selfType);
			// start sprite;
			sprites: while (true) {
				in = bufferedReader.readLine();
				if (in.equals("m")) {
					break sprites;
				}
				if (in.substring(0, 1).equals("s")) {
					addStructures(new Structure(in.substring(2, 5), Integer.parseInt(in.substring(6, 10)),
							Integer.parseInt(in.substring(11, 15))));
				}
				if (in.substring(0, 1).equals("c")) {
					addCreatures(new Creature(in.substring(2, 5), Integer.parseInt(in.substring(6, 10)),
							Integer.parseInt(in.substring(11, 15))));
				}
			}

			// start map
			while (bufferedReader.ready()) {
				for (int i = 0; i < Y; i++) {
					String line = bufferedReader.readLine();
					for (int j = 0; j < X; j++) {
						grid[j][i] = (line.substring(j * 2, j * 2 + 2));
						// System.out.print(grid[j][i]);
						// System.out.println(i + "," + j);
					}
					// System.out.print("\n");
				}
			}
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
		}
	}

	// save changes to map
	public void save() {
		try {
			FileWriter fileWriter = new FileWriter(fileName);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write("");
			bufferedWriter.close();
		} catch (IOException ex) {
			System.out.println("Error writing to file '" + fileName + "'");
		}
	}

	// update pos + changes
	public void update() {
		if (structures != null) {
			for (Structure s : structures) {
				s.update();
			}
		}
		if (creatures != null) {
			for (Creature s : creatures) {
				s.update();
			}
		}
		if (A != null) {
			A.update();
		}
	}

	// passes can draw or not
	public boolean isReady() {
		return ready;
	}

	// get map width
	public int getX() {
		return X;
	}

	// get map height
	public int getY() {
		return Y;
	}

	public void setX(int x) {
		X = x;
	}

	public void setY(int y) {
		Y = y;
	}

	public int getZ() {
		return Z;
	}

	public void setZ(int z) {
		Z = z;
	}

	public ArrayList<Creature> getCreatures() {
		return creatures;
	}

	public void setCreatures(ArrayList<Creature> sprites) {
		this.creatures = sprites;
	}

	public void addCreatures(Creature s) {
		s.setZ(Z);
		creatures.add(s);
	}

	public void addCreatures(int i, Creature s) {
		s.setZ(Z);
		creatures.add(i, s);
	}

	public void addCreatures(Creature s, int n) {
		for (int i = 0; i < n; i++) {
			creatures.add(s);
		}
	}

	public ArrayList<Structure> getStructures() {
		return structures;
	}

	public void setStructures(ArrayList<Structure> s) {
		this.structures = s;
	}

	public void addStructures(Structure s) {
		s.setZ(Z);
		structures.add(s);
	}

	public void addStructures(int i, Structure s) {
		s.setZ(Z);
		structures.add(i, s);
	}

	public void addStructures(Structure s, int n) {
		for (int i = 0; i < n; i++) {
			structures.add(s);
		}
	}

	public void addSelf(String n) {
		A = new Self(n, selfX, selfY);
	}

	public Self getSelf() {
		return A;
	}

	public void setSelf(Self s) {
		A = s;
	}

	public Self getA() {
		return A;
	}

	public void setA(Self a) {
		A = a;
	}

}
