package com.ood.groupOne.card_guessing_game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.control.Label;
public class Timer {
    private int timeLeft = 30;
    private boolean timeIsUp = false;
    private Timeline timeline;
    private Label timerLabel;

    public Timer(Label label) {
        timerLabel = label;
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateTimer()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timerLabel.setText("Time left: " + timeLeft);
    }

    private void updateTimer() {
        if (timeLeft > 0) {
            timeLeft--;
            timerLabel.setText("Time left: " + timeLeft);
        } else {
            timeIsUp = true;
            timeline.stop();
            timerLabel.setText("Time is up!");
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
        timeIsUp = false;
        timerLabel.setText("Time left: " + timeLeft);
    }

    public boolean timeIsUp() {
        return timeIsUp;
    }
}
