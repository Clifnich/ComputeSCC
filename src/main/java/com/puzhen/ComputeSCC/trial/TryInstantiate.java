package com.puzhen.ComputeSCC.trial;

import org.jgrapht.*;
import org.jgrapht.graph.*;

/*
 * An initial attempt to use JGraphT.
 * ----------------------------------
 * This program creates a simple graph with undirected edges.
 */

public class TryInstantiate {

	private TryInstantiate() {}
	
	public static void main(String[] args) {
		Graph<String, DefaultEdge> graph = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);
		
		// add the vertex
		graph.addVertex("1");
		graph.addVertex("2");
		
		// add the edge
		graph.addEdge("1", "2");
		System.out.println(graph.toString());
	}
}
