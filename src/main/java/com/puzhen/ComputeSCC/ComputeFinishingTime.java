package com.puzhen.ComputeSCC;

import java.io.*;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

public class ComputeFinishingTime {

	public static void main(String[] args) throws IOException {
		SCCCalculator calculator = new SCCCalculator();
		String filename = "C:\\Users\\puqian\\Desktop\\scc.txt";
		// #1. Let G_rev = G with all edges reversed
		Graph<String, DefaultEdge> inversed_graph = calculator.createInverseGraphFromFile(filename);
		// #2. Run DFS-Loop on G_rev
		System.out.println("Computing finishing time..");
		Map<String, String> f = calculator.computeFinishingTime(inversed_graph);
		System.out.println("Done computing, now writing f to file: f_time.txt");
		File file = new File("C:\\Users\\puqian\\Desktop\\f_time.txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		StringBuffer sb = null;
		for (String key : f.keySet()) {
			sb = new StringBuffer();
			sb.append(key);sb.append(" ");sb.append(f.get(key));sb.append("\r\n");
			writer.write(sb.toString());
			sb = null;
		}
		writer.flush();writer.close();
		System.out.println("Done writing to file.");
	}
}
