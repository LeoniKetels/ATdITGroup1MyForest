package test;

import org.junit.Assert;
import org.junit.Test;

import main.Area;

/**
 * @author Gruppe 1
 * Method for Unit Tests of class Area
 */
public class TestArea {
	
	/**
	 *Testing Area Id's
	 */
	@Test
	public void testGetAreaId() {
		Area area = new Area(456, "test");
		Assert.assertEquals(456, area.getId());
	}
	
	/**
	 *Testing of changes in area descriptions
	 */
	@Test 
	public void testSetDescription() {
		Area area = new Area (456, "test");
		area.setDescription("test2");
		Assert.assertEquals("test2", area.getDescription());	
	}

}
