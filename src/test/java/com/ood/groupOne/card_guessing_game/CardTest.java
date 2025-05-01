package com.ood.groupOne.card_guessing_game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CardTest {
    @Test
    void testCardCreation() {
        Card card = new Card("Spades", "Ace");
        assertEquals("Ace", card.rank());
        assertEquals("Spades", card.suit());
    }
}
