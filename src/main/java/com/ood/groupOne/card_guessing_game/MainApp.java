package com.ood.groupOne.card_guessing_game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class MainApp extends Application {
    private BorderPane mainLayout;

    //2D array - 52 cards and images
    //adding 52 images to a resource folder?? perhaps
    private Card[][] cards = new Card [4][13];
    private ImageView[][] cardImages = new ImageView[4][13];

    private final String[] suits = {"clubs", "diamonds", "hearts", "spades"};
    private final String[] ranks = {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};


    @Override
    public void start(Stage mainStage) {
        mainLayout = new BorderPane();
        Scene scene = new Scene(mainLayout, 800, 800);

        mainStage.setTitle("Group 1's Card Game");
        mainStage.setScene(scene);
        setupDeck();
        mainStage.show();
        displayStartPage();
    }
    private void setupDeck() {
        for (int i = 0; i < suits.length; i++) {
            for (int j = 0; j < ranks.length; j++) {
                // Create card
                cards[i][j] = new Card(ranks[j], suits[i]);

                // Load matching image
                String imagePath = "/CardImages/" + ranks[j] + "_of_" + suits[i] + ".png";
                try {
                    Image image = new Image(getClass().getResourceAsStream(imagePath));
                    ImageView imageView = new ImageView(image);
                    imageView.setFitWidth(100);
                    imageView.setFitHeight(150);
                    cardImages[i][j] = imageView;
                } catch (Exception e) {
                    System.out.println("Failed to load image: " + imagePath);
                    cardImages[i][j] = new ImageView(); // fallback blank image
                }
            }
        }
    }

    public void displayStartPage(){
        StartPage startPage = new StartPage(this);
        mainLayout.setCenter(startPage.getLayout());
    }
    public void startLevel(int levelNum){
        switch (levelNum) {
            case 1 -> mainLayout.setCenter(new Level1(this).getLayout());
            case 2 -> mainLayout.setCenter(new Level2(this).getLayout());
            case 3 -> mainLayout.setCenter(new Level3(this).getLayout());
            default -> System.out.println("Invalid level");
        }

    }
    public BorderPane getMainLayout() {
        return mainLayout;
    }

    public Card[][] getCards() {
        return cards;
    }
    public ImageView[][] getCardImages() {
        return cardImages;
    }
    public static void main(String[] args) {
        launch();
    }
}