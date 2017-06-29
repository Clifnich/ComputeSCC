package com.puzhen.ComputeSCC;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import com.puzhen.ComputeSCC.fileprocessor.FileProcessor;

import java.io.*;
import java.util.*;

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
	 * @throws IOException 
	 */
	public int[] computeSCC(String filename) throws IOException {
		int[] defaultReturnValue = {0,0,0,0,0};	
		if (!(new File(filename)).exists())
			return defaultReturnValue;
		// #1. Let G_rev = G with all edges reversed
		Graph<String, DefaultEdge> inversed_graph = createInverseGraphFromFile(filename);
		// #2. Run DFS-Loop on G_rev
		System.out.println("Computing finishing time..");
		Map<String, String> f = computeFinishingTime(inversed_graph);
		System.out.println("Done computing finishing time, computing leader map now...");
		// #3. Run DFS-Loop on G
		FileProcessor processor = new FileProcessor();
		String newFilename = filename + "_second";
		processor.changeVertexNames(filename, f, newFilename);
		Graph<String, DefaultEdge> graph = createGraphFromFile(newFilename);
		Map<String, String> leaderMap = computeLeaderMap(graph);
		System.out.println("Done computing leader map, sorting it now..");
		return getSCCFromLeaderMap(leaderMap);
	}
	
	/**
	 * Compute the finishing time mapping.
	 */
	public Map<String, String> computeFinishingTime(Graph<String, DefaultEdge> graph) {
		Map<String, String> f = new HashMap<String, String>();
		// stack for finishing time
		Stack<String> s_f = new Stack<String>();
		int t = 0;
		cleanMap(graph);
		for (int i = graph.vertexSet().size(); i > 0; i--) {
			// since vertex names are numbers, I can do this trick
			String vex = String.valueOf(i);
			if (!exploreMap.get(vex)) {
				// DFS(G, vex)
				Stack<String> stack = new Stack<String>();
				s_f = new Stack<String>();
				stack.push(vex);
				while (!stack.isEmpty()) {
					vex = stack.pop();
					s_f.push(vex);
					exploreMap.replace(vex, new Boolean(true));
					List<String> neighbors = 
							Graphs.successorListOf(
									(DirectedGraph<String, DefaultEdge>)graph, vex);
					for (String neighbor : neighbors) {
						if (!exploreMap.get(neighbor))
							stack.push(neighbor);
					}
				}
				while (!s_f.isEmpty()) {
					vex = s_f.pop();
					t++;
					f.put(vex, String.valueOf(t));
				}
			}
		}
		return f;
	}
	
	/**
	 * Use non-recursive dfs to compute the leader map
	 */
	public Map<String, String> computeLeaderMap(Graph<String, DefaultEdge> graph) {
		cleanMap(graph);
		Map<String, String> map = new HashMap<String, String>();
		String s = "";
		for (int i = graph.vertexSet().size(); i > 0; i--) {
			String vex = String.valueOf(i);
			if (!exploreMap.get(vex)) {
				s = vex;
				// DFS(G, vex)
				Stack<String> stack = new Stack<String>();
				stack.push(vex);
				while (!stack.isEmpty()) {
					String v = stack.pop();
					exploreMap.replace(v, new Boolean(true));
					// set leader(v) = node s
					map.put(v, s);
					List<String> neighbors = 
							Graphs.successorListOf(
									(DirectedGraph<String, DefaultEdge>)graph, v);
					for (String neighbor : neighbors) {
						if (!exploreMap.get(neighbor))
							stack.push(neighbor);
					}
				}
			}
		}
		return map;
	}
	
	/**
	 * Provide a leader map, compute the SCCs from it
	 * @param map
	 * @return
	 */
	private int[] getSCCFromLeaderMap(Map<String, String> map) {
		// count each SCC's size
		Map<String, Integer> countMap = new HashMap<String, Integer>();
		for (String key : map.keySet()) {
			String leader = map.get(key);
			if (countMap.containsKey(leader)) {
				countMap.replace(leader, countMap.get(leader).intValue() + 1);
			} else {
				countMap.put(leader, 1);
			}
		}
		// sort the map
		//countMap = MapUtility.sortByValue(countMap);
		// inverse the key value
		Map<Integer, String> inverse = new HashMap<Integer, String>();
		for (String key : countMap.keySet()) {
			inverse.put(countMap.get(key), key);
		}
		int[] array = new int[inverse.keySet().size()];
		int i = 0;
		for (Integer key : inverse.keySet()) {
			array[i] = key;
			i++;
		}
		QuickSorter sorter = new QuickSorter();
		int[] result = new int[5];
		sorter.quick_sort(array);
		for (i = 0; i < 5; i++) {
			try {
				result[i] = array[i];
			} catch (ArrayIndexOutOfBoundsException e) {
				result[i] = 0;
			}
		}
		
//		for (String key : countMap.keySet()) {
//			if (i == 5) break;
//			result[i] = countMap.get(key);
//			i++;
//		}
		return result;
	}
	
//	/** Some global variables for computing SCCs */
//	private int t = 0;
//	private String s;
	
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
	
	/**
	 * At the very beginning of each search,
	 * mark every vertices as unexplored.
	 * @param graph
	 */
	private void cleanMap(Graph<String, DefaultEdge> graph) {
		exploreMap = new HashMap<String, Boolean>();
		for (String vex : graph.vertexSet()) {
			exploreMap.put(vex, new Boolean(false));
		}
	}
	
	/** This map keeps track of if each particular vertex has been explored or not */
	private Map<String, Boolean> exploreMap;
	
	private static final String NORMAL = "NORMAL";
	private static final String INVERSE = "INVERSE";
	
	public static void main(String[] args) throws IOException {
		SCCCalculator calculator = new SCCCalculator();
		int[] result = calculator.computeSCC("/Users/Ishmael/Desktop/scc.txt");
		for (int i : result)
			System.out.println(i);
	}
}
