package main;

import dao.Serializer;
import dao.User;

/**
 * This class is for testing purposes only.
 * It will be used until the HR-Interface is build and in use to manage multiple users and their access rights.
 * 
 * This class will create a user "Testuser" with the password "est" if the database doesn't contain this user already.
 * 
 * @author Philipp
 *
 */
public class Initialize {
	String name = "Testuser";
	String pw = "est";
	
	User user = new User(name, pw);
	
	Serializer sers = new Serializer();
	
	
	
}
