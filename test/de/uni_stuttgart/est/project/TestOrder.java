package de.uni_stuttgart.est.project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dao.*;

/**
 * This class contains tests for the classes Order, BasicOrderComponent and
 * OrderComponentDecorator.
 * 
 * @author Anne
 *
 */
class TestOrder {

	@Test
	void testOrderName() {
		OrderComponent orderComp = new BasicOrderComponent();
		orderComp = new FixRate(orderComp);
		orderComp = new Material(orderComp, "Eisenstangen", 5, 15);
		orderComp = new Machine(orderComp, "Lastenkran", 2, 150);
		orderComp = new WorkingHours(orderComp, "Junior", 8, 80);
		Order order = new Order("Bestellung", orderComp, "Order");

		assertEquals("Bestellung", order.getOrdername());
		
		order.setOrdername("Auftrag");
		assertEquals("Auftrag", order.getOrdername());
	}

	@Test
	void testOrderComponent() {
		OrderComponent orderComp = new BasicOrderComponent();
		orderComp = new FixRate(orderComp);
		orderComp = new Material(orderComp, "Eisenstangen", 5, 15);
		orderComp = new Machine(orderComp, "Lastenkran", 2, 150);
		orderComp = new WorkingHours(orderComp, "Junior", 8, 80);
		Order order = new Order("Bestellung", orderComp, "Order");
		
		OrderComponent orderComp2 = new BasicOrderComponent();
		orderComp2 = new FixRate(orderComp2);
		orderComp2 = new Material(orderComp2, "Eisenstangen", 5, 15);
		orderComp2 = new Machine(orderComp2, "Lastenkran", 2, 150);
		orderComp2 = new WorkingHours(orderComp2, "Junior", 8, 80);
		Order order1 = new Order("Bestellung", orderComp2, "Order");
		
		assertTrue(orderComp.equals(order.getOrderComponent()));
		assertFalse(orderComp2.equals(order.getOrderComponent()));
		
	}

	@Test
	void testOrderAsText() {
		String text = "Text";
		OrderComponent orderComp = new BasicOrderComponent();
		orderComp = new FixRate(orderComp);
		orderComp = new Material(orderComp, "Eisenstangen", 5, 15);
		orderComp = new Machine(orderComp, "Lastenkran", 2, 150);
		orderComp = new WorkingHours(orderComp, "Junior", 8, 80);
		Order order = new Order("Bestellung", orderComp, text);

		assertTrue(text.equals(order.getOrderAsText()));
		
		String text2 = "Auftrag";
		order.setOrderAsText(text2);
		assertTrue(text2.equals(order.getOrderAsText()));
	}

	@Test
	public void testPriceCalculation() {
		OrderComponent orderComp = new BasicOrderComponent();
		orderComp = new FixRate(orderComp);
		orderComp = new Material(orderComp, "Eisenstangen", 5, 15);
		orderComp = new Machine(orderComp, "Lastenkran", 2, 150);
		orderComp = new WorkingHours(orderComp, "Junior", 8, 80);
		double price = orderComp.price();
		assertEquals(1015, price);

	}
	
	@Test
	public void testOrderEuqals() {
		String text1 = "Order1";
		OrderComponent orderComp = new BasicOrderComponent();
		orderComp = new FixRate(orderComp);
		orderComp = new Material(orderComp, "Eisenstangen", 5, 15);
		orderComp = new Machine(orderComp, "Lastenkran", 2, 150);
		orderComp = new WorkingHours(orderComp, "Junior", 8, 80);
		Order order1 = new Order("Bestellung", orderComp, text1);
		
		String text2 = "weitere Bestellung";
		OrderComponent orderComp2 = new BasicOrderComponent();
		orderComp2 = new FixRate(orderComp2);
		orderComp2 = new Material(orderComp2, "Eisenstangen", 5, 15);
		orderComp2 = new Machine(orderComp2, "Lastenkran", 2, 150);
		orderComp2 = new WorkingHours(orderComp2, "Junior", 8, 80);
		Order order2 = new Order("Auftrag", orderComp2, text2);
		
		assertTrue(order1.equals(order1, order1));
		assertFalse(order1.equals(order1, order2));
	}

}
