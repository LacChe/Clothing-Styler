import java.awt.Color;
import java.awt.Graphics2D;

public class Structure extends Sprite {

	// frames:
	// 0 = idle
	// 1 = in use

	// types:
	// 000 = repair

	private int bob, health, dHealth, built, def, atk, dir, ox, oy, diry;
	private boolean destroyed, on, held;
	private int[] bobs;

	public Structure(String n, int x, int y) {
		super(x, y);
		name = n;
		switch (n) {
		case ("000"):
			setImgs(new String[] { "res/repair0.png", "res/repair1.png" });
			health = 1000;
			dHealth = health;
			built = 1000;
			w = 40;
			h = 56;
			setDef(10);
			setAtk(0);
			team = 1;
			bob = (int) Math.random() * 50;
			bobs = new int[] { 0, 0, 0, 0, 0, 0 };
			setDir(-1);
			break;
		case ("001"):
			setImgs(new String[] { "res/laserBodyL0.png", "res/laserBodyL1.png", "res/laserBodyR0.png",
					"res/laserBodyR1.png" });
			health = 500;
			dHealth = health;
			built = 1000;
			w = 24;
			h = 12;
			setDef(0);
			setAtk(5);
			team = 1;
			bob = (int) Math.random() * 50;
			bobs = new int[] { 0, 1, 2, 2, 1, 0 };
			setDir(-1);
			on = true;
			held = false;
			ox = this.x;
			oy = this.y;
			diry = -1;
			animations.add(new Animation("res/laserBase.png", ox, oy + 28));
			break;
		}
		destroyed = false;
	}

	@Override
	public void update() {
		super.update();
		if (destroyed) {
			// set image
		}
		bob++;
		if (bob >= 60) {
			bob = 0;
		}
		switch (name) {
		case "000":
			break;
		case "001":
			if (dir == -1) {
				if (on) {
					frame = 1;
				} else if (!on) {
					frame = 0;
				}
			} else if (dir == 1) {
				if (on) {
					frame = 3;
				} else if (!on) {
					frame = 2;
				}
			}
			break;
		}
		px = x;
		py = y;
	}

	public void activate(boolean inUse) {
		if (inUse) {
			if (destroyed) {
				setBuilt(getBuilt() - 1);
			} else if (built < 1000) {
				setBuilt(getBuilt() + 1);
			} else if (dHealth < health) {
				setDHealth(getDHealth() + 10);
				m.getSelf().setResource(m.getSelf().getResource() - 1);
			} else {
				switch (name) {
				case "000":
					for (int i = 0; i < 5; i++) {
						m.getSelf().setPower(m.getSelf().getPower() + 1);
						m.getSelf().setResource(m.getSelf().getResource() + 1);
					}
					frame = 1;
					break;
				case "001":
					if (!held) {
						on = !on;
						held = true;
					}
					if (diry == 1) {
						setY(getY() + 1);
						System.out.println("down");
					} else if (diry == -1) {
						setY(getY() - 1);
						System.out.println("up");
					}
					if (py == y) {
						diry *= -1;
					}
					System.out.println(diry);
					break;
				}
			}
		}
		if (!inUse) {
			setDHealth(getDHealth() - 1);
			switch (name) {
			case "000":
				frame = 0;
				break;
			case "001":
				held = false;
				break;
			}
		}
	}

	@Override
	public void draw(Graphics2D g) {// draw
		if (name.equals("000")) {
			Boot.getOffScreenGraphics().drawImage(imgs[frame], x + 11, y + 11, Boot.getBoot());
		} else if (name.equals("001")) {
			for (Animation a : animations) {
				a.draw(g);
				System.out.println(a.getX() + "," + a.getY());
			}
			Boot.getOffScreenGraphics().drawImage(imgs[frame], x + 11, y + 11 + bobs[bob / 10], Boot.getBoot());
		}
		if (dHealth < health) {
			Color a = new Color(245, 0, 0, 150);
			Color b = new Color(255, 0, 0, 170);
			for (int i = 0; i < dHealth / 30; i++) {
				g.setColor(a);
				Boot.getOffScreenGraphics().drawLine(x + 11 + w / 10 + i, y - 7, x + 11 + w / 10 + i, y - 5);
			}
			for (int i = 0; i < health / 30; i++) {
				g.setColor(b);
				Boot.getOffScreenGraphics().drawLine(x + 11 + w / 10 + i, y - 4, x + 11 + w / 10 + i, y - 4);
			}
		}
	}

	public void underAttacked(int n) {
		for (int i = 0; i < n; i++) {
			setDHealth(getDHealth() - 1);
		}
	}

	@Override
	public String is() {
		return "S";
	}

	@Override
	public int getXVel() {
		return 0;
	}

	@Override
	public void setXVel(int x) {

	}

	@Override
	public int getYVel() {
		return 0;
	}

	@Override
	public void setYVel(int y) {

	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public void setX(int xx) {

	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setY(int yy) {
		switch (name) {
		case "000":
			break;
		case "001":
			if (oy + 20 > yy && oy < yy) {
				y = yy;
			}
			break;
		}
	}

	@Override
	public int getZ() {
		return z;
	}

	@Override
	public void setZ(int zz) {

	}

	@Override
	public int getPX() {
		return px;
	}

	@Override
	public void setPX(int x) {

	}

	@Override
	public int getPY() {
		return py;
	}

	@Override
	public void setPY(int y) {

	}

	@Override
	public int getPZ() {
		return pz;
	}

	@Override
	public void setPZ(int z) {

	}

	@Override
	public int getFrame() {
		return frame;
	}

	@Override
	public void setFrame(int i) {

	}

	@Override
	public void record() {

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
			destroyed = true;
			d = 0;
		}
		this.dHealth = d;
	}

	public int getBuilt() {
		return built;
	}

	public void setBuilt(int d) {
		if (d > 1000) {
			d = 1000;
		} else if (d < 0) {
			d = 0;
		}
		this.built = d;
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

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

}
