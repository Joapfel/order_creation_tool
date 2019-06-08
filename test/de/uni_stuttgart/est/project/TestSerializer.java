package de.uni_stuttgart.est.project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dao.*;
import main.Initialize;
import storage.Serializer;

/**
 * This class contains tests for the Serializer class.
 * 
 * @author Anne
 *
 */
class TestSerializer {

	@Test
	void testSaveUser() {
		Serializer storageObject = Initialize.getSerializer();
		User anton = new User("Anton", "sftx2", "normal");
		storageObject.saveUser(anton);
		assertTrue(storageObject.userExists(anton));
	}

	@Test
	void testSaveHRUser() {
		Serializer storageObject = Initialize.getSerializer();
		User balduin = new User("Balduin", "sth", "HR user");
		storageObject.saveUser(balduin);
		assertTrue(storageObject.userExists(balduin));
	}

	@Test
	void testDeleteUser() {
		Serializer storageObject = Initialize.getSerializer();
		User anton = new User("Anton", "sftx2", "normal");
		storageObject.saveUser(anton);
		storageObject.deleteUserByUsername("Anton");
		assertFalse(storageObject.userExists(anton));
	}

}
