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
	private String journeyID;
	private Car car;
	private Customer customer;
	private double elapsedDistance;
	private double totalDistance;
	private double price;
	
	/**
	 * Constructor with ID
	 */
	public Journey(String journeyID, Car car, Customer customer, double elapsedDistance, double totalDistance,
			double price) {
		this.journeyID = journeyID;
		this.car = car;
		this.customer = customer;
		this.elapsedDistance = elapsedDistance;
		this.totalDistance = totalDistance;
		this.price = price;
	}
	
	/**
	 * Constructor without ID to operate with AutoIncrement SQL ID
	 */
	public Journey(Car car, Customer customer, double elapsedDistance, double totalDistance,
			double price) {
		this.car = car;
		this.customer = customer;
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
	public String getJourneyID() {
		return journeyID;
	}

	public void setJourneyID(String journeyID) {
		this.journeyID = journeyID;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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
