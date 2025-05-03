package com.ood.groupOne.card_guessing_game;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Level2 extends BaseLevel{

    private TextField[] guessFields = new TextField[3];

    public Level2(MainApp mainApp) {
        super(mainApp);
        layout.setAlignment(Pos.CENTER);

        Label levelLabel = new Label("Level 2: MEDIUM MODE \nGuess the RANK of each card (ace, 2 ... queen, king)");
        levelLabel.setStyle("-fx-text-fill: black; -fx-font-size: 15px;");

        //timer
        Label timerLabel = new Label();
        timerLabel.setStyle("-fx-text-fill: black; -fx-font-size: 20px; -fx-font-weight: bold;");
        timer = new Timer(timerLabel, mainApp);
        timer.setTimeLeft(60);
        timer.start();

        HBox cardRow = new HBox(20);
        cardRow.setAlignment(Pos.CENTER);

        for (int i = 0; i < 3; i++) {

            Card drawnCard = mainApp.getDeck().drawCard();
            selectedCards[i] = drawnCard;

            VBox cardBox = new VBox(10);
            cardBox.setAlignment(Pos.CENTER);

            Rectangle background = new Rectangle(105, 155, Color.WHITE);

            ImageView cardBack = new ImageView(cardBackImage);
            cardBack.setFitWidth(100);
            cardBack.setFitHeight(150);
            selectedCardViews[i] = cardBack;

            StackPane cardPane = new StackPane();
            cardPane.getChildren().addAll(background, cardBack);

            TextField guessField = new TextField();
            guessField.setPromptText("Rank (lowercase only)");
            guessFields[i] = guessField;

            Button guessButton = new Button("Submit Guess");
            int index = i;
            guessButton.setOnAction(e -> handleGuess(index));

            cardBox.getChildren().addAll(cardPane, guessField, guessButton);
            cardRow.getChildren().add(cardBox);
        }

        layout.getChildren().addAll(timerLabel, levelLabel, cardRow, feedbackLabel);
    }


    // individual guess
    private void handleGuess(int index) {
        Game gameLvl2 = new Game();

        if (correctGuess[index]) return;

        String guess = guessFields[index].getText();
        String actual = selectedCards[index].rank();

        int guessedValue = getRankValue(guess);
        int actualValue = getRankValue(actual);

        gameLvl2.setLvl(levelNumber());
        if (guess.equals(actual)) {
            correctGuess[index] = true;
            flipCard(index);
            gameLvl2.setGuess(true);
        } else if (guessedValue > actualValue) {
            gameLvl2.setGuess(false);
            showFeedback("Too High!");
        } else {
            gameLvl2.setGuess(false);
            showFeedback("Too Low!");
        }
        gameLvl2.setScore(levelNumber());

        if (allCorrect()) {
            timer.stop();
            nextLevel("All cards guessed correctly! Are you ready for level 3?", 3);
        }
    }

    private int getRankValue(String rank) {
        switch (rank.toLowerCase()) {
            case "ace":
                return 1;
            case "2":
                return 2;
            case "3":
                return 3;
            case "4":
                return 4;
            case "5":
                return 5;
            case "6":
                return 6;
            case "7":
                return 7;
            case "8":
                return 8;
            case "9":
                return 9;
            case "10":
                return 10;
            case "jack":
                return 11;
            case "queen":
                return 12;
            case "king":
                return 13;
            default:
                return -1; // Invalid input
        }
    }

    public int levelNumber() {return 2;}
}
