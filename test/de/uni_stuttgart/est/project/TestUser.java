package de.uni_stuttgart.est.project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dao.User;

/**
 * This class contains tests for the User class.
 * 
 * @author Anne
 *
 */
class TestUser {

	@Test
	void testUserName() {
		User anton = new User("Anton", "sftx2");
		String name = anton.getUsername();
		assertEquals("Anton", name);
	}

	@Test
	void testRoleDefault() {
		User anton = new User("Anton", "sftx2");
		String role = anton.getRole();
		assertEquals("normal", role);
	}

	@Test
	void testRoles() {
		User anton = new User("Anton", "sftx2", "normal");
		User bert = new User("Bert", "hali3", "HR user");
		User carl = new User("Carl", "hrlf4", "management user");
		String roleA = anton.getRole();
		String roleB = bert.getRole();
		String roleC = carl.getRole();
		assertEquals("normal", roleA);
		assertEquals("HR user", roleB);
		assertEquals("management user", roleC);
	}
	
	@Test
	void testSetRole() {
		User anton = new User("Anton", "sftx2");
		anton.setRole("HR user");
		String role = anton.getRole();
		assertEquals("HR user", role);
	}
	
}
