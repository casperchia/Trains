
public class Trains {
	
	private static Routes routes;
	
	public static void main(String[] args) {
		// provide filenames for input and graph data
		routes = new Routes("input", "graph");
		routes.runCommands();
	}

}
