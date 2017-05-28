package ge.edu.tsu.tsp.server.solve.ga.helper;

import ge.edu.tsu.tsp.server.graph.Graph;
import ge.edu.tsu.tsp.server.solve.ga.main.Tour;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TourHelper {

	public static int getDistance(List<Integer> indexes, Graph graph) {
		int distance = 0;
		for (int i = 0; i < indexes.size() - 1; i++) {
			distance += graph.getNodes().get(indexes.get(i)).getConnections().get(indexes.get(i + 1)).getDistance();
		}
		distance += graph.getNodes().get(indexes.get(indexes.size() - 1)).getConnections().get(indexes.get(0)).getDistance();
		return distance;
	}

	public static Tour getRandomTour(Graph graph) {
		List<Integer> indexes = new ArrayList<>();
		for (int i = 1; i <= graph.getNodeNumber(); i++) {
			indexes.add(i);
		}
		Collections.shuffle(indexes);
		return new Tour(indexes, graph);
	}
}
