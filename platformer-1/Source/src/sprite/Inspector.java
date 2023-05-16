package sprite;

import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.imageio.ImageIO;

import boot.Boot;
import boot.Camera;
import boot.Keyboard;

public class Inspector extends Sprite {

	private static final long serialVersionUID = 3671143737509988741L;

	private boolean ghost = true;

	public Inspector(int x, int y) {
		super(x, y);
		imgName = "/res/inspector.png";
	}

	public void update() {

		updateLocale();

		updateVelocity();

		move();

		Camera.setX(x - 40 * 8);
		Camera.setY(y - 40 * 8);

	}

	private void move() {
		// move
		for (int i = 0; i < Math.abs(yVelocity); i++) {
			if (!collide(0, yVelocity / Math.abs(yVelocity)))
				y += yVelocity / Math.abs(yVelocity);
			else
				yVelocity = 0;
		}

		for (int i = 0; i < Math.abs(xVelocity); i++) {
			if (!collide(xVelocity / Math.abs(xVelocity), 0))
				x += xVelocity / Math.abs(xVelocity);
			else {
				xVelocity = 0;
			}
		}
	}

	// draw sprite
	public void draw(int OffX, int OffY) {
		if (img == null)
			try {
				this.img = ImageIO.read(getClass().getResourceAsStream(imgName));
			} catch (IOException e) {
				e.printStackTrace();
			}

		Boot.getOffScreenGraphics().drawImage(img, 320 + 8, 320 + 8, Boot.getBoot());
	}

	public boolean ghost() {
		return ghost;
	}

	public void setGhost(boolean g) {
		xVelocity = 0;
		yVelocity = 0;
		ghost = g;
	}

	private void updateVelocity() {
		if (Keyboard.isPressed(KeyEvent.VK_UP) && !Keyboard.isPressed(KeyEvent.VK_DOWN))
			yVelocity = -5;
		else if (Keyboard.isPressed(KeyEvent.VK_DOWN) && !Keyboard.isPressed(KeyEvent.VK_UP))
			yVelocity = 5;
		else
			yVelocity = 0;

		if (Keyboard.isPressed(KeyEvent.VK_LEFT) && !Keyboard.isPressed(KeyEvent.VK_RIGHT))
			xVelocity = -5;
		else if (Keyboard.isPressed(KeyEvent.VK_RIGHT) && !Keyboard.isPressed(KeyEvent.VK_LEFT))
			xVelocity = 5;
		else
			xVelocity = 0;
	}

}
