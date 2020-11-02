package main;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Gruppe 1
 * establishes connection to database
 * provides methods to get data
 *
 */

// Singleton 
public class DBConnection {
	public static Connection conn;

	public DBConnection()  {
		try {
		if(DBConnection.conn == null) {
		conn = DriverManager.getConnection(
				"jdbc:mysql://localhost/myforestDB?"
                        + "user=User&password=myforest");
		}
		}catch(SQLException se) {	
			se.printStackTrace();
			new ErrorFrame("Es gab einen Fehler bei der Datenbankverbindung.",
					"Pruefen Sie, ob Sie alle Schritte zur erfolgreichen Datenbankverbindung durchgefuehrt haben.",
					se.getMessage());
		}
//		conn = DriverManager.getConnection(
//				"jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7355900?user=sql7355900&password=kHaqmas865");
//		}
	}

	/**
	 * Get a list of all entries in the problem table
	 * 
	 * @return ArrayList of Problem
	 */
	public List<Problem> getAllProblems() {
		List<Problem> list = new ArrayList<Problem>();

		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT * FROM problem");

			while (rs.next()) {
				Problem problem = convertProblemRow(rs);
				list.add(problem);
			}
			return list;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			getSQLStatementError(e.getMessage());
			return null;
		}
	}

	/**
	 * get the entry in the problem table with the given id
	 * 
	 * @param id
	 * @return Problem
	 */
	public Problem getProblemByID(int id) {
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT * FROM problem WHERE id =" + id + "");
			Problem problem = null;
			if (rs.next()) {
				problem = convertProblemRow(rs);
			}
			return problem;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	/**
	 * get all entries in the area table
	 * 
	 * @return ArrayList of Areas
	 */
	public List<Area> getAllAreas() {
		List<Area> list = new ArrayList<Area>();

		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT * FROM area");

			while (rs.next()) {
				Area area = convertAreaRow(rs);
				list.add(area);
			}
			return list;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			getSQLStatementError(e.getMessage());
			return null;
		}
	}

	/**
	 * get the Area from the area table with the given id
	 * 
	 * @param id
	 * @return Area
	 */
	public Area getAreaById(int id) {
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT * FROM area WHERE id =" + id + "");
			Area area = null;
			if (rs.next()) {
				area = convertAreaRow(rs);
			}
			return area;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			getSQLStatementError(e.getMessage());
			return null;
		}
	}

	/**
	 * get all entries in the status table
	 * 
	 * @return ArrayList of Status
	 */
	public List<Status> getAllStatuses() {
		List<Status> list = new ArrayList<Status>();

		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT * FROM status");

			while (rs.next()) {
				Status status = convertStatusRow(rs);
				list.add(status);
			}
			return list;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			getSQLStatementError(e.getMessage());
			return null;
		}
	}
	
	/**
	 * get all entries in the Problem table that have the 'in Progress' Status
	 * 
	 * @return ArrayList of Problems
	 */
	public List<Problem> getProblemsInProgress(){
		List<Problem> list = new ArrayList<Problem>();

		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT * FROM problem WHERE status_id= 2");

			while (rs.next()) {
				Problem problem = convertProblemRow(rs);
				list.add(problem);
			}
			return list;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			getSQLStatementError(e.getMessage());
			return null;
		}
	}

	/**
	 * 
	 * @param id of the status 
	 * @return the status with said id
	 */
	public Status getStatusById(int id) {
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT * FROM status WHERE id =" + id + "");
			Status status = null;
			if (rs.next()) {
				status = convertStatusRow(rs);
			}
			return status;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			getSQLStatementError(e.getMessage());

			return null;
		}
	}

	private Problem convertProblemRow(ResultSet rs) throws SQLException {
		//bug: id is always 0 although the id in the database is set correctly
		int id = rs.getInt("id");
		String description = rs.getString("description");
		int area_id = rs.getInt("area_id");
		int status_id = rs.getInt("status_id");
		String tree = rs.getString("tree");

		return new Problem(id, description, area_id, status_id, tree);
	}

	private Area convertAreaRow(ResultSet rs) throws SQLException {

		int id = rs.getInt("id");
		String description = rs.getString("description");
		return new Area(id, description);
	}

	private Status convertStatusRow(ResultSet rs) throws SQLException {
		int id = rs.getInt("id");
		String description = rs.getString("description");
		return new Status(id, description);
	}
	
	
	/**
	 * Inserts a new row into the Database Table Problem 
	 * @param problem Object of type Problem that should be inserted into Database Table
	 */
	public void insertProblem(Problem problem) {
		String stmt = "INSERT INTO problem(id, description,area_id, status_id, tree) VALUES(?,?,?,?,?)";

		try (PreparedStatement pstmt = conn.prepareStatement(stmt)) {
			pstmt.setInt(1, problem.getId());
			pstmt.setString(2, problem.getDescription());
			pstmt.setInt(3, problem.getArea_id());
			pstmt.setInt(4, problem.getStatus_id());
			pstmt.setString(5, problem.getTree());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			getSQLStatementError(e.getMessage());
		}
	}
	
	/**
	 * insert an area into the database table Area
	 * @param area
	 */
	public void insertArea(Area area){
		String stmt = "INSERT INTO area(id, description) VALUES(?,?)";
		try (PreparedStatement pstmt = conn.prepareStatement(stmt)) {
			pstmt.setInt(1, area.getId());
			pstmt.setString(2, area.getDescription());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			getSQLStatementError(e.getMessage());
		}
	}
	
	/**
	 * insert a status into the database table Status
	 * @param status
	 */
	public void insertStatus(Status status){
		String stmt = "INSERT INTO status(id, description) VALUES(?,?)";
		try (PreparedStatement pstmt = conn.prepareStatement(stmt)) {
			pstmt.setInt(1, status.getId());
			pstmt.setString(2, status.getDescription());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			getSQLStatementError(e.getMessage());

		}
	}

	public void changeStatus(int problemId, int statusId) {
		String stmt = "UPDATE problem SET status_id = "+ statusId+ " WHERE id = "+ problemId ;
		try (PreparedStatement pstmt = conn.prepareStatement(stmt)) {
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			getSQLStatementError(e.getMessage());
		}
	}
	
	public void deleteProblem(int problemId) {
		String stmt = "DELETE FROM problem WHERE id = "+ problemId;
		try (PreparedStatement pstmt = conn.prepareStatement(stmt)) {
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			getSQLStatementError(e.getMessage());
		}
	}
	
	/**
	 * close the Database Connection 
	 */
	public static void close() {
		try {
			if(conn != null)
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private ErrorFrame getSQLStatementError(String message) {
		return new ErrorFrame("Es gab einen Fehler bei der SQL-Abfrage.","Pr�fen Sie, ob Ihre Datenbank l�uft, sonst verst�ndigen Sie bitte Ihre IT-Abteilung", message);
	}
	
//	public static void main(String[] args) {
//		DBConnection db;
//		try {
//			db = new DBConnection();
//			db.changeStatus(1, 3);
//			System.out.println(db.getProblemByID(1));
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}