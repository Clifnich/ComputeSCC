package com.puzhen.ComputeSCC.trial;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

/*
 * This class runs a program to see if the vertices 
 * will be automatically created when adding a new edge that
 * contains vertices that haven't been added to graph before
 */
public class WhoIsFirst {

	public static void main(String[] args) {
		Graph<String, DefaultEdge> graph = 
				new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
		
		graph.addEdge("1", "2");
		System.out.println(graph.toString());
		
		// It turns out that this program will trigger a IllegalArgumentException
	}
}
