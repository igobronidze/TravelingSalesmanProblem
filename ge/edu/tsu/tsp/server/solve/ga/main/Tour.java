package ge.edu.tsu.tsp.server.solve.ga.main;

import ge.edu.tsu.tsp.server.graph.Graph;
import ge.edu.tsu.tsp.server.solve.ga.helper.TourHelper;

import java.util.List;

public class Tour implements Comparable<Tour> {

	private List<Integer> nodeIndexes;

	private int distance;

	private double fitness;

	public Tour(List<Integer> nodeIndexes, Graph graph) {
		this.nodeIndexes = nodeIndexes;
		this.distance = TourHelper.getDistance(nodeIndexes, graph);
		this.fitness = 1.0 / distance;
	}

	public List<Integer> getNodeIndexes() {
		return nodeIndexes;
	}

	public int getDistance() {
		return distance;
	}

	public double getFitness() {
		return fitness;
	}

	@Override
	public int compareTo(Tour o) {
		return Double.compare(fitness, o.getFitness());
	}
}
