package ge.edu.tsu.tsp.server.tsp_helper;

import ge.edu.tsu.tsp.server.graph.Connection;
import ge.edu.tsu.tsp.server.graph.Node;
import ge.edu.tsu.tsp.server.graph.Graph;
import ge.edu.tsu.tsp.server.data.TSPInput;

import java.util.Random;

public class TSPDataCreator {

    public static Graph getRandomizeGraph(TSPInput input) {
        Random rand = new Random();
        Graph graph = new Graph();
        graph.setNodeNumber(input.getNodeNumber());
        for (int i = 1; i <= input.getNodeNumber(); i++) {
            Node node = new Node();
            node.setIndex(i);
            graph.getNodes().put(i, node);
        }
        for (int i = 1; i <= input.getNodeNumber(); i++) {
            Node firstNode = graph.getNodes().get(i);
            for (int j = 1; j <= input.getNodeNumber(); j++) {
                if (i != j) {
                    Node secondNode = graph.getNodes().get(j);
                    int distance = rand.nextInt((input.getMaxDistance() - input.getMinDistance()) + 1) + input.getMinDistance();
                    Connection connection = new Connection();
                    connection.setFirstNode(firstNode);
                    connection.setSecondNode(secondNode);
                    connection.setDistance(distance);
                    firstNode.getConnections().put(j, connection);
                    secondNode.getConnections().put(i, connection);
                }
            }
        }
        return graph;
    }
}
