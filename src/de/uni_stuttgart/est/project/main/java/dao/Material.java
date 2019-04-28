package dao;

public class Material extends OrderComponent{
	
	private OrderComponent orderComponent;
	private String description;
	private int quantity;
	private double pricePerUnit;
	private String outcome;

	public Material (OrderComponent orderComponent, String description, int quantity, double pricePerUnit) {
		this.orderComponent = orderComponent;
		this.description = description;
		this.quantity = quantity;
		this.pricePerUnit = pricePerUnit;
		
	}
	
	
	public String toString() {
		outcome
			.concat(this.description.toString()+": ")
			.concat(String.valueOf(quantity)+" Stück, ")
			.concat(String.valueOf(pricePerUnit)+" Euro/Stück, ")
			.concat("Gesamt: "+String.valueOf(quantity*pricePerUnit)+" Euro");
			
		
		return null;
	}
}
