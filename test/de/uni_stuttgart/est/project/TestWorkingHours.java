package de.uni_stuttgart.est.project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dao.BasicOrderComponent;
import dao.FixRate;
import dao.Machine;
import dao.Material;
import dao.OrderComponent;
import dao.WorkingHours;

class TestWorkingHours {

	@Test
	void testPrice() {
		OrderComponent orderComp = new BasicOrderComponent();
		orderComp = new FixRate(orderComp);
		orderComp = new Material(orderComp, "Eisenstangen", 5, 15);
		orderComp = new Machine(orderComp, "Lastenkran", 2, 150);
		orderComp = new WorkingHours(orderComp, "Junior", 8, 80);

		assertEquals(1015, orderComp.price());
	}

	@Test
	void testSummary() {
		OrderComponent orderComp = new BasicOrderComponent();
		orderComp = new FixRate(orderComp);
		orderComp = new Material(orderComp, "Eisenstangen", 5, 15);
		orderComp = new Machine(orderComp, "Lastenkran", 2, 150);
		orderComp = new WorkingHours(orderComp, "Junior", 8, 80);

		assertEquals("Junior: 4 Stunde(n), 80 Euro/Stunde, Gesamt: 320 Euro", orderComp.summary());
	}

}
