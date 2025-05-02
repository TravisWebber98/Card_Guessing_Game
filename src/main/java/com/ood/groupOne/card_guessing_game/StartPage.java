package com.ood.groupOne.card_guessing_game;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class StartPage {
    private VBox layout;
    private MainApp mainApp;

    public StartPage(MainApp mainApp) {
        this.mainApp = mainApp;
        layout = new VBox(40);
        layout.setAlignment(Pos.CENTER);

        Label welcome = new Label("Welcome to Group 1's Card Game!");
        welcome.setStyle("-fx-text-fill: black; -fx-font-size: 30px; -fx-font-weight: bold;");

        Button startButton = new Button("START GAME");
        startButton.setStyle("-fx-text-fill: black; -fx-font-size: 15px; -fx-font-weight: bold;");

        startButton.setOnAction(e -> mainApp.startLevel(1));

        layout.getChildren().addAll(welcome, startButton);
    }
    public VBox getLayout() {
        return layout;
    }
}
