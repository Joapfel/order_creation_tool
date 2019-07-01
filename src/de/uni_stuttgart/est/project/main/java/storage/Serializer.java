package storage;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

import dao.Customer;
import dao.Order;
import dao.User;

/**
 * Main-Active-Storage for nearly all used data.
 * A instance is realized in the static getSerializer-method in the Main-class.
 * Everytime a save...-function is used the Serializer-Object is stored in the external data-file.
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
	
	/*
	 * For testing only!!!
	 */
	public void printAllUsers() {
		
		for (String key: userMap.keySet()) {
			System.out.println();
			System.out.println("Users: ");
			System.out.println();
		    System.out.println("key : " + key);
		    System.out.println("value : " + userMap.get(key));
		}
	}
	
	/*
	 * For testing only!!!
	 */
	public void printAllCustomers() {
			
			for (String key: customerNameMap.keySet()) {
				System.out.println();
				System.out.println("Customers: ");
				System.out.println();
			    System.out.println("key : " + key);
			    System.out.println("value : " + customerNameMap.get(key).getCompanyName());
			}
		}

	/*
	 * For testing only!!!
	 */
	public void printAllOrders() {
		
		for (int key: orderIDMap.keySet()) {
			System.out.println();
			System.out.println("Orders: ");
			System.out.println();
		    System.out.println("key : " + key);
		    System.out.println("value : " + orderIDMap.get(key).getOrdername());
		}
	}

	/*
	 * removes a given username from the name-map and therefore makes it impossible to get access to the user-object
	 */
	@Override
	public boolean deleteUserByUsername(String userName) {
		userMap.remove(userName);
		return false;
	}

	/*
	 * returns a Linkedlist of all Users
	 */
	@Override
	public LinkedList<User> getAllUsers() {
		LinkedList<User> userList = new LinkedList<User>();
		for(Entry<String, User> entry: userMap.entrySet()) {
			userList.add(entry.getValue());
		}
		return userList;
	}
	
	/*
	 * returns a Linkedlist of all Orders
	 */
	@Override
	public LinkedList<Order> getAllOrders() {
		LinkedList<Order> orderList = new LinkedList<Order>();
		for(Entry<Integer, Order> entry: orderIDMap.entrySet()) {
			orderList.add(entry.getValue());
		}
		return orderList;
	}

	/*
	 * returns a Linkedlist of all Customers
	 */
	@Override
	public LinkedList<Customer> getAllCustomers() {
		LinkedList<Customer> customerList = new LinkedList<Customer>();
		for(Entry<Integer, Customer> entry: customerIDMap.entrySet()) {
			customerList.add(entry.getValue());
		}
		return customerList;
	}

	@Override
	public void delete_Username(User user) {
		user.change_Username("Deleted");
	}

	@Override
	public void delete_Customername(Customer customer) {
		customer.change_companyname("Deleted");
		customerNameMap.remove(customer.getCompanyName(), customer);
	}
	
}
