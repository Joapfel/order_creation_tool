package dao;

public class BasicOrderComponent implements OrderComponent{

	String summary = "Order setzt sich zusammen aus: ";

	@Override
	public double price() {
		return 0;
	}

	@Override
	public String summary() {
		return summary.concat(System.lineSeparator());
	}

}
