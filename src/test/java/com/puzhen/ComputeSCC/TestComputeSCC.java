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
	 */
	public void testCompute0() {
		SCCCalculator calculator = new SCCCalculator();
		int[] result = {0,0,0,0,0};
		assertTrue(comparitor.arrayEquals(result, calculator.computeSCC(null)));
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
}
