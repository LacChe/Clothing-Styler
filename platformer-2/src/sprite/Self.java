package sprite;

import map.Tile;
import boot.Boot;

public class Self extends Sprite {// <= extends Sprite

	// surrounding tiles solidity
	private boolean solidLU = false;
	private boolean solidLD = false;
	private boolean solidRU = false;
	private boolean solidRD = false;
	private boolean solidU = false;
	private boolean solidD = false;

	// surrounding tiles
	private Tile LU = null;
	private Tile LD = null;
	private Tile RU = null;
	private Tile RD = null;
	private Tile U = null;
	private Tile D = null;

	private static String[] fileName = new String[] { "selfL0", "selfR0" };

	// constructor for avatar
	public Self() {
		super(fileName);
		if (Boot.getMap().getWidth() == 500) {
			offx = 0;
			offy = 0;
		} else {
			offx = 0;
			offy = 0;
		}
		// starting/center pos
		x = 25 * 16;
		y = 25 * 16;
	}

	// draw avatar
	@Override
	public void render() {
		Boot.getBuffer().drawImage(imgs[frame], x + offx, y + offy,
				Boot.getBoot());
	}

	// update stuff, like position, ie jumping and moving
	@Override
	public void update(int i) {
		setSur();
		if (i == 3) {
			frame = 0;
		} else if (i == 1) {
			frame = 1;
		}
		if (LU != null) {
			solidLU = LU.isSolid();
			// System.out.print("LU " + solidLU);
		}
		if (LD != null) {
			solidLD = LD.isSolid();
			// System.out.print("LD " + solidLU);
		}
		if (RU != null) {
			solidRU = RU.isSolid();
			// System.out.print("RU " + solidLU);
		}
		if (RD != null) {
			solidRD = RD.isSolid();
			// System.out.print("RD " + solidLU);
		}
		if (U != null) {
			solidU = U.isSolid();
			// System.out.print("U " + solidLU);
		}
		if (D != null) {
			solidD = D.isSolid();
			// System.out.println("D " + solidLU);
		}
		switch (i) {
		case 0:
			Boot.getMap().update(i);
			break;
		case 1:
			if (!(solidRU || solidRD)) {
				// setOffx(offx + 8);
				Boot.getMap().update(i);
			} else {
				Boot.getMap().update(0);
			}
			break;
		case 2:
			if (!solidD && climbable) {
				// setOffy(offy + 8);
				Boot.getMap().update(i);
			}
			break;
		case 3:
			if (!(solidLU || solidLD)) {
				// setOffx(offx - 8);
				Boot.getMap().update(i);
			} else {
				Boot.getMap().update(0);
			}
			break;
		case 4:
			if (!solidU && climbable) {
				// setOffy(offy - 8);
				Boot.getMap().update(i);
			}
			break;
		}
		solidLU = false;
		solidLD = false;
		solidRU = false;
		solidRD = false;
		solidU = false;
		solidD = false;
		fall();
	}

	public void setSur() {
		// System.out.println();
		LU = Boot.getMap().getTiles()[(Boot.getMap().getOffx() + x + offx + 8) / 16 - 1][(Boot
				.getMap().getOffy() + y + offy) / 16];
		LD = Boot.getMap().getTiles()[(Boot.getMap().getOffx() + x + offx + 8) / 16 - 1][(Boot
				.getMap().getOffy() + y + offy) / 16 + 1];
		RU = Boot.getMap().getTiles()[(Boot.getMap().getOffx() + x + offx) / 16 + 1][(Boot
				.getMap().getOffy() + y + offy) / 16];
		RD = Boot.getMap().getTiles()[(Boot.getMap().getOffx() + x + offx) / 16 + 1][(Boot
				.getMap().getOffy() + y + offy) / 16 + 1];
		U = Boot.getMap().getTiles()[(Boot.getMap().getOffx() + x + offx) / 16][(Boot
				.getMap().getOffy() + y + offy) / 16 - 1];
		D = Boot.getMap().getTiles()[(Boot.getMap().getOffx() + x + offx) / 16][(Boot
				.getMap().getOffy() + y + offy) / 16 + 2];
	}

	// checks if can climb(ie walkable + not solid) at current pos, then climbs
	// if so
	public void climb() {
		if (Boot.getMap().getTiles()[(Boot.getMap().getOffx() + x + offx) / 16][(Boot
				.getMap().getOffy() + y + offy) / 16] != null) {
			walkable = Boot.getMap().getTiles()[(Boot.getMap().getOffx() + x + offx) / 16][(Boot
					.getMap().getOffy() + y + offy) / 16].isWalkable();
		}
		if (!walkable) {
			setOffy(offy + 16);
		}
	}

	// checks if can fall(ie walkable + solid) at current pos, then falls if so
	public void fall() {
		if (Boot.getMap().getTiles()[(Boot.getMap().getOffx() + x + offx) / 16][(Boot
				.getMap().getOffy() + y + offy) / 16 + 2] != null) {
			walkable = Boot.getMap().getTiles()[(Boot.getMap().getOffx() + x + offx) / 16][(Boot
					.getMap().getOffy() + y + offy) / 16 + 2].isWalkable();
		}
		if (!walkable) {
			setOffy(offy + 8);
			if (y != 25 * 16) {
				Boot.getMap().update(2);
			}
			falling = true;
		} else {
			falling = false;
		}
		// System.out.println((Boot.getMap().getOffy() + y + offy) / 16 + 2 +
		// " " + walkable);
	}

	public boolean isFalling() {
		return falling;
	}

	// moves x offset
	@Override
	public void setOffx(int x) {
		offx = x;
	}

	// moves y offset
	@Override
	public void setOffy(int y) {
		offy = y;
	}

	// passes x offset
	@Override
	public int getOffx() {
		return offx;
	}

	// passes y offset
	@Override
	public int getOffy() {
		return offy;
	}

}