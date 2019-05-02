package dao;

import java.io.Serializable;
import java.util.LinkedList;

import main.Main;

public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8904797428130831720L;
	
	private String companyName;
	private Address address;
	private LinkedList<Order> order= new LinkedList<Order>();
	private int customerID;
	
	
	public Customer(String companyName, Address address, Order order) {
		this.setCompanyName(companyName);
		this.setAddress(address);
		this.order.add(order);
		customerID = Main.getSerializer().getNextCustomer();
	}

	
	/**
	 * For Testing only!
	 */
	public Customer(String companyName, Address address, Order order, int ID) {
		this.setCompanyName(companyName);
		this.setAddress(address);
		this.order.add(order);
		customerID = ID;
	}


	/**
	 * @return the customerID
	 */
	public int getCustomerID() {
		return customerID;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}


	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}


	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}
	
	/**
	 * 
	 * @return list of orders by this customer
	 */
	public LinkedList<Order> getOrder(){
		return order;
	}
	
	
}
