package dao;

public class Material extends OrderComponent{
	
	private OrderComponent orderComponent;
	private String descritopn;
	private int quantity;
	private double pricePerUnit;
	private String outcome;

	public void Material (OrderComponent orderComponent, String description, int quantity, double pricePerUnit) {
		this.orderComponent = orderComponent;
		this.descritopn = description;
		this.quantity = quantity;
		this.pricePerUnit = pricePerUnit;
		
	}
	
	
	public String toString() {
		outcome
			.concat(this.descritopn.toString())
			.concat(": ")
			.concat(String.valueOf(quantity)+" Stück, ")
			.concat(String.valueOf(pricePerUnit)+" Euro/Stück, ")
			.concat("Gesamt: "+String.valueOf(quantity*pricePerUnit));
			
		
		return null;
	}
}
