package model;

/**
 * Class for Car objects.
 * 
 * @author Ivan
 * @version 12-10-18
 */

import java.math.BigDecimal;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Car {
	
	/**
	 * Attributes
	 */
	private String license;
	private String make;
	private String model;
	private BigDecimal longitude;
	private BigDecimal latitude;
	
	/**
	 * Required No Parameter Constructor 
	 */
	public Car() {
		
	}
	
	/**
	 * Getters/Setters
	*/
	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}
	
	
	

}
