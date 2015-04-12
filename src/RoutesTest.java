import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class RoutesTest {

	private static Routes routes;
	
	@Test
	public void getDistanceTest() {
		routes = new Routes("graph");
		assertEquals(5, routes.getDistance("A", "B"));
		assertEquals(4, routes.getDistance("B", "C"));
		assertEquals(8, routes.getDistance("C", "D"));
		assertEquals(8, routes.getDistance("D", "C"));
		assertEquals(6, routes.getDistance("D", "E"));
		assertEquals(5, routes.getDistance("A", "D"));
		assertEquals(2, routes.getDistance("C", "E"));
		assertEquals(3, routes.getDistance("E", "B"));
		assertEquals(7, routes.getDistance("A", "E"));
		
		System.out.println("getDistance() test successful!");
	}

	@Test
	public void getTotalDistanceTest() {
		routes = new Routes("graph");
		assertEquals(9, routes.getTotalDistance("A-B-C"));
		assertEquals(5, routes.getTotalDistance("A-D"));
		assertEquals(13, routes.getTotalDistance("A-D-C"));
		assertEquals(22, routes.getTotalDistance("A-E-B-C-D"));
		assertEquals(-1, routes.getTotalDistance("A-E-D"));

		System.out.println("getTotalDistance() test successful!");

	}
	
	@Test
	public void getStationsListTest() {
		routes = new Routes("graph");
		ArrayList<String> ls = routes.getStationsList();
		assertEquals(5, ls.size());
		assertTrue(ls.contains("A"));
		assertTrue(ls.contains("B"));
		assertTrue(ls.contains("C"));
		assertTrue(ls.contains("D"));
		assertTrue(ls.contains("E"));
		
		routes = new Routes("graph2");
		ls = routes.getStationsList();
		assertEquals(3, ls.size());
		assertTrue(ls.contains("A"));
		assertTrue(ls.contains("B"));
		assertTrue(ls.contains("C"));
		
		System.out.println("getStationsList() test successful!");

	}

}
