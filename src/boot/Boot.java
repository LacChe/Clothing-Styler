package boot;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
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

import map.Map;
import map.Tile;
import sprite.Self;
import sprite.Sprite;

public class Boot extends JPanel implements MouseListener, KeyListener,
		ActionListener {

	private static final long serialVersionUID = 1L;// idk
	private static Graphics offScreen;// buffer
	private Image img;// buffer img
	private static Boot game;// this
	private boolean ready = false;// ready to paint
	private static Map map;// current map
	private Timer timer;// timer, calls action performered
	private int dir;// where to update/move the map
	private static int frame;// frame of current map, initround resets to 0
	private ArrayList<Sprite> sprites;// all sprites on current map
	private Self AVA;// avater, self, special sprite
	private boolean pressed;
	private int type;

	// constructor for self, game
	public Boot() {

	}

	// passes game obj
	public static Boot getBoot() {
		return game;
	}

	// passes current frame
	public static int getFrame() {
		return frame;
	}

	// passes current map
	public static Map getMap() {
		return map;
	}

	// passes offscreen buffer
	public static Graphics getBuffer() {
		return offScreen;
	}

	// main, duh
	public static void main(String[] args) {
		game = new Boot();
		game.initGui();
		game.init();
	}

	// initializes game
	public void init() {
		img = createImage(800, 800);
		map = new Map("src/maps/mapHor.txt");
		initRound();
	}

	// starts or resets round, ex. transfer to new map
	public void initRound() {
		frame = 0;
		type = 0;
		pressed = false;
		offScreen = img.getGraphics();
		dir = 0;
		map.init();
		sprites = new ArrayList<Sprite>();
		AVA = new Self();
		timer = new Timer(60, this);
		timer.start();
		ready = true;
	}

	// make window
	public void initGui() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Platformer");
		frame.setResizable(false);
		frame.setVisible(true);

		frame.getContentPane().add(game);

		game.addMouseListener(game);
		game.addKeyListener(game);
		game.setFocusable(true);
		game.requestFocusInWindow();

		frame.setLocation(400, 50);

		game.setPreferredSize(new Dimension(800, 800));
		game.setMinimumSize(new Dimension(800, 800));

		Container c = frame.getContentPane();
		c.setLayout(new BorderLayout());
		c.add(game, BorderLayout.CENTER);
		frame.pack();
		frame.addKeyListener(game);
	}

	// paint, use draw instead
	public void paint(Graphics g) {
		draw((Graphics2D) offScreen);
		g.drawImage(img, 0, 0, this);
	}

	// use this to paint, not paint method
	public void draw(Graphics2D g) {
		if (ready) {
			super.paint(g);
			map.render();
			if (sprites != null) {
				for (Sprite s : sprites) {
					s.render();
				}
			}
			AVA.render();
			g.setColor(Color.WHITE);
			g.fillRect(9,9,18,18);
			new Tile(0,0,Tile.getTypeList()[type]).render(10,10);
			g.drawImage(img, 0, 0, this);
		}
		repaint();
	}

	// what to do every 30 ms, updates
	@Override
	public void actionPerformed(ActionEvent e) {
		if (ready) {
			frame++;
			AVA.update(dir);
			repaint();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if (pressed) {
			map.getTiles()[x / 16][y / 16].setType(type);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		pressed = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		pressed = false;
	}

	// whats pressed right now
	@SuppressWarnings({ "unused" })
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch (keyCode) {
		case KeyEvent.VK_A:
			dir = 3;
			// System.out.println(dir);
			break;
		case KeyEvent.VK_D:
			dir = 1;
			// System.out.println(dir);
			break;
		case KeyEvent.VK_W:
			if (false) {
				dir = 4;
			}
			// System.out.println(dir);
			break;
		case KeyEvent.VK_S:
			if (false) {
				dir = 2;
			}
			// System.out.println(dir);
			break;
		case KeyEvent.VK_T:
			map.teleport();
			initRound();
			break;
		case KeyEvent.VK_E:
			type++;
			if (type == Tile.getTypeList().length) {
				type = 0;
			}
			System.out.println(type);
			break;
		}
	}

	// what was just released
	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_D
				|| keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_W) {
			dir = 0;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
