package test;

import org.junit.Assert;
import org.junit.Test;

import main.DBConnection;
import main.Problem;
import main.Status;
import main.Area;
 


/**
 * @author Gruppe 1
 * Method for Unit Tests of class DBConnection
 */
public class TestDBConnection {
    /**
     *Testing get Problem by Id from Database
     * @throws Exception 
     */
	DBConnection connection;

    
    //insertProblem Methode testen mit contains Methode in der Liste funktioniert nicht
    /**
     *Testing get Problem by Id from Database
     * @throws Exception 
     */
    @Test
    public void testInsertProblemAndGetProblem() throws Exception {
    	connection = new DBConnection();
        Problem problemTest = new Problem(100, "Testproblem",1,1, "Eiche");
        connection.insertProblem(problemTest);
        Problem problem = connection.getProblemByID(100);
        Assert.assertEquals(problemTest.toString(), problem.toString());      
    }
    
    @Test
    public void testGetProblem() throws Exception {
        connection = new DBConnection();
        Problem problemTest = new Problem(1, "Der Baum ist kaputt",1,1, "Buche");
        Assert.assertEquals(problemTest.toString(),connection.getProblemByID(1).toString());
    }
    
    @Test
    public void testDeleteProblem() throws Exception {
    	connection = new DBConnection();
    	connection.deleteProblem(100);
    	Assert.assertNull(connection.getProblemByID(100));
    }
    
        /**
         *Testing get Problem by Id from Database
         * @throws Exception 
         */
        @Test
        public void testGetAllAreas() throws Exception {
        	connection = new DBConnection();
            Area testArea = new Area(1, "49.349187, 7.923600");
            Area area = connection.getAreaById(1);
            Assert.assertEquals(testArea.toString(), area.toString());
        }
    
    
    /**
     *Testing get Area by Id from Database
     * @throws Exception 
     */
    @Test
    public void testGetArea() throws Exception {
    	connection = new DBConnection();
        Area testArea = new Area(1, "49.349187, 7.923600");
        Assert.assertEquals(testArea.toString(),connection.getAreaById(1).toString());
    }
    
    /**
     *Testing get Status by Id from Database
     * @throws Exception 
     */
    @Test
    public void testGetStatus() throws Exception {
    	connection = new DBConnection();
        Status testStatus = new Status(1, "Offen");
        Assert.assertEquals(testStatus.toString(),connection.getStatusById(1).toString());
    } 
    
    /**
     *Testing change Status 
     * @throws Exception 
     */
    @Test
    public void testChangeStatus() throws Exception {
    	connection = new DBConnection();
        connection.changeStatus(1, 2);
        Problem problemTest = new Problem(1, "Der Baum ist kaputt",1,2, "Buche");
        Assert.assertEquals(problemTest.toString(),connection.getProblemByID(1).toString());
        connection.changeStatus(1, 1);
    }    
}