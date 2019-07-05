package dao;

import java.io.Serializable;

public class ShippingAddress  extends OrderComponentDecorator implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7453904746917677711L;
	private Address address;
	
	
	public ShippingAddress(OrderComponent orderComp, Address shippingAddress) {
		super(orderComp);
		this.address = shippingAddress;
	}

	public Address addressToShipTo() {
		return address;
	}
	

}
