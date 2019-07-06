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
	String shippingAddress = "";

	@Override
	public double price() {
		return 0;
	}

	@Override
	public String summary() {
		return summary.concat(System.lineSeparator());
	}

	@Override
	public String addressToShipTo() {
		return shippingAddress;
	}
}
