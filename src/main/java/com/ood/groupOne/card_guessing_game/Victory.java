package com.ood.groupOne.card_guessing_game;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
public class Victory {
    private VBox layout;
    private MainApp mainApp;

    public Victory(MainApp mainApp) {
        this.mainApp = mainApp;
        layout = new VBox(40);
        layout.setAlignment(Pos.CENTER);

        Label winMessage = new Label("YOU WIN!");
        Button restartButton = new Button("Play Again?");

        restartButton.setOnAction(e -> mainApp.startLevel(1));

        layout.getChildren().addAll(winMessage, restartButton);
    }
    public VBox getLayout() {
        return layout;
    }
}
