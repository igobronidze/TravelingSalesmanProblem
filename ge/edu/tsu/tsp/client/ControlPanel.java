package ge.edu.tsu.tsp.client;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ControlPanel extends Application {

    private static HBox root;

    private static RoutePane routePane;

    private static ParamsPane paramsPane;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("კომივოიაჟერის ამოცანა");
        root = new HBox();
        primaryStage.setScene(new Scene(root));
        primaryStage.setWidth(1100);
        primaryStage.setHeight(710);
        primaryStage.setResizable(false);
        initPanes();
        primaryStage.show();
    }

    private void initPanes() {
        this.paramsPane = new ParamsPane();
        this.routePane = new RoutePane();
        root.setSpacing(10);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(routePane, paramsPane);
    }

    public static RoutePane getRoutePane() {
        return routePane;
    }
}
