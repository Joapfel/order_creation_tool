package main;

import dao.User;
import storage.Serializer;
import storage.StorageController;

public class Initialize {

	private static Serializer singleton;
	
	private Initialize() {
		
	}
	
    /**
     * This method will create a user "Testuser" with the password "est" if the database doesn't contain this user already.
     * Additionally the database is loaded for further use.
     * 
     * @author Philipp
     *
     */
    public static Serializer initialize() {
    	/*
    	 * Testuser and his pw are meant to be implemented for first-time-login.
    	 * There should be a way to get rid of the password / to disable access with this account; that should be a future project and be done before shipping. 
    	 */
    	String name = "Testuser";
    	String pw = "est";
    	String role = "normal";
    	String hr_name = "TestuserHR";
    	String hr_pw = "est";
    	String hr_role ="HR user";
    	User user = new User(name, pw, role);
    	User hr_user = new User (hr_name, hr_pw, hr_role);
    	
    	Serializer sers = new Serializer();
    	if(StorageController.fileCheck()) {
    		sers = StorageController.loadDB();	//if the database-file is lost this would not find a file and throw an error
    	}
    	else {
    		StorageController.saveDB(sers); //therefor an empty file is created if database.ser is not found
    		System.out.println("A new database-file had to be created!");
    	}
    	
    	/*
    	 * If the Testuser exists in the database he should not be recreated. 
    	 */
    	if(sers.userExists(user)) {
    		// do nothing here; everything is fine
    	}
    	else {
    		sers.saveUser(user);
    	}
    	
    	/*
    	 * If the Testuser for HR exists in the database he should not be recreated. 
    	 */
    	if(sers.userExists(hr_user)) {
    		// do nothing here; everything is fine
    	}
    	else {
    		sers.saveUser(hr_user);
    	}
    	
    	/*
    	 * After initializing this method returns the loaded Serializer-Object.
    	 */
    	return sers;
    }
    
    public static synchronized Serializer getSerializer() {
    	if(singleton==null) {
    		singleton = initialize();
    	}
    	return singleton;
    }
}
