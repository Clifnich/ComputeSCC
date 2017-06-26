package com.puzhen.ComputeSCC;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.io.*;

public class SCCCalculator {
	
	/**
	 * This method computes the SCC(strongly connected components)
	 * based on the Kosaraju's Two-Pass Algorithm.
	 * @param graph
	 * @return
	 */
	public int[] computeSCC(Graph<String, DefaultEdge> graph) {
		int[] defaultReturnValue = {0,0,0,0,0};
		
		return defaultReturnValue;
	}
	
	/**
	 * This method reads the data file and create a directed graph
	 * @param filename
	 * @return
	 * @throws IOException 
	 */
	public Graph<String, DefaultEdge> createGraphFromFile(String filename) throws IOException {
		Graph<String, DefaultEdge> graph = 
				new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
		File file = new File(filename);
		BufferedReader rd = new BufferedReader(new FileReader(file));
		String line = "";
		while ((line = rd.readLine()) != null) {
			// if it's not an empty line
			if (!line.equals("")) {
				String[] vertices = line.split(" ");
				// add new vertices and edge to the graph
				if (!graph.containsVertex(vertices[0]))
					graph.addVertex(vertices[0]);
				if (!graph.containsVertex(vertices[1]))
					graph.addVertex(vertices[1]);
				graph.addEdge(vertices[0], vertices[1]);
			}
		}
		rd.close();
		return graph;
	}
}
