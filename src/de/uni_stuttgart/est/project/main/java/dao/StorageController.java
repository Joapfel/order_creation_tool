package dao;

import java.io.*;


public class StorageController {

	String filename = "database.ser";
	Serializer storage = new Serializer();
	
	
	public void output(Serializer database, String filename) throws Exception {
		FileOutputStream file = new FileOutputStream(filename); 
		ObjectOutputStream out = new ObjectOutputStream(file); 
		out.writeObject(storage); 
		out.close(); 
		file.close(); 
	}
 
}
