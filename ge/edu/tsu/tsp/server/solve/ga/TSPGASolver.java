package ge.edu.tsu.tsp.server.solve.ga;

import ge.edu.tsu.tsp.server.data.TSPInput;
import ge.edu.tsu.tsp.server.data.TSPOutput;
import ge.edu.tsu.tsp.server.data.TSPOutputResult;
import ge.edu.tsu.tsp.server.graph.Graph;
import ge.edu.tsu.tsp.server.solve.TSPSolver;
import ge.edu.tsu.tsp.server.solve.ga.main.GA;
import ge.edu.tsu.tsp.server.solve.ga.main.Tour;

public class TSPGASolver implements TSPSolver {

	@Override
	public TSPOutput solve(Graph graph, TSPInput input) {
		TSPOutput tspOutput = new TSPOutput();
		long currMS = System.currentTimeMillis();
		Tour tour = GA.getBestTour(graph, input.getMaxIteration());
		tspOutput.setGraph(graph);
		tspOutput.setTotalDistance(tour.getDistance());
		tspOutput.setRoute(tour.getNodeIndexes());
		tspOutput.setResult(TSPOutputResult.SUCCESS);
		tspOutput.setDuration(System.currentTimeMillis() - currMS);
		return tspOutput;
	}
}
