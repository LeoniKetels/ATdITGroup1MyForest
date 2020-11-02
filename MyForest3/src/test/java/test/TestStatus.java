package test;

import org.junit.Assert;
import org.junit.Test;
import main.Status;

 

/**
 * @author Gruppe 1
 * Method for Unit Tests of class status
 */
public class TestStatus {
    
    /**
     *Testing Status Id's
     */
    @Test
    public void testGetAreaId() {
        Status status = new Status(1, "Offen");
        Assert.assertEquals(1, status.getId());
    }
    
    /**
     *Testing of changes in status descriptions
     */
    @Test 
    public void testSetDescription() {
        Status status = new Status (1, "Offen");
        status.setDescription("testStatus");
        Assert.assertEquals("testStatus", status.getDescription());    
    }

 

}