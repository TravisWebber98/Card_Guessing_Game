/*
********NOT FOR TURN IN************
 */
package com.ood.groupOne.card_guessing_game;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TimerVisualTest extends Application {
    @Override
    public void start(Stage primaryStage) {
        Label timerLabel = new Label("Timer not started.");
        timerLabel.setStyle("-fx-font-size: 20px;");

        Timer timer = new Timer(timerLabel);

        Button startButton = new Button("Start");
        startButton.setOnAction(e -> timer.start());

        Button stopButton = new Button("Stop");
        stopButton.setOnAction(e -> timer.stop());

        Button resetButton = new Button("Reset");
        resetButton.setOnAction(e -> timer.reset());

        VBox root = new VBox(10, timerLabel, startButton, stopButton, resetButton);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-padding: 20px;");

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("Timer Visual Test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

