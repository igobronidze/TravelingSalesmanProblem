package ge.edu.tsu.tsp.server.solve.greedy;

import ge.edu.tsu.tsp.server.data.TSPInput;
import ge.edu.tsu.tsp.server.data.TSPOutput;
import ge.edu.tsu.tsp.server.data.TSPOutputResult;
import ge.edu.tsu.tsp.server.graph.Graph;
import ge.edu.tsu.tsp.server.solve.TSPSolver;
import ge.edu.tsu.tsp.server.solve.common.DisjointUnionSets;
import ge.edu.tsu.tsp.server.solve.common.Edge;
import ge.edu.tsu.tsp.server.solve.common.EdgeHelper;
import ge.edu.tsu.tsp.server.solve.ga.main.Tour;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class TSPGreedySolver implements TSPSolver {

    @Override
    public TSPOutput solve(Graph graph, TSPInput input) {
        TSPOutput tspOutput = new TSPOutput();
        long currMS = System.currentTimeMillis();
        List<Integer> indexes = getBestTour(EdgeHelper.getAllEdges(graph), graph.getNodeNumber());
        Tour tour = new Tour(indexes, graph);
        tspOutput.setGraph(graph);
        tspOutput.setTotalDistance(tour.getDistance());
        tspOutput.setRoute(tour.getNodeIndexes());
        tspOutput.setResult(TSPOutputResult.SUCCESS);
        tspOutput.setDuration(System.currentTimeMillis() - currMS);
        return tspOutput;
    }

    private List<Integer> getBestTour(Queue<Edge> edges, int nodeNumber) {
        List<Edge> tour = new ArrayList<>();
        Map<Integer, Integer> visited = new HashMap<>();
        for (int i = 1; i <= nodeNumber; i++) {
            visited.put(i, 0);
        }
        DisjointUnionSets disjointUnionSets = new DisjointUnionSets(nodeNumber + 1);
        for (Edge edge : edges) {
            if (visited.get(edge.getFirst()) == 2 || visited.get(edge.getSecond()) == 2) {
                continue;
            }
            if (visited.get(edge.getFirst()) == 1 && visited.get(edge.getSecond()) == 1 && disjointUnionSets.find(edge.getFirst()) == disjointUnionSets.find(edge.getSecond())
                    && tour.size() != nodeNumber - 1) {
                continue;
            }
            disjointUnionSets.union(edge.getFirst(), edge.getSecond());
            tour.add(edge);
            visited.put(edge.getFirst(), visited.get(edge.getFirst()) + 1);
            visited.put(edge.getSecond(), visited.get(edge.getSecond()) + 1);
        }
        return getIndexes(tour);
    }

    private List<Integer> getIndexes(List<Edge> edges) {
        List<Integer> indexes = new ArrayList<>();
        Edge edge = edges.get(0);
        indexes.add(edge.getFirst());
        indexes.add(edge.getSecond());
        while (indexes.size() != edges.size()) {
            for (Edge n : edges) {
                if (n != edge) {
                    if (indexes.get(indexes.size() - 1) == n.getFirst()) {
                        indexes.add(n.getSecond());
                        edge = n;
                        break;
                    }
                    if (indexes.get(indexes.size() - 1) == n.getSecond()) {
                        indexes.add(n.getFirst());
                        edge = n;
                        break;
                    }
                }
            }
        }
        return indexes;
    }
}
