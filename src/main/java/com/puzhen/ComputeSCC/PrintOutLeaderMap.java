package com.puzhen.ComputeSCC;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;


public class PrintOutLeaderMap {

	public static void main(String[] args) throws IOException {
		String filename = "C:\\Users\\puqian\\Desktop\\f_time.txt";

		String newFilename = filename + "_second";
		
		SCCCalculator calculator = new SCCCalculator();
		Graph<String, DefaultEdge> graph = calculator.createGraphFromFile(newFilename);
		Map<String, String> map = calculator.computeLeaderMap(graph);
		System.out.println("Done computing leader map, print it now..");
		
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
		
		for (int in : array)
			System.out.println(in);
	}
}
