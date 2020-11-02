package test;

import org.junit.Assert;
import org.junit.Test;

import main.Problem;

/**
 * @author Gruppe 1
 * Method for Unit Tests of class Problem
 */
public class TestProblem {
	
	/**
	 *Testing Problem Id's
	 */
	@Test
	public void testGetProblemId() {
		Problem problem = new Problem(1, "testProblem", 456, 1, "Eiche");
		Assert.assertEquals(1, problem.getId());
	}
	
	/**
	 *Testing correctness of tree name
	 */
	@Test 
	public void testGetTreeName() {
		Problem problem = new Problem(1, "testProblem", 456, 1, "Eiche");
		Assert.assertEquals("Eiche", problem.getTree());
		//System.out.println(problem.getTree());
	}

}
