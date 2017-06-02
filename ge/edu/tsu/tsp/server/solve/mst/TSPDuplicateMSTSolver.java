package ge.edu.tsu.tsp.server.solve.mst;

import ge.edu.tsu.tsp.server.data.TSPInput;
import ge.edu.tsu.tsp.server.data.TSPOutput;
import ge.edu.tsu.tsp.server.data.TSPOutputResult;
import ge.edu.tsu.tsp.server.graph.Graph;
import ge.edu.tsu.tsp.server.solve.TSPSolver;
import ge.edu.tsu.tsp.server.solve.common.MinimumSpanningTree;
import ge.edu.tsu.tsp.server.solve.ga.main.Tour;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TSPDuplicateMSTSolver implements TSPSolver {

	@Override
	public TSPOutput solve(Graph graph, TSPInput input) {
		TSPOutput tspOutput = new TSPOutput();
		long currMS = System.currentTimeMillis();
		MinimumSpanningTree minimumSpanningTree = new MinimumSpanningTree(graph);
		Tour tour = getTour(getDuplicatePath(minimumSpanningTree.getMST()), graph);
		tspOutput.setGraph(graph);
		tspOutput.setTotalDistance(tour.getDistance());
		tspOutput.setRoute(tour.getNodeIndexes());
		tspOutput.setResult(TSPOutputResult.SUCCESS);
		tspOutput.setDuration(System.currentTimeMillis() - currMS);
		return tspOutput;
	}

	private List<Integer> getDuplicatePath(Map<Integer, List<Integer>> mst) {
		boolean visited[] = new boolean[mst.keySet().size() + 1];
		List<Integer> path = new ArrayList<>();
		dfs(mst.keySet().iterator().next(), mst, visited, path);
		return path;
	}

	private void dfs(int index, Map<Integer, List<Integer>> mst, boolean visited[], List<Integer> path) {
		visited[index] = true;
		path.add(index);
		for (int x : mst.get(index)) {
			if (!visited[x]) {
				dfs(x, mst, visited, path);
			}
		}
		path.add(index);
	}

	private Tour getTour(List<Integer> duplicatePath, Graph graph) {
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
