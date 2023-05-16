package main;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Boot {

	private static final int wE = 32, hE = 32, wW = 32, hW = 32, wDst = 64, hDst = 32;
	private static int[] pixelsWorld = new int[wW * hW];
	private static int[] pixelsEntity = new int[wE * hE];

	private static BufferedImage image = new BufferedImage(wDst, hDst, BufferedImage.TYPE_INT_RGB);
	private static BufferedReader br;

	public static void main(String[] args) {
		args = new String[2];
		args[0] = "mainSW";
		args[1] = args[0] + "Entities";
		// args[1] = "Entities";
		if (args.length != 2) return;
		try {

			/*
			// parse world file
			br = new BufferedReader(new FileReader(new File(args[0] + ".txt")));
			parseWorld(br);
			*/

			// get tile pixels
			BufferedImage img = ImageIO.read(Boot.class.getResource("/" + args[0] + ".png"));
			img.getRGB(0, 0, wW, hW, pixelsWorld, 0, wW);

			// parse entity file
			br = new BufferedReader(new FileReader(new File("res/" + args[1] + ".txt")));
			parseEntity(br);
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		transferPixels();
		saveImage(args[0]);
	}

	public static void parseWorld(BufferedReader br) throws IOException {
		for (int i = 0; i < pixelsWorld.length; i++) {
			pixelsWorld[i] = ((255) << 16) + ((255) << 8) + (255);
		}
		String line;
		for (int w = 0; w < wW; w++) {
			line = br.readLine().replace("(", "").replace(")", "");
			String[] tiles = line.split(" ");
			for (int h = 0; h < hW; h++) {
				String[] rgb = tiles[h].split(",");
				int r = Integer.parseInt(rgb[0]);
				int g = Integer.parseInt(rgb[1]);
				int b = Integer.parseInt(rgb[2]);
				pixelsWorld[w + h * wW] = ((r) << 16) + ((g) << 8) + (b);
			}
		}
	}

	public static void parseEntity(BufferedReader br) throws NumberFormatException, IOException {
		int count = Integer.parseInt(br.readLine());
		pixelsEntity[0] = count;
		for (int i = 1; i <= count * 3; i += 3) {
			String[] s = br.readLine().split(" ");
			int r = Integer.parseInt(s[0]) / 10000;
			int g = (Integer.parseInt(s[0]) / 100) % 10000;
			int b = Integer.parseInt(s[0]) % 100;
			int rx = Integer.parseInt(s[1]) / 10000;
			int gx = (Integer.parseInt(s[1]) / 100) % 10000;
			int bx = Integer.parseInt(s[1]) % 100;
			int ry = Integer.parseInt(s[2]) / 10000;
			int gy = (Integer.parseInt(s[2]) / 100) % 10000;
			int by = Integer.parseInt(s[2]) % 100;
			pixelsEntity[i] = ((r) << 16) + ((g) << 8) + (b);
			pixelsEntity[i + 1] = ((rx) << 16) + ((gx) << 8) + (bx);
			pixelsEntity[i + 2] = ((ry) << 16) + ((gy) << 8) + (by);
		}
	}

	public static void transferPixels() {
		for (int w = 0; w < wDst; w++) {
			for (int h = 0; h < hDst; h++) {
				if (w < wW) image.setRGB(w, h, pixelsWorld[w + h * wW]);
				if (w >= wW) image.setRGB(w, h, pixelsEntity[w - wE + h * wE]);
			}
		}
	}

	public static void saveImage(String s) {
		try {
			File outImage = new File(s + ".png");
			ImageIO.write(image, "PNG", outImage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}