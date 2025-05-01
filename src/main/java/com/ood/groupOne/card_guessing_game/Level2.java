package com.ood.groupOne.card_guessing_game;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.Random;

public class Level2 {
    private VBox layout;
    private MainApp mainApp;
    private TextField[] guessFields = new TextField[3];
    private ImageView[] selectedCardViews = new ImageView[3];
    private Card[] selectedCards = new Card[3];
    private Image cardBackImage;
    private Label timerLabel;
    private Label retryLabel = null;
    private Button retryLevelButton = null;

    public Level2(MainApp mainApp) {
        this.mainApp = mainApp;
        layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);

        Label levelLabel = new Label("Level 2: MEDIUM MODE \nGuess the RANK of each card (Ace, 2 ... Queen, King)");

        //timer
        timerLabel = new Label("Timer Not Working RN)");

        cardBackImage = new Image(getClass().getResourceAsStream("/CardImages/back.png"));

        HBox cardRow = new HBox(20);
        cardRow.setAlignment(Pos.CENTER);

        Random rand = new Random();
        Card[][] cards = mainApp.getCards();

        for (int i = 0; i < 3; i++) {
            int suitIndex = rand.nextInt(4);
            int rankIndex = rand.nextInt(13);

            Card drawnCard = cards[suitIndex][rankIndex];
            selectedCards[i] = drawnCard;

            ImageView cardBack = new ImageView(cardBackImage);
            cardBack.setFitWidth(100);
            cardBack.setFitHeight(150);
            selectedCardViews[i] = cardBack;

            TextField guessField = new TextField();
            guessField.setPromptText("Rank");

            guessFields[i] = guessField;

            VBox cardBox = new VBox(10);
            cardBox.setAlignment(Pos.CENTER);
            cardBox.getChildren().addAll(cardBack, guessField);

            cardRow.getChildren().add(cardBox);
        }

        Button submitButton = new Button("Submit Guess");
        submitButton.setOnAction(e -> {
            checkGuesses();     //check if right or wrong
        });

        layout.getChildren().addAll(timerLabel, levelLabel, cardRow, submitButton);
    }



    //check if right or wrong
    private void checkGuesses() {
        int correctGuess = 0;

        for (int i = 0; i < 3; i++) {
            String guess = guessFields[i].getText();
            String actualRank = selectedCards[i].rank();

            if (guess.equals(actualRank)) {
                correctGuess++;
            }
        }

        if (correctGuess == 3) {
            flipCardsFaceUp();
            System.out.println("Correct! Starting Level 3...");
            mainApp.startLevel(3);
        } else {
            showRetryOption("WRONG. Try again.");
        }
    }

    //flip the card
    private void flipCardsFaceUp() {
        ImageView[][] cardImages = mainApp.getCardImages();

        for (int i = 0; i < 3; i++) {
            Card card = selectedCards[i];
            int suitIndex = getSuitIndex(card.suit());
            int rankIndex = getRankIndex(card.rank());

            if (suitIndex == -1 || rankIndex == -1) {
                System.out.println("Invalid rank for card " + i + ": " + card.suit() + " / " + card.rank());
                selectedCardViews[i].setImage(cardBackImage); // fallback
            } else {
                Image faceImage = cardImages[suitIndex][rankIndex].getImage();
                selectedCardViews[i].setImage(faceImage);
            }
        }
    }

    private void showRetryOption(String messageText) {
        if (retryLabel != null) layout.getChildren().remove(retryLabel);
        if (retryLevelButton != null) layout.getChildren().remove(retryLevelButton);

        retryLabel = new Label(messageText);
        retryLevelButton = new Button("Retry Level 2");
        retryLevelButton.setOnAction(e -> mainApp.startLevel(2));

        layout.getChildren().addAll(retryLabel, retryLevelButton);
    }

    private int getSuitIndex(String suit) {
        switch (suit) {
            case "Clubs":
            case "clubs": return 0;
            case "Diamonds":
            case "diamonds": return 1;
            case "Hearts":
            case "hearts": return 2;
            case "Spades":
            case "spades": return 3;
            default: return -1;

        }
    }

    private int getRankIndex(String rank) {
        switch (rank) {
            case "Ace":
            case "ace": return 0;
            case "2": return 1;
            case "3": return 2;
            case "4": return 3;
            case "5": return 4;
            case "6": return 5;
            case "7": return 6;
            case "8": return 7;
            case "9": return 8;
            case "10": return 9;
            case "Jack":
            case "jack": return 10;
            case "Queen":
            case "queen": return 11;
            case "King":
            case "king": return 12;
            default: return -1;
        }
    }

    public VBox getLayout() {
        return layout;
    }
}

