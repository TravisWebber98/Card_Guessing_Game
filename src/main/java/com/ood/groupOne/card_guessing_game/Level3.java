package com.ood.groupOne.card_guessing_game;

import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.*;

public class Level3 extends BaseLevel {
    private TextField[] rankFields = new TextField[3];
    private TextField[] suitFields = new TextField[3];
    private Label rankFeedbackLabel = new Label();
    private Label suitFeedbackLabel = new Label();
    private FadeTransition rankFade;
    private FadeTransition suitFade;

    public Level3(MainApp mainApp) {
        super(mainApp);
        layout.setAlignment(Pos.CENTER);

        Label levelLabel = new Label("Level 3: HARD MODE \nGuess BOTH the RANK and SUIT of each card to win!");
        levelLabel.setStyle("-fx-text-fill: black; -fx-font-size: 15px;");

        //timer
        Label timerLabel = new Label();
        timerLabel.setStyle("-fx-text-fill: black; -fx-font-size: 20px; -fx-font-weight: bold;");
        timer = new Timer(timerLabel, mainApp);
        timer.setTimeLeft(75);
        timer.start();

        //card layout
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

            TextField rankField = new TextField();
            rankField.setPromptText("Rank (lowercase only)");
            rankFields[i] = rankField;

            TextField suitField = new TextField();
            suitField.setPromptText("Suit (lowercase only)");
            suitFields[i] = suitField;

            Button guessButton = new Button("Submit Guess");
            int index = i;
            guessButton.setOnAction(e -> handleGuess(index));

            cardBox.getChildren().addAll(cardPane, rankField, suitField, guessButton);
            cardRow.getChildren().add(cardBox);
        }

//        rankFeedbackLabel.setVisible(false);
//        suitFeedbackLabel.setVisible(false);
        layout.getChildren().addAll(timerLabel, levelLabel, cardRow, rankFeedbackLabel, suitFeedbackLabel);
    }
    private void handleGuess(int index) {
        if (correctGuess[index]) return;

        String rankGuess = rankFields[index].getText();
        String suitGuess = suitFields[index].getText();
        String actualRank = selectedCards[index].rank();
        String actualSuit = selectedCards[index].suit();

        int guessNum = getRankValue(rankGuess);
        int actualNum = getRankValue(actualRank);
        String guessColor = getSuitColor(suitGuess);
        String actualColor = getSuitColor(actualSuit);

        if (rankGuess.equals(actualRank) && suitGuess.equals(actualSuit)) {
            correctGuess[index] = true;
            flipCard(index);
        } else {
            if (!rankGuess.equals(actualRank)) {
                if (guessNum == -1) {
                    rankFade = showFadeFeedback(rankFeedbackLabel, "Invalid rank!", rankFade, 6);
                } else if (guessNum > actualNum) {
                    rankFade = showFadeFeedback(rankFeedbackLabel, "Too High!", rankFade, 6);
                } else if (guessNum < actualNum) {
                    rankFade = showFadeFeedback(rankFeedbackLabel, "Too Low!", rankFade, 6);
                }
            }
            if (!suitGuess.equals(actualSuit)) {
                if (guessColor.equals(actualColor)) {
                    suitFade = showFadeFeedback(suitFeedbackLabel, "Wrong suit, correct color.", suitFade, 6);
                } else {
                    suitFade = showFadeFeedback(suitFeedbackLabel, "Wrong suit, wrong color.", suitFade, 6);
                }
            }
        }

        if (allCorrect()) {
            timer.stop();
            nextLevel("Congratulations!", 4);
        }
    }


    private String getSuitColor(String suit) {
        if (suit.equals("spades") || suit.equals("clubs")) {
            return "black";
        }
        return "red";
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

    public VBox getLayout() {
        return layout;
    }
}