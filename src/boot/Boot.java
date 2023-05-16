package boot;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import graphics.Camera;
import room.RoomManager;

public class Boot extends Canvas implements Runnable {

	public static int loaded = 1;

	private static final long serialVersionUID = -1L;

	public static final int WIDTH = 640, HEIGHT = 720;

	public static Boot boot;

	private Thread thread;
	private JFrame frame;
	private boolean running = false;

	private Mouse mouse;

	public Boot() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));

		frame = new JFrame();

		frame.setResizable(false);
		frame.setTitle("bathroom");
		frame.add(this);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		mouse = new Mouse();
		addMouseListener(mouse);

		Camera.init();
		RoomManager.init();
		// Mouse.init(frame);

	}

	public void update() {
		mouse.update(frame);
		// RoomManager.update();
	}

	public void render() {
		Camera.render();

		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.drawImage(Camera.image(), 0, 0, WIDTH, HEIGHT, null);

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
		while (running) {
			long now = System.nanoTime();
			Timer.delta += (now - Timer.lastTime) / Timer.ns;
			Timer.lastTime = now;

			while (Timer.delta > 1) {
				update();
				Timer.updates++;
				Timer.delta--;
			}
			render();
			Timer.frames++;

			if (System.currentTimeMillis() - Timer.timer > 1000) {
				Timer.timer += 1000;
				frame.setTitle("bathroom | FPS " + Timer.frames + " | UPS " + Timer.updates);
				Timer.frames = 0;
				Timer.updates = 0;
			}
		}
		stop();
	}

	public JFrame frame() {
		return frame;
	}
}