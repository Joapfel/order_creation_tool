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

		assertEquals("Order setzt sich zusammen aus: \r\n" + 
				"\r\n" + 
				"Eisenstangen: 5 St�ck, 15.0 Euro/St�ck, Gesamt: 75.0 Euro\r\n" + 
				"Lastenkran: 2 Stunde(n), 150.0 Euro/Stunde, Gesamt: 300.0 Euro\r\n" + 
				"Junior: 8 Stunde(n), 80.0 Euro/Stunde, Gesamt: 640.0 Euro", orderComp.summary());
	}

}
