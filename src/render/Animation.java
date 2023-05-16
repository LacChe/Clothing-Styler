package render;

public class Animation {

	private Sprite[] frames;
	private int pointer;
	private double elapsedTime, currentTime, lastTime, fps;

	public Animation(Sprite[] s, int fps) {
		pointer = 0;
		elapsedTime = currentTime = 0;
		lastTime = (double) System.nanoTime() / (double) 1000000000l;
		this.fps = 1.0 / (double) fps;

		frames = s;
	}

	public Animation(Sprite s) {
		pointer = 0;
		elapsedTime = currentTime = 0;
		lastTime = (double) System.nanoTime() / (double) 1000000000l;
		this.fps = 1.0 / (double) 3;
		frames = new Sprite[] { s };
	}

	public Sprite currentSprite() {
		this.currentTime = (double) System.nanoTime() / (double) 1000000000l;
		this.elapsedTime += currentTime - lastTime;
		if (elapsedTime >= fps) {
			elapsedTime = 0;
			pointer++;
		}
		if (pointer >= frames.length) pointer = 0;
		this.lastTime = currentTime;
		return frames[pointer];
	}

	public void currentSprite(Sprite s) {
		frames[pointer] = s;
	}

}