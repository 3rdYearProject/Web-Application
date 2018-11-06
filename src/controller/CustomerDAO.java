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

import model.Customer;

public class CustomerDAO {

	private static CustomerDAO instance;
	private DataSource dataSource;
	private String jndiName = "java:comp/env/jdbc/ubertrial";//db1.cmccvmmkjrch.eu-west-1.rds.amazonaws.com
	
	public static CustomerDAO getInstance() throws Exception {
		if (instance == null) {
			instance = new CustomerDAO();
		}
		
		return instance;
	}
	
	private CustomerDAO() throws Exception {		
		dataSource = getDataSource();
	}

	private DataSource getDataSource() throws NamingException {
		Context context = new InitialContext();
		
		DataSource theDataSource = (DataSource) context.lookup(jndiName);
		
		return theDataSource;
	}
		
	public List<Customer> getCustomers() throws Exception {

		List<Customer> customers = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();

			String sql = "select * from customer";

			myStmt = myConn.createStatement();

			myRs = myStmt.executeQuery(sql);

			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				//int id = myRs.getInt("PersonID");
				String firstName = myRs.getString("LastName");
				String lastName = myRs.getString("FirstName");
				int cardNumber = myRs.getInt("card_num");
				String email = myRs.getString("email");
				int phoneNumber = myRs.getInt("phone_num");
				
				//String county = myRs.getString("County");

				// create new customer object
				Customer tempCustomer = new Customer(lastName,firstName,email,phoneNumber,cardNumber);

				// add it to the list of customers
				customers.add(tempCustomer);
			}
			
			return customers;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}

	
	public void addCustomer(Customer c) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "insert into customer (firstName, lastName, email, phone_num, card_num) values (?, ?, ?, ?, ?)";

			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, c.getFirstName());
			myStmt.setString(2, c.getLastName());
			myStmt.setString(3, c.getEmail());
			myStmt.setInt(4, c.getPhoneNumber());
			myStmt.setInt(5, c.getCardNumber());
			
			myStmt.execute();			
		}
		finally {
			close (myConn, myStmt);
		}
		
	}
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

