package com.puzhen.ComputeSCC;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ComputeResult {

	public static void main(String[] args) throws IOException {
		Map<String, String> leader = new HashMap<String, String>();
		String filename = "C:\\Users\\puqian\\Desktop\\f_time.txt_second";
		File file = new File(filename);
		BufferedReader rd = new BufferedReader(new FileReader(file));
		String line = "";
		while ((line = rd.readLine()) != null) {
			String[] elements = line.split(" ");
			leader.put(elements[0], elements[1]);
		}
		rd.close();
		
		SCCCalculator calculator = new SCCCalculator();
		System.out.println("Done computing leader map, sorting it now..");
		int[] result = calculator.getSCCFromLeaderMap(leader);
		for (int i : result)
			System.out.println(i);
	}
}
