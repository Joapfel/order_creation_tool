package dao;

import java.util.Base64;

public class BasicAuthentication implements Authentication, {

	/*
	 * instance variable which is an object of the interface type Storage
	 */

	private Storage sto;

	/*
	 * constructor which takes a Storage object as argument and initializes its own
	 * Storage instance variable
	 */

	sto=new Storage();

	/*
	 * implement the login method from the Authentication interface
	 */

	public boolean login() {
		
		/*
		 * encode the password as Base64 string
		 */
		
		String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes());
		
		/* 
		 * create a User object with the username and the Base64 encoded password
		 */
		
		User thisUser = new.User(username, encodedPassword);
		
		/*
		 * use the Storage instance variable to check if such an User exists using the 
		 * findUserByUsername method (will return an User object)
		 */
		
		sto.findUserByUsername(username);
		
		/*
		 * compare the returned and self created User objects using the equals method (return true/false respectively)
		 */

		if sto.equals(thisUser) {
			return true;
		}
		else return false;
	
	
}