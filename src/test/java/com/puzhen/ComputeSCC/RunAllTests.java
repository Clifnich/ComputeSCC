package com.puzhen.ComputeSCC;

import junit.framework.Test;
import junit.framework.TestSuite;

public class RunAllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(RunAllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(TestArrayComparitor.class);
		suite.addTestSuite(TestComputeSCC.class);
		suite.addTestSuite(TestBasicSearch.class);
		//$JUnit-END$
		return suite;
	}

}
