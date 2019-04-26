package dao;

import java.io.Serializable;

public class IDHelper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2353033082801143155L;
	
	private int number=1;
	
	public int getNext() {
		return number++; 
	}
	
}
