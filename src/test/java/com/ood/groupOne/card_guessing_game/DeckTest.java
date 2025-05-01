package com.ood.groupOne.card_guessing_game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {
    @Test
    void testDeckSize() {
        Deck deck = new Deck();
        assertEquals(52, deck.size()); // Should initialize with 52 cards
    }

    @Test
    void testDrawCard() {
        Deck deck = new Deck();
        Card drawn = deck.drawCard();
        assertNotNull(drawn);
        System.out.println("Drawn suit: " + drawn.suit());
        System.out.println("Drawn rank: " + drawn.rank());
    }
}

