package dao;

public class Machine extends OrderComponentDecorator{

	private String description;
	private int hours;
	private double pricePerHour;
	private String outcome = new String();
	
	public Machine(OrderComponent orderComponent, String description, int hours, double pricePerHour) {
		super(orderComponent);
		this.description = description;
		this.hours = hours;
		this.pricePerHour = pricePerHour;
	}
		
	private double adding(double x, double y) {
		return x+y;
	}
	
	public String toString() {
		return outcome
				.concat(this.description.toString()+": ")
				.concat(String.valueOf(hours)+" Stunde(n), ")
				.concat(String.valueOf(pricePerHour)+" Euro/Stunde, ")
				.concat("Gesamt: "+String.valueOf(hours*pricePerHour)+" Euro");
	}
		

	@Override
	public double price() {
		double sum = super.price();
		return adding(sum,hours*pricePerHour);
	}
	
	@Override
	public String summary() {
		String summary = super.summary();
		String ownSummary = toString();
		return summary.concat(System.lineSeparator()+ownSummary);
	}
	
}
