package com.puzhen.ComputeSCC.script;

import java.io.*;

public class FindSmallNumbers {

	public static void main(String[] args) throws IOException {
		File file = new File("C:\\Users\\puqian\\Desktop\\f_time.txt");
		BufferedReader rd = new BufferedReader(new FileReader(file));
		String line = "";int count = 0;
		boolean foundSmallNumber = false;
		while ((line = rd.readLine()) != null) {
			String[] array = line.split(" ");
			int v = Integer.valueOf(array[1]);
			if (v <= 1000) {
				if (!foundSmallNumber) foundSmallNumber = true;
				System.out.println("Found " + v);
				count++;
			}
		}
		rd.close();
		if (!foundSmallNumber) {
			System.out.println("There is no number less than 1000.");
		}
		System.out.println("Count is: " + count);
	}
}
