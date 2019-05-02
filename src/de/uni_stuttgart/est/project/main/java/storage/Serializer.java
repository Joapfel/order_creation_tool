package storage;

import java.io.*;
import java.util.*;

import dao.Customer;
import dao.Order;
import dao.User;

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
	private static final long serialVersionUID = 1983614746413443136L;
	
	private HashMap<String, User> userMap = new HashMap<String, User>();
	private HashMap<String, Customer> customerNameMap = new HashMap<String, Customer>();
	private HashMap<Integer, Customer> customerIDMap = new HashMap<Integer, Customer>();
	private HashMap<Integer, Order> orderIDMap = new HashMap<Integer, Order>();
	private int counterC;
	private int counterO;
	
	public int getNextOrder() {
		return counterO++;
	}
	
	public int getNextCustomer() {
		return counterC++;
	}
	
	@Override
	public void saveUser(User user) {
		userMap.put(user.getUsername(), user);
		StorageController.saveDB(this);
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
	
	public boolean userExists(User user) {
		if(userMap.containsKey(user.getUsername())) {
			return true;
		} 
		else {
			return false;	
		}
		
	}
	

	@Override
	public void saveCustomer(Customer customer) {
		customerNameMap.put(customer.getCompanyName(), customer);
		customerIDMap.put(customer.getCustomerID(), customer);
		StorageController.saveDB(this);
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


	@Override
	public int saveOrder(Order order) {
		int pos = order.getOrderID();
		orderIDMap.put(pos, order);
		StorageController.saveDB(this);
		return pos;
	}


	@Override
	public Order findOrderByID(int orderID) {
		if(orderIDMap.containsKey(orderID)) {
			return orderIDMap.get(orderID);
		} 
		else {
			return null;
		}
	}
	
	public void printAllUsers() {
		
		for (String key: userMap.keySet()) {
			System.out.println();
			System.out.println("Users: ");
			System.out.println();
		    System.out.println("key : " + key);
		    System.out.println("value : " + userMap.get(key));
		}
	}
	
	public void printAllCustomers() {
			
			for (String key: customerNameMap.keySet()) {
				System.out.println();
				System.out.println("Customers: ");
				System.out.println();
			    System.out.println("key : " + key);
			    System.out.println("value : " + customerNameMap.get(key).getCompanyName());
			}
		}

	public void printAllOrders() {
		
		for (int key: orderIDMap.keySet()) {
			System.out.println();
			System.out.println("Orders: ");
			System.out.println();
		    System.out.println("key : " + key);
		    System.out.println("value : " + orderIDMap.get(key).getOrdername());
		}
	}
}