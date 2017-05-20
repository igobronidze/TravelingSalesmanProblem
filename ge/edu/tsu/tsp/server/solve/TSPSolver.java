package ge.edu.tsu.tsp.server.solve;

import ge.edu.tsu.tsp.server.data.TSPInput;
import ge.edu.tsu.tsp.server.data.TSPOutput;
import ge.edu.tsu.tsp.server.graph.Graph;

public interface TSPSolver {

    TSPOutput solve(Graph graph, TSPInput input);
}
