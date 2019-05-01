package dao;

public class BasicOrderComponent implements OrderComponent{

	@Override
	public double price() {
		return 0;
	}

	@Override
	public String summary() {
		return "";
	}

}
