package ge.edu.tsu.tsp.server.solve.common;

import ge.edu.tsu.tsp.server.graph.Graph;

import java.util.PriorityQueue;
import java.util.Queue;

public class EdgeHelper {

	public static Queue<Edge> getAllEdges(Graph graph) {
		Queue<Edge> edges = new PriorityQueue<>();
		for (int i = 1; i <= graph.getNodeNumber(); i++) {
			for (int j = i + 1; j <= graph.getNodeNumber(); j++) {
				edges.add(new Edge(i, j, graph.getNodes().get(i).getConnections().get(j).getDistance()));
			}
		}
		return edges;
	}
}
