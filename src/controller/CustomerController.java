package controller;


import java.util.ArrayList;
import java.util.List;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
				
	private void addErrorMessage(Exception exc) {
		FacesMessage message = new FacesMessage("Error: " + exc.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
}
