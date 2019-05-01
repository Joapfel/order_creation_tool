package dao;

public class FixRate extends OrderComponentDecorator{
	
	public FixRate(OrderComponent orderComponent) {
		super(orderComponent);
	}

	public double price() {
		return super.price()+0;
	}
}
