package com.puzhen.ComputeSCC.trial;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

public class TryDirectedGraph {

	private TryDirectedGraph() {}
	
	public static void main(String[] args) {
		Graph<String, DefaultEdge> graph = 
				new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
		
		// add the vertices
		graph.addVertex("1");
		graph.addVertex("2");
		
		// add the edges
		graph.addEdge("1", "2");
		graph.addEdge("2", "1");
		System.out.println(graph.toString());
	}
}
