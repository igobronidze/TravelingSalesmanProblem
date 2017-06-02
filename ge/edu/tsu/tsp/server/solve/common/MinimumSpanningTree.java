package ge.edu.tsu.tsp.server.solve.common;

import ge.edu.tsu.tsp.server.graph.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class MinimumSpanningTree {

	private Queue<Edge> allEdge;

	private int nodeNumber;

	public MinimumSpanningTree(Graph graph) {
		allEdge = EdgeHelper.getAllEdges(graph);
		nodeNumber = graph.getNodeNumber();
	}

	public Map<Integer, List<Integer>> getMST() {
		Map<Integer, List<Integer>> result = new HashMap<>();
		DisjointUnionSets disjointUnionSets = new DisjointUnionSets(nodeNumber + 1);
		for (Edge edge : allEdge) {
			int parentX = disjointUnionSets.find(edge.getFirst());
			int parentY = disjointUnionSets.find(edge.getSecond());
			if (parentX != parentY) {
				disjointUnionSets.union(edge.getFirst(), edge.getSecond());
				result.putIfAbsent(edge.getFirst(), new ArrayList<>());
				result.get(edge.getFirst()).add(edge.getSecond());
				result.putIfAbsent(edge.getSecond(), new ArrayList<>());
				result.get(edge.getSecond()).add(edge.getFirst());
			}
		}
		return result;
	}
}
