package com.puzhen.ComputeSCC.trial;

import java.io.*;

public class ReadFile {

	public static void main(String[] args) throws IOException {
		File file = new File("/Users/Ishmael/Desktop/scc.txt");
		BufferedReader rd = new BufferedReader(new FileReader(file));
		String line = "";
		while ((line = rd.readLine()) != null)
			System.out.println(line);
		rd.close();
	}
}
