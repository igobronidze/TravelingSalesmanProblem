package ge.edu.tsu.tsp.client.component;

import javafx.scene.control.Button;

public class TCHButton extends Button {

    public TCHButton() {
        initComponent();
    }

    public TCHButton(String text) {
        this();
        this.setText(text);
    }

    private void initComponent() {
        this.setStyle("-fx-font-family: sylfaen; -fx-font-size: 14px;");
    }
}