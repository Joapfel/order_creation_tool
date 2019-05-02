package dao;

import java.io.Serializable;

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
