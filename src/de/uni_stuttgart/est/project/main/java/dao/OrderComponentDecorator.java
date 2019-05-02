package dao;

import java.io.Serializable;

/**
 * Using the decorator pattern for price calculations and so forth.
 * 
 * @author Philipp
 *
 */
public abstract class OrderComponentDecorator implements OrderComponent, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4935174802470465775L;
	private OrderComponent orderComp;
	
	public OrderComponentDecorator(OrderComponent orderComp) {
		this.orderComp = orderComp;
	}
	
	public double price() {
		return orderComp.price();
	}
	
	public String summary() {
		return orderComp.summary();
	}
	
	// https://www.tutorialspoint.com/design_pattern/decorator_pattern.htm
	// Das bleibt mal hier für den Fall, dass ich nochmal darauf zurück greifen muss.
}
