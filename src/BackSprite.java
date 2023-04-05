import java.awt.Image;
import java.util.ArrayList;

public class BackSprite extends Sprite {

	public static ArrayList<BackSprite> backs = new ArrayList<BackSprite>();

	public BackSprite(String fileName) {
		super(fileName, 0, 0, 1280, 720);
		backs.add(this);
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public void setX(int x) {

	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setY(int y) {

	}

	@Override
	public int getW() {
		return w;
	}

	@Override
	public void setW(int w) {

	}

	@Override
	public int getH() {
		return h;
	}

	@Override
	public void setH(int h) {

	}

	@Override
	public int getSX() {
		return sx;
	}

	@Override
	public void setSX(int sx) {

	}

	@Override
	public int getSY() {
		return sy;
	}

	@Override
	public void setSY(int sy) {

	}

	@Override
	public int getSW() {
		return sw;
	}

	@Override
	public void setSW(int sw) {

	}

	@Override
	public int getSH() {
		return sh;
	}

	@Override
	public void setSH(int sh) {

	}

	@Override
	public int getFrame() {
		return frame;
	}

	@Override
	public void setFrame(int i) {

	}

	@Override
	public Image[] getImages() {
		return imgs;
	}

}
