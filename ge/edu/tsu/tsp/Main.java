package ge.edu.tsu.tsp;

import ge.edu.tsu.tsp.server.data.TSPInput;
import ge.edu.tsu.tsp.server.graph.Graph;
import ge.edu.tsu.tsp.server.solve.ga.TSPGASolver;
import ge.edu.tsu.tsp.server.tsp_helper.TSPDataCreator;
import ge.edu.tsu.tsp.server.solve.brute_force.TSPBruteForceSolver;
import ge.edu.tsu.tsp.server.solve.TSPSolver;

public class Main {

    public static void main(String[] args) {
        TSPInput input = new TSPInput();
        input.setNodeNumber(7);
        input.setMinDistance(1);
        input.setMaxDistance(7);
        input.setTimeOut(60);
        input.setMaxIteration(1000);
        Graph graph = TSPDataCreator.getRandomizeGraph(input);
        TSPSolver solver1 = new TSPGASolver();
        TSPSolver solver2 = new TSPBruteForceSolver();
        System.out.println("გენეტიკური ალგორითმი:");
        solver1.solve(graph, input).print(false);
        System.out.println("--------------------------------------------------------------------" + System.lineSeparator());
        System.out.println("სრული გადარჩევა:");
        solver2.solve(graph, input).print(false);
        System.out.println("--------------------------------------------------------------------" + System.lineSeparator());
    }
}
