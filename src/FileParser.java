import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class FileParser {

	private String inputFileName, graphFileName;
	private Scanner s;
	
	public FileParser(String inputFile, String graphFile) {
		inputFileName = inputFile;
		graphFileName = graphFile;
	}
	
	// Returns an array of commands given in the input file
	public ArrayList<String> getCommands() {
		Path path = Paths.get(inputFileName);
		ArrayList<String> commands = new ArrayList<String>();
		try {
			s = new Scanner(path);
			while (s.hasNextLine()) {
				commands.add(s.nextLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return commands;
	}
	
	// Read data from file and constructs a graph
	public HashMap<String, HashMap<String, Integer>> getMap() {
		Path path = Paths.get(graphFileName);
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
