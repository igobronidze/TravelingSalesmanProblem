package ge.edu.tsu.tsp.server.solve.mst;

import ge.edu.tsu.tsp.server.graph.Graph;
import ge.edu.tsu.tsp.server.solve.ga.main.Tour;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MSTHelper {

	public static Tour getTourFromDuplicatePath(List<Integer> duplicatePath, Graph graph) {
		List<Integer> nodeIndexes = new ArrayList<>();
		Set<Integer> set = new HashSet<>();
		for (int x : duplicatePath) {
			if (!set.contains(x)) {
				set.add(x);
				nodeIndexes.add(x);
			}
		}
		return new Tour(nodeIndexes, graph);
	}
}
