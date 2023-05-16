/**

fix reset maps

load new sprite in game

map mapping

parallax

world freeze limit

camera movement

sprite forward back img

update stats when map updates to make faster

object pooling

**/

package boot;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import map.Map;
import sprite.Inspector;
import sprite.Sprite;

public class Boot extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Timer timer;

	private int fCount, fCountTemp;
	private long deltaPrev, delta;
	private int uCount, uCountTemp;
	private long deltaUPSPrev, deltaUPS;

	private int upsPacer = 0, fpsPacer = 0;
	private int upsReg = 0, fpsReg = 0; // hight = slower

	private static Image offScreenBuffer;
	private static Graphics offScreenGraphics;

	private static Inspector I;

	private static Boot B;

	private static boolean ready = false;
	private static boolean showScreen = false;

	private Image back;// back ground
	private String backName = "/res/mountains.png";

	private static char[] runChars = new char[] { '-', '\\', '|', '/' };
	private static int running = -1;

	private static Keyboard keyboard = new Keyboard();

	public static void main(String[] args) {

		JFrame window = new JFrame("Frame");
		window.setBounds(40, 20, 976, 656 + 30);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);

		B = new Boot();
		window.getContentPane().add(B);
		window.setBackground(Color.BLACK);
		window.setVisible(true);
		B.init();

		window.addKeyListener(keyboard);

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
				Loader.save();
			}
		}));

	}

	public void init() {
		offScreenBuffer = createImage(getWidth(), getHeight());
		offScreenGraphics = offScreenBuffer.getGraphics();
		timer = new Timer(30, this);
		offScreenGraphics.clearRect(8, 8, 640, 640);

		fCount = 0;
		fCountTemp = 0;
		delta = System.currentTimeMillis();
		deltaPrev = System.currentTimeMillis();

		uCount = 0;
		uCountTemp = 0;
		deltaUPS = System.currentTimeMillis();
		deltaUPSPrev = System.currentTimeMillis();

		try {
			back = ImageIO.read(getClass().getResourceAsStream(backName));
		} catch (IOException e) {
			e.printStackTrace();
		}

		initMaps();

		I = new Inspector(40 * 8, 40 * 8);

		repaint();
		timer.start();
	}

	public static void initMaps() {
		Loader.load();
		ready = true;
		showScreen = true;
	}

	public void paint(Graphics g) {
		if (ready) {
			fpsPacer++;
			if (fpsPacer >= fpsReg) {
				fpsPacer = 0;
				fCountTemp++;
				draw((Graphics2D) offScreenGraphics);
				g.drawImage(offScreenBuffer, 0, 0, this);
			}
		}
		repaint();
	}

	private void draw(Graphics2D g) {

		if (Map.getMaps() == null) {
			initMaps();
		}

		if (showScreen) {

			drawScreen(g);

			if (back == null)
				try {
					back = ImageIO.read(getClass().getResourceAsStream(backName));
				} catch (IOException e) {
					e.printStackTrace();
				}

		}

		// fix
		if (true) {
			drawStats(g);
		}

		I.draw(0, 0);
	}

	private void drawScreen(Graphics2D g) {
		g.clearRect(7, 7, 641, 641);

		// draw background
		Boot.getOffScreenGraphics().drawImage(back, 8, 8, Boot.getBoot());

		// draws all maps
		Map.staticDraw(g);

		// draw all sprites
		Sprite.staticDraw();

		// draw outline
		g.setColor(Color.WHITE);
		g.drawRect(7, 7, 641, 641);
	}

	private void drawStats(Graphics2D g) {
		g.clearRect(656, 7, 304, 642);

		// fps
		g.drawString("FPS " + fCount, 660, 20);
		// ups
		g.drawString("UPS " + uCount, 660, 32);

		// draw running
		g.drawString("" + runChars[Math.max(running, 0)], 800, 21);

		// draw map times
		for (int i = 0; i < Map.getMaps().size(); i++)
			g.drawString(i + ": " + Map.getMaps().get(i).getTime() + " " + Map.getMaps().get(i).frozen(), 660,
					44 + i * 12);

		// draw outline
		g.setColor(Color.WHITE);
		g.drawRect(656, 7, 304, 642);
	}

	public void actionPerformed(ActionEvent e) {
		update();
	}

	public void update() {

		if (ready) {

			I.update();
			Camera.update();

			// fps calculations:
			delta = System.currentTimeMillis();
			if (delta - deltaPrev > 1000) {
				deltaPrev = System.currentTimeMillis();
				fCount = fCountTemp;
				fCountTemp = 0;
			}

			// ups calculations:
			deltaUPS = System.currentTimeMillis();
			if (deltaUPS - deltaUPSPrev > 1000) {
				deltaUPSPrev = System.currentTimeMillis();
				uCount = uCountTemp;
				uCountTemp = 0;
			}

			// update stuff
			if (running != -1) {
				upsPacer++;
				if (Map.getMaps() != null) {
					if (Map.getMaps().size() > 0 && upsPacer >= upsReg) {
						upsPacer = 0;
						uCountTemp++;

						// update maps
						for (int i = 0; i < Map.getMaps().size(); i++) {
							if (Map.getMaps().get(i) != null)
								Map.getMaps().get(i).update();
						}

						// update maps
						Map.staticUpdate();

						// update sprites
						Sprite.staticUpdate();

						running++;
						if (running == 4)
							running = 0;
					}
				}
			}
		}
		repaint();
	}

	public static Boot getBoot() {
		return B;
	}

	public static Inspector getInspector() {
		return I;
	}

	public static Graphics getOffScreenGraphics() {
		return offScreenGraphics;
	}

	public static void pause() {
		if (running == -1)
			running = 0;
		else
			running = -1;
	}

}