package entity;

import menu.Item;
import menu.Menu;
import render.Animation;
import render.Camera;
import world.Flow;
import world.Tile;
import world.World;

public class Event extends StaticEntity {

	private int ID;
	private int index = 0;
	private boolean needsAction;

	static int outsideX;
	static int outsideY;

	public Event(int x, int y, int w, int h, int ID) {
		super(x, y, w, h, 0, 0, new Animation[] {});
		if (ID < index) System.err.println("Teleporter ID duplicate: " + ID);
		needsAction = false;
		switch (ID) {
		case 0:
			needsAction = true;
			break;
		case 1:
			needsAction = true;
			break;
		case 26:
			needsAction = true;
			break;
		case 27:
			needsAction = true;
			break;
		case 28:
			needsAction = true;
			break;
		case 29:
			needsAction = true;
			break;
		case 30:
			needsAction = true;
			break;
		case 31:
			needsAction = true;
			break;
		case 32:
			needsAction = true;
			break;
		case 33:
			needsAction = true;
			break;
		case 34:
			needsAction = true;
			break;
		case 35:
			needsAction = true;
			break;
		case 36:
			needsAction = true;
			break;
		case 37:
			needsAction = true;
			break;
		case 38:
			needsAction = true;
			break;
		case 39:
			needsAction = true;
			break;
		case 40:
			needsAction = true;
			break;
		case 41:
			needsAction = true;
			break;
		case 42:
			needsAction = true;
			break;
		case 43:
			needsAction = true;
			break;
		case 44:
			needsAction = true;
			break;
		case 45:
			needsAction = true;
			break;
		case 46:
			needsAction = true;
			break;
		case 47:
			needsAction = true;
			break;
		case 48:
			needsAction = true;
			break;
		case 49:
			needsAction = true;
			break;
		case 50:
			needsAction = true;
			break;
		case 51:
			needsAction = true;
			break;
		case 52:
			needsAction = true;
			break;
		case 53:
			needsAction = true;
			break;
		case 54:
			needsAction = true;
			break;
		case 55:
			needsAction = true;
			break;
		case 56:
			needsAction = true;
			break;
		case 57:
			needsAction = true;
			break;
		case 58:
			needsAction = true;
			break;
		case 59:
			needsAction = true;
			break;
		case 60:
			needsAction = true;
			break;
		case 61:
			needsAction = true;
			break;
		case 62:
			needsAction = true;
			break;
		case 63:
			needsAction = true;
		case 64:
			needsAction = true;
		case 65:
			needsAction = true;
		case 66:
			needsAction = true;
		case 67:
			needsAction = true;
		case 68:
			needsAction = true;
		case 69:
			needsAction = true;
		case 70:
			needsAction = true;
		case 71:
			needsAction = true;
		default:
		}
		this.ID = ID;
		this.index++;
		this.solid = false;
	}

	public void action() {
		switch (ID) {
		case 0:// enter onion
			outsideX = World.world().player().x();
			outsideY = World.world().player().y();
			World.world(World.ROOM_ONION);
			World.world().setPlayer(World.MAIN_NW.player());
			World.world().player().x(12 * Tile.TILE_S + 7);
			World.world().player().y(13 * Tile.TILE_S + 40);
			Camera.camera().offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
			Camera.camera().offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
			break;
		case 1:// exit onion
			World.world(World.MAIN_NW);
			World.world().setPlayer(World.ROOM_ONION.player());
			World.world().player().x(outsideX);
			World.world().player().y(outsideY);
			Camera.camera().offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
			Camera.camera().offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
			break;
		case 2:// NW => N
			if (World.world().player().facingDir == 1) {
				World.world(World.MAIN_N);
				World.world().setPlayer(World.MAIN_NW.player());
				World.world().player().x(0 * Tile.TILE_S + 7);
				World.world().player().y(3 * Tile.TILE_S + 30);
				Camera.camera()
						.offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
				Camera.camera()
						.offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
			}
			break;
		case 3:// N => NW
			if (World.world().player().facingDir == 2) {
				World.world(World.MAIN_NW);
				World.world().setPlayer(World.MAIN_N.player());
				World.world().player().x(31 * Tile.TILE_S + 7);
				World.world().player().y(3 * Tile.TILE_S + 30);
				Camera.camera()
						.offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
				Camera.camera()
						.offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
			}
			break;
		case 4:// NW => W
			if (World.world().player().facingDir == 0) {
				World.world(World.MAIN_W);
				World.world().setPlayer(World.MAIN_NW.player());
				World.world().player().x(14 * Tile.TILE_S + 30 + Tile.TILE_S / 2);
				World.world().player().y(Tile.TILE_S / 2);
				Camera.camera()
						.offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
				Camera.camera()
						.offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
			}
			break;
		case 5:// W => NW
			if (World.world().player().facingDir == 3) {
				World.world(World.MAIN_NW);
				World.world().setPlayer(World.MAIN_W.player());
				World.world().player().x(14 * Tile.TILE_S + 30 + Tile.TILE_S / 2);
				World.world().player().y(31 * Tile.TILE_S + Tile.TILE_S / 2);
				Camera.camera()
						.offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
				Camera.camera()
						.offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
			}
			break;
		case 6:// N => NE
			if (World.world().player().facingDir == 1) {
				World.world(World.MAIN_NE);
				World.world().setPlayer(World.MAIN_N.player());
				World.world().player().x(0 * Tile.TILE_S + 7);
				World.world().player().y(3 * Tile.TILE_S + 30);
				Camera.camera()
						.offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
				Camera.camera()
						.offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
			}
			break;
		case 7:// NE => N
			if (World.world().player().facingDir == 2) {
				World.world(World.MAIN_N);
				World.world().setPlayer(World.MAIN_NE.player());
				World.world().player().x(31 * Tile.TILE_S + 7);
				World.world().player().y(3 * Tile.TILE_S + 30);
				Camera.camera()
						.offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
				Camera.camera()
						.offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
			}
			break;
		case 8:// NE => E
			if (World.world().player().facingDir == 0) {
				outsideX = World.world().player().x();
				World.world(World.MAIN_E);
				World.world().setPlayer(World.MAIN_NE.player());
				World.world().player().x(outsideX);
				World.world().player().y(0 * Tile.TILE_S + 7);
				Camera.camera()
						.offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
				Camera.camera()
						.offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
			}
			break;
		case 9:// E => NE
			if (World.world().player().facingDir == 3) {
				outsideX = World.world().player().x();
				World.world(World.MAIN_NE);
				World.world().setPlayer(World.MAIN_E.player());
				World.world().player().x(outsideX);
				World.world().player().y(31 * Tile.TILE_S + 7);
				Camera.camera()
						.offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
				Camera.camera()
						.offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
			}
			break;
		case 10:// W => C
			if (World.world().player().facingDir == 1) {
				outsideY = World.world().player().y();
				World.world(World.MAIN_C);
				World.world().setPlayer(World.MAIN_W.player());
				World.world().player().x(0 * Tile.TILE_S + 7);
				World.world().player().y(outsideY);
				Camera.camera()
						.offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
				Camera.camera()
						.offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
			}
			break;
		case 11:// C => W
			if (World.world().player().facingDir == 2) {
				outsideY = World.world().player().y();
				World.world(World.MAIN_W);
				World.world().setPlayer(World.MAIN_C.player());
				World.world().player().x(31 * Tile.TILE_S + 7);
				World.world().player().y(outsideY);
				Camera.camera()
						.offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
				Camera.camera()
						.offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
			}
			break;
		case 12:// C => E
			if (World.world().player().facingDir == 1) {
				outsideY = World.world().player().y();
				World.world(World.MAIN_E);
				World.world().setPlayer(World.MAIN_C.player());
				World.world().player().x(0 * Tile.TILE_S + 7);
				World.world().player().y(outsideY);
				Camera.camera()
						.offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
				Camera.camera()
						.offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
			}
			break;
		case 13:// E => C
			if (World.world().player().facingDir == 2) {
				outsideY = World.world().player().y();
				World.world(World.MAIN_C);
				World.world().setPlayer(World.MAIN_E.player());
				World.world().player().x(31 * Tile.TILE_S + 7);
				World.world().player().y(outsideY);
				Camera.camera()
						.offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
				Camera.camera()
						.offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
			}
			break;
		case 14:// E => SE
			if (World.world().player().facingDir == 0) {
				outsideX = World.world().player().x();
				World.world(World.MAIN_SE);
				World.world().setPlayer(World.MAIN_E.player());
				World.world().player().x(outsideX);
				World.world().player().y(0 * Tile.TILE_S + 7);
				Camera.camera()
						.offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
				Camera.camera()
						.offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
			}
			break;
		case 15:// SE => E
			if (World.world().player().facingDir == 3) {
				outsideX = World.world().player().x();
				World.world(World.MAIN_E);
				World.world().setPlayer(World.MAIN_SE.player());
				World.world().player().x(outsideX);
				World.world().player().y(31 * Tile.TILE_S + 7);
				Camera.camera()
						.offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
				Camera.camera()
						.offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
			}
			break;
		case 16:// SE => S
			if (World.world().player().facingDir == 2) {
				outsideY = World.world().player().y();
				World.world(World.MAIN_S);
				World.world().setPlayer(World.MAIN_SE.player());
				World.world().player().x(31 * Tile.TILE_S + 7);
				World.world().player().y(outsideY);
				Camera.camera()
						.offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
				Camera.camera()
						.offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
			}
			break;
		case 17:// S => SE
			if (World.world().player().facingDir == 1) {
				outsideY = World.world().player().y();
				World.world(World.MAIN_SE);
				World.world().setPlayer(World.MAIN_S.player());
				World.world().player().x(0 * Tile.TILE_S + 7);
				World.world().player().y(outsideY);
				Camera.camera()
						.offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
				Camera.camera()
						.offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
			}
			break;
		case 20:// W => SW
			if (World.world().player().facingDir == 0) {
				outsideX = World.world().player().x();
				World.world(World.MAIN_SW);
				World.world().setPlayer(World.MAIN_W.player());
				World.world().player().x(outsideX);
				World.world().player().y(0 * Tile.TILE_S + 7);
				Camera.camera()
						.offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
				Camera.camera()
						.offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
				Corn.sleep();
			}
			break;
		case 21:// SW => W
			if (World.world().player().facingDir == 3) {
				World.world().player().holdingPole(false);
				outsideX = World.world().player().x();
				World.world(World.MAIN_W);
				World.world().setPlayer(World.MAIN_SW.player());
				World.world().player().x(outsideX);
				World.world().player().y(31 * Tile.TILE_S + 7);
				Camera.camera()
						.offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
				Camera.camera()
						.offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
			}
			break;
		case 22:// enter cave
			if (World.world().player().facingDir == 3) {
				outsideX = World.world().player().x();
				outsideY = World.world().player().y();
				World.world(World.CAVE);
				World.world().setPlayer(World.MAIN_N.player());
				World.world().player().x(16 * Tile.TILE_S + 7);
				World.world().player().y(16 * Tile.TILE_S + 40);
				Camera.camera()
						.offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
				Camera.camera()
						.offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
			}
			break;
		case 23:// exit cave
			if (World.world().player().facingDir == 0) {
				outsideX = World.world().player().x();
				outsideY = World.world().player().y();
				World.world(World.MAIN_N);
				World.world().setPlayer(World.CAVE.player());
				World.world().player().x(19 * Tile.TILE_S + 7);
				World.world().player().y(14 * Tile.TILE_S + 40);
				Camera.camera()
						.offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
				Camera.camera()
						.offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
			}
			break;
		case 24:// enter volcano
			if (World.world().player().facingDir == 3) {
				outsideX = World.world().player().x();
				outsideY = World.world().player().y();
				World.world(World.VOLCANO);
				World.world().setPlayer(World.MAIN_N.player());
				World.world().player().x(16 * Tile.TILE_S + 7);
				World.world().player().y(16 * Tile.TILE_S + 40);
				Camera.camera()
						.offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
				Camera.camera()
						.offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
			}
			break;
		case 25:// exit volcano
			if (World.world().player().facingDir == 0) {
				outsideX = World.world().player().x();
				outsideY = World.world().player().y();
				World.world(World.MAIN_N);
				World.world().setPlayer(World.VOLCANO.player());
				World.world().player().x(13 * Tile.TILE_S + 7);
				World.world().player().y(5 * Tile.TILE_S + 40);
				Camera.camera()
						.offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
				Camera.camera()
						.offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
			}
			break;
		case 26:// dump volcano
			if (World.world().player().getInv().remove(Item.rubbish)) {
				World.world().addDroppingSack();
				Camera.camera().setText(new String[] { "You dump the sack of", "rubbish." });
				Camera.camera().showText(true);
				Flow.dumped();
			} else {
				Camera.camera().setText(new String[] { "You do not have anything", "to dump." });
				Camera.camera().showText(true);
			}
			break;
		case 27:// enter farm
			if (World.world().player().facingDir == 3) {
				outsideX = World.world().player().x();
				outsideY = World.world().player().y();
				World.world(World.FARM);
				World.world().setPlayer(World.MAIN_W.player());
				World.world().player().x(16 * Tile.TILE_S + 7);
				World.world().player().y(16 * Tile.TILE_S + 40);
				World.world().player().setDirection(0);
				Camera.camera()
						.offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
				Camera.camera()
						.offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
			}
			break;
		case 28:// exit farm
			if (World.world().player().facingDir == 3) {
				outsideX = World.world().player().x();
				outsideY = World.world().player().y();
				World.world(World.MAIN_W);
				World.world().setPlayer(World.FARM.player());
				World.world().player().x(18 * Tile.TILE_S + 7 + 32);
				World.world().player().y(16 * Tile.TILE_S + 40 + 20);
				World.world().player().setDirection(0);
				Camera.camera()
						.offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
				Camera.camera()
						.offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
			}
			break;
		case 29:// get mush cube
			String s = "";
			if (Flow.canFarm()) {
				if (World.world().player().getInv().mushroomCount() < 20) {
					World.world().player().getInv().addMushroom();
					Menu.menu().updatePixels();
					s = "You now have " + World.world().player().getInv().mushroomCount() + " mushcube";
					if (World.world().player().getInv().mushroomCount() > 1) s = s + "s";
					Camera.camera().setText(s + ".");
				} else {
					Camera.camera().setText(new String[] { "Your bag is full of", "mushcubes." });
				}
			} else {
				Camera.camera().setText(new String[] { "This is Eggplants", "mushcube farm" });
			}
			Camera.camera().showText(true);
			break;
		/*
		case 30:// enter garlic
		outsideX = World.world().player().x();
		outsideY = World.world().player().y();
		World.world(World.ROOM_GARLIC);
		World.world().setPlayer(World.MAIN_NW.player());
		World.world().player().x(12 * Tile.TILE_S + 7);
		World.world().player().y(13 * Tile.TILE_S + 40);
		Camera.camera().offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
		Camera.camera().offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
		break;
		case 31:// exit garlic
		World.world(World.MAIN_NW);
		World.world().setPlayer(World.ROOM_GARLIC.player());
		World.world().player().x(outsideX);
		World.world().player().y(outsideY);
		Camera.camera().offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
		Camera.camera().offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
		break;
		case 32:// enter carrot
		System.out.println("hi");
		outsideX = World.world().player().x();
		outsideY = World.world().player().y();
		World.world(World.ROOM_CARROT);
		World.world().setPlayer(World.MAIN_W.player());
		World.world().player().x(12 * Tile.TILE_S + 7);
		World.world().player().y(13 * Tile.TILE_S + 40);
		Camera.camera().offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
		Camera.camera().offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
		break;
		case 33:// exit carrot
		World.world(World.MAIN_W);
		World.world().setPlayer(World.ROOM_CARROT.player());
		World.world().player().x(outsideX);
		World.world().player().y(outsideY);
		Camera.camera().offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
		Camera.camera().offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
		break;
		case 34:// enter eggplant
		outsideX = World.world().player().x();
		outsideY = World.world().player().y();
		World.world(World.ROOM_EGGPLANT);
		World.world().setPlayer(World.MAIN_W.player());
		World.world().player().x(12 * Tile.TILE_S + 7);
		World.world().player().y(13 * Tile.TILE_S + 40);
		Camera.camera().offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
		Camera.camera().offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
		break;
		case 35:// exit eggplant
		World.world(World.MAIN_W);
		World.world().setPlayer(World.ROOM_EGGPLANT.player());
		World.world().player().x(outsideX);
		World.world().player().y(outsideY);
		Camera.camera().offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
		Camera.camera().offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
		break;
		case 36:// enter lettuce
		outsideX = World.world().player().x();
		outsideY = World.world().player().y();
		World.world(World.ROOM_LETTUCE);
		World.world().setPlayer(World.MAIN_W.player());
		World.world().player().x(12 * Tile.TILE_S + 7);
		World.world().player().y(13 * Tile.TILE_S + 40);
		Camera.camera().offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
		Camera.camera().offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
		break;
		case 37:// exit lettuce
		World.world(World.MAIN_W);
		World.world().setPlayer(World.ROOM_LETTUCE.player());
		World.world().player().x(outsideX);
		World.world().player().y(outsideY);
		Camera.camera().offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
		Camera.camera().offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
		break;
		case 38:// enter corn
		outsideX = World.world().player().x();
		outsideY = World.world().player().y();
		World.world(World.ROOM_CORN);
		World.world().setPlayer(World.MAIN_W.player());
		World.world().player().x(12 * Tile.TILE_S + 7);
		World.world().player().y(13 * Tile.TILE_S + 40);
		Camera.camera().offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
		Camera.camera().offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
		break;
		case 39:// exit corn
		World.world(World.MAIN_W);
		World.world().setPlayer(World.ROOM_CORN.player());
		World.world().player().x(outsideX);
		World.world().player().y(outsideY);
		Camera.camera().offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
		Camera.camera().offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
		break;
		*/
		case 40:// enter gym
			outsideX = World.world().player().x();
			outsideY = World.world().player().y();
			World.world(World.ROOM_GYM);
			World.world().setPlayer(World.MAIN_C.player());
			World.world().player().x(12 * Tile.TILE_S + 7);
			World.world().player().y(13 * Tile.TILE_S + 40);
			Camera.camera().offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
			Camera.camera().offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
			break;
		case 41:// exit gym
			World.world(World.MAIN_C);
			World.world().setPlayer(World.ROOM_GYM.player());
			World.world().player().x(outsideX);
			World.world().player().y(outsideY);
			Camera.camera().offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
			Camera.camera().offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
			break;
		case 42:// enter school
			outsideX = World.world().player().x();
			outsideY = World.world().player().y();
			World.world(World.ROOM_SCHOOL);
			World.world().setPlayer(World.MAIN_C.player());
			World.world().player().x(12 * Tile.TILE_S + 7);
			World.world().player().y(13 * Tile.TILE_S + 40);
			Camera.camera().offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
			Camera.camera().offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
			break;
		case 43:// exit school
			World.world(World.MAIN_C);
			World.world().setPlayer(World.ROOM_SCHOOL.player());
			World.world().player().x(outsideX);
			World.world().player().y(outsideY);
			Camera.camera().offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
			Camera.camera().offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
			break;
		case 44:// enter library
			outsideX = World.world().player().x();
			outsideY = World.world().player().y();
			World.world(World.ROOM_LIBRARY);
			World.world().setPlayer(World.MAIN_C.player());
			World.world().player().x(12 * Tile.TILE_S + 7);
			World.world().player().y(13 * Tile.TILE_S + 40);
			Camera.camera().offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
			Camera.camera().offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
			break;
		case 45:// exit library
			World.world(World.MAIN_C);
			World.world().setPlayer(World.ROOM_LIBRARY.player());
			World.world().player().x(outsideX);
			World.world().player().y(outsideY);
			Camera.camera().offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
			Camera.camera().offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
			break;
		case 46:// enter storage
			outsideX = World.world().player().x();
			outsideY = World.world().player().y();
			World.world(World.ROOM_STORAGE);
			World.world().setPlayer(World.MAIN_C.player());
			World.world().player().x(12 * Tile.TILE_S + 7);
			World.world().player().y(13 * Tile.TILE_S + 40);
			Camera.camera().offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
			Camera.camera().offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
			break;
		case 47:// exit storage
			World.world(World.MAIN_C);
			World.world().setPlayer(World.ROOM_STORAGE.player());
			World.world().player().x(outsideX);
			World.world().player().y(outsideY);
			Camera.camera().offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
			Camera.camera().offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
			break;
		/*
		case 48:// enter cherry
		outsideX = World.world().player().x();
		outsideY = World.world().player().y();
		World.world(World.ROOM_CHERRY);
		World.world().setPlayer(World.MAIN_NE.player());
		World.world().player().x(14 * Tile.TILE_S + 7);
		World.world().player().y(13 * Tile.TILE_S + 40);
		Camera.camera().offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
		Camera.camera().offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
		break;
		case 49:// exit cherry
		World.world(World.MAIN_NE);
		World.world().setPlayer(World.ROOM_CHERRY.player());
		World.world().player().x(outsideX);
		World.world().player().y(outsideY);
		Camera.camera().offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
		Camera.camera().offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
		break;
		case 50:// enter watermelon
		outsideX = World.world().player().x();
		outsideY = World.world().player().y();
		World.world(World.ROOM_WATERMELON);
		World.world().setPlayer(World.MAIN_NE.player());
		World.world().player().x(14 * Tile.TILE_S + 7);
		World.world().player().y(13 * Tile.TILE_S + 40);
		Camera.camera().offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
		Camera.camera().offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
		break;
		case 51:// exit watermelon
		World.world(World.MAIN_NE);
		World.world().setPlayer(World.ROOM_WATERMELON.player());
		World.world().player().x(outsideX);
		World.world().player().y(outsideY);
		Camera.camera().offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
		Camera.camera().offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
		break;
		*/
		case 52:// enter clothes
			outsideX = World.world().player().x();
			outsideY = World.world().player().y();
			World.world(World.ROOM_CLOTHES);
			World.world().setPlayer(World.MAIN_NE.player());
			World.world().player().x(14 * Tile.TILE_S + 7);
			World.world().player().y(13 * Tile.TILE_S + 40);
			Camera.camera().offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
			Camera.camera().offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
			break;
		case 53:// exit clothes
			World.world(World.MAIN_NE);
			World.world().setPlayer(World.ROOM_CLOTHES.player());
			World.world().player().x(outsideX);
			World.world().player().y(outsideY);
			Camera.camera().offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
			Camera.camera().offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
			break;
		case 54:// enter salon
			outsideX = World.world().player().x();
			outsideY = World.world().player().y();
			World.world(World.ROOM_SALON);
			World.world().setPlayer(World.MAIN_NE.player());
			World.world().player().x(14 * Tile.TILE_S + 7);
			World.world().player().y(13 * Tile.TILE_S + 40);
			Camera.camera().offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
			Camera.camera().offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
			break;
		case 55:// exit salon
			World.world(World.MAIN_NE);
			World.world().setPlayer(World.ROOM_SALON.player());
			World.world().player().x(outsideX);
			World.world().player().y(outsideY);
			Camera.camera().offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
			Camera.camera().offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
			break;
		/*
		case 56:// enter pear
		outsideX = World.world().player().x();
		outsideY = World.world().player().y();
		World.world(World.ROOM_PEAR);
		World.world().setPlayer(World.MAIN_E.player());
		World.world().player().x(14 * Tile.TILE_S + 7);
		World.world().player().y(13 * Tile.TILE_S + 40);
		Camera.camera().offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
		Camera.camera().offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
		break;
		case 57:// exit pear
		World.world(World.MAIN_E);
		World.world().setPlayer(World.ROOM_PEAR.player());
		World.world().player().x(outsideX);
		World.world().player().y(outsideY);
		Camera.camera().offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
		Camera.camera().offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
		break;
		case 58:// enter raspberry
		outsideX = World.world().player().x();
		outsideY = World.world().player().y();
		World.world(World.ROOM_RASPBERRY);
		World.world().setPlayer(World.MAIN_E.player());
		World.world().player().x(14 * Tile.TILE_S + 7);
		World.world().player().y(13 * Tile.TILE_S + 40);
		Camera.camera().offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
		Camera.camera().offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
		break;
		case 59:// exit raspberry
		World.world(World.MAIN_E);
		World.world().setPlayer(World.ROOM_RASPBERRY.player());
		World.world().player().x(outsideX);
		World.world().player().y(outsideY);
		Camera.camera().offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
		Camera.camera().offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
		break;
		case 60:// enter mango
		outsideX = World.world().player().x();
		outsideY = World.world().player().y();
		World.world(World.ROOM_MANGO);
		World.world().setPlayer(World.MAIN_E.player());
		World.world().player().x(14 * Tile.TILE_S + 7);
		World.world().player().y(13 * Tile.TILE_S + 40);
		Camera.camera().offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
		Camera.camera().offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
		break;
		case 61:// exit mango
		World.world(World.MAIN_E);
		World.world().setPlayer(World.ROOM_MANGO.player());
		World.world().player().x(outsideX);
		World.world().player().y(outsideY);
		Camera.camera().offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
		Camera.camera().offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
		break;
		case 62:// enter banana
		outsideX = World.world().player().x();
		outsideY = World.world().player().y();
		World.world(World.ROOM_BANANA);
		World.world().setPlayer(World.MAIN_E.player());
		World.world().player().x(14 * Tile.TILE_S + 7);
		World.world().player().y(13 * Tile.TILE_S + 40);
		Camera.camera().offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
		Camera.camera().offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
		break;
		case 63:// exit banana
		World.world(World.MAIN_E);
		World.world().setPlayer(World.ROOM_BANANA.player());
		World.world().player().x(outsideX);
		World.world().player().y(outsideY);
		Camera.camera().offX(World.world().player().x() + World.world().player().w() / 2 - Camera.camera().w() / 2);
		Camera.camera().offY(World.world().player().y() + World.world().player().h() / 2 - Camera.camera().h() / 2);
		break;
		*/
		case 64:// mushstall
			String stallMS1 = "";
			String stallMS2 = "";
			String stallMS3 = "";
			String stallMS4 = "";
			if (World.world().player().getInv().mushroomCount() >= 20) {
				stallMS1 = "Here are 20 fruit chips";
				stallMS2 = "for your mushcubes";
				World.world().player().getInv().mushroomCount(0);
				World.world().player().getInv().addFruitchips(20);
				Menu.menu().updatePixels();
				Flow.sellMush();
			} else {
				stallMS1 = "Hi Onion!";
				stallMS2 = "If you bring me more";
				stallMS3 = "mushcubes I can trade";
				stallMS4 = "you fruit chips for them.";
			}
			Camera.camera().setText(new String[] { stallMS1, stallMS2, stallMS3, stallMS4 });
			Camera.camera().showText(true);
			break;
		case 65:// fishstall
			String stallFS1 = "";
			String stallFS2 = "";
			String stallFS3 = "";
			String stallFS4 = "";
			if (World.world().player().getInv().fishCount() >= 20) {
				stallFS1 = "Here are 40 fruit chips";
				stallFS2 = "for your jellydisc.";
				World.world().player().getInv().fishCount(0);
				World.world().player().getInv().addFruitchips(40);
				Menu.menu().updatePixels();
			} else {
				stallFS1 = "Hi Onion!";
				stallFS2 = "If you bring me more";
				stallFS3 = "jellydisc I can trade";
				stallFS4 = "you fruit chips for them.";
			}
			Camera.camera().setText(new String[] { stallFS1, stallFS2, stallFS3, stallFS4 });
			Camera.camera().showText(true);
			break;
		case 66: // un/hold pole
			if (Flow.canFish()) {
				World.world().player().holdingPole(!World.world().player().holdingPole());
			} else {
				Camera.camera().setText(new String[] { "Corn likes to fish here." });
				Camera.camera().showText(true);
			}
			break;
		case 67: // catch fish 1
			if (World.world().player().holdingPole() && World.world().fishingSpots() != null) {
				if (World.world().player().getInv().fishCount() < 20) {
					if (World.world().fishingSpots()[0].caught()) {
						Menu.menu().updatePixels();
						Camera.camera().setText(new String[] { "You caught a jellydisc." });
						Camera.camera().showText(true);
					}
				} else {
					Camera.camera().setText(new String[] { "Your bag is full of", "jellydisc." });
					Camera.camera().showText(true);
				}
			}
			break;
		case 68: // catch fish 2
			if (World.world().player().holdingPole() && World.world().fishingSpots() != null) {
				if (World.world().player().getInv().fishCount() < 20) {
					if (World.world().fishingSpots()[1].caught()) {
						Menu.menu().updatePixels();
						Camera.camera().setText(new String[] { "You caught a jellydisc." });
						Camera.camera().showText(true);
					}
				} else {
					Camera.camera().setText(new String[] { "Your bag is full of", "jellydisc." });
					Camera.camera().showText(true);
				}
			}
			break;
		case 69: // catch fish 3
			if (World.world().player().holdingPole() && World.world().fishingSpots() != null) {
				if (World.world().player().getInv().fishCount() < 20) {
					if (World.world().fishingSpots()[2].caught()) {
						Menu.menu().updatePixels();
						Camera.camera().setText(new String[] { "You caught a jellydisc." });
						Camera.camera().showText(true);
					}
				} else {
					Camera.camera().setText(new String[] { "Your bag is full of", "jellydisc." });
					Camera.camera().showText(true);
				}
			}
			break;
		case 70: // eggplant in farm
			Camera.camera().setText(Eggplant.getComment());
			Camera.camera().showText(true);
			break;
		case 71: // corn fishing
			Camera.camera().setText(Corn.getComment());
			Camera.camera().showText(true);
			break;
		default:
		}

	}

	public boolean needsAction() {
		return needsAction;
	}

}