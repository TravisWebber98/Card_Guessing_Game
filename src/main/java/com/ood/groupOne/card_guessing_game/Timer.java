package com.ood.groupOne.card_guessing_game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.control.Label;
public class Timer {
    private int timeLeft = 60;
    private Timeline timeline;
    private Label timerLabel;
    private MainApp mainApp;

    public Timer(Label label, MainApp mainApp) {
        timerLabel = label;
        this.mainApp = mainApp;
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateTimer()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timerLabel.setText("Time left: " + timeLeft);
    }

    private void updateTimer() {
        if (timeLeft > 0) {
            timeLeft--;
            timerLabel.setText("Time left: " + timeLeft);
        } else {
            timeline.stop();
            mainApp.startLevel(5);
        }
    }

    public void start() {
        timerLabel.setText("Time left: " + timeLeft);
        timeline.play();
    }

    public void stop() {
        timeline.stop();
    }

    public void reset() {
        timeLeft = 30;
        timerLabel.setText("Time left: " + timeLeft);
    }
}
