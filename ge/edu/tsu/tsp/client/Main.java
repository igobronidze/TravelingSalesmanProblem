package ge.edu.tsu.tsp.client;

import ge.edu.tsu.tsp.server.data.TSPInput;
import ge.edu.tsu.tsp.server.graph.Graph;
import ge.edu.tsu.tsp.server.solve.TSPDataCreator;
import ge.edu.tsu.tsp.server.solve.TSPSolverMethod;
import ge.edu.tsu.tsp.server.solve.branch_and_bound.TSPBranchAndBoundSolver;
import ge.edu.tsu.tsp.server.solve.brute_force.TSPBruteForceSolver;
import ge.edu.tsu.tsp.server.solve.ga.TSPGASolver;
import ge.edu.tsu.tsp.server.solve.ga.configuration.GAParams;
import ge.edu.tsu.tsp.server.solve.ga.operation.selection.SelectionType;
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

    private static boolean printFullPath = false;

    private static boolean random = false;

    public static void main(String[] args) {
        TSPInput input = initInput();
        Graph graph;
        if (random) {
            graph = TSPDataCreator.getRandomizeGraph(input);
        } else {
            int[][] matrix = getMatrix();
            input.setNodeNumber(matrix.length);
            graph = TSPDataCreator.getGraphFromMatrix(matrix);
        }
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
            solverMethod.getTspSolver().solve(graph, input).print(printFullPath);
            System.out.println("--------------------------------------------------------------------" + System.lineSeparator());
        }
    }

    private static TSPInput initInput() {
        TSPInput input = new TSPInput();
        System.out.println("შემთხვევით რიცხვები:(true/false)");
        random = scanner.nextBoolean();
        if (random) {
            System.out.println("ქალაქების რაოდენობა:");
            input.setNodeNumber(scanner.nextInt());
            System.out.println("მინიმალური მანძილი:");
            input.setMinDistance(scanner.nextInt());
            System.out.println("მაქსიმალური მანძილი:");
            input.setMaxDistance(scanner.nextInt());
        }
        System.out.println("დროის ლიმიტი(წმ):");
        input.setTimeOut(scanner.nextInt());
        System.out.println("სრული გზის დაბეჭდვა:(true/false)");
        printFullPath = Boolean.valueOf(scanner.next());
        return input;
    }

    private static List<TSPSolverMethod> initSolvers() {
        List<TSPSolverMethod> tspSolveMethods = new ArrayList<>();
        tspSolveMethods.add(new TSPSolverMethod("სრული გადარჩევა:", 1, "O((n-1)!)", new TSPBruteForceSolver()));
        tspSolveMethods.add(new TSPSolverMethod("გენეტიკური:", 2, "...", new TSPGASolver()));
        tspSolveMethods.add(new TSPSolverMethod("უახლოესი მეზობელი:", 3, "O(n^2)", new TSPNearestNeighborSolver()));
        tspSolveMethods.add(new TSPSolverMethod("უახლოესი მეზობელი ყველა წვეროსთვის:", 4, "O(n^3)", new TSPNearestForAllSolver()));
        tspSolveMethods.add(new TSPSolverMethod("გაორმაგებული მინინიმალური დამფარავი ხე:", 6, "O(n^2log(n))", new TSPDuplicateMSTSolver()));
        tspSolveMethods.add(new TSPSolverMethod("ხარბი:", 5, "O(n^2log(n))", new TSPGreedySolver()));
        tspSolveMethods.add(new TSPSolverMethod("შტოების და საზღვრების მეთოდი:", 7, "O(n^4)", new TSPBranchAndBoundSolver()));
        tspSolveMethods.add(new TSPSolverMethod("ორის ამოშლა:", 8, "...", new TSPTwoOptSolver()));
        tspSolveMethods.add(new TSPSolverMethod("სამის ამოშლა:", 9, "...", new TSPThreeOptSolver()));
        return tspSolveMethods;
    }

    private static int[][] getMatrix() {
        return new int[][]{
                {0, 76, 1000, 38, 51, 42, 19, 80},
                {42, 0, 49, 26, 78, 52, 39, 87},
                {48, 1000, 0, 36, 53, 44, 68, 61},
                {72, 31, 29, 0, 42, 49, 50, 38},
                {30, 52, 38, 47, 0, 64, 75, 82},
                {66, 51, 83, 51, 52, 0, 37, 71},
                {77, 62, 93, 54, 69, 38, 0, 26},
                {42, 58, 66, 76, 41, 52, 83, 0}
        };
    }
}
