package ge.edu.tsu.tsp.server.solve.branch_and_bound;

import ge.edu.tsu.tsp.server.data.TSPInput;
import ge.edu.tsu.tsp.server.data.TSPOutput;
import ge.edu.tsu.tsp.server.data.TSPOutputResult;
import ge.edu.tsu.tsp.server.graph.Graph;
import ge.edu.tsu.tsp.server.solve.TSPSolver;
import ge.edu.tsu.tsp.server.solve.ga.main.Tour;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TSPBranchAndBoundSolver implements TSPSolver {

    @Override
    public TSPOutput solve(Graph graph, TSPInput input) {
        TSPOutput tspOutput = new TSPOutput();
        long currMS = System.currentTimeMillis();
        int n = graph.getNodeNumber();
        List<Integer> route = new ArrayList<>();
        route.add(1);
        Set<Integer> notVisited = new HashSet<>();
        for (int i = 2; i <= n; i++) {
            notVisited.add(i);
        }
        int lastNode = 1;
        int matrix[][] = getMatrixFromGraph(graph);
        int cost = applyReduction(matrix);
        for (int t = 0; t < n -1; t ++) {
            int nextNode = -1;
            int bestCost = Integer.MAX_VALUE;
            for (int next : notVisited) {
                int copy[][] = MatrixUtil.copyMatrix(matrix);
                removeNodes(lastNode, next, copy);
                int c = graph.getNodes().get(lastNode).getConnections().get(next).getDistance() +cost + applyReduction(copy);
                if (c < bestCost) {
                    bestCost = c;
                    nextNode = next;
                }
            }
            removeNodes(lastNode, nextNode, matrix);
            cost = graph.getNodes().get(lastNode).getConnections().get(nextNode).getDistance() + cost + applyReduction(matrix);
            route.add(nextNode);
            notVisited.remove(nextNode);
            lastNode = nextNode;
        }
        Tour tour = new Tour(route, graph);
        tspOutput.setGraph(graph);
        tspOutput.setTotalDistance(tour.getDistance());
        tspOutput.setRoute(tour.getNodeIndexes());
        tspOutput.setResult(TSPOutputResult.SUCCESS);
        tspOutput.setDuration(System.currentTimeMillis() - currMS);
        return tspOutput;
    }

    private void removeNodes(int x, int y, int[][] matrix) {
        for (int i = 1; i < matrix.length; i++) {
            matrix[x][i] = -1;
        }
        for (int i = 1; i < matrix.length; i++) {
            matrix[i][y] = -1;
        }
        matrix[y][x] = -1;
    }

    private int[][] getMatrixFromGraph(Graph graph) {
        int n = graph.getNodeNumber();
        int matrix[][] = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    matrix[i][j] = -1;
                } else {
                    matrix[i][j] = graph.getNodes().get(i).getConnections().get(j).getDistance();
                }
            }
        }
        return matrix;
    }

    @SuppressWarnings("Duplicates")
    private int applyReduction(int [][] matrix) {
        int n = matrix.length - 1;
        int cost = 0;
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j <= n; j++) {
                if (matrix[i][j] != -1) {
                    min = Math.min(matrix[i][j], min);
                }
            }
            if (min != 0) {
                cost += min;
                for (int j = 1; j <= n; j++) {
                    if (matrix[i][j] != -1) {
                        matrix[i][j] -= min;
                    }
                }
            }
        }
        for (int j = 1; j <= n; j++) {
            int min = Integer.MAX_VALUE;
            for (int i = 1; i <= n; i++) {
                if (matrix[i][j] != -1) {
                    min = Math.min(matrix[i][j], min);
                }
            }
            if (min != 0) {
                cost += min;
                for (int i = 1; i <= n; i++) {
                    if (matrix[i][j] != -1) {
                        matrix[i][j] -= min;
                    }
                }
            }
        }
        return cost;
    }
}
