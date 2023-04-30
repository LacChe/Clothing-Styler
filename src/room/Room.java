package room;

import java.util.ArrayList;

import boot.Button;
import boot.Transform;
import graphics.Sprite;
import inventory.Item;

public class Room {

	private Sprite background;
	private String name;

	private Transform transform = new Transform(20, 20);
	private double w = 600, h = 600;

	private ArrayList<RoomChanger> roomChangers = new ArrayList<RoomChanger>();
	private ArrayList<Item> items = new ArrayList<Item>();
	private ArrayList<Button> buttons = new ArrayList<Button>();

	public Room(String n, Sprite b) {
		name = n;
		background = b;
	}

	public void update() {
	}

	public void render() {
		if (transform != null) background.render(transform);
		if (items.size() > 0) for (Item i : items)
			if (!i.inBag()) {
				i.render();
				// i.renderButton(); // render button
			}
	}

	public String name() {
		return name;
	}

	public void setRoomChangers(ArrayList<RoomChanger> r) {
		roomChangers = r;
	}

	public ArrayList<RoomChanger> roomChangers() {
		return roomChangers;
	}

	public void setItems(ArrayList<Item> i) {
		items = i;
	}

	public ArrayList<Item> items() {
		return items;
	}

	public void setButtons(ArrayList<Button> b) {
		buttons = b;
	}

	public ArrayList<Button> buttons() {
		return buttons;
	}

	public double w() {
		return w;
	}

	public double h() {
		return h;
	}

}