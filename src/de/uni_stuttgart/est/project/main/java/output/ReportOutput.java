package output;

import dao.Order;
import main.Initialize;
import storage.Serializer;
import utils.FileWriter;

public class ReportOutput {
	
	/**
	 * creates a report over all orders
	 */
	public static void createReport(String path) {
    	Serializer serializer = Initialize.getSerializer();
    	StringBuilder sb = new StringBuilder();
    	sb.append("Auftrag\tPreis");
    	sb.append(System.lineSeparator());
    	sb.append("===============");
    	sb.append(System.lineSeparator());
    	sb.append(System.lineSeparator());
    	
    	for(Order order : serializer.getAllOrders()) {
    		sb.append(order.getOrdername() 
    				+ "\t" + order.getOrderComponent().price());
    		sb.append(System.lineSeparator());
    		sb.append(order.getOrderAsText());
    		sb.append(System.lineSeparator());
    		sb.append(System.lineSeparator());
    	}
    	
    	FileWriter.writeToFile(path, sb.toString());
	}
}
