package render;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class TextEngine {

	private int x, y, w, h;

	private BufferedImage image;
	private int[] pixels;

	private int charAmount, charW, charH;
	private int[][] charPixels;

	private int offText;

	private Sprite bG;

	private boolean selected = false;

	public TextEngine(int x, int y, int w, int h, Sprite bG) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		this.pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		this.charW = charH = 16;
		this.charAmount = 50;
		this.charPixels = new int[charAmount][charW * charH];
		this.offText = charW;
		this.bG = bG;
		loadCharPixels();
	}

	public TextEngine(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		this.pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		this.charW = charH = 16;
		this.charAmount = 50;
		this.charPixels = new int[charAmount][charW * charH];
		this.offText = charW;
		this.bG = null;
		loadCharPixels();
	}

	public void renderString(String[] string) {
		clear();
		if (bG != null) {
			for (int i = 0; i < w; i++) {
				for (int j = 0; j < h; j++) {
					int pixel = bG.pixels((i + bG.pathX()) + (j + bG.pathY()) * SpriteSheet.menuETC.w());
					pixels[i + j * w] = pixel;
				}
			}
		}
		for (int i = 0; i < string.length; i++) {
			string[i] = string[i].toLowerCase();
			int offX = 0, offY = 0;
			// render output
			if (string[i].length() > 0 && string[i].charAt(0) == '&') {
				selected = true;
				string[i] = string[i].substring(1);
			} else selected = false;
			for (int c = 0; c < string[i].length(); c++) {
				offX = (c % ((w - 32) / charW) * charW);
				offY = (c / ((w - 32) / charW) + i) * charH + i * 5;
				renderChar(string[i].charAt(c), offX, offY);
			}
		}
	}

	private void renderChar(char c, int offX, int offY) {
		int charIndex = 0;
		switch (c) {
		case '0':
			charIndex = 0;
			break;
		case '1':
			charIndex = 1;
			break;
		case '2':
			charIndex = 2;
			break;
		case '3':
			charIndex = 3;
			break;
		case '4':
			charIndex = 4;
			break;
		case '5':
			charIndex = 5;
			break;
		case '6':
			charIndex = 6;
			break;
		case '7':
			charIndex = 7;
			break;
		case '8':
			charIndex = 8;
			break;
		case '9':
			charIndex = 9;
			break;
		case 'a':
			charIndex = 10;
			break;
		case 'b':
			charIndex = 11;
			break;
		case 'c':
			charIndex = 12;
			break;
		case 'd':
			charIndex = 13;
			break;
		case 'e':
			charIndex = 14;
			break;
		case 'f':
			charIndex = 15;
			break;
		case 'g':
			charIndex = 16;
			break;
		case 'h':
			charIndex = 17;
			break;
		case 'i':
			charIndex = 18;
			break;
		case 'j':
			charIndex = 19;
			break;
		case 'k':
			charIndex = 20;
			break;
		case 'l':
			charIndex = 21;
			break;
		case 'm':
			charIndex = 22;
			break;
		case 'n':
			charIndex = 23;
			break;
		case 'o':
			charIndex = 24;
			break;
		case 'p':
			charIndex = 25;
			break;
		case 'q':
			charIndex = 26;
			break;
		case 'r':
			charIndex = 27;
			break;
		case 's':
			charIndex = 28;
			break;
		case 't':
			charIndex = 29;
			break;
		case 'u':
			charIndex = 30;
			break;
		case 'v':
			charIndex = 31;
			break;
		case 'w':
			charIndex = 32;
			break;
		case 'x':
			charIndex = 33;
			break;
		case 'y':
			charIndex = 34;
			break;
		case 'z':
			charIndex = 35;
			break;
		case '.':
			charIndex = 36;
			break;
		case '!':
			charIndex = 37;
			break;
		case '?':
			charIndex = 38;
			break;
		case '%':
			charIndex = 39;
			break;
		case '"':
			charIndex = 40;
			break;
		case '(':
			charIndex = 41;
			break;
		case ')':
			charIndex = 42;
			break;
		case ':':
			charIndex = 43;
			break;
		case '=':
			charIndex = 44;
			break;
		case '|':
			charIndex = 45;
			break;
		case '+':
			charIndex = 46;
			break;
		case '-':
			charIndex = 47;
			break;
		case '*':
			charIndex = 48;
			break;
		case '/':
			charIndex = 49;
			break;
		case ' ':
			charIndex = -1;
			break;
		}
		if (charIndex != -1) {
			for (int x = 0; x < charW; x++) {
				for (int y = 0; y < charH; y++) {
					if (offText + x + offX >= 0 && offText + x + offX < w && offText + 1 + y + offY >= 0
							&& offText + 1 + y + offY < h
							&& ((charPixels[charIndex][x + y * charW] >> 24) & 0xff) != 0) {
						if (selected) pixels[offText + x + offX + (offText + 1 + y + offY) * w] = 0xffffff00;
						else pixels[offText + x + offX + (offText + 1 + y + offY) * w] = charPixels[charIndex][x
								+ y * charW];
					}
				}
			}
		}
	}

	private void clear() {
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				pixels[i + j * w] = 0x000000;
			}
		}
	}

	public String cleanString(String s) {
		s = s.toLowerCase();
		String cleanS = "";
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '0' || s.charAt(i) == '1' || s.charAt(i) == '2' || s.charAt(i) == '3'
					|| s.charAt(i) == '4' || s.charAt(i) == '5' || s.charAt(i) == '6' || s.charAt(i) == '7'
					|| s.charAt(i) == '8' || s.charAt(i) == '9' || s.charAt(i) == 'a' || s.charAt(i) == 'b'
					|| s.charAt(i) == 'c' || s.charAt(i) == 'd' || s.charAt(i) == 'e' || s.charAt(i) == 'f'
					|| s.charAt(i) == 'g' || s.charAt(i) == 'h' || s.charAt(i) == 'i' || s.charAt(i) == 'j'
					|| s.charAt(i) == 'k' || s.charAt(i) == 'l' || s.charAt(i) == 'm' || s.charAt(i) == 'n'
					|| s.charAt(i) == 'o' || s.charAt(i) == 'p' || s.charAt(i) == 'q' || s.charAt(i) == 'r'
					|| s.charAt(i) == 's' || s.charAt(i) == 't' || s.charAt(i) == 'u' || s.charAt(i) == 'v'
					|| s.charAt(i) == 'w' || s.charAt(i) == 'x' || s.charAt(i) == 'y' || s.charAt(i) == 'z'
					|| s.charAt(i) == '.' || s.charAt(i) == '!' || s.charAt(i) == '?' || s.charAt(i) == '%'
					|| s.charAt(i) == '"' || s.charAt(i) == '(' || s.charAt(i) == ')' || s.charAt(i) == ':'
					|| s.charAt(i) == '=' || s.charAt(i) == '|' || s.charAt(i) == '+' || s.charAt(i) == '-'
					|| s.charAt(i) == '*' || s.charAt(i) == '/' || s.charAt(i) == ' ') {
				cleanS = cleanS + s.charAt(i);
			}
		}
		return cleanS;
	}

	private void loadCharPixels() {
		int offX = 0, offY = 0;
		for (int c = 0; c < charAmount; c++) {
			for (int i = 0; i < charPixels[c].length; i++) {
				charPixels[c][i] = 0x000000;
			}
			offX = c % 10;
			offY = c / 10;
			for (int x = 0; x < charW; x++) {
				for (int y = 0; y < charH; y++) {
					charPixels[c][x + y * charW] = SpriteSheet.menuETC.pixels()[x + offX * charW
							+ (y + offY * charH) * SpriteSheet.menuETC.w()];
				}
			}
		}
	}

	public BufferedImage getImage() {
		return image;
	}

	public void x(int x) {
		this.x = x;
	}

	public void y(int y) {
		this.y = y;
	}

	public int x() {
		return x;
	}

	public int y() {
		return y;
	}

	public void w(int w) {
		this.w = w;
	}

	public void h(int h) {
		this.h = h;
	}

	public int w() {
		return w;
	}

	public int h() {
		return h;
	}

	public int[] pixels() {
		return pixels;
	}

	public void setBG(Sprite bG) {
		this.bG = bG;
	}

}