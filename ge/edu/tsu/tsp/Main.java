package ge.edu.tsu.tsp;

import ge.edu.tsu.tsp.server.data.TSPInput;
import ge.edu.tsu.tsp.server.graph.Graph;
import ge.edu.tsu.tsp.server.solve.ga.TSPGASolver;
import ge.edu.tsu.tsp.server.solve.greedy.TSPGreedySolver;
import ge.edu.tsu.tsp.server.solve.nearestneighbor.TSPNearestForAllSolver;
import ge.edu.tsu.tsp.server.solve.nearestneighbor.TSPNearestNeighborSolver;
import ge.edu.tsu.tsp.server.tsp_helper.TSPDataCreator;
import ge.edu.tsu.tsp.server.solve.brute_force.TSPBruteForceSolver;
import ge.edu.tsu.tsp.server.solve.TSPSolver;

public class Main {

    public static void main(String[] args) {
        TSPInput input = new TSPInput();
        input.setNodeNumber(14);
        input.setMinDistance(1);
        input.setMaxDistance(7);
        input.setTimeOut(60);
        input.setMaxIteration(10000);
        Graph graph = TSPDataCreator.getRandomizeGraph(input);

        TSPSolver solver1 = new TSPGASolver();
        System.out.println("გენეტიკური:");
        solver1.solve(graph, input).print(false);
        System.out.println("--------------------------------------------------------------------" + System.lineSeparator());

        TSPSolver solver2 = new TSPBruteForceSolver();
        System.out.println("სრული გადარჩევა:");
        solver2.solve(graph, input).print(false);
        System.out.println("--------------------------------------------------------------------" + System.lineSeparator());
        System.out.println("უახლოესი მეზობელი:");

        TSPSolver solver3 = new TSPNearestNeighborSolver();
        solver3.solve(graph, input).print(false);
        System.out.println("--------------------------------------------------------------------" + System.lineSeparator());

        TSPSolver solver4 = new TSPNearestForAllSolver();
        System.out.println("უახლოესი მეზობელი ყველა წვეროსთვის:");
        solver4.solve(graph, input).print(false);
        System.out.println("--------------------------------------------------------------------" + System.lineSeparator());

        TSPSolver solver5 = new TSPGreedySolver();
        System.out.println("ხარბი:");
        solver5.solve(graph, input).print(false);
        System.out.println("--------------------------------------------------------------------" + System.lineSeparator());
    }
}
