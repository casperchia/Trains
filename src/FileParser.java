import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class FileParser {

	private String fileName;
	private Scanner s;
	
	public FileParser(String file) {
		fileName = file;
	}
	
	
	// Read data from file and constructs a graph
	public HashMap<String, HashMap<String, Integer>> getMap() {
		Path path = Paths.get(fileName);
		try {
			s = new Scanner(path).useDelimiter("\\s*,\\s*");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		HashMap<String, HashMap<String, Integer>> map = new HashMap<String, HashMap<String, Integer>>();
		while (s.hasNext()) {
			String route = s.next();
			String start = String.valueOf(route.charAt(0));
			String end = String.valueOf(route.charAt(1));
			int distance = Character.getNumericValue(route.charAt(2));

			//Insert values into hashmap
			if (map.containsKey(start)) {
				map.get(start).put(end, distance);
			}else{
				HashMap<String, Integer> destination = new HashMap<String, Integer>();
				destination.put(end, distance);
				map.put(start, destination);				
			}
		}

		return map;

	}
	

}
