package com.puzhen.ComputeSCC.fileprocessor;

import java.io.*;

public class FileProcessor {

	public void deleteEmptyLines(String filename) throws IOException {
		File origin = new File(filename);
		File temp = new File(filename + ".temp");
		BufferedReader rd = new BufferedReader(new FileReader(origin));
		BufferedWriter writer = new BufferedWriter(new FileWriter(temp));
		String line = "";
		// write the non-empty lines to the temp file
		while ((line = rd.readLine()) != null) {
			if (!line.equals(""))
				writer.write(line + "\r\n");
		}
		rd.close();
		writer.flush();writer.close();
		
		// let the temp file become the only file
		origin.delete();
		temp.renameTo(origin);
	}
	
	public static void main(String[] args) {
		FileProcessor processor = new FileProcessor();
		try {
			processor.deleteEmptyLines("33300.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
