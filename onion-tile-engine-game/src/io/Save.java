package io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import world.World;

public class Save implements Serializable {

	private static final long serialVersionUID = 1L;

	private World world;

	public Save(World world) {
		this.world = world;
	}

	public World world() {
		return world;
	}

	public static void save(Save save, String saveName) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(saveName));
			out.writeObject(save);
			out.close();
		} catch (IOException exc) {
			System.err.println("no file on save");
			exc.printStackTrace();
		}

	}

	public static Save load(String loadName) {
		Save load = new Save(new World(1, 1));
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(loadName));
			load = (Save) in.readObject();
			in.close();
		} catch (IOException exc) {
			System.err.println("no file on load");
			// exc.printStackTrace();
		} catch (ClassNotFoundException exc) {
			System.err.println("no class on load");
			// exc.printStackTrace();
		}
		return load;
	}

}