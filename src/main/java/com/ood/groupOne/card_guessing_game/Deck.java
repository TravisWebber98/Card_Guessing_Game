package com.ood.groupOne.card_guessing_game;

import java.util.ArrayList;
import java.util.Random;
public class Deck {
    private ArrayList<Card> deck = new ArrayList<>();

    public Deck() {
        String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

        for (String suit : suits) {
            for (String rank : ranks) {
                deck.add(new Card(suit, rank));
            }
        }
    }

    public Card drawCard() {
        Random rand = new Random();
        return deck.get(rand.nextInt(52));
    }

    public int size() {
        return deck.size();
    }
}
