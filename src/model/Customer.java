package model;

/**
 * Class for Customer objects.
 * Includes List of counties initialized in constructor.
 * 
 * @author Ivan
 * @version 12-10-18
 */


import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Customer {
	
	/**
	 * Attributes
	 */
	private int customerID;
	private String firstName;
	private String lastName;
	private String email;
	private int phoneNumber;
	private int cardNumber;
	
	/**
	 * Constructor without ID to operate with AutoIncrement SQL ID
	 */
	public Customer(String firstName,String lastName, String email, int phoneNumber, int cardNumber) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.cardNumber = cardNumber;
		
	}
	
	/**
	 * Constructor with ID
	 */
	public Customer(int customerID, String firstName,String lastName, String email, int phoneNumber, int cardNumber) {
		this.customerID = customerID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.cardNumber = cardNumber;
		
	}
	
		

	/**
	 * Required No Parameter Constructor 
	 */
	public Customer() {
		
	}
	
	/**
	 * Getters/Setters
	*/
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public int getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	@Override
	public String toString() {
		return "Customer firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phoneNumber="
				+ phoneNumber + ", cardNumber=" + cardNumber;
	}
	
	
	
	
	

}
