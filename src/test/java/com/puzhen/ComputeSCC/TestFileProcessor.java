package com.puzhen.ComputeSCC;

import com.puzhen.ComputeSCC.fileprocessor.FileProcessor;

import junit.framework.TestCase;

import java.io.IOException;
import java.util.*;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

public class TestFileProcessor extends TestCase {

	public TestFileProcessor(String name) {
		super(name);
	}
	
	/**
	 * Test changing Vertex names
	 * @throws IOException 
	 */
	public void testChaningVertexName() throws IOException {
		Map<String, String> f = new HashMap<String, String>();
		f.put("1", "7");
		f.put("2", "3");
		f.put("3", "1");
		f.put("4", "8");
		f.put("5", "2");
		f.put("6", "5");
		f.put("7", "9");
		f.put("8", "4");
		f.put("9", "6");
		(new FileProcessor()).changeVertexNames("tiny.txt", f, "tiny_second.txt");
		SCCCalculator calculator = new SCCCalculator();
		Graph<String, DefaultEdge> graph = calculator.createGraphFromFile("tiny_second.txt");
		assertEquals(9, graph.vertexSet().size());
		assertEquals(1, graph.getAllEdges("2", "4").size());
		assertEquals(0, graph.getAllEdges("8", "5").size());
	}
}
