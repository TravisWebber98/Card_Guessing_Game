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

public class Level1 {
    private VBox layout;
    private MainApp mainApp;
    private TextField[] guessFields = new TextField[3];
    private Card[] selectedCards = new Card[3];
    private ImageView[] selectedCardViews = new ImageView[3];
    private Image cardBackImage;
    private Label timerLabel;
    private Label retryLabel = null;
    private Button retryLevelButton = null;



    public Level1(MainApp mainApp) {
        this.mainApp = mainApp;
        layout = new VBox(40);
        layout.setAlignment(Pos.CENTER);

        //level and instuctions
        Label levelLabel = new Label("Level 1 : EASY MODE : Guess the suits. \n(Only enter Hearts, Clubs, Diamonds, Spades (case sensitive) ");

        //timer
        timerLabel = new Label("Timer Not Working RN");

        //load cardback image from Resources/CardImages
        cardBackImage = new Image(getClass().getResourceAsStream("/CardImages/back.png"));

        //Row for the cards
        HBox cardRow = new HBox(40);
        cardRow.setAlignment(Pos.CENTER);

        //generate random cards
        Random rand = new Random();
        Card[][] deck = mainApp.getCards();

        for (int i = 0; i < 3; i++) {
            int suitIndex = rand.nextInt(4);
            int rankIndex = rand.nextInt(13);

            Card cardDrawn = mainApp.getCards()[suitIndex][rankIndex];
            selectedCards[i] = cardDrawn;

            VBox cardBox = new VBox(20);
            cardBox.setAlignment(Pos.CENTER);

            ImageView cardBack = new ImageView(cardBackImage);
            cardBack.setFitWidth(100);
            cardBack.setFitHeight(150);
            selectedCardViews[i] = cardBack;

            TextField guessField = new TextField();
            guessField.setPromptText("Suit");

            guessFields[i] = guessField;
            cardBox.getChildren().addAll(cardBack, guessField);
            cardRow.getChildren().add(cardBox);
        }

        Button submitButton = new Button("Submit Guess");
        submitButton.setOnAction(e -> {
            //    timer.stop();

            checkGuesses();
        });

        layout.getChildren().addAll(timerLabel, levelLabel, cardRow, submitButton);
    }
    //check if right or wrong
    private void checkGuesses() {
        int correctGuess = 0;

        for (int i = 0; i < 3; i++) {
            String guess = guessFields[i].getText();
            String actualSuit = selectedCards[i].suit();

            if (guess.equals(actualSuit)) {
                correctGuess++;
            }
        }

        if (correctGuess == 3) {
            flipCardsFaceUp();
            System.out.println("Correct! Starting Level 2...");
            mainApp.startLevel(2);
        } else {
            showRetryOption("Wrong. Try again.");

        }
    }
    //flip card
    private void flipCardsFaceUp() {
        ImageView[][] cardImages = mainApp.getCardImages();

        for (int i = 0; i < 3; i++) {
            Card card = selectedCards[i];

            int suitIndex = getSuitIndex(card.suit());
            int rankIndex = getRankIndex(card.rank());

            if (suitIndex == -1 || rankIndex == -1) {
                System.out.println("Invalid suit for card " + i + ": " + card.suit() + " / " + card.rank());
                selectedCardViews[i].setImage(cardBackImage); // Keep showing back image
            } else {
                Image faceImage = cardImages[suitIndex][rankIndex].getImage();
                selectedCardViews[i].setImage(faceImage);
            }
        }

    }
    //retry level
    private void showRetryOption(String messageText) {
        //delete old retry label/button if they exist
        if (retryLabel != null) layout.getChildren().remove(retryLabel);
        if (retryLevelButton != null) layout.getChildren().remove(retryLevelButton);

        retryLabel = new Label(messageText);
        retryLevelButton = new Button("Retry Level 1");
        retryLevelButton.setOnAction(e -> mainApp.startLevel(1));

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
