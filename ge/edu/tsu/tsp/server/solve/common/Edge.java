package ge.edu.tsu.tsp.server.solve.common;

public class Edge implements Comparable<Edge> {

	private int first;

	private int second;

	private int distance;

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public Edge(int first, int second, int distance) {
		this.first = first;
		this.second = second;
		this.distance = distance;
	}

	@Override
	public int compareTo(Edge edge) {
		return Integer.compare(distance, edge.getDistance());
	}
}
