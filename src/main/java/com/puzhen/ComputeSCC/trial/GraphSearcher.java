package com.puzhen.ComputeSCC.trial;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import java.util.*;

public class GraphSearcher {

	/**
	 * This method is an entry to the core dfs method
	 * @param graph
	 * @param start_vex
	 * @param target_vex
	 * @return
	 */
	public boolean dfs_recur(Graph<String, DefaultEdge> graph,
			String start_vex, String target_vex) {
		return dfs(graph, start_vex, target_vex, RECURSIVE);
	}
	
	/**
	 * This method is an entry to the core non-recursive dfs method
	 * @param graph
	 * @param start_vex
	 * @param target_vex
	 * @return
	 */
	public boolean dfs_nonrecur(Graph<String, DefaultEdge> graph,
			String start_vex, String target_vex) {
		return dfs(graph, start_vex, target_vex, NON_RECURSIVE);
	}

	/**
	 * Based on the option, this method starts the dfs algorithm.
	 * @param graph
	 * @param start_vex
	 * @param target_vex
	 * @param option
	 * @return
	 */
	private boolean dfs(Graph<String, DefaultEdge> graph,
			String start_vex, String target_vex, String option) {
		cleanMap(graph);
		if (option.equals(RECURSIVE))
			return dfs_core_recur(graph, start_vex, target_vex);
		else
			return dfs_core_nonrecur(graph, start_vex, target_vex);
	}
	
	/**
	 * This method is the core part of the DFS algorithm.
	 * It doesn't recursively call itself.
	 * @param graph
	 * @param start_vex
	 * @param target_vex
	 * @return
	 */
	private boolean dfs_core_nonrecur(Graph<String, DefaultEdge> graph,
			String start_vex, String target_vex) {
		// #1 mark start_vex as explored
		exploreMap.replace(start_vex, new Boolean(true));
		
		// #2 initialize the stack
		Stack<String> stack = new Stack<String>();
		stack.push(start_vex);
		while (!stack.isEmpty()) {
			String v = stack.pop();
			List<String> neighbors = 
					Graphs.successorListOf((DirectedGraph<String, DefaultEdge>)graph, v);
			// #3 traverse the adjacent list
			for (String neighbor : neighbors) {
				if (neighbor.equals(target_vex)) return true;
				if (exploreMap.get(neighbor)) continue;
				exploreMap.replace(neighbor, new Boolean(true));
				stack.push(neighbor);
			}
		}
		return false;
	}
	
	/**
	 * This method is the core part of the DFS algorithm.
	 * It recursively calls itself.
	 * @param graph
	 * @param start_vex
	 * @param target_vex
	 * @return
	 */
	private boolean dfs_core_recur(Graph<String, DefaultEdge> graph,
			String start_vex, String target_vex) {
		// #1 mark start_vex as explored
		exploreMap.replace(start_vex, new Boolean(true));
		
		// #2 traverse its adjacent list
		List<String> neighbors = 
				Graphs.successorListOf((DirectedGraph<String, DefaultEdge>)graph, start_vex);
		for (String neighbor : neighbors) {
			if (neighbor.equals(target_vex)) return true;
			// #3 if neighbor is explored, continue
			if (exploreMap.get(neighbor)) continue;
			if (dfs_core_recur(graph, neighbor, target_vex))
				return true;
		}
		return false;
	}
	
	/**
	 * This method uses BFS to find the element needed.
	 * @param graph
	 * @param start_vex
	 * @param target_vex
	 * @return
	 */
	public boolean bfs(Graph<String, DefaultEdge> graph,
			String start_vex, String target_vex) {
		cleanMap(graph);
		// #1 mark start_vex as explored
		exploreMap.replace(start_vex, new Boolean(true));
		
		// #2 initialize the queue
		Queue<String> queue = new LinkedList<String>();
		queue.add(start_vex);
		while (queue.size() != 0) {
			// #3 remove the first node of queue
			String v = queue.poll();
			// #4 traverse its neighbors
			List<String> neighbors = 
					Graphs.successorListOf((DirectedGraph<String, DefaultEdge>)graph, v);
			for (String neighbor : neighbors) {
				if (neighbor.equals(target_vex))
					return true;
				// #5 if neighbor is explored, continue
				if (exploreMap.get(neighbor)) continue;
				exploreMap.replace(neighbor, new Boolean(true));
				queue.add(neighbor);
			}
		}
		return false;
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
	
	private static final String RECURSIVE = "RECURSIVE";
	private static final String NON_RECURSIVE = "NON_RECURSIVE";
}
