package dao;

import java.io.Serializable;

public class IDHelper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2353033082801143155L;
	
	private int numberC=1;
	private int numberO=1;
	
	public synchronized int getNextCustomer() {
		return numberC++; 
	}
	
	public synchronized int getNextOrder() {
		return numberO++;
	}
	
}
