package com.ood.groupOne.card_guessing_game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.util.ArrayList;

public class MainApp extends Application {
    private BorderPane mainLayout;
    // Create deck and image lists for image mapping
    private Deck deck = new Deck();
    private ArrayList<ImageView> cardImages = new ArrayList<>();

    @Override
    public void start(Stage mainStage) {
        mainLayout = new BorderPane();
        mainLayout.setBackground(new Background(new BackgroundFill(Color.DEEPSKYBLUE, null, null)));
        Scene scene = new Scene(mainLayout, 800, 600);

        mainStage.setTitle("Group 1's Card Game");
        mainStage.setScene(scene);
        setupDeck();
        mainStage.show();
        displayStartPage();
    }

    private void setupDeck() {
        ArrayList<Card> d = deck.deck();
        for (int i = 0; i < deck.size(); i++) {
            // Load matching image from /Resources/CardImages/
            String imagePath = "/CardImages/" + d.get(i).toString() + ".png";
            Image image = new Image(getClass().getResourceAsStream(imagePath));
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(100);
            imageView.setFitHeight(150);
            cardImages.add(imageView);
            // Card and image list indexes should now match
        }
    }

    public void displayStartPage(){
        StartPage startPage = new StartPage(this);
        mainLayout.setCenter(startPage.getLayout());
    }

    public void startLevel(int levelNum){
        switch (levelNum) {
            case 1:
                mainLayout.setCenter(new Level1(this).getLayout());
                break;
            case 2:
                mainLayout.setCenter(new Level2(this).getLayout());
                break;
            case 3:
                mainLayout.setCenter(new Level3(this).getLayout());
                break;
            case 4:
                mainLayout.setCenter(new Victory(this).getLayout());
                break;
            case 5:
                mainLayout.setCenter(new GameOver(this).getLayout());
            default:
                System.out.println("Invalid level");
                break;
        }

    }

    public BorderPane getMainLayout() {
        return mainLayout;
    }
    public Deck getDeck() {
        return deck;
    }
    public ArrayList<ImageView> getCardImages() {
        return cardImages;
    }

    public static void main(String[] args) {
        launch();
    }
}