package dao;

import java.io.Serializable;

/**
 * Decorator to connect Material-informations with the OderComponent.
 * 
 * 
 * @author Philipp
 *
 */
public class Material extends OrderComponentDecorator implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7481772131090627776L;
	private String description;
	private int quantity;
	private double pricePerUnit;
	private String outcome = new String();

	public Material (OrderComponent orderComponent, String description, int quantity, double pricePerUnit) {
		super(orderComponent);
		this.description = description;
		this.quantity = quantity;
		this.pricePerUnit = pricePerUnit;	
	}
	
	private double adding(double x, double y) {
		return x+y;
	}
	
	public String toString() {
		return outcome
				.concat(this.description.toString()+": ")
				.concat(String.valueOf(quantity)+" Einheiten, ")
				.concat(String.valueOf(pricePerUnit)+" Euro/Einheit, ")
				.concat("Gesamt: "+String.valueOf(quantity*pricePerUnit)+" Euro");
	}
	
	@Override
	public double price() {
		double sum = super.price();
		return adding(sum,quantity*pricePerUnit);
	}
	
	@Override
	public String summary() {
		String summary = super.summary();
		String ownSummary = toString();
		return summary.concat(System.lineSeparator()+ownSummary);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}
	
	
	
	
}
