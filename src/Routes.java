import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;


public class Routes {
	
	private FileParser fp;
	public HashMap<String, HashMap<String, Integer>> map;
	
	public Routes(String fileName) {
		fp = new FileParser(fileName);
		map = fp.getMap();

	}
	
	public int getDistance(String x, String y) {
		if (map.get(x).get(y) == null) {
			//No such route!
			return -1;
		}
		return (int) map.get(x).get(y);
	}
	
	public int getTotalDistance(String route) {
		Scanner s = new Scanner(route).useDelimiter("\\s*-\\s*");
		ArrayList<String> ls = new ArrayList<String>(); 
		while (s.hasNext()) {
			ls.add(s.next());
		}

		int total = 0;
		int distance;
		for (int i = 0; i < ls.size() - 1; i++) {
			distance = getDistance(ls.get(i), ls.get(i + 1));
			if (distance == -1){
				// No such route!
				return -1;
			}
			total += distance;
		}
		return total;
	}
	
	public ArrayList<String> getStationsList() {
		ArrayList<String> ls = new ArrayList<String>();
		
		for (String key : map.keySet()) {
			if (!ls.contains(key)) {
				ls.add(key);
			}
			HashMap<String, Integer> innerMap = map.get(key);
			for(String innerKey : innerMap.keySet()) {
				if (!ls.contains(innerKey)) {
					ls.add(innerKey);
				}
			}
		}	
		return ls;
	}
	
	//Get number of trips from "start" to "end" with maximum of "max" stops.
	public int getMaxStops(String start, String end, int max) {
		if (max > 0) {
			int totalStops = 0;
			ArrayList<String> ls = canTravelTo(map, start);
			
			for (int i = 0; i < ls.size(); i++) {
				String currentStation = ls.get(i);
				
				//Check if destination reached
				if (currentStation.equals(end)) {
					totalStops += 1 + getMaxStops(currentStation, end, max - 1);
				} else {
					totalStops += getMaxStops(currentStation, end, max - 1);					
				}
				
			}
			return totalStops;
		} else {
			return 0;
		}
	}
	
	
	//Return list of stations that can be travelled to from "from"
	public ArrayList<String> canTravelTo(HashMap<String, HashMap<String, Integer>> map, String from) {
		HashMap<String, Integer> toMap = map.get(from);
		ArrayList<String> ls = new ArrayList<String>();
		if (toMap == null) {
//			System.out.println("No such starting station.");
			return ls;
		}
		for (String key : toMap.keySet()) {
			ls.add(key);
		}
		return ls;
	}
	
	//To be deleted!
	//Creates a deep copy of given 2D HashMap
	public HashMap<String, HashMap<String, Integer>> deepCopy (HashMap<String, HashMap<String, Integer>> map) {
		HashMap<String, HashMap<String, Integer>> clone = new HashMap<String, HashMap<String, Integer>>();
		for (String key : map.keySet()) {
			HashMap<String, Integer> innerMap = new HashMap<String, Integer>();;
			for (String innerKey : map.get(key).keySet()) {
				innerMap.put(innerKey, map.get(key).get(innerKey));
			}
			clone.put(key, innerMap);
		}		
		return clone;
	}

	public int getExactStops(String start, String end, int stops) {
		if (stops > 0) {
			int totalStops = 0;
			ArrayList<String> ls = canTravelTo(map, start);
			
			for (int i = 0; i < ls.size(); i++) {
				String currentStation = ls.get(i);
							
				//Check if destination reached
				if (currentStation.equals(end) && stops - 1 == 0) {
					totalStops += 1;
				} else {
					totalStops += getExactStops(currentStation, end, stops - 1);					
				}
				
			}
			return totalStops;
		} else {
			return 0;
		}
	}
	
	public int getShortest(String start, String end) {
		Comparator<Node> comparator = new NodeDistanceComparator();
		PriorityQueue<Node> queue = new PriorityQueue<Node>(comparator);
		ArrayList<String> explored = new ArrayList<String>();
		
		Node startNode = new Node(start, 0);
		explored.add(startNode.getStation());
		
		ArrayList<String> stationsToVisit = canTravelTo(map, start);
		for (int i = 0; i < stationsToVisit.size(); i++) {
			queue.add(new Node(stationsToVisit.get(i), getDistance(start, stationsToVisit.get(i))));
		}
		
		while (!queue.isEmpty()) {
			Node current = queue.remove();
			String currentStation = current.getStation();
			int currentDistance = current.getDistance();
			
			if (currentStation.equals(end)) {
				return currentDistance;
			}
			
			if (!explored.contains(currentStation)) {
				explored.add(currentStation);
				
				stationsToVisit = canTravelTo(map, currentStation);
				for (int i = 0; i < stationsToVisit.size(); i++) {
					String nextStation = stationsToVisit.get(i);
					queue.add(new Node(nextStation, currentDistance + getDistance(currentStation, nextStation)));
				}
			}
		}
	
		return 0;
	}
	
	public int getMaxDistances(String start, String end, int max) {
		if (max > 0) {
			int totalRoutes = 0;
			ArrayList<String> ls = canTravelTo(map, start);
			
			for (int i = 0; i < ls.size(); i++) {
				String currentStation = ls.get(i);
				int distanceRemaining = max - getDistance(start, currentStation);
				//Check if destination reached
				if (currentStation.equals(end) && distanceRemaining > 0) {
					totalRoutes += 1 + getMaxDistances(currentStation, end, distanceRemaining);
				} else {
					totalRoutes += getMaxDistances(currentStation, end, distanceRemaining);					
				}
				
			}
			return totalRoutes;
		} else {
			return 0;
		}
	}
	
}










