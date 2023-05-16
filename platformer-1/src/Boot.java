import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Boot extends JPanel implements MouseListener, KeyListener, ActionListener {

	private static final long serialVersionUID = 1L;
	private Timer timer;
	private int fCount, fCountTemp;
	private long deltaPrev, delta;
	private static Image offScreenBuffer;
	private static Graphics offScreenGraphics;
	private static Boot b;
	private static boolean ready = false, running = false, finGen = false, loading = false;
	private static boolean showScreen = false;
	private static ArrayList<Map> maps;
	private static double loaded = 0;

	public static void main(String[] args) {
		JFrame window = new JFrame("Frame");
		window.setBounds(40, 20, 1280, 720);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);

		b = new Boot();
		window.getContentPane().add(b);
		window.setBackground(Color.BLACK);
		window.setVisible(true);
		b.init();
		window.addKeyListener(b);
		window.addMouseListener(b);
	}

	public void init() {
		offScreenBuffer = createImage(getWidth(), getHeight());
		offScreenGraphics = offScreenBuffer.getGraphics();
		timer = new Timer(30, this);
		initRound();
	}

	public void initRound() {
		offScreenGraphics.clearRect(0, 0, 1280, 720);
		fCount = 0;
		fCountTemp = 0;
		delta = System.currentTimeMillis();
		deltaPrev = System.currentTimeMillis();
		ready = true;
		repaint();
		timer.start();
	}

	public static void initMaps() {
		Thread mapGen = new Thread(new Runnable() {
			public void run() {
				loading = true;
				maps = new ArrayList<Map>();
				maps.add(new Map(0));
				setButtons();
				finGen = true;
				loading = false;
				showScreen = true;
			}
		});
		mapGen.start();
	}

	public void paint(Graphics g) {
		if (ready) {
			draw((Graphics2D) offScreenGraphics);
			g.drawImage(offScreenBuffer, 0, 0, this);
		}
		repaint();
	}

	private void draw(Graphics2D g) {
		g.clearRect(0, 0, 1280, 720);

		if (maps == null) {
			initMaps();
		}

		if (showScreen) {
			drawScreen(g);
		}

		// load bar
		g.setColor(Color.WHITE);
		g.drawRect(0, 680, 1273, 10);
		g.fillRect(0, 680, (int) (1273 * loaded), 10);
		g.drawString("FPS " + fCount, 1222, 9);
	}

	private void drawScreen(Graphics2D g) {
		g.setColor(Color.WHITE);
		maps.get(0).draw(g);

		if (running) {
			g.drawString("RUNNING", 1162, 9);
		} else if (!running) {
			g.drawString("PAUSED", 1170, 9);
		}
		g.drawRect(10, 10, 1249, 660);
	}

	public void actionPerformed(ActionEvent e) {
		delta = System.currentTimeMillis();
		if (delta - deltaPrev == 1000) {
			deltaPrev = System.currentTimeMillis();
			fCount = fCountTemp;
			fCountTemp = 0;
		}
		if (maps != null && showScreen && running) {
			for (Map m : maps) {
				m.update();
			}
		}
		fCountTemp++;
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent E) {
		int key = E.getKeyCode();
		Thread space = new Thread(new Runnable() {
			@Override
			public void run() {
				if (!loading && key == KeyEvent.VK_SPACE) {
					running = !running;
				}
			}
		});
		Thread up = new Thread(new Runnable() {
			@Override
			public void run() {
				if (key != KeyEvent.VK_A) {
					if (!loading && key == KeyEvent.VK_UP && maps.get(0).getSelf().getYVel() > -1) {
						maps.get(0).getSelf().setYVel(maps.get(0).getSelf().getYVel() - 1);
					}
				}
			}
		});
		Thread down = new Thread(new Runnable() {
			@Override
			public void run() {
				if (key != KeyEvent.VK_A) {
					if (!loading && key == KeyEvent.VK_DOWN && maps.get(0).getSelf().getYVel() < 1) {
						maps.get(0).getSelf().setYVel(maps.get(0).getSelf().getYVel() + 1);
					}
				}
			}
		});
		Thread left = new Thread(new Runnable() {
			@Override
			public void run() {
				if (key != KeyEvent.VK_A) {
					if (!loading && key == KeyEvent.VK_LEFT && maps.get(0).getSelf().getXVel() > -1) {
						maps.get(0).getSelf().setXVel(maps.get(0).getSelf().getXVel() - 1);
					}
				}
			}
		});
		Thread right = new Thread(new Runnable() {
			@Override
			public void run() {
				if (key != KeyEvent.VK_A) {
					if (!loading && key == KeyEvent.VK_RIGHT && maps.get(0).getSelf().getXVel() < 1) {
						maps.get(0).getSelf().setXVel(maps.get(0).getSelf().getXVel() + 1);
					}
				}
			}
		});
		Thread a = new Thread(new Runnable() {
			@Override
			public void run() {
				if (!loading && key == KeyEvent.VK_A) {
					maps.get(0).getSelf().setWorking(1);
				}
			}
		});
		space.start();
		up.start();
		down.start();
		left.start();
		right.start();
		a.start();
	}

	@Override
	public void keyReleased(KeyEvent E) {
		int key = E.getKeyCode();
		Thread up = new Thread(new Runnable() {
			@Override
			public void run() {
				if (key != KeyEvent.VK_A) {
					if (key == KeyEvent.VK_UP && maps.get(0).getSelf().getYVel() != 1) {
						maps.get(0).getSelf().setYVel(maps.get(0).getSelf().getYVel() + 1);
					}
				}
			}
		});
		Thread down = new Thread(new Runnable() {
			@Override
			public void run() {
				if (key != KeyEvent.VK_A) {
					if (key == KeyEvent.VK_DOWN && maps.get(0).getSelf().getYVel() != -1) {
						maps.get(0).getSelf().setYVel(maps.get(0).getSelf().getYVel() - 1);
					}
				}
			}
		});
		Thread left = new Thread(new Runnable() {
			@Override
			public void run() {
				if (key != KeyEvent.VK_A) {
					if (key == KeyEvent.VK_LEFT && maps.get(0).getSelf().getXVel() != 1) {
						maps.get(0).getSelf().setXVel(maps.get(0).getSelf().getXVel() + 1);
					}
				}
			}
		});
		Thread right = new Thread(new Runnable() {
			@Override
			public void run() {
				if (key != KeyEvent.VK_A) {
					if (key == KeyEvent.VK_RIGHT && maps.get(0).getSelf().getXVel() != -1) {
						maps.get(0).getSelf().setXVel(maps.get(0).getSelf().getXVel() - 1);
					}
				}
			}
		});
		Thread a = new Thread(new Runnable() {
			@Override
			public void run() {
				if (key == KeyEvent.VK_A) {
					maps.get(0).getSelf().setWorking(0);
				}
			}
		});
		Thread z = new Thread(new Runnable() {
			@Override
			public void run() {
				if (!loading && key == KeyEvent.VK_Z) {
					maps.get(0).getSelf().setFall(!maps.get(0).getSelf().isFall());
				}
			}
		});
		Thread e = new Thread(new Runnable() {
			@Override
			public void run() {
				if (key == KeyEvent.VK_E) {
					maps.get(0).getSelf().nextTile();
				}
			}
		});
		Thread r = new Thread(new Runnable() {
			@Override
			public void run() {
				if (key == KeyEvent.VK_R && maps.get(0).getSelf().getWorking() == 1) {
					maps.get(0).getSelf().replicate();
				}
			}
		});
		up.start();
		down.start();
		left.start();
		right.start();
		a.start();
		z.start();
		e.start();
		r.start();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		@SuppressWarnings(value = { "unused" })
		int key = e.getKeyCode();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int X = e.getX() - 3;
		int Y = e.getY() - 26;
		if (!loading) {
			if (showScreen) {
				clickedScreen(X, Y);
			}
		}
	}

	private void clickedScreen(int x, int y) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	private static void setButtons() {

	}

	public static Boot getBoot() {
		return b;
	}

	public static Graphics getOffScreenGraphics() {
		return offScreenGraphics;
	}

	public static boolean isShowScreen() {
		return showScreen;
	}

	public static void setShowScreen(boolean showScreen) {
		Boot.showScreen = showScreen;
	}

	public static boolean isFinGen() {
		return finGen;
	}

	public static void setFinGen(boolean finGen) {
		Boot.finGen = finGen;
	}

	public static double getLoaded() {
		return loaded;
	}

	public static void setLoaded(double loaded) {
		Boot.loaded = loaded;
	}

	public static ArrayList<Map> getMaps() {
		return maps;
	}

	public static void setMaps(ArrayList<Map> maps) {
		Boot.maps = maps;
	}

}// end class Boot