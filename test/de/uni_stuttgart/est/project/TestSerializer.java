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
	
	@Test
	void testUserStorageWithId() {
		Serializer storageObject = Initialize.getSerializer();
		User carl = new User("Carl", "shjeo", "hi");
		storageObject.saveUser(carl);
		assertTrue(carl.equals(storageObject.findUserByUsername("Carl")));
	}
	
	@Test
	public void testOrderStorage() {
		Serializer storageObject = Initialize.getSerializer();
		OrderComponent orderComp = new BasicOrderComponent();
		orderComp = new FixRate(orderComp);
		orderComp = new Material(orderComp, "Eisenstangen", 5, 15);
		orderComp = new Machine(orderComp, "Lastenkran", 2, 150);
		orderComp = new WorkingHours(orderComp, "Junior", 8, 80);
		Order order = new Order("Herstellung von Eisenkunst", orderComp, "text");

		storageObject.saveOrder(order);

		assertTrue(order.equals(storageObject.findOrderByID(order.getOrderID())));

	}

	@Test
	public void testOrderStorageWrongID() {
		Serializer storageObject = new Serializer();
		OrderComponent orderComp = new BasicOrderComponent();
		orderComp = new FixRate(orderComp);
		orderComp = new Material(orderComp, "Eisenstangen", 5, 15);
		orderComp = new Machine(orderComp, "Lastenkran", 2, 150);
		orderComp = new WorkingHours(orderComp, "Junior", 8, 80);
		Order order = new Order("Herstellung von Eisenkunst", orderComp, "text");

		storageObject.saveOrder(order);

		Order order1 = storageObject.findOrderByID(2);
		// should fail
		assertFalse(order.equals(order1));
	}

	@Test
	public void testCustomerStorage() {
		Serializer storageObject = Initialize.getSerializer();
		OrderComponent orderComp = new BasicOrderComponent();
		orderComp = new FixRate(orderComp);
		orderComp = new Material(orderComp, "Eisenstangen", 3, 20);
		orderComp = new Machine(orderComp, "Lastenkran", 1, 150);
		orderComp = new WorkingHours(orderComp, "Junior", 4, 80);
		Order order = new Order("Herstellung von Eisenkunst", orderComp, "text");
		Address address = new Address("Kunstweg", 1, 12345, "Kunststadt", "Deutschland");
		Customer kunde = new Customer("Kunstfirma", address, order);

		storageObject.saveCustomer(kunde);

		assertTrue(kunde.equals(storageObject.findCustomersByCompanyName("Kunstfirma")));
	}
	
	
	@Test
	void testCustomerStorageWithId() {
		Serializer storageObject = Initialize.getSerializer();
		OrderComponent orderComp = new BasicOrderComponent();
		orderComp = new FixRate(orderComp);
		orderComp = new Material(orderComp, "Eisenstangen", 3, 20);
		orderComp = new Machine(orderComp, "Lastenkran", 1, 150);
		orderComp = new WorkingHours(orderComp, "Junior", 4, 80);
		Order order = new Order("Herstellung von Eisenkunst", orderComp, "text");
		Address address = new Address("Kunstweg", 1, 12345, "Kunststadt", "Deutschland");
		Customer kunde = new Customer("Kunstfirma", address, order);

		storageObject.saveCustomer(kunde);

		assertTrue(kunde.equals(storageObject.findCustomerById(kunde.getCustomerID())));
		
	}
	

}
