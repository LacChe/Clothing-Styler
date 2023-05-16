import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;

public abstract class Reader {

	private static ArrayList<String> initInfo = new ArrayList<String>();
	private static int outfitCount;

	public static void Read() {
		try {
			String pathname = "src\\res\\init.txt";
			File file = new File(pathname);
			InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
			BufferedReader br = new BufferedReader(reader);

			String line = "";
			while ((line = br.readLine()) != null) {
				// System.out.println(line);
				if (!line.startsWith("#"))
					initInfo.add(line);
			}

			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		initOutfits(initInfo);

		Boot.setReady(true);
	}

	public static void write() {
		try {
			File writename = new File("res\\save.txt");
			writename.createNewFile();
			BufferedWriter out = new BufferedWriter(new FileWriter(writename));
			out.write("save this");
			out.flush();
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void initOutfits(ArrayList<String> s) {
		new BackSprite("res\\" + s.remove(0));
		outfitCount = Integer.parseInt(s.remove(0));
		for (int x = 0; x < outfitCount; x++) {
			// System.out.println(s.get(0));
			String[] outfitString = s.remove(0).split(" ", -1);
			String[] itemNames = new String[Integer.parseInt(outfitString[2])];
			for (int i = 0; i < Integer.parseInt(outfitString[2]); i++) {
				itemNames[i] = s.remove(0);
			}

			int[][][] dimensions = new int[Integer.parseInt(outfitString[1])][Integer.parseInt(outfitString[2])][8];
			for (int i = 0; i < Integer.parseInt(outfitString[1]); i++) {

				for (int j = 0; j < Integer.parseInt(outfitString[2]); j++) {
					String[] tempDim = s.remove(0).split(" ", -1);
					dimensions[i][j][0] = Integer.parseInt(tempDim[0]);
					dimensions[i][j][1] = Integer.parseInt(tempDim[1]);
					dimensions[i][j][2] = Integer.parseInt(tempDim[2]);
					dimensions[i][j][3] = Integer.parseInt(tempDim[3]);
					dimensions[i][j][4] = Integer.parseInt(tempDim[4]);
					dimensions[i][j][5] = Integer.parseInt(tempDim[5]);
					dimensions[i][j][6] = Integer.parseInt(tempDim[6]);
					dimensions[i][j][7] = Integer.parseInt(tempDim[7]);
				}

			}

			new Outfit("res\\" + outfitString[0], itemNames, dimensions, Integer.parseInt(outfitString[1]),
					Integer.parseInt(outfitString[2]));
		}
	}

}