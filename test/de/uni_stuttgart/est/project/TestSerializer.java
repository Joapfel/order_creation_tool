package de.uni_stuttgart.est.project;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Scanner;

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

	@Test
	void testGetAllUsers() {
		Serializer storage = Initialize.getSerializer();
		ByteArrayOutputStream baoStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(baoStream);
		PrintStream oldStream = System.out;
		System.setOut(printStream);
		storage.printAllUsers();

		System.out.flush();
		System.setOut(oldStream);

		String fullUsers = baoStream.toString();

		fullUsers = fullUsers.replaceAll("Users", "");
		fullUsers = fullUsers.replaceAll("key :", "");
		fullUsers = fullUsers.replaceAll("value", "");
		fullUsers = fullUsers.replaceAll("[/\\s/]", "");
		String[] splittedUsers = fullUsers.split(":");

		LinkedList<User> userList = new LinkedList<>();
		for (int i = 1; i < splittedUsers.length; i = i + 2) {
			userList.add(storage.findUserByUsername(splittedUsers[i]));
		}

		assertEquals(userList, storage.getAllUsers());
	}

	@Test
	void testGetAllCustomers() {
		Serializer storage = Initialize.getSerializer();
		ByteArrayOutputStream baoStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(baoStream);
		PrintStream oldStream = System.out;
		System.setOut(printStream);
		storage.printAllCustomers();

		System.out.flush();
		System.setOut(oldStream);

		String fullCustomers = baoStream.toString();

		fullCustomers = fullCustomers.replaceAll("Customers", "");
		fullCustomers = fullCustomers.replaceAll("key :", "");
		fullCustomers = fullCustomers.replaceAll("value", "");
		fullCustomers = fullCustomers.replaceAll("[/\\s/]", "");
		String[] splittedCustomers = fullCustomers.split(":");

		LinkedList<Customer> customerList = new LinkedList<>();
		for (int i = 1; i < splittedCustomers.length; i = i + 2) {
			customerList.add(storage.findCustomersByCompanyName(splittedCustomers[i]));
		}

		assertEquals(customerList, storage.getAllCustomers());
	}

	@Test
	void testGetAllOrders() {
		Serializer storage = Initialize.getSerializer();
		ByteArrayOutputStream baoStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(baoStream);
		PrintStream oldStream = System.out;
		System.setOut(printStream);
		storage.printAllOrders();

		System.out.flush();
		System.setOut(oldStream);

		String fullOrders = baoStream.toString();
		fullOrders = fullOrders.replaceAll("Orders:", "");
		fullOrders = fullOrders.replaceAll("key :", "");
		fullOrders = fullOrders.replaceAll("value", "");
		fullOrders = fullOrders.replaceAll("Auftrag 1", "");
		fullOrders = fullOrders.replaceAll("[\nA-Za-z]", "");
		fullOrders = fullOrders.replaceAll("[/\\s]", "");
		String[] fullOrders2 = fullOrders.split(":");

		LinkedList<Order> orderList = new LinkedList<>();
		for (int i = 0; i < fullOrders2.length; i++) {
			int a = Integer.parseInt(fullOrders2[i]);
			orderList.add(storage.findOrderByID(a));
		}

		assertEquals(orderList, storage.getAllOrders());
	}

	@Test
	void testDeleteUsername() {
		Serializer storage = Initialize.getSerializer();
		User anton = new User("Anton", "1234");
		storage.saveUser(anton);
		storage.delete_Username(anton);

		assertEquals(anton.getUsername(), "Deleted");

	}

	@Test
	void testDeleteCustomername() {
		Serializer storage = Initialize.getSerializer();
		OrderComponent orderComp = new BasicOrderComponent();
		orderComp = new FixRate(orderComp);
		orderComp = new Material(orderComp, "Eisenstangen", 3, 20);
		orderComp = new Machine(orderComp, "Lastenkran", 1, 150);
		orderComp = new WorkingHours(orderComp, "Junior", 4, 80);
		Order order = new Order("Herstellung von Eisenkunst", orderComp, "text");
		Address address = new Address("Kunstweg", 1, 12345, "Kunststadt", "Deutschland");
		Customer kunde = new Customer("Kunstfirma", address, order);

		storage.saveCustomer(kunde);
		storage.delete_Customername(kunde);

		assertEquals(kunde.getCompanyName(), "Deleted");
		assertEquals(storage.findCustomersByCompanyName(kunde.getCompanyName()), null);
	}
}
