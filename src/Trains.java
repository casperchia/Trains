
public class Trains {
	
	private static Routes routes;
	
	public static void main(String[] args) {
		routes = new Routes("graph");
		System.out.println("C-E: " + routes.getDistance("C", "E"));
		System.out.println("A-B-C: " + routes.getTotalDistance("A-B-C"));
		System.out.println("A-D: " + routes.getTotalDistance("A-D"));
		System.out.println("A-D-C: " + routes.getTotalDistance("A-D-C"));
		System.out.println("A-E-B-C-D: " + routes.getTotalDistance("A-E-B-C-D"));
		System.out.println("A-E-D: " + routes.getTotalDistance("A-E-D"));
	}

}
