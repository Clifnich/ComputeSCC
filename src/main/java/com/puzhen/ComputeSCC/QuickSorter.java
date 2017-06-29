package com.puzhen.ComputeSCC;
import java.io.*;
import java.util.*;

public class QuickSorter {

	public static final String FIRST = "FIRST";
	public static final String MEDIAN = "MEDIAN";
	public static final String LAST = "LAST";
	
	/** By default pivot_type is the first element */
	private String pivot_type;
	private int comparisons;
	
	public QuickSorter() {
		comparisons = 0;
		pivot_type = FIRST;
	}
	
	public int quick_sort_from_file(String filename, String type) {
		comparisons = 0;
		pivot_type = type;
		int[] array = read_array_from_file(filename);
		//choose_pivot(array);
		return quick_sort(array);
	}
	
	/**
	 * This method reads an array from file
	 * and call the quick_sort method
	 * @param filename
	 * @return # of comparisons
	 */
	public int quick_sort_from_file(String filename) {
		quick_sort(read_array_from_file(filename));
		return comparisons;
	}
	
	/**
	 * This method sorts the input array with quick_sort algorithm
	 * and adds up the number of comparisons on the go
	 * @param array
	 */
	public int quick_sort(int[] array) {
		int n = array.length;
		comparisons += n - 1;
		if (n == 1) return 0;
		
		// choose pivot
		int pivot = choose_pivot(array);
		
		int pivot_index = partition(array, 0, n - 1);
		int firstHalfLength = pivot_index;
		int secondHalfLength = n - pivot_index - 1;
		int[] firstHalf = new int[firstHalfLength];
		for (int i = 0; i < firstHalfLength; i++)
			firstHalf[i] = array[i];
		int[] secondHalf = new int[secondHalfLength];
		for (int i = 0; i < secondHalfLength; i++)
			secondHalf[i] = array[i + pivot_index + 1];
		
		if (firstHalfLength != 0)
			quick_sort(firstHalf);
		if (secondHalfLength != 0)
			quick_sort(secondHalf);
		
		// combine
		for (int i = 0; i < firstHalfLength; i++)
			array[i] = firstHalf[i];
		for (int i = 0; i < secondHalfLength; i++)
			array[i + pivot_index + 1] = secondHalf[i];
		array[pivot_index] = pivot;
		
		return comparisons;
	}
	
	/**
	 * This method can tell whether two array are equal to each other
	 * @param a
	 * @param b
	 */
	public boolean equals(int[] a, int[] b) {
		if (a.length != b.length)
			return false;
		for (int i = 0; i < a.length; i++) {
			if (a[i] != b[i])
				return false;
		}
		return true;
	}
	
	private int[] read_array_from_file(String filename) {
		File file = new File("/Users/Ishmael/java/QuickSort/" + filename);
		try {
			BufferedReader rd = new BufferedReader(new FileReader(file));
			String line = "";
			List<Integer> list = new ArrayList<Integer>();
			while ((line = rd.readLine()) != null) {
				list.add(Integer.valueOf(line));
			}
			rd.close();
			int[] array = new int[list.size()];
			for (int i = 0; i < list.size(); i++)
				array[i] = list.get(i);
			return array;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * This method partition partitions the array
	 * with the first element as the pivot
	 * @return the index of pivot after the partitioning
	 */
	private int partition(int[] array, int l, int r) {
		// choose pivot, before partition happens
		// the right pivot has been exchanged to the first place
		int p = array[0];
		int i = l + 1;
		for (int j = l + 1; j <= r; j++) {
			if (array[j] < p) {
				swap(array, i, j);
				i++;
			}
				
		}
		int temp = array[l];
		array[l] = array[i-1];
		array[i-1] = temp;
		return i-1;
	}
	
	/**
	 * This method swaps the i-th and j-th element 
	 * in an array
	 * @param array
	 * @param i
	 * @param j
	 */
	private void swap(int[] array, int i, int j) {
		int temp = array[j];
		array[j] = array[i];
		array[i] = temp;
	}
	
	/**
	 * This method finds the pivot, swap the position
	 * and return the pivot value
	 * @param array
	 */
	private int choose_pivot(int[] array) {
		int n = array.length;
		switch (pivot_type) {
		case FIRST:
			break;
		case MEDIAN:
			int median_index = (n % 2 == 0) ? n/2-1 : n/2;
			int first = array[0];
			int median = array[median_index];
			int last = array[n-1];
			if (median > first) {
				if (last > median)
					swap(array, median_index, 0);
				else if (last > first)
					swap(array, 0, n-1);
			} else if (median > last) {
				swap(array, 0, median_index);
			} else if (last < first)
				swap(array, 0, n-1);
			break;
		case LAST:
			swap(array, n-1, 0);
			break;
		default:
			break;
		}
		return array[0];
	}

//	/**
//	 * This method bring the index-th element to 
//	 * the first place in that array which means 
//	 * that all the other elements will get shifted 
//	 * to the right by one position
//	 * @param array
//	 * @param index
//	 */
//	private void element_to_front(int[] array, int index) {
//		int temp = array[index];
//		for (int i = index; i > 0; i--)
//			array[i] = array[i-1];
//		array[0] = temp;
//	}
}
