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
		assertEquals(-1, routes.getDistance("B", "A"));
		assertEquals(-1, routes.getDistance("B", "F"));
		
		System.out.println("1 ) getDistance() test successful!");
	}

	@Test
	public void getTotalDistanceTest() {
		routes = new Routes("graph");
		assertEquals(9, routes.getTotalDistance("A-B-C"));
		assertEquals(5, routes.getTotalDistance("A-D"));
		assertEquals(13, routes.getTotalDistance("A-D-C"));
		assertEquals(22, routes.getTotalDistance("A-E-B-C-D"));
		assertEquals(-1, routes.getTotalDistance("A-E-D"));
		assertEquals(-1, routes.getTotalDistance("A-A-A"));
		assertEquals(-1, routes.getTotalDistance("A-F-J"));

		System.out.println("2 )getTotalDistance() test successful!");

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
		
		System.out.println("3 ) getStationsList() test successful!");

	}
	
	@Test
	public void getMaxStopsTest() {
		routes = new Routes("graph");
		assertEquals(2, routes.getMaxStops("C", "C", 3, routes.map));
		assertEquals(0, routes.getMaxStops("C", "C", 0, routes.map));
		assertEquals(0, routes.getMaxStops("C", "C", 1, routes.map));
		assertEquals(0, routes.getMaxStops("C", "C", -1, routes.map));
		assertEquals(0, routes.getMaxStops("A", "C", 0, routes.map));
		assertEquals(0, routes.getMaxStops("A", "C", 1, routes.map));
		assertEquals(2, routes.getMaxStops("A", "C", 2, routes.map));
		assertEquals(3, routes.getMaxStops("A", "C", 3, routes.map));
		assertEquals(4, routes.getMaxStops("A", "C", 4, routes.map));
		assertEquals(4, routes.getMaxStops("A", "C", 5, routes.map));

		System.out.println("4 ) getMaxStops() test successful!");
	}
	
	@Test
	public void canTravelToTest() {
		routes = new Routes("graph");
		ArrayList<String> ls = routes.canTravelTo(routes.map, "A");
		assertEquals(3, ls.size());
		assertTrue(ls.contains("B"));
		assertTrue(ls.contains("D"));
		assertTrue(ls.contains("E"));

		ls = routes.canTravelTo(routes.map, "F");
		assertEquals(0, ls.size());
		assertFalse(ls.contains("A"));
		assertFalse(ls.contains("B"));
		assertFalse(ls.contains("C"));
		
		System.out.println("5 ) canTravelToTest() test successful!");
	}

	

}
