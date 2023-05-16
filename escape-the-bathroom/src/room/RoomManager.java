package room;

import java.util.ArrayList;

import boot.Button;
import boot.Transform;
import graphics.Sprite;
import inventory.Item;

public class RoomManager {

	public static ArrayList<Room> rooms = new ArrayList<Room>();

	private static String lastRoom, currentRoom;

	public static void init() {
		// add room1
		Room tempRoom = new Room("room1", Sprite.room1);
		ArrayList<RoomChanger> tempChangers = new ArrayList<RoomChanger>();
		ArrayList<Item> tempItems = new ArrayList<Item>();
		ArrayList<Button> tempButtons = new ArrayList<Button>();
		tempChangers.add(new RoomChanger("1to6", "room1", "room6", new Transform(20, 20), 40, 600));
		tempChangers.add(new RoomChanger("1to2", "room1", "room2", new Transform(580, 20), 40, 600));
		tempRoom.setRoomChangers(tempChangers);
		tempItems.add(new Item("bandage", new Transform(350, 530, 1.5, 1.5), 32, 32, Sprite.bandage));
		tempRoom.setItems(tempItems);
		tempButtons.add(new Button("doorHandle", new Transform(272, 389, 1, 1), 12, 32));
		tempRoom.setButtons(tempButtons);
		rooms.add(tempRoom);

		// add room2
		tempRoom = new Room("room2", Sprite.room2);
		tempChangers = new ArrayList<RoomChanger>();
		tempItems = new ArrayList<Item>();
		tempButtons = new ArrayList<Button>();
		tempChangers.add(new RoomChanger("2to1", "room2", "room1", new Transform(20, 20), 40, 600));
		tempChangers.add(new RoomChanger("2to3", "room2", "room3", new Transform(580, 20), 40, 600));
		tempRoom.setRoomChangers(tempChangers);
		tempItems.add(new Item("battery", new Transform(195, 445, 1.5, 1.5), 32, 32, Sprite.battery));
		tempRoom.setItems(tempItems);
		tempRoom.setButtons(tempButtons);
		rooms.add(tempRoom);

		// add room3
		tempRoom = new Room("room3", Sprite.room3);
		tempChangers = new ArrayList<RoomChanger>();
		tempItems = new ArrayList<Item>();
		tempButtons = new ArrayList<Button>();
		tempChangers.add(new RoomChanger("3to2", "room3", "room2", new Transform(20, 20), 40, 600));
		tempChangers.add(new RoomChanger("3to4", "room3", "room4", new Transform(580, 20), 40, 600));
		tempRoom.setRoomChangers(tempChangers);
		tempItems.add(new Item("brick", new Transform(330, 140, 1.5, 1.5), 32, 32, Sprite.brick));
		tempRoom.setItems(tempItems);
		tempRoom.setButtons(tempButtons);
		rooms.add(tempRoom);

		// add room4
		tempRoom = new Room("room4", Sprite.room4);
		tempChangers = new ArrayList<RoomChanger>();
		tempItems = new ArrayList<Item>();
		tempButtons = new ArrayList<Button>();
		tempChangers.add(new RoomChanger("4to3", "room4", "room3", new Transform(20, 20), 40, 600));
		tempChangers.add(new RoomChanger("4to5", "room4", "room5", new Transform(580, 20), 40, 600));
		tempRoom.setRoomChangers(tempChangers);
		tempItems.add(new Item("megaphone", new Transform(260, 550, 1.5, 1.5), 32, 32, Sprite.megaphone));
		tempRoom.setItems(tempItems);
		tempRoom.setButtons(tempButtons);
		rooms.add(tempRoom);

		// add room5
		tempRoom = new Room("room5", Sprite.room5);
		tempChangers = new ArrayList<RoomChanger>();
		tempItems = new ArrayList<Item>();
		tempButtons = new ArrayList<Button>();
		tempChangers.add(new RoomChanger("5to4", "room5", "room4", new Transform(20, 20), 40, 600));
		tempChangers.add(new RoomChanger("5to6", "room5", "room6", new Transform(580, 20), 40, 600));
		tempRoom.setRoomChangers(tempChangers);
		tempRoom.setItems(tempItems);
		tempRoom.setButtons(tempButtons);
		rooms.add(tempRoom);

		// add room6
		tempRoom = new Room("room6", Sprite.room6);
		tempChangers = new ArrayList<RoomChanger>();
		tempItems = new ArrayList<Item>();
		tempButtons = new ArrayList<Button>();
		tempChangers.add(new RoomChanger("6to5", "room6", "room5", new Transform(20, 20), 40, 600));
		tempChangers.add(new RoomChanger("6to1", "room6", "room1", new Transform(580, 20), 40, 600));
		tempRoom.setRoomChangers(tempChangers);
		tempItems.add(new Item("broom", new Transform(480, 400, 3, 3), 32, 64, Sprite.broom));
		tempRoom.setItems(tempItems);
		tempRoom.setButtons(tempButtons);
		rooms.add(tempRoom);

		currentRoom = lastRoom = rooms.get(0).name();
	}

	public static void changeRoom(String name) {
		if (currentRoom != null) {
			lastRoom = currentRoom;
			currentRoom = name;
		}
	}

	public static Room currentRoom() {
		for (Room r : rooms) {
			if (r.name() == currentRoom) return r;
		}
		return null;
	}

	public static Room lastRoom() {
		for (Room r : rooms) {
			if (r.name() == lastRoom) return r;
		}
		return null;
	}

	public static void update() {
		for (Room r : rooms) {
			r.update();
		}
	}

	public static void render() {
		for (Room r : rooms) {
			if (r.name() == currentRoom) {
				r.render();
				return;
			}
		}
	}

}