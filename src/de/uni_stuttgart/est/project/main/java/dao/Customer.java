package dao;

import java.io.Serializable;
import java.util.LinkedList;

import main.Initialize;

/**
 * Class to store Customer-Data.
 * 
 * @author Philipp
 *
 */
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8904797428130831720L;
	
	private String companyName;
	private Address address;
	private LinkedList<Order> order= new LinkedList<Order>();
	private int customerID;
	
	/**
	 * Constructor for a new Customer. The customerID is given from the Serializer to make sure it is incremented and unique.
	 * 
	 * @param companyName
	 * @param address
	 * @param order
	 */
	public Customer(String companyName, Address address, Order order) {
		this.setCompanyName(companyName);
		this.setAddress(address);
		this.order.add(order);
		customerID = Initialize.getSerializer().getNextCustomer();
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
