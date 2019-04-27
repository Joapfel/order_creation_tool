package dao;

import java.io.*;

	/**
	 * Class to store User-Data.
	 * 
	 * @
	 * @author Philipp
	 *
	 */
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4456805580456631474L;
	
	private String username;
	private String password;
	
	/**
	 * Constructor for Users.
	 * 
	 * @param username
	 * @param password
	 */
	public User(String username, String password){
		this.username = username;
		this.password = password;
	}
	
	/**
	 * Compares two User and returns true if username and password are identical.
	 * 
	 * @param user_1
	 * @param user_2
	 * @return
	 */
	public boolean equals(User user_1, User user_2) {
		if(user_1.username.equals(user_2.username)&&(user_1.password.equals(user_2.password))) 
		{
			return true;
		}
		else {
			return false;
		}
	}
	
	public String getUsername() {
		return username;
	}
}
