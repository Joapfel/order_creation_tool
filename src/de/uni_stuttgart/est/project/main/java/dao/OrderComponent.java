package dao;

/**
 * Interface in use with the decorator pattern.
 * 
 * @author Philipp
 *
 */
public interface OrderComponent{
	public double price();
	public String summary();
	public String addressToShipTo();
}
