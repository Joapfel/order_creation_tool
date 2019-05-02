package authentication;

import dao.User;
import main.Main;
import storage.Serializer;

public class BasicAuthentication implements Authentication{

	/*
	 * instance variable which is an object of the interface type Storage
	 * 
	 * constructor which takes a Storage object as argument and initializes its own
	 * Storage instance variable
	 */	
	private Serializer sto;
	
	/*
	 * imo we should make the login-part static
	 * would have to get rid of the interface, but it is not needed anyway -PT
	 */
	public BasicAuthentication() {
		sto = Main.getSerializer();
	}
	

	/*
	 * implement the login method from the Authentication interface
	 */
	public boolean login(String username, String password) {
		
		/*
		 * encode the password as Base64 string
		 */
		
		//String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes());
		
		/* 
		 * create a User object with the username and the Base64 encoded password
		 */
		
		//User thisUser = new User(username, encodedPassword);
		User thisUser = new User(username, password);
		
		/*
		 * use the Storage instance variable to check if such an User exists using the 
		 * findUserByUsername method (will return an User object)
		 */
		
		User storedUser = sto.findUserByUsername(username);
		
		
		/*
		 * compare the returned and self created User objects using the equals method (return true/false respectively)
		 */
		
		if (storedUser == null) {
			return false;
		}
		else return storedUser.equals(storedUser, thisUser);
	}
}
