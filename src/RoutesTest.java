import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;


public class RoutesTest {

	private static Routes routes;
	
	@Test
	public void getDistanceTest() {
		routes = new Routes("input", "graph");
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
		
	}

	@Test
	public void getTotalDistanceTest() {
		routes = new Routes("input", "graph");
		assertEquals(9, routes.getTotalDistance("ABC"));
		assertEquals(5, routes.getTotalDistance("AD"));
		assertEquals(13, routes.getTotalDistance("ADC"));
		assertEquals(22, routes.getTotalDistance("AEBCD"));
		assertEquals(-1, routes.getTotalDistance("AED"));
		assertEquals(-1, routes.getTotalDistance("AAA"));
		assertEquals(-1, routes.getTotalDistance("AFJ"));


	}
	
	@Test
	public void getStationsListTest() {
		routes = new Routes("input", "graph");
		ArrayList<String> ls = routes.getStationsList();
		assertEquals(5, ls.size());
		assertTrue(ls.contains("A"));
		assertTrue(ls.contains("B"));
		assertTrue(ls.contains("C"));
		assertTrue(ls.contains("D"));
		assertTrue(ls.contains("E"));
		
		routes = new Routes("input", "graph2");
		ls = routes.getStationsList();
		assertEquals(3, ls.size());
		assertTrue(ls.contains("A"));
		assertTrue(ls.contains("B"));
		assertTrue(ls.contains("C"));
		

	}
	
	@Test
	public void getMaxStopsTest() {
		routes = new Routes("input", "graph2");
		assertEquals(1, routes.getMaxStops("A", "C", 1));
		assertEquals(2, routes.getMaxStops("A", "C", 2));

		routes = new Routes("input", "graph");
		assertEquals(2, routes.getMaxStops("C", "C", 3));
		assertEquals(0, routes.getMaxStops("C", "C", 0));
		assertEquals(0, routes.getMaxStops("C", "C", 1));
		assertEquals(0, routes.getMaxStops("C", "C", -1));
		assertEquals(0, routes.getMaxStops("A", "C", 0));
		assertEquals(0, routes.getMaxStops("A", "C", 1));
		assertEquals(2, routes.getMaxStops("A", "C", 2));
		assertEquals(3, routes.getMaxStops("A", "C", 3));
		assertEquals(6, routes.getMaxStops("A", "C", 4));
		assertEquals(9, routes.getMaxStops("A", "C", 5));
		
		assertEquals(0, routes.getMaxStops("F", "A", 5));
		
		routes = new Routes("input", "graph3");
		assertEquals(0, routes.getMaxStops("A", "A", 1));
		assertEquals(0, routes.getMaxStops("A", "A", 2));
		assertEquals(1, routes.getMaxStops("A", "A", 3));
		assertEquals(1, routes.getMaxStops("A", "A", 4));
		assertEquals(1, routes.getMaxStops("A", "A", 5));
		assertEquals(2, routes.getMaxStops("A", "A", 6));

	}
	
	@Test
	public void canTravelToTest() {
		routes = new Routes("input", "graph");
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
		
	}
	
	@Test
	public void deepCopyTest() {
		routes = new Routes("input", "graph");
		HashMap<String, HashMap<String, Integer>> clone = routes.deepCopy(routes.map);
		assertEquals(clone, routes.map);
		
	}

	@Test
	public void getExactStopsTest() {
		routes = new Routes("input", "graph");
		assertEquals(3, routes.getExactStops("A", "C", 4));

		routes = new Routes("input", "graph3");
		assertEquals(0, routes.getExactStops("A", "A", 1));
		assertEquals(0, routes.getExactStops("A", "A", 2));
		assertEquals(1, routes.getExactStops("A", "A", 3));
		assertEquals(0, routes.getExactStops("A", "A", 4));
		assertEquals(0, routes.getExactStops("A", "A", 5));
		assertEquals(1, routes.getExactStops("A", "A", 6));

		assertEquals(0, routes.getExactStops("B", "A", 0));
		assertEquals(0, routes.getExactStops("B", "A", 1));
		assertEquals(1, routes.getExactStops("B", "A", 2));

		assertEquals(0, routes.getExactStops("A", "F", 0));
		assertEquals(0, routes.getExactStops("A", "F", 5));
		assertEquals(0, routes.getExactStops("G", "F", 5));
		
	}
	
	@Test
	public void getShortestTest() {
		routes = new Routes("input", "graph2");
		assertEquals(1, routes.getShortest("A", "B"));
		assertEquals(3, routes.getShortest("A", "C"));
		assertEquals(2, routes.getShortest("B", "C"));
		assertEquals(0, routes.getShortest("C", "A"));
		assertEquals(0, routes.getShortest("A", "A"));
		
		routes = new Routes("input", "graph3");
		assertEquals(6, routes.getShortest("A", "A"));
		assertEquals(1, routes.getShortest("A", "B"));
		assertEquals(3, routes.getShortest("A", "C"));
		
		routes = new Routes("input", "graph");
		assertEquals(9, routes.getShortest("A", "C"));
		assertEquals(9, routes.getShortest("B", "B"));
		assertEquals(8, routes.getShortest("C", "D"));
		assertEquals(0, routes.getShortest("A", "A"));
		assertEquals(0, routes.getShortest("A", "G"));
		assertEquals(0, routes.getShortest("G", "A"));
		assertEquals(0, routes.getShortest("G", "G"));

	}
	
	@Test
	public void getMaxDistancesTest() {
		routes = new Routes("input", "graph");
		assertEquals(0, routes.getMaxDistances("A", "C", 5));
		assertEquals(0, routes.getMaxDistances("A", "C", 9));
		assertEquals(1, routes.getMaxDistances("A", "C", 10));
		assertEquals(1, routes.getMaxDistances("A", "C", 13));
		assertEquals(2, routes.getMaxDistances("A", "C", 14));
		assertEquals(3, routes.getMaxDistances("A", "C", 15));
		assertEquals(7, routes.getMaxDistances("C", "C", 30));
		assertEquals(0, routes.getMaxDistances("F", "C", 30));
		assertEquals(0, routes.getMaxDistances("F", "F", 30));
		assertEquals(0, routes.getMaxDistances("F", "F", 0));
		assertEquals(0, routes.getMaxDistances("C", "C", -1));

		routes = new Routes("input", "graph3");
		assertEquals(0, routes.getMaxDistances("A", "A", 0));
		assertEquals(0, routes.getMaxDistances("A", "A", 1));
		assertEquals(0, routes.getMaxDistances("A", "A", 2));
		assertEquals(0, routes.getMaxDistances("A", "A", 3));
		assertEquals(0, routes.getMaxDistances("A", "A", 4));
		assertEquals(0, routes.getMaxDistances("A", "A", 5));
		assertEquals(0, routes.getMaxDistances("A", "A", 6));
		assertEquals(1, routes.getMaxDistances("A", "A", 7));
		assertEquals(1, routes.getMaxDistances("A", "A", 8));
		assertEquals(1, routes.getMaxDistances("A", "A", 9));
		assertEquals(1, routes.getMaxDistances("A", "A", 10));
		assertEquals(1, routes.getMaxDistances("A", "A", 11));
		assertEquals(1, routes.getMaxDistances("A", "A", 12));
		assertEquals(2, routes.getMaxDistances("A", "A", 13));
		assertEquals(2, routes.getMaxDistances("A", "A", 14));
		
	}

}
