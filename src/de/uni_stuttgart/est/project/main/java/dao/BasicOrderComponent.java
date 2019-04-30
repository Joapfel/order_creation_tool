package dao;

public class BasicOrderComponent implements OrderComponent{

	double sum = 0;
	String summary = "";

	@Override
	public double price() {
		return sum;
	}

	@Override
	public String summary() {
		return summary;
	}

}
