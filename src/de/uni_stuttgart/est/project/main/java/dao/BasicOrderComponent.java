package dao;

import java.io.Serializable;

/**
 * Part of the decorator for OrderComponents.
 * 
 * @author Philipp
 *
 */
public class BasicOrderComponent implements OrderComponent, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1808056003371099993L;
	String summary = "Order setzt sich zusammen aus: ";
	Address shippingAddress;

	@Override
	public double price() {
		return 0;
	}

	@Override
	public String summary() {
		return summary.concat(System.lineSeparator());
	}

	@Override
	public Address addressToShipTo() {
		return shippingAddress;
	}
}
