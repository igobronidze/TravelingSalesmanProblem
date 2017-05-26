package ge.edu.tsu.tsp.server.data;

import java.util.ArrayList;
import java.util.List;

public class TSPOutput {

    private TSPOutputResult result;

    private List<Integer> route = new ArrayList<>();

    private int totalDistance;

    private long duration;

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
}
