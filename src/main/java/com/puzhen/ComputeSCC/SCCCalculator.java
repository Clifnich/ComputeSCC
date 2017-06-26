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
	 * This method computes the SCC(strongly connected components)
	 * based on the Kosaraju's Two-Pass Algorithm.
	 */
	public int[] computeSCC(String filename) {
		int[] defaultReturnValue = {0,0,0,0,0};	
		return defaultReturnValue;
	}
	
	/**
	 * Create a graph with inverse edges from file
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public Graph<String, DefaultEdge> createInverseGraphFromFile(String filename) throws IOException {
		return createGraph(filename, INVERSE);
	}

	/**
	 * Create a graph from file normally.
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public Graph<String, DefaultEdge> createGraphFromFile(String filename) throws IOException {
		return createGraph(filename, NORMAL);
	}
	
	/**
	 * This method reads the data file and create a directed graph.
	 * Client of this method has two options, NORMAL and INVERSE.
	 * @param filename
	 * @param option: NORMAL or INVERSE
	 * @return
	 * @throws IOException 
	 */
	private Graph<String, DefaultEdge> createGraph(String filename, String option) throws IOException {
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
				// edge option, normal or inverse
				if (option.equals(NORMAL))
					graph.addEdge(vertices[0], vertices[1]);
				else if (option.equals(INVERSE))
					graph.addEdge(vertices[1], vertices[0]);
			}
		}
		rd.close();
		return graph;
	}
	
	private static final String NORMAL = "NORMAL";
	private static final String INVERSE = "INVERSE";
}
