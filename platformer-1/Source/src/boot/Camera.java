package boot;

import map.Map;

public class Camera {

	private static int X = 0, Y = 0, W = 640, H = 640;
	private boolean atLeft, atRight, atTop, atBottom;

	public static void update() {

	}

	public static int getX() {
		return X;
	}

	public static void setX(int x) {
		X = x;
	}

	public static int getY() {
		return Y;
	}

	public static void setY(int y) {
		Y = y;
	}

	public static int getW() {
		return W;
	}

	public static int getH() {
		return H;
	}

	public boolean isAtLeft() {
		if (X == 0)
			atLeft = true;
		else
			atLeft = false;
		return atLeft;
	}

	public boolean isAtRight() {
		if (X + W == Map.rightMost())
			atRight = true;
		else
			atRight = false;
		return atRight;
	}

	public boolean isAtTop() {
		if (Y == 0)
			atTop = true;
		else
			atTop = false;
		return atTop;
	}

	public boolean isAtBottom() {
		if (Y + H == Map.bottomMost())
			atBottom = true;
		else
			atBottom = false;
		return atBottom;
	}

	public static boolean inView(int x, int y, int w, int h) {
		if ((x + w >= X && x <= X + W + w) && (y + h >= Y && y <= Y + H + h))
			return true;
		return false;
	}

}
