package io;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import render.Camera;
import world.World;

public class Boot extends Canvas implements Runnable {

	public static int loaded = 1;

	private static final long serialVersionUID = -1L;

	public static final int WIDTH = 960, HEIGHT = WIDTH / 16 * 9;

	public static Boot boot;

	private Thread thread;
	private JFrame frame;
	private Keyboard key;
	private Mouse mouse;
	private boolean running = false;

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public Boot() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));

		frame = new JFrame();

		frame.setResizable(false);
		frame.setTitle("tileEngine");
		frame.add(this);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		key = new Keyboard();
		addKeyListener(key);
		mouse = new Mouse();
		addMouseListener(mouse);

	}

	public void update() {
		if (!Initializer.done) {
			Initializer.initialize();
		} else {
			if (Keyboard.ESC) stop();
			World.world().update();
			Camera.camera().update(World.world());
		}
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		for (int i = 0; i < pixels.length; i++) {
		}

		if (!Initializer.done) {
			g.fillRect(20 * loaded + 10, 30, 10, 10);
			g.drawString(loaded + "", 20 * loaded + 10, 50);
		} else {
			Camera.camera().render();
			g.drawImage(Camera.camera().getImage(), 0, 0, Camera.camera().w(), Camera.camera().h(), null);
		}
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		boot = new Boot();
		boot.start();
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			System.exit(0);
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0, updates = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			while (delta > 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle("tileEngine | FPS " + frames + " | UPS " + updates);
				frames = 0;
				updates = 0;
			}
		}
		// Save.save(new Save(world), "save1.ser");
		stop();
	}
}