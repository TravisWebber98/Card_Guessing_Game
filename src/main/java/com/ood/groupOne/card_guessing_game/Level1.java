package com.ood.groupOne.card_guessing_game;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Level1 extends BaseLevel {
    private TextField[] guessFields = new TextField[3];

    public Level1(MainApp mainApp) {
        super(mainApp);
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
        //feedbackLabel.setVisible(false);
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
            nextLevel("All cards guessed correctly! Moving to level 2.", 2);
        }
    }

    private String getSuitColor(String suit) {
        if (suit.equals("spades") || suit.equals("clubs")) {
            return "black";
        }
        return "red";
    }


    public int levelNumber() {
        return 1;
    }

    public VBox getLayout() {
        return layout;
    }
}

