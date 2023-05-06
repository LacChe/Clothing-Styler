package world;

public class Flow {

	private static boolean canFarm = false;
	private static boolean justSoldMush = false;

	private static boolean canFish = false;
	private static boolean justSoldFish = false;

	private static boolean needDumping = true;
	private static boolean justDumped = false;

	public static boolean needDumping() {
		return needDumping;
	}

	public static void needDumping(boolean b) {
		needDumping = b;
	}

	public static boolean justDumped() {
		return justDumped;
	}

	public static void justDumped(boolean b) {
		justDumped = b;
	}

	public static boolean justSoldMush() {
		return justSoldMush;
	}

	public static void justSoldMush(boolean b) {
		justSoldMush = b;
	}

	public static boolean canFarm() {
		return canFarm;
	}

	public static void canFarm(boolean b) {
		canFarm = b;
	}

	public static boolean justSoldFish() {
		return justSoldFish;
	}

	public static void justSoldFish(boolean b) {
		justSoldFish = b;
	}

	public static boolean canFish() {
		return canFish;
	}

	public static void canFish(boolean b) {
		canFish = b;
	}

	public static void sellMush() {
		System.out.println("hi " + canFish + " " + justSoldMush);
		if (!canFish) justSoldMush = true;
		System.out.println("again " + canFish + " " + justSoldMush);
	}

	public static void sellFish() {
		justSoldFish = true;
	}

	public static void dumped() {
		if (needDumping) {
			needDumping = false;
			justDumped = true;
		}
	}

}