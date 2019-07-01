package de.uni_stuttgart.est.project;

import static org.junit.jupiter.api.Assertions.*;

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
		//storage.printAllUsers();
		System.out.println("User nach getAll" + storage.getAllUsers());
		
		LinkedList<User> userList = new LinkedList<>();
		userList.add(storage.findUserByUsername(""));
		userList.add(storage.findUserByUsername("Balduin"));
		userList.add(storage.findUserByUsername("Anton"));
		userList.add(storage.findUserByUsername("Testuser"));
		userList.add(storage.findUserByUsername("Carl"));
		userList.add(storage.findUserByUsername("TestuserHR"));
		userList.add(storage.findUserByUsername("Test3"));
		userList.add(storage.findUserByUsername("Test2"));
		
		System.out.println("Users nach userList" + userList);
		
		assertEquals(userList, storage.getAllUsers());
	}
	
	@Test 
	void testGetAllCostumers() {
		Serializer storage = Initialize.getSerializer();
		//storage.printAllCustomers();
		System.out.println("Customers nach getAll:" + storage.getAllCustomers());
		
		LinkedList<Customer> customerList = new LinkedList<>();
		customerList.add(storage.findCustomersByCompanyName("bluib"));
		customerList.add(storage.findCustomersByCompanyName("ddddd"));
		customerList.add(storage.findCustomersByCompanyName("ghesgzgbiuzdfv"));
		customerList.add(storage.findCustomersByCompanyName("testtest"));
		customerList.add(storage.findCustomersByCompanyName("fefwefwf"));
		customerList.add(storage.findCustomersByCompanyName("Kunstfirma"));
		customerList.add(storage.findCustomersByCompanyName("dewdwdwedw"));
		customerList.add(storage.findCustomersByCompanyName("3wdd"));
		customerList.add(storage.findCustomersByCompanyName("rrrrr"));
		customerList.add(storage.findCustomersByCompanyName("eeee"));
		customerList.add(storage.findCustomersByCompanyName("ffff"));
		
		System.out.println("Customers nach customerList" + customerList);
		
		assertEquals(customerList, storage.getAllCustomers());
	}
	
	@Test
	void testGetAllOrders() {
		Serializer storage = Initialize.getSerializer();
		//storage.printAllOrders();
		LinkedList<Order> orderList = new LinkedList<>();
		orderList.add(storage.findOrderByID(1));
		orderList.add(storage.findOrderByID(33));
		orderList.add(storage.findOrderByID(65));
		orderList.add(storage.findOrderByID(2));
		orderList.add(storage.findOrderByID(5));
		orderList.add(storage.findOrderByID(37));
		orderList.add(storage.findOrderByID(69));
		orderList.add(storage.findOrderByID(41));
		orderList.add(storage.findOrderByID(73));
		orderList.add(storage.findOrderByID(11));
		orderList.add(storage.findOrderByID(12));
		orderList.add(storage.findOrderByID(13));
		orderList.add(storage.findOrderByID(45));
		orderList.add(storage.findOrderByID(77));
		orderList.add(storage.findOrderByID(15));
		orderList.add(storage.findOrderByID(49));
		orderList.add(storage.findOrderByID(53));
		orderList.add(storage.findOrderByID(57));
		orderList.add(storage.findOrderByID(61));
		
		System.out.println("Orders nach getAll" + storage.getAllOrders());
		System.out.println("Orders nach printAll" + orderList);
		
		assertEquals(orderList, storage.getAllOrders());
		
	}
	
//	@Test
//	void testGetOrders() {
//		Serializer storage = Initialize.getSerializer();
//		Scanner scan = new Scanner(System.in);
//		String input = scan.nextLine();
//		storage.printAllOrders();
//		scan.close();
//		System.out.println("Gescannt:" + input);
//		
//	}
	
}
