package com.ood.groupOne.card_guessing_game;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
public class Victory {
    private VBox layout;

    public Victory(MainApp mainApp) {
        layout = new VBox(40);
        layout.setAlignment(Pos.CENTER);

        Label winMessage = new Label("You won!\nFinal Score: " + Game.getScore());
        winMessage.setStyle("-fx-text-fill: green; -fx-font-size: 30px; -fx-font-weight: bold;");

        Button restartButton = new Button("Play Again?");
        restartButton.setStyle("-fx-text-fill: black; -fx-font-size: 15px; -fx-font-weight: bold;");

        restartButton.setOnAction(e -> mainApp.startLevel(1));

        layout.getChildren().addAll(winMessage, restartButton);
        Game.resetScore();
    }
    public VBox getLayout() {
        return layout;
    }
}
