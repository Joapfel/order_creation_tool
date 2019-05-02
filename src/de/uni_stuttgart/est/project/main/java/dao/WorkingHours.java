package dao;

public class WorkingHours extends OrderComponentDecorator{

	private String description;
	private int hours;
	private double ratePerHour;
	private String outcome;
	
	public WorkingHours(OrderComponent orderComponent, String description, int hours, double ratePerHour) {
		super(orderComponent);
		this.description = description;
		this.hours = hours;
		this.ratePerHour = ratePerHour;
	}
		
	private double adding(double x, double y) {
		return x+y;
	}
	
	public String toString() {
		return outcome
				.concat(this.description.toString()+": ")
				.concat(String.valueOf(hours)+" Stunde(n), ")
				.concat(String.valueOf(ratePerHour)+" Euro/Stunde, ")
				.concat("Gesamt: "+String.valueOf(adding(hours,ratePerHour))+" Euro");
	}
		

	@Override
	public double price() {
		double sum = super.price();
		return adding(sum,adding(hours,ratePerHour));
	}
	
	@Override
	public String summary() {
		String summary = super.summary();
		String ownSummary = toString();
		return summary.concat(System.lineSeparator()+ownSummary);
	}
	
}
