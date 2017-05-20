package ge.edu.tsu.tsp;

import ge.edu.tsu.tsp.server.data.TSPInput;
import ge.edu.tsu.tsp.server.graph.Graph;
import ge.edu.tsu.tsp.server.tsp_helper.TSPDataCreator;
import ge.edu.tsu.tsp.server.solve.TSPBruteForceSolver;
import ge.edu.tsu.tsp.server.solve.TSPSolver;

public class Main {

    public static void main(String[] args) {
        TSPInput input = new TSPInput();
        input.setNodeNumber(12);
        input.setMinDistance(1);
        input.setMaxDistance(7);
        input.setTimeOut(60);
        Graph graph = TSPDataCreator.getRandomizeGraph(input);
        TSPSolver solver = new TSPBruteForceSolver();
        System.out.println(solver.solve(graph, input).getTotalDistance());
    }
}
