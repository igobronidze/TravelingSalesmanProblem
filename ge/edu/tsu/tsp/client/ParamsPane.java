package ge.edu.tsu.tsp.client;

import ge.edu.tsu.tsp.client.component.TCHButton;
import ge.edu.tsu.tsp.client.component.TCHComboBox;
import ge.edu.tsu.tsp.client.component.TCHComponentSize;
import ge.edu.tsu.tsp.client.component.TCHFieldLabel;
import ge.edu.tsu.tsp.client.component.TCHNumberTextField;
import ge.edu.tsu.tsp.server.data.TSPInput;
import ge.edu.tsu.tsp.server.graph.Graph;
import ge.edu.tsu.tsp.server.solve.TSPSolveMethod;
import ge.edu.tsu.tsp.server.tsp_helper.TSPDataCreator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

import java.util.Arrays;

public class ParamsPane extends VBox {

    private final TSPInput input = new TSPInput();

    public ParamsPane() {
        this.setSpacing(25);
        this.setAlignment(Pos.TOP_CENTER);
        initTopPane();
        initBottomPane();
    }

    private void initTopPane() {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setSpacing(7);
        vBox.setPadding(new Insets(10));
        vBox.setStyle("-fx-border-color: green; -fx-border-radius: 5px; -fx-border-size: 1px;");
        TCHNumberTextField citiesNumberField = new TCHNumberTextField(TCHComponentSize.SMALL);
        TCHFieldLabel citiesNumberFieldLabel = new TCHFieldLabel("ქალაქების რაოდენობა", citiesNumberField);
        TCHNumberTextField minValueField = new TCHNumberTextField(TCHComponentSize.SMALL);
        TCHFieldLabel minValueFieldLabel = new TCHFieldLabel("მინ. მნიშვნელობა", minValueField);
        TCHNumberTextField maxValueField = new TCHNumberTextField(TCHComponentSize.SMALL);
        TCHFieldLabel maxValueFieldLabel = new TCHFieldLabel("მაქს. მნიშვნელობა", maxValueField);
        TCHButton buildButton = new TCHButton("აგება");
        buildButton.setOnAction(event -> {
            input.setNodeNumber(citiesNumberField.getNumber().intValue());
            input.setMinDistance(minValueField.getNumber().intValue());
            input.setMaxDistance(maxValueField.getNumber().intValue());
            Graph graph = TSPDataCreator.getRandomizeGraph(input);
            ControlPanel.getRoutePane().buildRoute(graph);
        }) ;
        vBox.getChildren().addAll(citiesNumberFieldLabel, minValueFieldLabel, maxValueFieldLabel,buildButton);
        this.getChildren().add(vBox);
    }

    private void initBottomPane() {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setSpacing(7);
        vBox.setPadding(new Insets(10));
        vBox.setStyle("-fx-border-color: green; -fx-border-radius: 5px; -fx-border-size: 1px;");
        TCHComboBox methodComboBox = new TCHComboBox(Arrays.asList(TSPSolveMethod.values()));
        TCHFieldLabel methodFieldLabel = new TCHFieldLabel("მეთოდი", methodComboBox);
        TCHNumberTextField timeOutField = new TCHNumberTextField(TCHComponentSize.SMALL);
        TCHFieldLabel timeOutFieldLabel = new TCHFieldLabel("დროის ლიმიტი", timeOutField);
        TCHNumberTextField sleepField = new TCHNumberTextField(TCHComponentSize.SMALL);
        TCHFieldLabel sleepFieldLabel = new TCHFieldLabel("ინტერვალი", sleepField);
        TCHButton solveButton = new TCHButton("ამოხსნა");
        vBox.getChildren().addAll(methodFieldLabel, timeOutFieldLabel, sleepFieldLabel, solveButton);
        this.getChildren().add(vBox);
    }
}
