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
        Button startButton = new Button("START GAME");

        startButton.setOnAction(e -> mainApp.startLevel(1));

        layout.getChildren().addAll(welcome, startButton);
    }
    public VBox getLayout() {
        return layout;
    }
}
