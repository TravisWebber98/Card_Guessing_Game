package com.ood.groupOne.card_guessing_game;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
public class GameOver {
    private VBox layout;
    private MainApp mainApp;

    public GameOver(MainApp mainApp) {
        this.mainApp = mainApp;
        layout = new VBox(40);
        layout.setAlignment(Pos.CENTER);

        Label loseMessage = new Label("Sorry, time's up!");
        loseMessage.setStyle("-fx-text-fill: red; -fx-font-size: 30px; -fx-font-weight: bold;");

        Button restartButton = new Button("Try Again?");
        restartButton.setStyle("-fx-text-fill: black; -fx-font-size: 15px; -fx-font-weight: bold;");

        restartButton.setOnAction(e -> mainApp.startLevel(1));

        layout.getChildren().addAll(loseMessage, restartButton);
    }
    public VBox getLayout() {
        return layout;
    }
}
