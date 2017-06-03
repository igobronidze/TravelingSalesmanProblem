package ge.edu.tsu.tsp.server.solve.mst;

import ge.edu.tsu.tsp.server.data.TSPInput;
import ge.edu.tsu.tsp.server.data.TSPOutput;
import ge.edu.tsu.tsp.server.data.TSPOutputResult;
import ge.edu.tsu.tsp.server.graph.Graph;
import ge.edu.tsu.tsp.server.solve.TSPSolver;
import ge.edu.tsu.tsp.server.solve.common.MinimumSpanningTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TSPChristofidesSolver implements TSPSolver {

	@Override
	public TSPOutput solve(Graph graph, TSPInput input) {
		TSPOutput tspOutput = new TSPOutput();
		long currMS = System.currentTimeMillis();
		MinimumSpanningTree minimumSpanningTree = new MinimumSpanningTree(graph);
		Map<Integer, List<Integer>> mst = minimumSpanningTree.getMST();
		List<Integer> oddDegreeEdges = getOddDegreeEdges(mst);

		tspOutput.setGraph(graph);
//		tspOutput.setTotalDistance(tour.getDistance());
//		tspOutput.setRoute(tour.getNodeIndexes());
		tspOutput.setResult(TSPOutputResult.SUCCESS);
		tspOutput.setDuration(System.currentTimeMillis() - currMS);
		return tspOutput;
	}

	private List<Integer> getOddDegreeEdges(Map<Integer, List<Integer>> mst) {
		List<Integer> result = new ArrayList<>();
		for (int x : mst.keySet()) {
			if (mst.get(x).size() % 2 == 1) {
				result.add(x);
			}
		}
		return result;
	}
}
