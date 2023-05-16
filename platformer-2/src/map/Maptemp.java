package map;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

import boot.Boot;

public class Map {

	private int HEIGHT, WIDTH;//size of map, depends on txt file
	private String fileName;//which file
	private String maptxt[][];//array of asccii, maybe skip
	private Tile maptile[][];//array of tile
	private int offx, offy;//off for map
	private BufferedImage back;//back ground
	private BufferedImage cloud;//clouds
	private int offxClouds, offyClouds;//off for clouds
	private String cloudFile;//file, depends on txt file
	private int hor, ver;//which type of map
	private boolean ready = false;//can draw

	//constructor for map
	public Map(String n) {
		fileName = n;
	}

	//start map
	public void init() {
		HEIGHT = 0;
		WIDTH = 0;
		offxClouds = 0;
		offyClouds = 0;
		read();
		ready = true;
		if (WIDTH == 500) {
			offx = 200 * 16;
			offy = 0;
			hor = 1;
			ver = 0;
			cloudFile = "/res/clouds_hor.png";
		} else {
			offx = 0;
			offy = 200 * 16;
			hor = 0;
			ver = 1;
			cloudFile = "/res/clouds_ver.png";
		}
		try {
			back = ImageIO.read(getClass().getResourceAsStream(
					"/res/sky_background.png"));
			cloud = ImageIO.read(getClass().getResourceAsStream(cloudFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//draw each tile
	public void render() {
		Boot.getBuffer().drawImage(back, 0, 0, Boot.getBoot());
		Boot.getBuffer().drawImage(cloud, 0 - offxClouds, 0 - offyClouds,
				Boot.getBoot());
		for (int j = offy / 16; j < 50 + ver + offy / 16; j++) {
			for (int i = offx / 16; i < 50 + hor + offx / 16; i++) {
				if (maptile[i][j] != null) {
					maptile[i][j].render(offx, offy);
				}
			}
		}
	}

	//change map
	public void teleport() {
		if (fileName.equals("src/maps/mapHor.txt")) {
			fileName = "src/maps/mapVer.txt";
		} else {
			fileName = "src/maps/mapHor.txt";
		}
	}

	//txt to tiles
	public void transferTiles() {
		System.out.println("start tran");
		for (int j = 0; j < HEIGHT; j++) {
			for (int i = 0; i < WIDTH; i++) {
				switch (maptxt[i][j]) {
				case ". ":
					maptile[i][j] = null;
					break;
				case "T0":
					maptile[i][j] = new Tile(i, j, TileType.Earth);
					break;
				case "T1":
					maptile[i][j] = new Tile(i, j, TileType.Ground);
					break;
				case "T2":
					maptile[i][j] = new Tile(i, j, TileType.Grass);
					break;
				}
			}
		}
		System.out.println("fin tran");
	}

	//read file get txt
	public void read() {
		ready = false;
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			WIDTH = Integer.parseInt(bufferedReader.readLine());
			HEIGHT = Integer.parseInt(bufferedReader.readLine());
			maptxt = new String[WIDTH][HEIGHT];
			maptile = new Tile[WIDTH][HEIGHT];
			while (bufferedReader.ready()) {
				for (int j = 0; j < HEIGHT; j++) {
					String line = bufferedReader.readLine();
					int n = 0;
					for (int i = 0; i < WIDTH; i++) {
						maptxt[i][j] = (new Character(line.charAt(n)))
								+ (new Character(line.charAt(n + 1)))
										.toString();
						n += 2;
					}
				}
			}
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
		}
		transferTiles();
	}

	//save changes to map
	public void save() {
		try {
			FileWriter fileWriter = new FileWriter(fileName);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			for (int j = 0; j < HEIGHT; j++) {
				for (int i = 0; i < WIDTH; i++) {
					bufferedWriter.write(maptxt[i][j]);
				}
				bufferedWriter.write("\n");
			}
			bufferedWriter.close();
		} catch (IOException ex) {
			System.out.println("Error writing to file '" + fileName + "'");
		}
	}

	//update pos + changes
	public void update(int i) {
		switch (i) {
		case 0:
			break;
		case 1:
			setOffx(offx + 8);
			break;
		case 2:
			setOffy(offy + 8);
			break;
		case 3:
			setOffx(offx - 8);
			break;
		case 4:
			setOffy(offy - 8);
			break;
		}
		setOffxClouds((offx) / 20);
		setOffyClouds((offy) / 20);
	}

	//passes can draw or not
	public boolean isReady() {
		return ready;
	}

	//change x pos
	public void setOffx(int x) {
		if (x >= 0 && x / 16 < WIDTH - 50) {
			offx = x;
		}
	}

	//change y pos
	public void setOffy(int y) {
		if (y >= 0 && y / 16 < HEIGHT - 50) {
			offy = y;
		}
	}

	//passes tiles
	public Tile[][] getTiles() {
		return maptile;
	}

	//passes x pos
	public int getOffx() {
		return offx;
	}

	//passes y pos
	public int getOffy() {
		return offy;
	}

	//passes clouds x pos
	public int getOffxClouds() {
		return offxClouds;
	}

	//passes clouds y pos
	public int getOffyClouds() {
		return offyClouds;
	}

	//set clouds x pos
	public void setOffxClouds(int x) {
		if (x >= 0 && x < WIDTH - 50) {
			offxClouds = x;
		}
	}

	//set clouds x pos
	public void setOffyClouds(int y) {
		if (y >= 0 && y < HEIGHT - 50) {
			offyClouds = y;
		}
	}

	//get map width
	public int getWidth() {
		return WIDTH;
	}

	//get map height
	public int getHeight() {
		return HEIGHT;
	}

}
