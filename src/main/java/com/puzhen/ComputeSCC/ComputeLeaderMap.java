package com.puzhen.ComputeSCC;

import java.util.*;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import com.puzhen.ComputeSCC.fileprocessor.FileProcessor;

import java.io.*;

public class ComputeLeaderMap {

	public static void main(String[] args) throws IOException {
		Map<String, String> f = new HashMap<String, String>();
		String f_file = "C:\\Users\\puqian\\Desktop\\f_time.txt";
		File file = new File(f_file);
		BufferedReader rd = new BufferedReader(new FileReader(file));
		String line = "";
		while ((line = rd.readLine()) != null) {
			String[] elements = line.split(" ");
			f.put(elements[0], elements[1]);
		}
		rd.close();
		
		String filename = "C:\\Users\\puqian\\Desktop\\scc.txt";
		FileProcessor processor = new FileProcessor();
		String newFilename = filename + "_second";
		processor.changeVertexNames(filename, f, newFilename);
		
		SCCCalculator calculator = new SCCCalculator();
		Graph<String, DefaultEdge> graph = calculator.createGraphFromFile(newFilename);
		Map<String, String> leaderMap = calculator.computeLeaderMap(graph);
		System.out.println("Done computing leader map, sorting it now..");
		int[] result = calculator.getSCCFromLeaderMap(leaderMap);
		for (int i : result)
			System.out.println(i);
	}
}
