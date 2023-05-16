import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Self extends Sprite {

	// 00 = drone

	private int dir, bob, xSpeed, atStructure, working, power, resource, team;
	private int[] bobs;
	private boolean xAccelerating, fall;
	private int tile;
	private static TileType[] typeList = new TileType[] { TileType.Null, TileType.Dirt, TileType.Grass };

	// speed

	// prev

	public Self(String n, int x, int y) {
		super(x, y);
		power = 1000;
		resource = 1000;
		tile = 0;
		dir = 1;
		xVel = 0;
		xAccelerating = false;
		atStructure = 0;
		working = 0;
		bob = 0;
		xSpeed = 0;
		bobs = new int[] { 0, 1, 2, 2, 1, 0 };
		fall = true;
		switch (n) {
		case "00":
			setImgs(new String[] { "res/selfR0.png", "res/selfL0.png", "res/selfR1.png", "res/selfL1.png" });
			w = 24;
			h = 16;
			setTeam(0);
			break;
		}
		record();
	}

	public static TileType[] getTypeList() {
		return typeList;
	}

	public static void setTypeList(TileType[] typeList) {
		Self.typeList = typeList;
	}

	public void setTile(int tile) {
		this.tile = tile;
	}

	@Override
	public void draw(Graphics2D g) {
		Boot.getOffScreenGraphics().drawImage(imgs[frame], x + 11, y + 11 + bobs[bob / 10], Boot.getBoot());
		drawHUD(g);
	}

	private void drawHUD(Graphics2D g) {
		double num = ((double) power) / 1000.0 * 294.0;
		for (int i = 0; i < (int) num; i++) {
			Color a;
			Color b;
			if (num < 50) {
				a = new Color(100, 0, 0, 150);
				b = new Color(80, 0, 0, 170);
			} else if (num < 150) {
				a = new Color(100, 100, 0, 150);
				b = new Color(80, 80, 0, 170);
			} else {
				a = new Color(0, 100, 0, 150);
				b = new Color(0, 80, 0, 170);
			}
			g.setColor(a);
			g.drawLine(22 + i, 22, 22 + i, 37);
			g.setColor(b);
			g.drawLine(22 + i, 38, 22 + i, 46);
		}
		g.setColor(Color.lightGray);
		g.fillRect(19, 19, 3, 30);
		g.fillRect(315, 19, 3, 30);
		g.fillRect(19, 19, 299, 3);
		g.fillRect(19, 46, 299, 3);

		BufferedImage tile = null;
		try {
			tile = ImageIO.read(getClass().getResourceAsStream(typeList[this.tile].fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.fillRect(19, 46, 3, 30);
		g.fillRect(122, 46, 3, 30);
		g.fillRect(19, 76, 106, 3);
		for (int i = 0; i < (resource / 10); i++) {
			g.setColor(new Color(255, 255, 255, 150));
			g.drawLine(22 + i, 49, 22 + i, 67);
			g.setColor(new Color(205, 205, 205, 170));
			g.drawLine(22 + i, 68, 22 + i, 75);
		}
		g.setColor(Color.lightGray);
		g.fillRect(130, 56, 14, 14);
		g.drawImage(tile, 133, 59, Boot.getBoot());
	}

	public void record() {
		px = x;
		py = y;
		pz = z;
	}

	@Override
	public void update() {
		// System.out.println(yOff + " " + (y + 16 + 4 * 8) / 8 + " " + (y + 16
		// + 4 * 8));
		// System.out.println(power);
		super.update();
		atStructure();
		if (power < 1) {
			fall = true;
			working = 0;
		}

		bob++;
		if (bob >= 60) {
			bob = 0;
		}

		if (xVel != 0) {
			xAccelerating = true;
			dir = xVel;
		} else {
			xAccelerating = false;
		}
		if (dir == 1) {
			if (working != 0) {
				frame = 2;
			} else {
				frame = 0;
			}
		} else if (dir == -1) {
			if (working != 0) {
				frame = 3;
			} else {
				frame = 1;
			}
		}
		accelerate();
		for (int i = 0; i < xSpeed; i++) {
			move(dir, 0);
		}
		move(0, yVel);
		if (fall) {
			fall();
			fall();
			fall();
		}
		if (working != 0) {
			setPower(getPower() - 1);
		}
		if (!fall && power > 0) {
			setPower(getPower() - 1);
		} else if (fall && power < 1000) {
			// setPower(getPower() + 1);
		}
	}

	public void move(int i, int j) {
		if (i == -1 && x > 0) {
			if (m.getTiles()[(x - 1) / 8][(y) / 8].isPassable() && m.getTiles()[(x - 1) / 8][(y + 8) / 8].isPassable()
					&& m.getTiles()[(x - 1) / 8][(y + 15) / 8].isPassable()) {
				setX(getX() - 1);
			}
		} else if (i == 1 && x + w < m.getX() * 8) {
			if (m.getTiles()[(x + 24) / 8][(y) / 8].isPassable() && m.getTiles()[(x + 24) / 8][(y + 8) / 8].isPassable()
					&& m.getTiles()[(x + 24) / 8][(y + 15) / 8].isPassable()) {
				setX(getX() + 1);
			}
		} else if (i == 0) {
			//
		}
		if (!fall) {
			if (j == -1 && y > 0) {
				if (m.getTiles()[(x) / 8][(y - 1) / 8].isPassable()
						&& m.getTiles()[(x + 8) / 8][(y - 1) / 8].isPassable()
						&& m.getTiles()[(x + 16) / 8][(y - 1) / 8].isPassable()
						&& m.getTiles()[(x + 23) / 8][(y - 1) / 8].isPassable()) {
					setY(getY() - 1);
				}
			} else if (j == 1 && y + h < m.getY() * 8) {
				if (m.getTiles()[(x) / 8][(y + 16) / 8].isPassable()
						&& m.getTiles()[(x + 8) / 8][(y + 16) / 8].isPassable()
						&& m.getTiles()[(x + 16) / 8][(y + 16) / 8].isPassable()
						&& m.getTiles()[(x + 23) / 8][(y + 16) / 8].isPassable()) {
					setY(getY() + 1);
				}
			} else if (j == 0) {
				//
			}
		}
	}

	public void accelerate() {
		if (xAccelerating && xSpeed < 8) {
			xSpeed++;
		} else if (!xAccelerating && xSpeed >= 0) {
			xSpeed--;
		}
	}

	public void fall() {
		boolean fall = true;
		for (int n = 0; n < 4; n++) {
			if ((y + 47 - n * 8) / 8 < m.getY()) {
				if ((!m.getTiles()[x / 8][(y + 47 - n * 8) / 8].isPassable()
						|| !m.getTiles()[(x + 8) / 8][(y + 47 - n * 8) / 8].isPassable()
						|| !m.getTiles()[(x + 16) / 8][(y + 47 - n * 8) / 8].isPassable()
						|| !m.getTiles()[(x + 23) / 8][(y + 47 - n * 8) / 8].isPassable())) {
					if (m.getTiles()[x / 8][(y - 1) / 8].isPassable()
							&& m.getTiles()[(x + 8) / 8][(y - 1) / 8].isPassable()
							&& m.getTiles()[(x + 16) / 8][(y - 1) / 8].isPassable()
							&& m.getTiles()[(x + 23) / 8][(y - 1) / 8].isPassable()) {
						setY(getY() - 1);
					}
					fall = false;
				}
			}
		}
		if (fall) {
			if (m.getTiles()[x / 8][(y + 48) / 8].isPassable() && m.getTiles()[(x + 8) / 8][(y + 48) / 8].isPassable()
					&& m.getTiles()[(x + 16) / 8][(y + 48) / 8].isPassable()
					&& m.getTiles()[(x + 23) / 8][(y + 48) / 8].isPassable()) {
				for (int i = 0; i < 1; i++) {
					setY(getY() + 1);
				}
			}
		}
	}

	public void replicate() {
		if (dir == -1) {
			if (tile != 0) {
				setResource(getResource() - 5);
			}
			m.getTiles()[(x - 1) / 8][(y + 8) / 8] = new Tile((x - 1) / 8, (y + 8) / 8, typeList[tile]);
		} else if (dir == 1) {
			if (tile != 0) {
				setResource(getResource() - 5);
			}
			m.getTiles()[(x + 24) / 8][(y + 8) / 8] = new Tile((x + 24) / 8, (y + 8) / 8, typeList[tile]);
		}
		updateMap();
	}

	public void atStructure() {
		if (working != 0) {
			for (Structure s : m.getStructures()) {
				if ((s.getX() - 8 <= x && s.getX() + 16 >= x) && (s.getY() - 8 <= y && s.getY() + 8 >= y)) {
					s.activate(true);
				} else {
					s.activate(false);
				}
			}
			// System.out.println("add");
		} else if (working == 0) {
			for (Structure s : m.getStructures()) {
				s.activate(false);
			}
			// System.out.println("sub");
		}
	}

	@Override
	public String is() {
		return "@";
	}

	public int getPower() {
		return power;
	}

	public void setPower(int p) {
		this.power = p;
		if (power < 0) {
			power = 0;
		} else if (power > 1000) {
			power = 1000;
		}
	}

	public boolean isFall() {
		return fall;
	}

	public void setFall(boolean fall) {
		if (power > 50 && fall == true) {
			this.fall = fall;
		} else if (fall == false) {
			this.fall = fall;
		}
	}

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

	public boolean isXAccelerating() {
		return xAccelerating;
	}

	public void setXAccelerating(boolean accelerating) {
		this.xAccelerating = accelerating;
	}

	public int getAtStructure() {
		return atStructure;
	}

	public void setAtStructure(int atStructure) {
		this.atStructure = atStructure;
	}

	public int getWorking() {
		return working;
	}

	public void setWorking(int w) {
		if (power >= 0) {
			this.working = w;
		}
	}

	public int getXVel() {
		return xVel;
	}

	public void setXVel(int x) {
		xVel = x;
	}

	public int getYVel() {
		return yVel;
	}

	public void setYVel(int y) {
		yVel = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int xx) {
		if (xx < 0) {
			xx = 0;
		}
		this.x = xx;
	}

	public int getY() {
		return y;
	}

	public void setY(int yy) {
		if (yy < 0) {
			yy = 0;
		}
		this.y = yy;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public int getPX() {
		return px;
	}

	public void setPX(int x) {
		this.px = x;
	}

	public int getPY() {
		return py;
	}

	public void setPY(int y) {
		this.py = y;
	}

	public int getPZ() {
		return pz;
	}

	public void setPZ(int z) {
		this.pz = z;
	}

	public int getFrame() {
		return frame;
	}

	public void setFrame(int i) {
		this.frame = i;
	}

	public int getTile() {
		return tile;
	}

	public void nextTile() {
		tile++;
		if (tile >= typeList.length) {
			tile = 0;
		}
	}

	public int getResource() {
		return resource;
	}

	public void setResource(int resource) {
		if (resource < 0) {
			resource = 0;
		} else if (resource > 1000) {
			resource = 1000;
		}
		this.resource = resource;
	}

	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}

}
