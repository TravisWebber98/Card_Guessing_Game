package com.ood.groupOne.card_guessing_game;

public class Card {
    private final String suit;
    private final String rank;

    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public String suit() {
        return suit;
    }

    public String rank() {
        return rank;
    }

    @Override
    public String toString() {
        return rank + " " + suit;
    }
}
