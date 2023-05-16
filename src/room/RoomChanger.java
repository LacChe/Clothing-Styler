package room;

import audio.Audio;
import boot.Button;
import boot.Transform;
import graphics.Camera;

public class RoomChanger extends Button {

	private String source, destination;

	public RoomChanger(String n, String s, String d, Transform t, double w, double h) {
		super(n, t, w, h);
		source = s;
		destination = d;
	}

	public String source() {
		return source;
	}

	public String destination() {
		return destination;
	}

	public void clicked() {
		Audio.play(Audio.changeRoom);
		RoomManager.changeRoom(destination);
		Camera.changeRooms();
	}

}