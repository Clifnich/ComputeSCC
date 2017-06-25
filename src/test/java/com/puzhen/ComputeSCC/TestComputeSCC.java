package com.puzhen.ComputeSCC;

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
	
	public void testCreateGraph() {
		// here we assume that the file is at the root directory of this project
		String filename = "";
		SCCCalculator calculator = new SCCCalculator();
		Graph<String, DefaultEdge> graph = calculator.createGraphFromFile(filename);
		assertEquals(9, graph.vertexSet().size());
		assertEquals(1, graph.getAllEdges("1", "4").size());
		assertTrue(graph.containsVertex("5"));
	}
}
