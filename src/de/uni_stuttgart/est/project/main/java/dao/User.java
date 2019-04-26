package dao;

public class User {
	
	private String username;
	private String password;
	
	public User(String username, String password){
		this.username = username;
		this.password = password;
	}
	
	public boolean equals(String username, String password) {
		
		if(this.username.equals(username)&&this.password.equals(password)) {
			return true;
		} else {
			return false;
		}
	}
}
