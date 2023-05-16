
public class Animation extends Sprite {

	public Animation(String fileName, int x, int y) {
		super(fileName, x, y);
		this.x /= 8;
		this.y /= 8;
	}

	public Animation(String fileName[], int x, int y) {
		super(fileName, x, y);
		this.x /= 8;
		this.y /= 8;
	}

	@Override
	public String is() {
		return "A";
	}

	@Override
	public int getXVel() {
		return xVel;
	}

	@Override
	public void setXVel(int x) {

	}

	@Override
	public int getYVel() {
		return yVel;
	}

	@Override
	public void setYVel(int y) {

	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public void setX(int xx) {
		x = xx;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setY(int yy) {
		y = yy;
	}

	@Override
	public int getZ() {
		return z;
	}

	@Override
	public void setZ(int zz) {
		z = zz;
	}

	@Override
	public int getPX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPX(int x) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getPY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPY(int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getPZ() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPZ(int z) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getFrame() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setFrame(int i) {
		// TODO Auto-generated method stub

	}

	@Override
	public void record() {
		// TODO Auto-generated method stub

	}

}