package ge.edu.tsu.tsp.server.solve.nearestneighbor;

import ge.edu.tsu.tsp.server.data.TSPInput;
import ge.edu.tsu.tsp.server.data.TSPOutput;
import ge.edu.tsu.tsp.server.data.TSPOutputResult;
import ge.edu.tsu.tsp.server.graph.Graph;
import ge.edu.tsu.tsp.server.solve.TSPSolver;
import ge.edu.tsu.tsp.server.solve.ga.main.Tour;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class TSPNearestNeighborSolver implements TSPSolver {

    @Override
    public TSPOutput solve(Graph graph, TSPInput input) {
        TSPOutput tspOutput = new TSPOutput();
        long currMS = System.currentTimeMillis();
        Tour tour = getBestTour(graph);
        tspOutput.setGraph(graph);
        tspOutput.setTotalDistance(tour.getDistance());
        tspOutput.setRoute(tour.getNodeIndexes());
        tspOutput.setResult(TSPOutputResult.SUCCESS);
        tspOutput.setDuration(System.currentTimeMillis() - currMS);
        return tspOutput;
    }

    private Tour getBestTour(Graph graph) {
        Set<Integer> unvisited = new HashSet<>();
        for (int i = 1; i <= graph.getNodeNumber(); i++) {
            unvisited.add(i);
        }
        Random ran = new Random();
        int last = ran.nextInt(graph.getNodeNumber()) + 1;
        unvisited.remove(last);
        List<Integer> indexes = new ArrayList<>();
        indexes.add(last);
        while (!unvisited.isEmpty()) {
            int nearest = 0;
            for (int i : unvisited) {
                if (nearest == 0) {
                    nearest = i;
                } else {
                    if (graph.getNodes().get(last).getConnections().get(i).getDistance() <
                            graph.getNodes().get(last).getConnections().get(nearest).getDistance()) {
                        nearest = i;
                    }
                }
            }
            indexes.add(nearest);
            unvisited.remove(nearest);
        }
        return new Tour(indexes, graph);
    }
}
