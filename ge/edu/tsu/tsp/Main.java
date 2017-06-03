package ge.edu.tsu.tsp;

import ge.edu.tsu.tsp.server.data.TSPInput;
import ge.edu.tsu.tsp.server.graph.Graph;
import ge.edu.tsu.tsp.server.solve.TSPDataCreator;
import ge.edu.tsu.tsp.server.solve.TSPSolverMethod;
import ge.edu.tsu.tsp.server.solve.branch_and_bound.TSPBranchAndBoundSolver;
import ge.edu.tsu.tsp.server.solve.brute_force.TSPBruteForceSolver;
import ge.edu.tsu.tsp.server.solve.ga.TSPGASolver;
import ge.edu.tsu.tsp.server.solve.greedy.TSPGreedySolver;
import ge.edu.tsu.tsp.server.solve.mst.TSPDuplicateMSTSolver;
import ge.edu.tsu.tsp.server.solve.nearestneighbor.TSPNearestForAllSolver;
import ge.edu.tsu.tsp.server.solve.nearestneighbor.TSPNearestNeighborSolver;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        TSPInput input = new TSPInput();
        input.setNodeNumber(10);
        input.setMinDistance(1);
        input.setMaxDistance(7);
        input.setTimeOut(60);
        input.setMaxIteration(100);
        Graph graph = TSPDataCreator.getRandomizeGraph(input);

        List<TSPSolverMethod> solverMethods = initSolvers();

        for (TSPSolverMethod solverMethod : solverMethods) {
            System.out.println(solverMethod.getDescription() + " " + solverMethod.getComplexity());
            solverMethod.getTspSolver().solve(graph, input).print(true);
            System.out.println("--------------------------------------------------------------------" + System.lineSeparator());
        }
    }

    private static List<TSPSolverMethod> initSolvers() {
        List<TSPSolverMethod> tspSolveMethods = new ArrayList<>();
        tspSolveMethods.add(new TSPSolverMethod("სრული გადარჩევა:", 1, "O((n-1)!)", new TSPBruteForceSolver()));
        tspSolveMethods.add(new TSPSolverMethod("გენეტიკური:", 2, "X", new TSPGASolver()));
        tspSolveMethods.add(new TSPSolverMethod("უახლოესი მეზობელი:", 3, "O(n^2)", new TSPNearestNeighborSolver()));
        tspSolveMethods.add(new TSPSolverMethod("უახლოესი მეზობელი ყველა წვეროსთვის:", 4, "O(n^3)", new TSPNearestForAllSolver()));
        tspSolveMethods.add(new TSPSolverMethod("ხარბი:", 5, "O(n^2log^2(n))", new TSPGreedySolver()));
        tspSolveMethods.add(new TSPSolverMethod("გაორმაგებული მინინიმალური დამფარავი ხე::", 6, "O(n^2log(n))", new TSPDuplicateMSTSolver()));
        tspSolveMethods.add(new TSPSolverMethod("შტოების და საზღვრების მეთოდი:", 7, "O(n^4)", new TSPBranchAndBoundSolver()));
        return tspSolveMethods;
    }
}
