package ge.edu.tsu.tsp.server.graph;

import java.util.HashMap;
import java.util.Map;

public class Node {

    private int index;

    private Map<Integer, Connection> connections = new HashMap<>();

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Map<Integer, Connection> getConnections() {
        return connections;
    }

    public void setConnections(Map<Integer, Connection> connections) {
        this.connections = connections;
    }
}
