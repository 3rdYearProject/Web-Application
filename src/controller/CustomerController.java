package controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import model.Customer;

@ManagedBean
@SessionScoped
public class CustomerController {

	private List<Customer> customers;
	private CustomerDAO customerDAO;
	
	
	public CustomerController() throws Exception {
		customers = new ArrayList<>();
		
		customerDAO = CustomerDAO.getInstance();
	}
	
	public List<Customer> getCustomers() {
		return customers;
	}
	
	/**
	 * Method to get all Customers
	 */
	public void loadCustomers() {
		customers.clear();
		try {
			// get all customers from database
			customers = customerDAO.getCustomers();
		} catch (Exception exc) {
			// add error message for JSF page
			addErrorMessage(exc);
		}
	}
	
	/**
	 * Method to add a new Customer
	 * @param newCustomer
	 * @return
	 */
	public String addCustomer(Customer newCustomer) {
		try {
			// add customer to the database
			customerDAO.addCustomer(newCustomer);

		} catch (Exception exc) {
			// add error message for JSF page
			addErrorMessage(exc);
			return null;
		}

		return "customer-table?faces-redirect=true";
	}
	
	public String loadCustomer(int customerID) {
		
		try {
			// get customer from DB
			Customer customer = customerDAO.getCustomer(customerID);
			
			// request attributefor form page
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();		

			Map<String, Object> requestMap = externalContext.getRequestMap();
			requestMap.put("customer", customer);	
			
		} catch (Exception exc) {
			// add error message for JSF page
			addErrorMessage(exc);
			
			return null;
		}
				
		return "update-customer-form.xhtml";
	}	
	
	/**
	 * Method to update Customer
	 * @param customer Customer to update
	 * @return
	 */
	public String updateCustomer(Customer customer) {

		try {
			
			// update student in the database
			customerDAO.updateCustomer(customer);
			
		} catch (Exception exc) {
			// add error message for JSF page
			addErrorMessage(exc);
			
			return null;
		}
		
		return "customer_table?faces-redirect=true";		
	}
	
	/**
	 * Method to delete customer from DB
	 * @param customerID id of customer to delete
	 * @return customer_table
	 */
	public String deleteCustomer(int customerID) {
		try {
			// delete customer from DB
			customerDAO.deleteCustomer(customerID);
			
		} catch (Exception exc) {
			// add error message for JSF page
			addErrorMessage(exc);
			return null;
		}
		return "customer_table";	
	}	
		
	/**
	 * Error message
	 * @param exc
	 */
	private void addErrorMessage(Exception exc) {
		FacesMessage message = new FacesMessage("Error: " + exc.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	
	
}
