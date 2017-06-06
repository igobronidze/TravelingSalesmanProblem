package ge.edu.tsu.tsp.server.data;

import ge.edu.tsu.tsp.server.graph.Graph;

import java.util.ArrayList;
import java.util.List;

public class TSPOutput {

    private TSPOutputResult result;

    private List<Integer> route = new ArrayList<>();

    private int totalDistance;

    private long duration;

    private Graph graph;

    public TSPOutputResult getResult() {
        return result;
    }

    public void setResult(TSPOutputResult result) {
        this.result = result;
    }

    public List<Integer> getRoute() {
        return route;
    }

    public void setRoute(List<Integer> route) {
        this.route = route;
    }

    public int getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(int totalDistance) {
        this.totalDistance = totalDistance;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public void print(boolean fullPath) {
        System.out.println("შედეგი: " + result);
        System.out.println("დრო: " + duration + "მწმ");
        if (result != TSPOutputResult.SUCCESS) {
            return;
        }
        if (fullPath) {
            int indexOfOne = 0;
            for (int i = 0; i < route.size(); i++) {
                if (route.get(i) == 1) {
                    indexOfOne = i;
                    break;
                }
            }
            for (int i = indexOfOne; i < route.size() - 1; i++) {
                System.out.println(route.get(i) + " -> " + route.get(i + 1) + "   " + graph.getNodes().get(route.get(i)).getConnections().get(route.get(i + 1)).getDistance());
            }
            int j = route.size() - 1;
            System.out.println(route.get(j) + " -> " + route.get(0) + "   " + graph.getNodes().get(route.get(j)).getConnections().get(route.get(0)).getDistance());
            for (int i = 0; i < indexOfOne; i++) {
                System.out.println(route.get(i) + " -> " + route.get(i + 1) + "   " + graph.getNodes().get(route.get(i)).getConnections().get(route.get(i + 1)).getDistance());
            }
        }
        System.out.println("სრული მანძილი: " + totalDistance);
    }
}
