import java.awt.Image;

public class ClothingSprite extends Sprite {

	public ClothingSprite(String fileName, int x, int y, int w, int h, int sx, int sy, int sw, int sh) {
		super(fileName, x, y, w, h, sx, sy, sw, sh);
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int getFrame() {
		return frame;
	}

	@Override
	public void setFrame(int i) {

	}

	@Override
	public int getW() {
		return w;
	}

	@Override
	public void setW(int w) {
		this.w = w;
	}

	@Override
	public int getH() {
		return h;
	}

	@Override
	public void setH(int h) {
		this.h = h;
	}

	public Image[] getImages() {
		return imgs;
	}

	@Override
	public int getSX() {
		return sx;
	}

	@Override
	public void setSX(int sx) {
		this.sx = sx;
	}

	@Override
	public int getSY() {
		return sy;
	}

	@Override
	public void setSY(int sy) {
		this.sy = sy;
	}

	@Override
	public int getSW() {
		return sw;
	}

	@Override
	public void setSW(int sw) {
		this.sw = sw;
	}

	@Override
	public int getSH() {
		return sh;
	}

	@Override
	public void setSH(int sh) {
		this.sh = sh;
	}

}
