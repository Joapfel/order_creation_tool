package de.uni_stuttgart.est.project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dao.*;

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
	}
	
	@Test
	void testOrderComponent() {
		OrderComponent orderComp = new BasicOrderComponent();
		orderComp = new FixRate(orderComp);
		orderComp = new Material(orderComp, "Eisenstangen", 5, 15);
		orderComp = new Machine(orderComp, "Lastenkran", 2, 150);
		orderComp = new WorkingHours(orderComp, "Junior", 8, 80);
		Order order = new Order("Bestellung", orderComp, "Order");
		
		assertTrue(orderComp.equals(order.getOrderComponent()));
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
	}

}
