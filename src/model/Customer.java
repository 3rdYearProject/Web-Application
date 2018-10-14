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
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String cardNumber;
	private String county;
	private List<String> countyOptions;
	
	
	
	public Customer(String lastName,String firstName, String email, String phoneNumber, String cardNumber,
			String county) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.cardNumber = cardNumber;
		this.county = county;
	}

	/**
	 * Required No Parameter Constructor 
	 */
	public Customer() {
		//initialize List | add counties for display in xhtml page
		countyOptions = new ArrayList<>();
		countyOptions.add("Carlow");
		countyOptions.add("Cavan");
		countyOptions.add("Clare");
		countyOptions.add("Cork");
		countyOptions.add("Donegal");
		countyOptions.add("Dublin");
		countyOptions.add("Galway");
		countyOptions.add("Kerry");
		countyOptions.add("Kildare");
		countyOptions.add("Kilkenny");
		countyOptions.add("Laois");
		countyOptions.add("Leitrim");
		countyOptions.add("Limerick");
		countyOptions.add("Longford");
		countyOptions.add("Louth");
		countyOptions.add("Mayo");
		countyOptions.add("Meath");
		countyOptions.add("Monaghan");
		countyOptions.add("Offaly");
		countyOptions.add("Roscommon");
		countyOptions.add("Sligo");
		countyOptions.add("Tipperary");
		countyOptions.add("Waterford");
		countyOptions.add("Westmeath");
		countyOptions.add("Wexford");
		countyOptions.add("Wicklow");
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}
	
	public List<String> getCountyOptions() {
		return countyOptions;
	}

	@Override
	public String toString() {
		return "Customer [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phoneNumber="
				+ phoneNumber + ", cardNumber=" + cardNumber + ", county=" + county + "]";
	}
	
	
	
	
	

}
