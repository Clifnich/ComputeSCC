package com.puzhen.ComputeSCC.testutility;

public class ArrayComparitor {

	/**
	 * This method compares two arrays.
	 * If they are equal the return value will be true,
	 * otherwise false.
	 * @param a
	 * @param b
	 * @return whether a and b are equal or not
	 */
	public boolean arrayEquals(int[] a, int[] b) {
		if (a.length != b.length)
			return false;
		for (int i : a) {
			if (!elementInArray(i, b))
				return false;
		}
		return true;
	}
	
	/**
	 * This method return whether an element is
	 * included in the array.
	 * @param e
	 * @param array
	 * @return
	 */
	private boolean elementInArray(int e, int[] array) {
		for (int i : array)
			if (e == i)
				return true;
		return false;
	}
	
}
