package dao;

import java.io.*;

/**
 * The class handles the saving and loading from the database-file (named: database.ser).
 * In this version the loader is not testing if the loaded database is corrupted or tempered with!
 * 
 * @author Philipp
 *
 */
public class StorageController {

	private static String filename = "database.ser"; //hardcoded filename for convenience
		
	public static void saveDB(Serializer serializerToSave){
		try {
		FileOutputStream file = new FileOutputStream(filename); 
		ObjectOutputStream out = new ObjectOutputStream(file); 
		out.writeObject(serializerToSave); 
		out.close(); 
		file.close();
		}
		catch(Exception e) {
			System.out.println("Critical Error while writing into database!");
			e.printStackTrace();
		}
	}
	
	public static Serializer loadDB(){
		Serializer storage = new Serializer();

		try {
		FileInputStream file = new FileInputStream(filename); 
        ObjectInputStream in = new ObjectInputStream(file); 
        storage = (Serializer)in.readObject();
        in.close();
        file.close();
		return storage;
		}
		catch(Exception e) {
			System.out.println("Critical Error while reading from database!");
			e.printStackTrace();

			return storage;
		}
	}
	
	public static boolean fileCheck() {
		return new File(filename).exists();
	}
}
