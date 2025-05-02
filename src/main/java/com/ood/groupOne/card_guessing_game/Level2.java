package com.ood.groupOne.card_guessing_game;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.ArrayList;

public class Level2 {
    private VBox layout;
    private MainApp mainApp;
    private TextField[] guessFields = new TextField[3];
    private ImageView[] selectedCardViews = new ImageView[3];
    private Card[] selectedCards = new Card[3];
    private Image cardBackImage;
    private Label timerLabel;
    private boolean[] correctGuess = new boolean[3]; // Track if each card has been guessed correctly

    public Level2(MainApp mainApp) {
        this.mainApp = mainApp;
        layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);

        Label levelLabel = new Label("Level 2: MEDIUM MODE \nGuess the RANK of each card (Ace, 2 ... Queen, King)");

        //timer
        timerLabel = new Label();
        Timer timer = new Timer(timerLabel, mainApp);
        timer.start();

        cardBackImage = new Image(getClass().getResourceAsStream("/CardImages/back.png"));

        HBox cardRow = new HBox(20);
        cardRow.setAlignment(Pos.CENTER);

        for (int i = 0; i < 3; i++) {

            Card drawnCard = mainApp.getDeck().drawCard();
            selectedCards[i] = drawnCard;

            VBox cardBox = new VBox(10);
            cardBox.setAlignment(Pos.CENTER);

            ImageView cardBack = new ImageView(cardBackImage);
            cardBack.setFitWidth(100);
            cardBack.setFitHeight(150);
            selectedCardViews[i] = cardBack;

            TextField guessField = new TextField();
            guessField.setPromptText("Rank (lowercase only)");
            guessFields[i] = guessField;

            Button guessButton = new Button("Submit Guess");
            int index = i;
            guessButton.setOnAction(e -> handleGuess(index));

            cardBox.getChildren().addAll(cardBack, guessField);
            cardRow.getChildren().add(cardBox);
        }
        layout.getChildren().addAll(timerLabel, levelLabel, cardRow);
    }


    // individual guess
    private void handleGuess(int index) {
        if (correctGuess[index]) return;

        String guess = guessFields[index].getText();
        String actual = selectedCards[index].rank();

        if (guess.equals(actual)) {
            correctGuess[index] = true;
            flipCard(index);
        }

        if (allCorrect()) {
            mainApp.startLevel(3);
        }
    }

    // Flip
    private void flipCard(int index) {
        Card card = selectedCards[index];
        Image faceImage = mainApp.getCardImages().get(mainApp.getDeck().deck().indexOf(card)).getImage();
        selectedCardViews[index].setImage(faceImage);
    }

    // Check
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
