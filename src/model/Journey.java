package model;

/**
 * Class for Journey objects.
 * 
 * @author Ivan
 * @version 12-10-18
 */

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Journey {
	
	/**
	 * Attributes
	 */
	private int journeyID;
	private String carRegistration;
	private int customerID;
	private double elapsedDistance;
	private double totalDistance;
	private double price;
	
	/**
	 * Constructor with ID
	 */
	public Journey(int journeyID, String carRegistration, int customerID, double elapsedDistance, double totalDistance,
			double price) {
		this.journeyID = journeyID;
		this.carRegistration = carRegistration;
		this.customerID = customerID;
		this.elapsedDistance = elapsedDistance;
		this.totalDistance = totalDistance;
		this.price = price;
	}
	
	/**
	 * Constructor without ID to operate with AutoIncrement SQL ID
	 */
	public Journey(String carRegistration, int customerID, double elapsedDistance, double totalDistance,
			double price) {
		this.carRegistration = carRegistration;
		this.customerID = customerID;
		this.elapsedDistance = elapsedDistance;
		this.totalDistance = totalDistance;
		this.price = price;
	}
	
	

	/**
	 * Required No Parameter Constructor 
	 */
	public Journey() {
		
	}
	
	/**
	 * Getters/Setters
	*/
	public int getJourneyID() {
		return journeyID;
	}

	public void setJourneyID(int journeyID) {
		this.journeyID = journeyID;
	}

	public String getCarRegistration() {
		return carRegistration;
	}

	public void setCarRegistration(String carRegistration) {
		this.carRegistration = carRegistration;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public double getElapsedDistance() {
		return elapsedDistance;
	}

	public void setElapsedDistance(double elapsedDistance) {
		this.elapsedDistance = elapsedDistance;
	}

	public double getTotalDistance() {
		return totalDistance;
	}

	public void setTotalDistance(double totalDistance) {
		this.totalDistance = totalDistance;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	

}
