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
	private String jndiName = "java:comp/env/jdbc/ubertrial";
	
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
		//List to hold returned DB data
		List<Customer> customers = new ArrayList<>();
		//connection,prepared statement,result set
		Connection myConn = null;
		PreparedStatement myPS = null;//TODO
		ResultSet myPSRS = null;
		
		
		try {
			//establish connection to DB
			myConn = getConnection();
			//select all Customers
			String sql = "select * from customer";
			//add auto-increment id to result set
			myPS = myConn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			//get result set
			myPSRS = myPS.executeQuery();
			
			// iterate over result set
			while (myPSRS.next()) {//while (myRs.next())
				
				int customerID = myPSRS.getInt("customer_id");
				String firstName = myPSRS.getString("FirstName");
				String lastName = myPSRS.getString("LastName");
				String email = myPSRS.getString("Email");
				int phoneNumber = myPSRS.getInt("Phone_Number");
				int cardNumber = myPSRS.getInt("Card_Number");
				
				// create new customer object
				Customer tempCustomer = new Customer(customerID,firstName,lastName,email,phoneNumber,cardNumber);
				// add it to the list of customers
				customers.add(tempCustomer);
			}
			//return List generated from DB
			return customers;		
		}
		finally {
			close (myConn, myPS, myPSRS);
		}
	}

	
	public void addCustomer(Customer c) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "insert into customer (FirstName, LastName, Email, Phone_Number, Card_Number) values (?, ?, ?, ?, ?)";

			myStmt = myConn.prepareStatement(sql);

			// set params
			/*myStmt.setString(1, null);
			myStmt.setString(2, c.getFirstName());
			myStmt.setString(3, c.getLastName());
			myStmt.setString(4, c.getEmail());
			myStmt.setInt(5, c.getPhoneNumber());
			myStmt.setInt(6, c.getCardNumber());
			*/
			
			
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
	
	/**
	 * Method to find Customer via CustomerID
	 * @param customerID ID of Customer
	 * @return Customer
	 * @throws Exception
	 */
	public Customer getCustomer(int customerID) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();

			String sql = "select * from customer where customer_id=?";

			myStmt = myConn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			
			// set params
			myStmt.setInt(1, customerID);
			
			myRs = myStmt.executeQuery();

			Customer customer = null;
			
			// retrieve data from result set row
			if (myRs.next()) {
				int id = myRs.getInt("customer_id");
				String firstName = myRs.getString("FirstName");
				String lastName = myRs.getString("LastName");
				String email = myRs.getString("Email");
				int phoneNumber = myRs.getInt("Phone_Number");
				int cardNumber = myRs.getInt("Card_Number");

				customer = new Customer(id, firstName, lastName,
						email,phoneNumber,cardNumber);
			}
			else {
				throw new Exception("Could not find customer id: " + customerID);
			}

			return customer;
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
	
	
	
	public void updateCustomer(Customer customer) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "update customer "
						+ " set FirstName=?, LastName=?, Email=?, Phone_Number=?, Card_Number=?"
						+ " where customer_id=?";

			myStmt = myConn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

			// set params
			myStmt.setString(1, customer.getFirstName());
			myStmt.setString(2, customer.getLastName());
			myStmt.setString(3, customer.getEmail());
			myStmt.setInt(4, customer.getPhoneNumber());
			myStmt.setInt(5, customer.getCardNumber());
			myStmt.setInt(6, customer.getCustomerID());
			
			myStmt.execute();
		}
		finally {
			close (myConn, myStmt);
		}
		
	}
	
	/**
	 * Method to delete Customer from DB via customer id
	 * @param customerID id of customer to delete
	 * @throws Exception
	 */
	public void deleteCustomer(int customerID) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "delete from customer where customer_id=?";

			myStmt = myConn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

			// set params
			myStmt.setInt(1, customerID);
			
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

