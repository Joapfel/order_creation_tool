package dao;

import java.io.*;
import java.util.*;

/**
 * Speichern von User-Elementen
 * Jeder User wird mit seinem username gemapped.
 * Der username darf aus diesem Grund nur einmalig im System vorkommen!
 * 
 * @author Philipp
 *
 */
public class User_Serializer implements Serializable, Storage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -749050638548344881L;
	
	private HashMap<String, User> nameMapping = new HashMap<String, User>();
	
	@Override
	public User findUserByUsername(String username) {	
		if(nameMapping.containsKey(username)) {
			return nameMapping.get(username);
		}
		else {
			return null;	
		}
	}
	

	@Override
	public void saveUser(User user) {
		nameMapping.put(user.getUsername(), user);
	}
	
	
	
	

}
