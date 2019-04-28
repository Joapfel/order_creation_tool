package dao;

public class Material extends OrderComponentDecorator{
	
	private String description;
	private int quantity;
	private double pricePerUnit;
	private String outcome;

	public Material (OrderComponent orderComponent, String description, int quantity, double pricePerUnit) {
		super(orderComponent);
		this.description = description;
		this.quantity = quantity;
		this.pricePerUnit = pricePerUnit;	
	}
	
	private double adding(double x, double y) {
		return x*y;
	}
	
	public String toString() {
		return outcome
				.concat(this.description.toString()+": ")
				.concat(String.valueOf(quantity)+" Stück, ")
				.concat(String.valueOf(pricePerUnit)+" Euro/Stück, ")
				.concat("Gesamt: "+String.valueOf(adding(quantity,pricePerUnit))+" Euro");
	}
	
	@Override
	public double price() {
		double sum = super.price();
		return adding(sum,adding(quantity,pricePerUnit));
	}
	
	@Override
	public String summary() {
		String summary = super.summary();
		String ownSummary = toString();
		return summary.concat(System.lineSeparator()+ownSummary);
	}
}
