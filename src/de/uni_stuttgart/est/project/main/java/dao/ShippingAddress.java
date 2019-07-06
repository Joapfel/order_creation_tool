package dao;

import java.io.Serializable;

public class ShippingAddress  extends OrderComponentDecorator implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7453904746917677711L;
	private Address address;
	private String shippingAddress = new String();
	
	
	public ShippingAddress(OrderComponent orderComp, Address shippingAddress) {
		super(orderComp);
		this.address = shippingAddress;
	}

	public String addressToShipTo() {
		shippingAddress = super.addressToShipTo();
		
		return shippingAddress
				.concat(address.getStreetname()+" "+address.getHousenumber()+System.lineSeparator())
				.concat(address.getZipcode()+" "+address.getCity()+System.lineSeparator())
				.concat(address.getCountry()+System.lineSeparator());
	}
}
