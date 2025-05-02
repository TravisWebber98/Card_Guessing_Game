package com.ood.groupOne.card_guessing_game;

import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Level1 {
    private VBox layout;
    private MainApp mainApp;
    private TextField[] guessFields = new TextField[3];
    private Card[] selectedCards = new Card[3];
    private ImageView[] selectedCardViews = new ImageView[3];
    private Image cardBackImage;
    private boolean[] correctGuess = new boolean[3]; // Track if each card has been guessed correctly
    private Timer timer;
    private Label feedbackLabel = new Label();


    public Level1(MainApp mainApp) {
        this.mainApp = mainApp;
        this.layout = new VBox(40);
        layout.setAlignment(Pos.CENTER);

        //level and instuctions
        Label levelLabel = new Label("Level 1 : EASY MODE : Guess the suits. \n(spades, diamonds, hearts, clubs) ");
        levelLabel.setStyle("-fx-text-fill: black; -fx-font-size: 15px;");

        //timer
        Label timerLabel = new Label();
        timerLabel.setStyle("-fx-text-fill: black; -fx-font-size: 20px; -fx-font-weight: bold;");
        timer = new Timer(timerLabel, mainApp);
        timer.setTimeLeft(45);
        timer.start();

        //load cardback image from Resources/CardImages
        cardBackImage = new Image(getClass().getResourceAsStream("/CardImages/back.png"));

        //Row for the cards
        HBox cardRow = new HBox(40);
        cardRow.setAlignment(Pos.CENTER);

        for (int i = 0; i < 3; i++) {

            Card cardDrawn = mainApp.getDeck().drawCard();
            selectedCards[i] = cardDrawn;

            VBox cardBox = new VBox(20);
            cardBox.setAlignment(Pos.CENTER);

            Rectangle background = new Rectangle(105, 155, Color.WHITE);

            ImageView cardBack = new ImageView(cardBackImage);
            cardBack.setFitWidth(100);
            cardBack.setFitHeight(150);
            selectedCardViews[i] = cardBack;

            StackPane cardPane = new StackPane();
            cardPane.getChildren().addAll(background, cardBack);

            TextField guessField = new TextField();
            guessField.setPromptText("Suit (lowercase only)");
            guessFields[i] = guessField;

            //Individual guess button for this card
            Button guessButton = new Button("Submit Guess");
            int index = i;
            guessButton.setOnAction(e -> handleGuess(index));

            //add all components for individual card into its VBox
            cardBox.getChildren().addAll(cardPane, guessField, guessButton);
            cardRow.getChildren().add(cardBox);
        }
        feedbackLabel.setVisible(false);
        layout.getChildren().addAll(timerLabel, levelLabel, cardRow, feedbackLabel);
    }
    // individual guess
    private void handleGuess(int index) {
        if (correctGuess[index]) return;  // skip if already guessed correctly

        String guess = guessFields[index].getText();
        String actual = selectedCards[index].suit();

        if (guess.equals(actual)) {
            correctGuess[index] = true;
            flipCard(index); // Flip the correct card
        } else {
            String guessedColor = getSuitColor(guess);
            String actualColor = getSuitColor(actual);
            if (guessedColor.equals(actualColor)) {
                showFeedback("Wrong suit, correct color.");
            } else {
                showFeedback("Wrong suit, wrong color.");
            }
        }

        // If all cards are guessed correctly, advance to next level
        if (allCorrect()) {
            timer.stop();
            nextLevel("All cards guessed correctly! Moving to level 2.");
        }
    }

    // Flip
    private void flipCard(int index) {
        Card card = selectedCards[index];
        Image faceImage = mainApp.getCardImages().get(mainApp.getDeck().deck().indexOf(card)).getImage();
        selectedCardViews[index].setImage(faceImage);
    }

    // Check all cards have been guessed correctly
    private boolean allCorrect() {
        for (boolean correct : correctGuess) {
            if (!correct) return false;
        }
        return true;
    }

    private String getSuitColor(String suit) {
        if (suit.equals("spades") || suit.equals("clubs")) {
            return "black";
        }
        return "red";
    }

    private void showFeedback(String message) {
        feedbackLabel.setText(message);
        feedbackLabel.setStyle("-fx-text-fill: red; -fx-font-size: 15px;");
        feedbackLabel.setOpacity(1.0);
        feedbackLabel.setVisible(true);

        FadeTransition fade = new FadeTransition(javafx.util.Duration.seconds(5), feedbackLabel);
        fade.setFromValue(1.0);
        fade.setToValue(0.0);
        fade.setOnFinished(e -> {
            feedbackLabel.setVisible(false);
        });
        fade.play();
    }

    private void nextLevel(String message) {
        feedbackLabel.setText(message);
        feedbackLabel.setStyle("-fx-text-fill: green; -fx-font-size: 20px;");
        feedbackLabel.setOpacity(1.0);
        feedbackLabel.setVisible(true);

        FadeTransition fade = new FadeTransition(javafx.util.Duration.seconds(5), feedbackLabel);
        fade.setFromValue(1.0);
        fade.setToValue(1.0);
        fade.setOnFinished(e -> {
            feedbackLabel.setVisible(false);
            feedbackLabel.setOpacity(1.0);
            mainApp.startLevel(2); // Transition to next level
        });
        fade.play();
    }

    public VBox getLayout() {
        return layout;
    }
}

