package boot;

public class Timer {
	
	public static long lastTime = System.nanoTime();
	public static long timer = System.currentTimeMillis();
	public static final double ns = 1000000000.0 / 60.0;
	public static double delta = 0;
	public static int frames = 0, updates = 0;

}
