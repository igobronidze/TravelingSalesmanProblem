package ge.edu.tsu.tsp.server.solve.opt;

import ge.edu.tsu.tsp.server.data.TSPInput;
import ge.edu.tsu.tsp.server.data.TSPOutput;
import ge.edu.tsu.tsp.server.data.TSPOutputResult;
import ge.edu.tsu.tsp.server.graph.Graph;
import ge.edu.tsu.tsp.server.solve.TSPSolver;
import ge.edu.tsu.tsp.server.solve.ga.main.Tour;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TSPThreeOptSolver implements TSPSolver {

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
        int x = -1, y = -1, z = -1;
        int method = -1;
        for (int i = 0; i < route.size(); i++) {
            for (int j = i + 2; j < route.size(); j++) {
                for (int k = j + 2; k < route.size() - 1; k++) {
                    int oldValue = graph.getNodes().get(route.get(i)).getConnections().get(route.get(i + 1)).getDistance() +
                            graph.getNodes().get(route.get(j)).getConnections().get(route.get(j + 1)).getDistance() +
                            graph.getNodes().get(route.get(k)).getConnections().get(route.get(k + 1)).getDistance();
                    int newValue1 = graph.getNodes().get(route.get(i + 1)).getConnections().get(route.get(k + 1)).getDistance() +
                            graph.getNodes().get(route.get(j)).getConnections().get(route.get(k)).getDistance() +
                            graph.getNodes().get(route.get(i)).getConnections().get(route.get(j + 1)).getDistance();
                    int newValue2 = graph.getNodes().get(route.get(i + 1)).getConnections().get(route.get(k)).getDistance() +
                            graph.getNodes().get(route.get(j)).getConnections().get(route.get(k + 1)).getDistance() +
                            graph.getNodes().get(route.get(i)).getConnections().get(route.get(j + 1)).getDistance();
                    if (oldValue - newValue1 > maxGain) {
                        maxGain = oldValue - newValue1;
                        x = i;
                        y = j;
                        z = k;
                        method = 1;
                    }
                    if (oldValue - newValue2 > maxGain) {
                        maxGain = oldValue - newValue2;
                        x = i;
                        y = j;
                        z = k;
                        method = 2;
                    }
                }
            }
        }
        if (maxGain > 0) {
            List<Integer> newRoute = new ArrayList<>();
            if (method == 1) {
                newRoute.addAll(route.subList(0, x + 1));
                newRoute.addAll(route.subList(y + 1, z + 1));
                List<Integer> tmp = new ArrayList<>(route.subList(x + 1, y + 1));
                Collections.reverse(tmp);
                newRoute.addAll(tmp);
                newRoute.addAll(route.subList(z + 1, route.size()));
            } else {
                newRoute.addAll(route.subList(0, x + 1));
                newRoute.addAll(route.subList(y + 1, z + 1));
                newRoute.addAll(route.subList(x + 1, y + 1));
                newRoute.addAll(route.subList(z + 1, route.size()));
            }
            for (int i = 0; i < route.size(); i++) {
                route.set(i, newRoute.get(i));
            }
        }
        return maxGain;
    }
}
