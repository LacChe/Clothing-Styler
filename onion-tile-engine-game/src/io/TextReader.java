package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TextReader {

	public static String readFile(String fileName) {
		StringBuilder string = new StringBuilder();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(new File("res/" + fileName + ".txt")));
			String line;
			while ((line = br.readLine()) != null) {
				string.append(line).append("\n");
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return string.toString();
	}

}