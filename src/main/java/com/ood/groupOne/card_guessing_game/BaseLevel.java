package com.ood.groupOne.card_guessing_game;

import javafx.animation.FadeTransition;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class BaseLevel {
    protected VBox layout = new VBox(20);
    protected MainApp mainApp;
    protected Card[] selectedCards = new Card[3];
    protected ImageView[] selectedCardViews = new ImageView[3];
    protected boolean[] correctGuess = new boolean[3];
    protected Image cardBackImage;
    protected Timer timer;
    protected Label feedbackLabel = new Label();
    private FadeTransition activeFade;

    public BaseLevel(MainApp mainApp) {
        this.mainApp = mainApp;
        layout.setStyle("-fx-padding: 20px;");
        cardBackImage = new Image(getClass().getResourceAsStream("/CardImages/back.png"));
        feedbackLabel.setVisible(false);
    }

    //flip card
    protected void flipCard(int index) {
        Card card = selectedCards[index];
        Image faceImage = mainApp.getCardImages().get(mainApp.getDeck().deck().indexOf(card)).getImage();
        selectedCardViews[index].setImage(faceImage);
    }

    //check if all 3 cards are correct
    protected boolean allCorrect() {
        for (boolean correct : correctGuess) {
            if (!correct) return false;
        }
        return true;
    }

    protected void showFeedback(String message) {
        feedbackLabel.setText(message);
        feedbackLabel.setStyle("-fx-text-fill: red; -fx-font-size: 15px;");
        feedbackLabel.setOpacity(1.0);
        feedbackLabel.setVisible(true);

        if (activeFade != null) {
            activeFade.stop();
        }

        activeFade = new FadeTransition(Duration.seconds(5), feedbackLabel);
        activeFade.setFromValue(1.0);
        activeFade.setToValue(0.0);
        activeFade.setOnFinished(e -> {
            feedbackLabel.setVisible(false);
            activeFade = null;
        });
        activeFade.play();
    }

    protected void nextLevel(String message, int nextLevel) {
        feedbackLabel.setText(message);
        feedbackLabel.setStyle("-fx-text-fill: green; -fx-font-size: 20px;");
        feedbackLabel.setOpacity(1.0);
        feedbackLabel.setVisible(true);

        if (activeFade != null) {
            activeFade.stop(); // Prevent overlapping fade if jumping levels
        }

        activeFade = new FadeTransition(Duration.seconds(5), feedbackLabel);
        activeFade.setFromValue(1.0);
        activeFade.setToValue(1.0);
        activeFade.setOnFinished(e -> {
            feedbackLabel.setVisible(false);
            mainApp.startLevel(nextLevel);
        });
        activeFade.play();
    }
    protected FadeTransition showFadeFeedback(Label label, String message, FadeTransition previousFade, int seconds) {
        label.setText(message);
        label.setStyle("-fx-text-fill: red; -fx-font-size: 15px;");
        label.setOpacity(1.0);
        label.setVisible(true);

        if (previousFade != null) {
            previousFade.stop();
        }

        FadeTransition fade = new FadeTransition(Duration.seconds(seconds), label);
        fade.setFromValue(1.0);
        fade.setToValue(0.0);
        fade.setOnFinished(e -> label.setVisible(false));
        fade.play();

        return fade;
    }

    public VBox getLayout() {
        return layout;
    }
}
