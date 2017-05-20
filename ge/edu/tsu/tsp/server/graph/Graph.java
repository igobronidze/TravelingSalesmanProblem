package ge.edu.tsu.tsp.server.graph;

import java.util.HashMap;
import java.util.Map;

public class Graph {

    private int nodeNumber;

    private Map<Integer, Node> nodes = new HashMap<>();

    public int getNodeNumber() {
        return nodeNumber;
    }

    public void setNodeNumber(int nodeNumber) {
        this.nodeNumber = nodeNumber;
    }

    public Map<Integer, Node> getNodes() {
        return nodes;
    }

    public void setNodes(Map<Integer, Node> nodes) {
        this.nodes = nodes;
    }
}
