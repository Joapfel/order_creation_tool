package dao;

import java.io.*;
import java.util.*;

/**
 * This class handles the data-saving operations for the program.
 * 
 * @author Philipp
 *
 */
public class Serializer implements Serializable, Storage {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4750776875572132999L;
	private HashMap<String, User> userMap = new HashMap<String, User>();
	private HashMap<String, Customer> customerNameMap = new HashMap<String, Customer>();
	private HashMap<Integer, Customer> customerIDMap = new HashMap<Integer, Customer>();
	
	
	@Override
	public void saveUser(User user) {
		userMap.put(user.getUsername(), user);
	}
	
	
	@Override
	public User findUserByUsername(String username) {	
		if(userMap.containsKey(username)) {
			return userMap.get(username);
		}
		else {
			return null;	
		}
	}
	

	@Override
	public void saveCustomer(Customer customer) {
		customerNameMap.put(customer.getCompanyName(), customer);
		customerIDMap.put(customer.getCustomerID(), customer);
	}


	@Override
	public Customer findCustomersByCompanyName(String companyName) {
		if(customerNameMap.containsKey(companyName)) {
			return customerNameMap.get(companyName);
		} 
		else {
			return null;
		}
	}


	@Override
	public Customer findCustomerById(int companyID) {
		if(customerIDMap.containsKey(companyID)) {
			return customerIDMap.get(companyID);
		} 
		else {
			return null;
		}
	}
	
	
	
	

}
