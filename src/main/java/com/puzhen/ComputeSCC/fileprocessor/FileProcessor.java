package com.puzhen.ComputeSCC.fileprocessor;

import java.io.*;
import java.util.Map;

/*
 * The test cases copied from discussion forum contains
 * empty lines. This program helps get rid of it.
 * ----------------------------------------------------
 * USAGE:
 * 1. put the data file at the root directory of this program
 * 2. in line "processor.deleteEmptyLines("33200.txt");", change the argument to the 
 * name of your file.
 * 3. run it and boom you get what you want.
 */
public class FileProcessor {

	/**
	 * Change vertex name based on a mapping function.
	 * @throws IOException 
	 */
	public void changeVertexNames(String old_filename, 
			Map<String, String> f, String new_filename) throws IOException {
		File new_file = new File(new_filename);
		File old_file = new File(old_filename);
		BufferedReader rd = new BufferedReader(new FileReader(old_file));
		BufferedWriter writer = new BufferedWriter(new FileWriter(new_file));
		String line = "";
		boolean first = true;
		while ((line = rd.readLine()) != null) {
			String[] vertices = line.split(" ");
			if (first) {
				writer.write(f.get(vertices[0]) + " " + f.get(vertices[1]));
				first = false;
			} else
				writer.write("\r\n" + f.get(vertices[0]) + " " + f.get(vertices[1]));
		}
		rd.close();
		writer.flush();writer.close();
	}
	
	/**
	 * Delete empty lines in a file.
	 * @param filename
	 * @throws IOException
	 */
	public void deleteEmptyLines(String filename) throws IOException {
		File origin = new File(filename);
		File temp = new File(filename + ".temp");
		BufferedReader rd = new BufferedReader(new FileReader(origin));
		BufferedWriter writer = new BufferedWriter(new FileWriter(temp));
		String line = "";
		// write the non-empty lines to the temp file
		boolean first = true;
		while ((line = rd.readLine()) != null) {
			if (!line.equals("")) {
				if (first) {
					writer.write(line);
					first = false;
				} else
					writer.write("\r\n" + line);
			}
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
			processor.deleteEmptyLines("63210.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
