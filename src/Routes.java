import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Routes {
	
	private FileParser fp;
	private HashMap<String, HashMap> map;
	
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
		ArrayList<String> ls = new ArrayList(); 
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
	
}
