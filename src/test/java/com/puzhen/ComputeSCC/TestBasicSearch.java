package com.puzhen.ComputeSCC;


import java.io.IOException;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import com.puzhen.ComputeSCC.trial.GraphSearcher;

import junit.framework.TestCase;

public class TestBasicSearch extends TestCase {

	private SCCCalculator calculator;
	
	public TestBasicSearch(String name) {
		super(name);
		calculator = new SCCCalculator();
	}
	
	/**
	 * Test the recursive DFS method.
	 * Case 1.
	 * @throws Exception 
	 */
	public void test0() throws Exception {
		GraphSearcher searcher = new GraphSearcher();
		Graph<String, DefaultEdge> graph = calculator.createGraphFromFile("33200.txt");
		assertTrue(searcher.dfs_recur(graph, "1", "5"));
		assertTrue(searcher.dfs_recur(graph, "1", "8"));
		assertTrue(searcher.dfs_recur(graph, "8", "5"));
		assertFalse(searcher.dfs_recur(graph, "8", "1"));
	}
	
	/**
	 * Test the recursive DFS method.
	 * Case 2.
	 * @throws IOException 
	 */
	public void testDFSRecurisive() throws IOException {
		GraphSearcher searcher = new GraphSearcher();
		Graph<String, DefaultEdge> graph = calculator.createGraphFromFile("33300.txt");
		assertTrue(searcher.dfs_recur(graph, "1", "4"));
		assertTrue(searcher.dfs_recur(graph, "1", "7"));
		assertFalse(searcher.dfs_recur(graph, "1", "6"));
		assertTrue(searcher.dfs_recur(graph, "3", "9"));
		assertFalse(searcher.dfs_recur(graph, "3", "2"));
	}
	
	/**
	 * Test the BFS method
	 * Case 1. 33200.txt
	 */
	public void testBFS0() throws IOException {
		GraphSearcher searcher = new GraphSearcher();
		Graph<String, DefaultEdge> graph = calculator.createGraphFromFile("33200.txt");
		assertTrue(searcher.bfs(graph, "1", "5"));
		assertTrue(searcher.bfs(graph, "1", "8"));
		assertTrue(searcher.bfs(graph, "8", "5"));
		assertFalse(searcher.bfs(graph, "8", "1"));
	}
	
	/**
	 * Test the recursive BFS method.
	 * Case 2.
	 * @throws IOException 
	 */
	public void testBFS1() throws IOException {
		GraphSearcher searcher = new GraphSearcher();
		Graph<String, DefaultEdge> graph = calculator.createGraphFromFile("33300.txt");
		assertTrue(searcher.bfs(graph, "1", "4"));
		assertTrue(searcher.bfs(graph, "1", "7"));
		assertFalse(searcher.bfs(graph, "1", "6"));
		assertTrue(searcher.bfs(graph, "3", "9"));
		assertFalse(searcher.bfs(graph, "3", "2"));
	}
	
	
	/**
	 * Test the non-recursive DFS method.
	 * Case 1.
	 * @throws Exception 
	 */
	public void testDFSNonrecursive0() throws Exception {
		GraphSearcher searcher = new GraphSearcher();
		Graph<String, DefaultEdge> graph = calculator.createGraphFromFile("33200.txt");
		assertTrue(searcher.dfs_nonrecur(graph, "1", "5"));
		assertTrue(searcher.dfs_nonrecur(graph, "1", "8"));
		assertTrue(searcher.dfs_nonrecur(graph, "8", "5"));
		assertFalse(searcher.dfs_nonrecur(graph, "8", "1"));
	}
	
	/**
	 * Test the non-recursive DFS method.
	 * Case 2.
	 * @throws IOException 
	 */
	public void testDFSNonRecurisive1() throws IOException {
		GraphSearcher searcher = new GraphSearcher();
		Graph<String, DefaultEdge> graph = calculator.createGraphFromFile("33300.txt");
		assertTrue(searcher.dfs_nonrecur(graph, "1", "4"));
		assertTrue(searcher.dfs_nonrecur(graph, "1", "7"));
		assertFalse(searcher.dfs_nonrecur(graph, "1", "6"));
		assertTrue(searcher.dfs_nonrecur(graph, "3", "9"));
		assertFalse(searcher.dfs_nonrecur(graph, "3", "2"));
	}
}
