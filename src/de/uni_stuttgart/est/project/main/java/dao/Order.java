package dao;

import java.io.Serializable;

import main.Main;

public class Order implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6977392681746496112L;
	
	private String ordername;
	private OrderComponent orderComponent;
	private String orderAsText;
	private int orderID;
	
	public Order(String name, OrderComponent component, String text) {
		this.ordername = name;
		this.orderComponent = component;
		this.orderAsText = text;
		orderID = Main.getSerializer().getNextOrder();
	}
	
	/**
	 * @return the ordername
	 */
	public String getOrdername() {
		return ordername;
	}
	/**
	 * @param ordername the ordername to set
	 */
	public void setOrdername(String ordername) {
		this.ordername = ordername;
	}
	/**
	 * @return the orderComponent
	 */
	public OrderComponent getOrderComponent() {
		return orderComponent;
	}
	/**
	 * @param orderComponent the orderComponent to set
	 */
	public void setOrderComponent(OrderComponentDecorator orderComponent) {
		this.orderComponent = orderComponent;
	}
	/**
	 * @return the orderAsText
	 */
	public String getOrderAsText() {
		return orderAsText;
	}
	/**
	 * @param orderAsText the orderAsText to set
	 */
	public void setOrderAsText(String orderAsText) {
		this.orderAsText = orderAsText;
	}
	
	public boolean equals(Order order_1, Order order_2) {
		if(order_1.ordername.equals(order_2.ordername)&&(order_1.orderComponent.equals(order_2.orderComponent))) 
		{
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * @return the orderID
	 */
	public int getOrderID() {
		return orderID;
	}
}
