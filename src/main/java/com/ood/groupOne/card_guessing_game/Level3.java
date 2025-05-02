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

public class Level3 {
    private VBox layout;
    private MainApp mainApp;
    private TextField[] rankFields = new TextField[3];
    private TextField[] suitFields = new TextField[3];
    private ImageView[] selectedCardViews = new ImageView[3];
    private Card[] selectedCards = new Card[3];
    private Image cardBackImage;
    private Label timerLabel;
    private Label retryLabel = null;
    private Button retryLevelButton = null;

    public Level3(MainApp mainApp) {
        this.mainApp = mainApp;
        layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);

        Label levelLabel = new Label("Level 3: HARD MODE \nGuess BOTH the RANK and SUIT of each card to win!");

        //timer
        timerLabel = new Label();
        Timer timer = new Timer(timerLabel, mainApp);
        timer.start();

        cardBackImage = new Image(getClass().getResourceAsStream("/CardImages/back.png"));

        HBox cardRow = new HBox(20);
        cardRow.setAlignment(Pos.CENTER);

//        Random rand = new Random();
//        Card[][] cards = mainApp.getCards();

        for (int i = 0; i < 3; i++) {
//            int suitIndex = rand.nextInt(4);
//            int rankIndex = rand.nextInt(13);

            Card drawnCard = mainApp.getDeck().drawCard();
            selectedCards[i] = drawnCard;

            ImageView cardBack = new ImageView(cardBackImage);
            cardBack.setFitWidth(100);
            cardBack.setFitHeight(150);
            selectedCardViews[i] = cardBack;

            TextField rankField = new TextField();
            rankField.setPromptText("Rank (lowercase only)");
            rankFields[i] = rankField;

            TextField suitField = new TextField();
            suitField.setPromptText("Suit (lowercase only)");
            suitFields[i] = suitField;

            VBox cardBox = new VBox(10);
            cardBox.setAlignment(Pos.CENTER);
            cardBox.getChildren().addAll(cardBack, rankField, suitField);

            cardRow.getChildren().add(cardBox);
        }

        Button submitButton = new Button("Submit Guess");
        submitButton.setOnAction(e -> {
            checkGuesses();
        });

        layout.getChildren().addAll(timerLabel, levelLabel, cardRow, submitButton);
    }
    // Check
    private void checkGuesses() {
        int correct = 0;

        for (int i = 0; i < 3; i++) {
            String rankGuess = rankFields[i].getText();
            String suitGuess = suitFields[i].getText();

            String actualRank = selectedCards[i].rank();
            String actualSuit = selectedCards[i].suit();

            if (rankGuess.equals(actualRank) && suitGuess.equals(actualSuit)) {
                correct++;
            }
        }
        flipCardsFaceUp();
        if (correct == 3) {
            flipCardsFaceUp();
            mainApp.startLevel(4);
        } else {
            showRetryOption("Wrong! Try again.");
        }
    }
    //flip card
    private void flipCardsFaceUp() {
        ArrayList<ImageView> cardImages = mainApp.getCardImages();

        for (int i = 0; i < 3; i++) {
            Card card = selectedCards[i];

//            int suitIndex = getSuitIndex(card.suit());
//            int rankIndex = getRankIndex(card.rank());
//
//            if (suitIndex == -1 || rankIndex == -1) {
//                System.out.println("Invalid suit for card " + i + ": " + card.suit() + " / " + card.rank());
//                selectedCardViews[i].setImage(cardBackImage); // Keep showing back image
//            } else {
//                Image faceImage = cardImages[suitIndex][rankIndex].getImage();
//                selectedCardViews[i].setImage(faceImage);
//            }
            Image faceImage;
            faceImage = cardImages.get(mainApp.getDeck().deck().indexOf(card)).getImage();
            selectedCardViews[i].setImage(faceImage);
        }
    }

    private void showRetryOption(String messageText) {
        //remove old labels/buttons
        if (retryLabel != null) layout.getChildren().remove(retryLabel);
        if (retryLevelButton != null) layout.getChildren().remove(retryLevelButton);

        retryLabel = new Label(messageText);
        retryLevelButton = new Button("Retry Level 2");
        retryLevelButton.setOnAction(e -> mainApp.startLevel(2));

        layout.getChildren().addAll(retryLabel, retryLevelButton);
    }



    private int getSuitIndex(String suit) {
        switch (suit) {
            case "clubs": return 0;
            case "diamonds": return 1;
            case "hearts": return 2;
            case "spades": return 3;
            default: return -1;
        }
    }

    private int getRankIndex(String rank) {
        switch (rank) {
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
            case "jack": return 10;
            case "queen": return 11;
            case "king": return 12;
            default: return -1;
        }
    }

    public VBox getLayout() {
        return layout;
    }
}