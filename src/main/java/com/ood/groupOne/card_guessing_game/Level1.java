package com.ood.groupOne.card_guessing_game;

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
    private Label timerLabel;
    private boolean[] correctGuess = new boolean[3]; // Track if each card has been guessed correctly


    public Level1(MainApp mainApp) {
        this.mainApp = mainApp;
        layout = new VBox(40);
        layout.setAlignment(Pos.CENTER);

        //level and instuctions
        Label levelLabel = new Label("Level 1 : EASY MODE : Guess the suits. \n(spades, diamonds, hearts, clubs) ");

        //timer
        timerLabel = new Label();
        Timer timer = new Timer(timerLabel, mainApp);
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

        layout.getChildren().addAll(timerLabel, levelLabel, cardRow);
    }
    // individual guess
    private void handleGuess(int index) {
        if (correctGuess[index]) return;  // skip if already guessed correctly

        String guess = guessFields[index].getText();
        String actual = selectedCards[index].suit();

        if (guess.equals(actual)) {
            correctGuess[index] = true;
            flipCard(index); // Flip the correct card
        }

        // If all cards are guessed correctly, advance to next level
        if (allCorrect()) {
            mainApp.startLevel(2);
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

    public VBox getLayout() {
        return layout;
    }
}
