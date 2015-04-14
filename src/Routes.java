import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Routes {
	
	private FileParser fp;
	public HashMap<String, HashMap<String, Integer>> map;
	
	public Routes(String inputFileName, String graphFileName) {
		fp = new FileParser(inputFileName, graphFileName);
		map = fp.getMap();
	}
	
	// Run commands from input file and prints results
	public void runCommands() {
		ArrayList<String> commands = fp.getCommands();
		char type;
		Pattern p;
		Matcher m;
		int answer = 0;
		for (int i = 0; i < commands.size(); i++) {
			String command = commands.get(i);
			type = command.charAt(0);
			switch (type) {
			case '-':
				p = Pattern.compile("([A-Z]{2,})");
				m = p.matcher(command);
				if (m.find()) {
					answer = getTotalDistance(m.group(0));
				}
				break;

			case '#':
				p = Pattern.compile("([A-Z]{1})([A-Z]{1})([0-9]+)");
				m = p.matcher(command);
				if (m.find()) {
					answer = getMaxStops(m.group(1), m.group(2), Integer.parseInt(m.group(3)));
				}
				break;
			
			case '=':
				p = Pattern.compile("([A-Z]{1})([A-Z]{1})([0-9]+)");
				m = p.matcher(command);
				if (m.find()) {
					answer = getExactStops(m.group(1), m.group(2), Integer.parseInt(m.group(3)));
				}
				break;

			case '&':
				p = Pattern.compile("([A-Z]{1})([A-Z]{1})");
				m = p.matcher(command);
				if (m.find()) {
					answer = getShortest(m.group(1), m.group(2));
				}
				break;

			case '%':
				p = Pattern.compile("([A-Z]{1})([A-Z]{1})([0-9]+)");
				m = p.matcher(command);
				if (m.find()) {
					answer = getMaxDistances(m.group(1), m.group(2), Integer.parseInt(m.group(3)));
				}
				break;

			default:
				System.out.println("No such command!");
				break;
			}
			
			if (answer > 0) {				
				System.out.println("Output #" + (i+1) + ": " + answer);
			} else {
				System.out.println("Output #" + (i+1) + ": NO SUCH ROUTE");
			}
		}
	}
	
	// Get distance of route from x to y
	public int getDistance(String x, String y) {
		if (map.get(x).get(y) == null) {
			//No such route!
			return -1;
		}
		return (int) map.get(x).get(y);
	}
	
	// Get total distance from route A-B-C-D..
	public int getTotalDistance(String route) {
		String[] stations = route.split("");

		int total = 0;
		int distance;
		for (int i = 0; i < stations.length - 1; i++) {
			distance = getDistance(stations[i], stations[i + 1]);
			if (distance == -1){
				// No such route!
				return -1;
			}
			total += distance;
		}
		return total;
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










