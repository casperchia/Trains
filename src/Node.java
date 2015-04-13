
public class Node {
	private String station;
	private int distance;

	public Node(String station, int distance) {
		this.station = station;
		this.distance = distance;
	}

	public int getDistance() {
		return distance;
	}

	public String getStation() {
		return station;
	}

}
