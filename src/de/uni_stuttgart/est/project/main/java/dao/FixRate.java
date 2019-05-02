package dao;

import java.io.Serializable;

/**
 * Decorator to connect FixRate with the OderComponent.
 * So far nothing happens in this class; it is realized for future interactions.
 * 
 * @author Philipp
 *
 */
public class FixRate extends OrderComponentDecorator implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2400158883954608609L;

	public FixRate(OrderComponent orderComponent) {
		super(orderComponent);
	}

	public double price() {
		return super.price()+0;
	}
}
