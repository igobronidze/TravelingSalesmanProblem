package ge.edu.tsu.tsp;

import ge.edu.tsu.tsp.server.data.TSPInput;
import ge.edu.tsu.tsp.server.graph.Graph;
import ge.edu.tsu.tsp.server.solve.ga.TSPGASolver;
import ge.edu.tsu.tsp.server.solve.greedy.TSPGreedySolver;
import ge.edu.tsu.tsp.server.solve.mst.TSPDuplicateMSTSolver;
import ge.edu.tsu.tsp.server.solve.nearestneighbor.TSPNearestForAllSolver;
import ge.edu.tsu.tsp.server.solve.nearestneighbor.TSPNearestNeighborSolver;
import ge.edu.tsu.tsp.server.tsp_helper.TSPDataCreator;
import ge.edu.tsu.tsp.server.solve.brute_force.TSPBruteForceSolver;
import ge.edu.tsu.tsp.server.solve.TSPSolver;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main {

    private static Map<Integer, TSPSolver> solvers = new TreeMap<>();

    private static Map<Integer, String> descriptions = new HashMap<>();

    public static void main(String[] args) {
        TSPInput input = new TSPInput();
        input.setNodeNumber(8);
        input.setMinDistance(1);
        input.setMaxDistance(7);
        input.setTimeOut(60);
        input.setMaxIteration(1000);
        Graph graph = TSPDataCreator.getRandomizeGraph(input);

        initSolvers();

        for (int x : solvers.keySet()) {
            System.out.println(descriptions.get(x));
            solvers.get(x).solve(graph, input).print(false);
            System.out.println("--------------------------------------------------------------------" + System.lineSeparator());
        }
    }

    private static void initSolvers() {
        solvers.put(1, new TSPBruteForceSolver());
        descriptions.put(1, "სრული გადარჩევა:");
        solvers.put(2, new  TSPGASolver());
        descriptions.put(2, "გენეტიკური:");
        solvers.put(3, new  TSPNearestNeighborSolver());
        descriptions.put(3, "უახლოესი მეზობელი:");
        solvers.put(4, new  TSPNearestForAllSolver());
        descriptions.put(4, "უახლოესი მეზობელი ყველა წვეროსთვის:");
        solvers.put(5, new  TSPGreedySolver());
        descriptions.put(5, "ხარბი:");
        solvers.put(6, new TSPDuplicateMSTSolver());
        descriptions.put(6, "გაორმაგებული მინინიმალური დამფარავი ხე:");
    }
}
