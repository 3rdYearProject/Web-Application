package utility;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import model.Customer;

@ManagedBean
@ApplicationScoped
public class CustomerDataUtil {
	
private List<Customer> Customers;
	
	public CustomerDataUtil() {
		loadSampleData();
	}
	
	/*
	 * private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private int cardNumber;
	private String county;
	 */
	
	public void loadSampleData() {
		Customers = new ArrayList<>();
		Customers.add(new Customer("Joe","Smith","js@gmail.com","089765345","xxxx-xxxx-xxxx-xxxx","Dublin"));
		Customers.add(new Customer("Mary","Jones","maryj@gmail.com","085678543","xxxx-xxxx-xxxx-xxxx","Carlow"));
		Customers.add(new Customer("Tom","Murphy","tmurph@yahoo.com","08712345","xxxx-xxxx-xxxx-xxxx","Cork"));
	}
	
	public List<Customer> getCustomers() {
		return Customers;
	}

}
