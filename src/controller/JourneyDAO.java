package controller;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.Journey;




public class JourneyDAO {

	private static JourneyDAO instance;
	private DataSource dataSource;
	private String jndiName = "java:comp/env/jdbc/ubertrial";
	
	public static JourneyDAO getInstance() throws Exception {
		if (instance == null) {
			instance = new JourneyDAO();
		}
		
		return instance;
	}
	
	private JourneyDAO() throws Exception {		
		dataSource = getDataSource();
	}

	private DataSource getDataSource() throws NamingException {
		Context context = new InitialContext();
		
		DataSource theDataSource = (DataSource) context.lookup(jndiName);
		
		return theDataSource;
	}
		
	public List<Journey> getJourneys() throws Exception {
		//List to hold returned DB data
		List<Journey> journeys = new ArrayList<>();
		//connection,prepared statement,result set
		Connection myConn = null;
		PreparedStatement myPS = null;
		ResultSet myPSRS = null;
		
		
		try {
			//establish connection to DB
			myConn = getConnection();
			//select all Journeyss
			String sql = "select * from journey";
			//add auto-increment id to result set
			myPS = myConn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			//get result set
			myPSRS = myPS.executeQuery();
			
			// iterate over result set
			while (myPSRS.next()) {
			
				int journeyID = myPSRS.getInt("journey_id");
				String carRegistration = myPSRS.getString("car_registration");
				int customerID = myPSRS.getInt("customer_id");
				double elapsedDistance = myPSRS.getDouble("elapsed_distance");
				double totalDistance = myPSRS.getDouble("total_distance");
				double price = myPSRS.getDouble("price");
				
				// create new journey object
				Journey tempJourney = new Journey(journeyID,carRegistration,customerID,elapsedDistance,totalDistance,price);
				// add it to the list of customers
				journeys.add(tempJourney);
			}
			//return List generated from DB
			return journeys;		
		}
		finally {
			close (myConn, myPS, myPSRS);
		}
	}
	
	
	
	
	
	
	public void addJourney(Journey j) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();
			
			String sql = "insert into journey (car_registration, customer_id, elapsed_distance, total_distance, price) values (?, ?, ?, ?, ?)";

			myStmt = myConn.prepareStatement(sql);

			myStmt.setString(1, j.getCarRegistration());
			myStmt.setInt(2, j.getCustomerID());
			myStmt.setDouble(3, j.getElapsedDistance());
			myStmt.setDouble(4, j.getTotalDistance());
			myStmt.setDouble(5, j.getPrice());
			
			myStmt.execute();			
		}
		finally {
			close (myConn, myStmt);
		}
		
	}
	
	/**
	 * Method to find Journey via JourneyID
	 * @param journeyID of Journey
	 * @return Journey
	 * @throws Exception
	 */
	public Journey getJourney(int journeyID) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();

			String sql = "select * from journey where journey_id=?";

			myStmt = myConn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			
			// set params
			myStmt.setInt(1, journeyID);
			
			myRs = myStmt.executeQuery();

			Journey journey = null;
			
			// retrieve data from result set row
			if (myRs.next()) {
				
				int id = myRs.getInt("journey_id");
				String carRegistration = myRs.getString("car_registration");
				int customerID = myRs.getInt("customer_id");
				double elapsedDistance = myRs.getDouble("elapsed_distance");
				double totalDistance = myRs.getDouble("total_distance");
				double price = myRs.getDouble("price");
			
				
				journey = new Journey(id,carRegistration,customerID,elapsedDistance,totalDistance,price);
				
			}
			else {
				throw new Exception("Could not find journey id: " + journeyID);
			}

			return journey;
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
	
	
	
	public void updateJourney(Journey journey) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();
			String sql = "update journey "
						+ " set car_registration=?, customer_id=?, elapsed_distance=?, total_distance=?, price=?"
						+ " where journey_id=?";

			myStmt = myConn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

			// set params
			myStmt.setString(1, journey.getCarRegistration());
			myStmt.setInt(2, journey.getCustomerID());
			myStmt.setDouble(3, journey.getElapsedDistance());
			myStmt.setDouble(4, journey.getTotalDistance());
			myStmt.setDouble(5, journey.getPrice());
			myStmt.setInt(6, journey.getJourneyID());
			
			myStmt.execute();
		}
		finally {
			close (myConn, myStmt);
		}
		
	}
	
	/**
	 * Method to delete Journey from DB via journey id
	 * @param journeyID id of journey to delete
	 * @throws Exception
	 */
	public void deleteJourney(int journeyID) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "delete from journey where journey_id=?";

			myStmt = myConn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

			// set params
			myStmt.setInt(1, journeyID);
			
			myStmt.execute();
		}
		finally {
			close (myConn, myStmt);
		}		
	}
	
	
	/**
	 * Helper classes for Database interactions
	 */
	private Connection getConnection() throws Exception {

		Connection theConn = dataSource.getConnection();
		
		return theConn;
	}
	
	private void close(Connection theConn, Statement theStmt) {
		close(theConn, theStmt, null);
	}
	
	private void close(Connection theConn, Statement theStmt, ResultSet theRs) {

		try {
			if (theRs != null) {
				theRs.close();
			}

			if (theStmt != null) {
				theStmt.close();
			}

			if (theConn != null) {
				theConn.close();
			}
			
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}	
	
	
	
	
	
}