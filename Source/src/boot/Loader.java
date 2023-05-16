package boot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import map.Map;
import sprite.Sprite;

public class Loader {

	public static void save() {
		try {
			FileOutputStream fileOut = new FileOutputStream("src//lizards//world.lzrd");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(Map.getMaps());
			out.close();
			fileOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
		try {
			FileOutputStream fileOut = new FileOutputStream("src//lizards//sprite.lzrd");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(Sprite.getSprites());
			out.close();
			fileOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static void load() {

		ArrayList<Map> maps = new ArrayList<Map>();
		ArrayList<Sprite> sprites = new ArrayList<Sprite>();
		File fw = new File("src//lizards//world.lzrd");
		File fs = new File("src//lizards//sprite.lzrd");

		if (!fw.exists() || fw.isDirectory()) {

			maps.add(new Map("src//maps//1.txt"));
			maps.add(new Map("src//maps//2.txt"));
			maps.add(new Map("src//maps//3.txt"));
			maps.add(new Map("src//maps//4.txt"));

		} else
			try {
				FileInputStream fileIn = new FileInputStream("src//lizards//world.lzrd");
				ObjectInputStream in = new ObjectInputStream(fileIn);
				maps = (ArrayList<Map>) in.readObject();
				in.close();
				fileIn.close();
			} catch (IOException i) {
				i.printStackTrace();
				return;
			} catch (ClassNotFoundException c) {
				System.out.println("Map class not found");
				c.printStackTrace();
				return;
			}
		Map.setMaps(maps);
		Map.refreshBounds();

		if (!fs.exists() || fs.isDirectory()) {
			sprites = new ArrayList<Sprite>();
		} else
			try {
				FileInputStream fileIn = new FileInputStream("src//lizards//sprite.lzrd");
				ObjectInputStream in = new ObjectInputStream(fileIn);
				sprites = (ArrayList<Sprite>) in.readObject();
				in.close();
				fileIn.close();
			} catch (IOException i) {
				i.printStackTrace();
				return;
			} catch (ClassNotFoundException c) {
				System.out.println("Sprite class not found");
				c.printStackTrace();
				return;
			}
		Sprite.setSprites(sprites);
	}

	public static void addMap(String n) {
		Map.getMaps().add(new Map("src//maps//" + n + ".txt"));
	}

	public static void resetMap(String n) {
		for (int i = 0; i < Map.getMaps().size(); i++) {
			if (Map.getMaps().get(i).getFileName().equals("src//maps//" + n + ".txt")) {
				Map.getMaps().remove(i);
				break;
			}
		}
		Map.getMaps().add(new Map("src//maps//" + n + ".txt"));
	}

	public static void resetMap(int ID) {
		if (ID == -1)
			return;
		String n = "";
		for (int i = 0; i < Map.getMaps().size(); i++) {
			if (Map.getMaps().get(i).getID() == ID) {
				n = Map.getMaps().get(i).getFileName();
				Map.getMaps().remove(i);
				break;
			}
		}
		Map.getMaps().add(new Map("src//maps//" + n + ".txt"));
	}

}