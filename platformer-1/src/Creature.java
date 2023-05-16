import java.awt.Color;
import java.awt.Graphics2D;

public class Creature extends Sprite {// <= extends Sprite

	// frames:
	// 0-3 = L
	// 4-7 = R
	// 8-9 = fall
	// 10-11 = dead

	// types:
	// 000 = lizard
	// 001 = wings

	private int dir, speed, fallSpeed, jumpH, dJumpH, health, dHealth, def, atk;
	private boolean walking, falling, jumping, dead;

	public Creature(String fileName, int i, int j) {
		super(i, j);
		name = fileName;
		switch (fileName) {
		case "000":
			setImgs(new String[] { "/res/lizardL0.png", "/res/lizardL1.png", "/res/lizardL2.png", "/res/lizardL1.png",
					"/res/lizardR0.png", "/res/lizardR1.png", "/res/lizardR2.png", "/res/lizardR1.png",
					"/res/lizardLF.png", "/res/lizardRF.png", "/res/lizardLD.png", "/res/lizardRD.png" });
			w = 16;
			h = 16;
			speed = 1;
			fallSpeed = 5;
			jumpH = 0;
			dJumpH = jumpH;
			health = 500;
			dHealth = health;
			setDef(2);
			setAtk(0);
			team = 1;
			break;
		case "001":
			setImgs(new String[] { "/res/wingsL0.png", "/res/wingsL1.png", "/res/wingsL2.png", "/res/wingsL1.png",
					"/res/wingsR0.png", "/res/wingsR1.png", "/res/wingsR2.png", "/res/wingsR1.png", "/res/wingsLF.png",
					"/res/wingsRF.png", "/res/wingsLD.png", "/res/wingsRD.png" });
			w = 24;
			h = 8;
			speed = 3;
			fallSpeed = 1;
			jumpH = 7;
			dJumpH = jumpH;
			health = 500;
			dHealth = health;
			setDef(0);
			setAtk(0);
			team = 1;
			break;
		}
		dir = -1;
		frame = 0;
		dFrame = 1;
		walking = false;
		falling = false;
		jumping = false;
		dead = false;
	}

	public void draw(Graphics2D g) {
		Boot.getOffScreenGraphics().drawImage(imgs[frame], x + 11, y + 11, Boot.getBoot());// draw
		if (dHealth < health && dHealth != 0) {
			Color a = new Color(245, 0, 0, 150);
			Color b = new Color(255, 0, 0, 170);
			for (int i = 0; i < dHealth / 30; i++) {
				g.setColor(a);
				Boot.getOffScreenGraphics().drawLine(x + 11 + w / 10 + i, y - 6, x + 11 + w / 10 + i, y - 5);
			}
			for (int i = 0; i < health / 30; i++) {
				g.setColor(b);
				Boot.getOffScreenGraphics().drawLine(x + 11 + w / 10 + i, y - 4, x + 11 + w / 10 + i, y - 4);
			}
		}
	}

	// update pos
	public void update() {
		super.update();
		if (dead) {
			if (dir == -1) {
				frame = 10;
			} else if (dir == 1) {
				frame = 11;
			}
		} else {
			if (y > py) {
				falling = true;
			} else if (y <= py) {
				falling = false;
			}
			if (walking) {
				if (name.equals("001") || !falling) {
					for (int i = 0; i < speed; i++) {
						ranWalking();
					}
				}
				if ((int) (Math.random() * 50) < 1) {
					turn();
				}
			}
			if (!jumping) {
				fall();
			}
			if (!falling && ((jumpH == dJumpH && (int) (Math.random() * 500) < 1) || jumpH != dJumpH)) {
				jump();
			}
			if (falling) {
				walking = false;
				if (dir == -1) {
					frame = 8;
				} else if (dir == 1) {
					frame = 9;
				}
			}
			if ((int) (Math.random() * 10) < 3) {
				walking = !walking;
			}
			if ((int) (Math.random() * 10) < 3 || falling) {
				walking = false;
			}
			px = x;
			py = y;
		}
		setDHealth(getDHealth() - 1);
	}

	public void walk() {

	}

	public void ranWalking() {
		dFrame++;
		if (dFrame > 40) {
			dFrame = 0;
		}
		if (dFrame % 2 == 0) {
			if (dir == -1) {
				frame++;
				if (frame > 3) {
					frame = 0;
				}
			} else if (dir == 1) {
				frame++;
				if (frame > 7) {
					frame = 4;
				}
			}
		}
		move(dir, 0);
		if (!jumping && !falling && px == x) {
			turn();
			if (name.equals("001")) {
				jump();
			}
		}
	}

	public void move(int i, int j) {
		px = x;
		py = y;
		boolean good = true;
		if (i == -1 && x > 0) {
			if (name.equals("001") && m.getTiles()[(x - 1) / 8][(y + h) / 8].isPassable() && !falling) {
				good = false;
			}
			for (int num = 0; num < h / 8; num++) {
				if (!m.getTiles()[(x - 1) / 8][(y + num * 8) / 8].isPassable()
						|| !m.getTiles()[(x - 1) / 8][(y + num * 8 + 7) / 8].isPassable()) {
					good = false;
				}
			}
			if (good) {
				setX(getX() - 1);
			}
		} else if (i == 1 && x + w < m.getX() * 8) {
			if (name.equals("001") && m.getTiles()[(x + w) / 8][(y + h) / 8].isPassable() && !falling) {
				good = false;
			}
			for (int num = 0; num < h / 8; num++) {
				if (!m.getTiles()[(x + w) / 8][(y + num * 8) / 8].isPassable()
						|| !m.getTiles()[(x + w) / 8][(y + num * 8 + 7) / 8].isPassable()) {
					good = false;
				}
			}
			if (good) {
				setX(getX() + 1);
			}
		} else if (i == 0) {
			//
		}
		if (j == -1 && y > 0) {
			for (int num = 0; num < w / 8; num++) {
				if (!m.getTiles()[(x + num * 8) / 8][(y - 1) / 8].isPassable()
						|| !m.getTiles()[(x + num * 8 + 7) / 8][(y - 1) / 8].isPassable()) {
					good = false;
				}
			}
			if (good) {
				setY(getY() - 1);
			}
		} else if (j == 1 && y + h < m.getY() * 8) {
			for (int num = 0; num < w / 8; num++) {
				if (!m.getTiles()[(x + num * 8) / 8][(y + h) / 8].isPassable()
						|| !m.getTiles()[(x + num * 8 + 7) / 8][(y + h) / 8].isPassable()) {
					good = false;
				}
			}
			if (good) {
				setY(getY() + 1);
			}
		} else if (j == 0) {
			//
		}
	}

	public void turn() {
		if (!jumping && !falling) {
			dir *= -1;
			if (frame < 4) {
				frame = 4;
			} else if (frame < 8) {
				frame = 0;
			}
		}
	}

	public void jump() {
		if (!falling) {
			jumping = true;
			int jump = 5;
			if (dJumpH < 5) {
				jump = dJumpH;
			}
			for (int i = 0; i < jump; i++) {
				move(0, -1);
			}
			dJumpH -= 1;
			if (dJumpH < 0) {
				dJumpH = jumpH;
				jumping = false;
			}
		}
	}

	public void fall() {
		boolean good = true;
		for (int i = 0; i < fallSpeed; i++) {
			for (int num = 0; num < w / 8; num++) {
				if (!m.getTiles()[(x + num * 8) / 8][(y + h) / 8].isPassable()
						|| !m.getTiles()[(x + num * 8 + 7) / 8][(y + h) / 8].isPassable()) {
					good = false;
					falling = false;
				}
			}
			if (good) {
				falling = true;
				move(0, 1);
				if (name.equals("001")) {
					for (int j = 0; j < 2; j++) {
						move(dir, 0);
					}
				}
			}
		}
	}

	public boolean isOverlap() {
		return false;
	}

	public String is() {
		return "C";
	}

	public void record() {

	}

	@Override
	public int getXVel() {
		// TODO Auto-generated method stub
		return xVel;
	}

	@Override
	public void setXVel(int x) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getYVel() {
		// TODO Auto-generated method stub
		return yVel;
	}

	@Override
	public void setYVel(int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int getZ() {
		return z;
	}

	@Override
	public void setZ(int z) {
		this.z = z;
	}

	@Override
	public int getPX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPX(int x) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getPY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPY(int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getPZ() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPZ(int z) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getFrame() {
		return frame;
	}

	@Override
	public void setFrame(int i) {
		frame = i;
	}

	public int getHealth() {
		return health;
	}

	public int getDHealth() {
		return dHealth;
	}

	public void setDHealth(int d) {
		if (d > 1000) {
			d = 1000;
		} else if (d <= 0) {
			dead = true;
			d = 0;
		}
		this.dHealth = d;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

}