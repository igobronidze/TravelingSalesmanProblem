package ge.edu.tsu.tsp.server.solve.greedy;

import ge.edu.tsu.tsp.server.data.TSPInput;
import ge.edu.tsu.tsp.server.data.TSPOutput;
import ge.edu.tsu.tsp.server.data.TSPOutputResult;
import ge.edu.tsu.tsp.server.graph.Graph;
import ge.edu.tsu.tsp.server.solve.TSPSolver;
import ge.edu.tsu.tsp.server.solve.ga.main.Tour;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class TSPGreedySolver implements TSPSolver {

    @Override
    public TSPOutput solve(Graph graph, TSPInput input) {
        TSPOutput tspOutput = new TSPOutput();
        long currMS = System.currentTimeMillis();
        List<Integer> indexes = getBestTour(getAllNeighbor(graph), graph.getNodeNumber());
        Tour tour = new Tour(indexes, graph);
        tspOutput.setGraph(graph);
        tspOutput.setTotalDistance(tour.getDistance());
        tspOutput.setRoute(tour.getNodeIndexes());
        tspOutput.setResult(TSPOutputResult.SUCCESS);
        tspOutput.setDuration(System.currentTimeMillis() - currMS);
        return tspOutput;
    }

    private Queue<Neighbor> getAllNeighbor(Graph graph) {
        Queue<Neighbor> neighbors = new PriorityQueue<>();
        for (int i = 1; i <= graph.getNodeNumber(); i++) {
            for (int j = i + 1; j <= graph.getNodeNumber(); j++) {
                neighbors.add(new Neighbor(i, j, graph.getNodes().get(i).getConnections().get(j).getDistance()));
            }
        }
        return neighbors;
    }

    private List<Integer> getBestTour(Queue<Neighbor> neighbors, int nodeNumber) {
        List<Neighbor> tour = new ArrayList<>();
        Map<Integer, Integer> visited = new HashMap<>();
        for (int i = 1; i <= nodeNumber; i++) {
            visited.put(i, 0);
        }
        DisjointUnionSets disjointUnionSets = new DisjointUnionSets(nodeNumber + 1);
        for (Neighbor neighbor : neighbors) {
            if (visited.get(neighbor.first) == 2 || visited.get(neighbor.second) == 2) {
                continue;
            }
            if (visited.get(neighbor.first) == 1 && visited.get(neighbor.second) == 1 && disjointUnionSets.find(neighbor.first) == disjointUnionSets.find(neighbor.second)
                    && tour.size() != nodeNumber - 1) {
                continue;
            }
            disjointUnionSets.union(neighbor.first, neighbor.second);
            tour.add(neighbor);
            visited.put(neighbor.first, visited.get(neighbor.first) + 1);
            visited.put(neighbor.second, visited.get(neighbor.second) + 1);
        }
        return getIndexes(tour);
    }

    private List<Integer> getIndexes(List<Neighbor> neighbors) {
        List<Integer> indexes = new ArrayList<>();
        Neighbor neighbor = neighbors.get(0);
        indexes.add(neighbor.first);
        indexes.add(neighbor.second);
        while (indexes.size() != neighbors.size()) {
            for (Neighbor n : neighbors) {
                if (n != neighbor) {
                    if (indexes.get(indexes.size() - 1) == n.first) {
                        indexes.add(n.second);
                        neighbor = n;
                        break;
                    }
                    if (indexes.get(indexes.size() - 1) == n.second) {
                        indexes.add(n.first);
                        neighbor = n;
                        break;
                    }
                }
            }
        }
        return indexes;
    }

    private class Neighbor implements Comparable<Neighbor> {

        private int first;

        private int second;

        private int distance;

        private Neighbor(int first, int second, int distance) {
            this.first = first;
            this.second = second;
            this.distance = distance;
        }

        @Override
        public int compareTo(Neighbor o) {
            return Integer.compare(distance, o.distance);
        }
    }
}
