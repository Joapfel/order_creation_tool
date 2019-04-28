package dao;

/**
 * Using the decorator pattern for price calculations and so forth.
 * 
 * @author Philipp
 *
 */
public abstract class OrderComponent {
	
	private double sum;
	private String summary;
	
	public double price() {
		return sum;
	}
	
	public String summary() {
		return summary;
	}
}
