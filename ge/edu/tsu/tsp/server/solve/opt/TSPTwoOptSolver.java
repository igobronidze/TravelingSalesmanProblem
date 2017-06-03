package ge.edu.tsu.tsp.server.solve.opt;

import ge.edu.tsu.tsp.server.data.TSPInput;
import ge.edu.tsu.tsp.server.data.TSPOutput;
import ge.edu.tsu.tsp.server.data.TSPOutputResult;
import ge.edu.tsu.tsp.server.graph.Graph;
import ge.edu.tsu.tsp.server.solve.TSPSolver;
import ge.edu.tsu.tsp.server.solve.ga.main.Tour;

import java.util.List;

public class TSPTwoOptSolver implements TSPSolver {

    @Override
    @SuppressWarnings("Duplicates")
    public TSPOutput solve(Graph graph, TSPInput input) {
        TSPOutput tspOutput = new TSPOutput();
        long currMS = System.currentTimeMillis();
        List<Integer> route = OptHelper.getRandomRoute(graph.getNodeNumber());
        while (true) {
            int gain = applyBestRemoving(route, graph);
            if (gain <= 0) {
                break;
            }
        }
        route.remove(route.size() - 1);
        Tour tour = new Tour(route, graph);
        tspOutput.setGraph(graph);
        tspOutput.setTotalDistance(tour.getDistance());
        tspOutput.setRoute(tour.getNodeIndexes());
        tspOutput.setResult(TSPOutputResult.SUCCESS);
        tspOutput.setDuration(System.currentTimeMillis() - currMS);
        return tspOutput;
    }

    private int applyBestRemoving(List<Integer> route, Graph graph) {
        int maxGain = Integer.MIN_VALUE;
        int x = -1, y = -1;
        for (int i = 0; i < route.size(); i++) {
            for (int  j = i + 2; j < route.size() - 1; j++) {
                int oldValue = graph.getNodes().get(route.get(i)).getConnections().get(route.get(i + 1)).getDistance() +
                        graph.getNodes().get(route.get(j)).getConnections().get(route.get(j + 1)).getDistance();
                int newValue = graph.getNodes().get(route.get(i)).getConnections().get(route.get(j)).getDistance() +
                        graph.getNodes().get(route.get(i + 1)).getConnections().get(route.get(j + 1)).getDistance();
                if (oldValue - newValue > maxGain) {
                    maxGain = oldValue - newValue;
                    x = i;
                    y = j;
                }
            }
        }
        if (maxGain > 0) {
            OptHelper.swap(x + 1, y, route);
        }
        return maxGain;
    }
}
