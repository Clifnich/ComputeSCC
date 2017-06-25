package com.puzhen.ComputeSCC;

import com.puzhen.ComputeSCC.testutility.ArrayComparitor;

import junit.framework.TestCase;

public class TestArrayComparitor extends TestCase {

	private ArrayComparitor comparitor;
	
	public TestArrayComparitor(String name) {
		super(name);
		comparitor = new ArrayComparitor();
	}
	
	public void testCompare0() {
		int[] a = {1, 2, 3};
		int[] b = {1, 2, 3};
		assertTrue(comparitor.arrayEquals(a, b));
	}
	
	/**
	 * Shuffle the sequence of numbers
	 */
	public void testCompare1() {
		int[] a = {1, 3, 2};
		int[] b = {1, 2, 3};
		assertTrue(comparitor.arrayEquals(a, b));
	}
}
