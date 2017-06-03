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
import ge.edu.tsu.tsp.server.solve.opt.TSPThreeOptSolver;
import ge.edu.tsu.tsp.server.solve.opt.TSPTwoOptSolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        TSPInput input = initInput();
        Graph graph = TSPDataCreator.getRandomizeGraph(input);
        List<TSPSolverMethod> solverMethods = initSolvers();
        for (int i = 0; i < solverMethods.size(); i++) {
            TSPSolverMethod solverMethod = solverMethods.get(i);
            System.out.println((i + 1) + ". " + solverMethod.getDescription() + " " + solverMethod.getComplexity());
        }
        System.out.println("0. დამთავრება");
        while (true) {
            int x = scanner.nextInt();
            if (x == 0) {
                break;
            }
            TSPSolverMethod solverMethod = solverMethods.get(x - 1);
            if (x == 2) {
                System.out.println("იტერაციის რაოდენობა:");
                input.setMaxIteration(scanner.nextInt());
            }
            System.out.println(solverMethod.getDescription() + " " + solverMethod.getComplexity());
            solverMethod.getTspSolver().solve(graph, input).print(true);
            System.out.println("--------------------------------------------------------------------" + System.lineSeparator());
        }
    }

    private static TSPInput initInput() {
        TSPInput input = new TSPInput();
        System.out.println("ქალაქების რაოდენობა:");
        input.setNodeNumber(scanner.nextInt());
        System.out.println("მინიმალური მანძილი:");
        input.setMinDistance(scanner.nextInt());
        System.out.println("მაქსიმალური მანძილი:");
        input.setMaxDistance(scanner.nextInt());
        System.out.println("დროის ლიმიტი(წმ):");
        input.setTimeOut(scanner.nextInt());
        return input;
    }

    private static List<TSPSolverMethod> initSolvers() {
        List<TSPSolverMethod> tspSolveMethods = new ArrayList<>();
        tspSolveMethods.add(new TSPSolverMethod("სრული გადარჩევა:", 1, "O((n-1)!)", new TSPBruteForceSolver()));
        tspSolveMethods.add(new TSPSolverMethod("გენეტიკური:", 2, "X", new TSPGASolver()));
        tspSolveMethods.add(new TSPSolverMethod("უახლოესი მეზობელი:", 3, "O(n^2)", new TSPNearestNeighborSolver()));
        tspSolveMethods.add(new TSPSolverMethod("უახლოესი მეზობელი ყველა წვეროსთვის:", 4, "O(n^3)", new TSPNearestForAllSolver()));
        tspSolveMethods.add(new TSPSolverMethod("გაორმაგებული მინინიმალური დამფარავი ხე::", 6, "O(n^2log(n))", new TSPDuplicateMSTSolver()));
        tspSolveMethods.add(new TSPSolverMethod("ხარბი:", 5, "O(n^2log^2(n))", new TSPGreedySolver()));
        tspSolveMethods.add(new TSPSolverMethod("შტოების და საზღვრების მეთოდი:", 7, "O(n^4)", new TSPBranchAndBoundSolver()));
        tspSolveMethods.add(new TSPSolverMethod("ორის ამოშლა:", 8, "X", new TSPTwoOptSolver()));
        tspSolveMethods.add(new TSPSolverMethod("სამის ამოშლა:", 9, "X", new TSPThreeOptSolver()));
        return tspSolveMethods;
    }
}
