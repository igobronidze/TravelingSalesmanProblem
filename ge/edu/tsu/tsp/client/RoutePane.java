package ge.edu.tsu.tsp.client;

import ge.edu.tsu.tsp.server.data.TSPInput;
import ge.edu.tsu.tsp.server.graph.Graph;
import ge.edu.tsu.tsp.server.solve.mst.TSPDuplicateMSTSolver;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RoutePane extends AnchorPane {

    private final int CANVAS_WIDTH = 800;

    private final int CANVAS_HEIGHT = 630;

    private GraphicsContext gc;

    private Canvas canvas;

    private List<Point2D> points = new ArrayList<>();

    public RoutePane() {
        this.setStyle("-fx-border-color: green; -fx-border-style: solid; -fx-border-width: 5px; -fx-background-color: white");
        initCanvas();
    }

    private void initCanvas() {
        canvas = new Canvas(CANVAS_WIDTH + 20, CANVAS_HEIGHT + 20);
        gc = canvas.getGraphicsContext2D();
        this.getChildren().add(canvas);
        AnchorPane.setTopAnchor(canvas, 5.0);
        AnchorPane.setLeftAnchor(canvas, 5.0);
    }

    public void buildRoute(Graph graph) {
        points = new ArrayList<>();
        Random rand = new Random();
        gc.setFill(Color.BLUE);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(3);
        gc.clearRect(0, 0, CANVAS_WIDTH + 20, CANVAS_HEIGHT + 20);
        for (int i = 0; i < graph.getNodeNumber(); i++) {
            int x = rand.nextInt((CANVAS_WIDTH - 0) + 1) + 0;
            int y = rand.nextInt((CANVAS_HEIGHT - 0) + 1) + 0;
            points.add(new Point2D(x + 10, y + 10));
            gc.fillOval(x, y, 20, 20);
        }
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                double x1 = points.get(i).getX();
                double y1 = points.get(i).getY();
                double x2 = points.get(j).getX();
                double y2 = points.get(j).getY();
                gc.strokeLine(x1, y1, x2, y2);
                double x = Math.max(x1, x2) - Math.abs(x1 - x2) / 2 - 15;
                double y = Math.max(y1, y2) - Math.abs(y1 - y2) / 2 - 15;
                int distance = graph.getNodes().get(i + 1).getConnections().get(j + 1).getDistance();
                gc.fillText("" + distance, x, y);
            }
        }
        testSolver(graph);
    }

    private void testSolver(Graph graph) {
        TSPInput input = new TSPInput();
        input.setNodeNumber(8);
        input.setMinDistance(1);
        input.setMaxDistance(7);
        input.setTimeOut(60);
        input.setMaxIteration(1000);
        new TSPDuplicateMSTSolver().solve(graph, input).print(false);
    }
}
