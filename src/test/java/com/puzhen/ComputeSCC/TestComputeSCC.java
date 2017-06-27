package com.puzhen.ComputeSCC;

import java.io.IOException;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import com.puzhen.ComputeSCC.testutility.ArrayComparitor;

import junit.framework.TestCase;

public class TestComputeSCC extends TestCase {

	private ArrayComparitor comparitor;
	
	public TestComputeSCC(String name) {
		super(name);
		comparitor = new ArrayComparitor();
	}
	
	/**
	 * This case test that when given an empty graph
	 * represented by null here, the calculator will
	 * produce a result of {0,0,0,0,0} as specified
	 * in the assignment page.
	 * @throws IOException 
	 */
	public void testCompute0() throws IOException {
		SCCCalculator calculator = new SCCCalculator();
		int[] result = {0,0,0,0,0};
		assertTrue(comparitor.arrayEquals(result, 
				calculator.computeSCC("")));
	}
	
	/**
	 * Compute the graph from 33200.txt
	 * Expect the result to be 33200
	 * @throws IOException
	 */
	public void testCompute1() throws IOException {
		SCCCalculator calculator = new SCCCalculator();
		int[] result = {3,3,2,0,0};
		assertTrue(comparitor.arrayEquals(result, 
				calculator.computeSCC("33200.txt")));
	}
	
	/**
	 * Test the create graph method.
	 * Case 1.
	 * @throws IOException
	 */
	public void testCreateGraph() throws IOException {
		// here we assume that the file is at the root directory of this project
		String filename = "33300.txt";
		SCCCalculator calculator = new SCCCalculator();
		Graph<String, DefaultEdge> graph = calculator.createGraphFromFile(filename);
		assertEquals(9, graph.vertexSet().size());
		assertEquals(1, graph.getAllEdges("1", "4").size());
		assertTrue(graph.containsVertex("5"));
	}
	
	/**
	 * Test the create graph method.
	 * Case 2.
	 */
	public void testCreateGraph1() throws IOException {
		String filename = "33200.txt";
		SCCCalculator calculator = new SCCCalculator();
		Graph<String, DefaultEdge> graph = calculator.createGraphFromFile(filename);
		assertEquals(8, graph.vertexSet().size());
		assertEquals(1, graph.getAllEdges("1", "2").size());
		assertEquals(0, graph.getAllEdges("1", "7").size());
		assertTrue(graph.containsVertex("3"));
	}
	
	/**
	 * Test create inverse graph.
	 * Case 1.
	 */
	public void testCreateInvGraph0() throws IOException {
		String filename = "33300.txt";
		SCCCalculator calculator = new SCCCalculator();
		Graph<String, DefaultEdge> graph = calculator.createInverseGraphFromFile(filename);
		assertEquals(9, graph.vertexSet().size());
		assertEquals(1, graph.getAllEdges("4", "1").size());
		assertEquals(0, graph.getAllEdges("3", "6").size());
	}
	
	/**
	 * Test creating inverse graph.
	 * Case 2.
	 */
	public void testCreateInvGraph1() throws IOException {
		String filename = "33200.txt";
		SCCCalculator calculator = new SCCCalculator();
		Graph<String, DefaultEdge> graph = calculator.createInverseGraphFromFile(filename);
		assertEquals(8, graph.vertexSet().size());
		assertEquals(1, graph.getAllEdges("4", "2").size());
		assertEquals(0, graph.getAllEdges("8", "5").size());
	}
}
