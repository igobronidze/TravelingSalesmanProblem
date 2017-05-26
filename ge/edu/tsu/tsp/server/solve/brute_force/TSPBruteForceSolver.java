package ge.edu.tsu.tsp.server.solve.brute_force;

import ge.edu.tsu.tsp.server.data.TSPInput;
import ge.edu.tsu.tsp.server.data.TSPOutput;
import ge.edu.tsu.tsp.server.data.TSPOutputResult;
import ge.edu.tsu.tsp.server.graph.Graph;
import ge.edu.tsu.tsp.server.solve.TSPSolver;

import java.util.ArrayList;
import java.util.List;

public class TSPBruteForceSolver implements TSPSolver {

    private long currMS = 0;

    private TSPOutput tspOutput = new TSPOutput();

    @Override
    public TSPOutput solve(Graph graph, TSPInput input) {
        currMS = System.currentTimeMillis();
        List<Integer> indexes = new ArrayList<>();
        for (int i = 2; i <= graph.getNodeNumber(); i++) {
            indexes.add(i);
        }
        List<Integer> route = new ArrayList<>();
        route.add(1);
        tspOutput.setTotalDistance(Integer.MAX_VALUE);
        applyRecursion(graph, input, 0, route, indexes, 1);
        tspOutput.setResult(TSPOutputResult.SUCCESS);
        tspOutput.setDuration(System.currentTimeMillis() - currMS);
        return tspOutput;
    }

    private void applyRecursion(Graph graph, TSPInput input, int currDistance, List<Integer> existedRoute, List<Integer> remainingIndexes, int lastIndex) {
        if (tspOutput.getResult() == TSPOutputResult.TIMEOUT) {
            return;
        }
        if (System.currentTimeMillis() - currMS >= input.getTimeOut() * 1000) {
            tspOutput.setResult(TSPOutputResult.TIMEOUT);
            return;
        }
        if (remainingIndexes.isEmpty()) {
            int distance = currDistance + graph.getNodes().get(lastIndex).getConnections().get(1).getDistance();
            if (distance < tspOutput.getTotalDistance()) {
                tspOutput.setTotalDistance(distance);
                tspOutput.setRoute(existedRoute);
            }
            return;
        }
        for (Integer i : remainingIndexes) {
            currDistance += graph.getNodes().get(lastIndex).getConnections().get(i).getDistance();
            List<Integer> newRoute = new ArrayList<>(existedRoute);
            newRoute.add(i);
            List<Integer> newRemaining = new ArrayList<>(remainingIndexes);
            newRemaining.remove(i);
            applyRecursion(graph, input, currDistance, newRoute, newRemaining, i);
        }
    }
}
