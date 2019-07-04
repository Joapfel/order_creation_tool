package dao;

import java.io.Serializable;

public class shippingAddress  extends OrderComponentDecorator implements Serializable{

	private Address address;
	
	
	public shippingAddress(OrderComponent orderComp, Address shippingAddress) {
		super(orderComp);
		this.address = shippingAddress;
	}

	public Address addressToShipTo() {
		return address;
	}
	

}
