package dao;

import java.io.Serializable;
import java.util.LinkedList;

public class Customer implements Serializable {

	private int customerID;
	private String companyName;
	private Address address;
	private LinkedList<Order> order= new LinkedList<Order>();
	private IDHelper seq;
	
	
	public Customer(String companyName, Address address, Order order) {
		setCustomerID(seq.getNext());
		this.setCompanyName(companyName);
		this.setAddress(address);
		this.order.add(order);
	}


	/**
	 * @return the customerID
	 */
	public int getCustomerID() {
		return customerID;
	}


	/**
	 * @param customerID the customerID to set
	 */
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
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
