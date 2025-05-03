package com.ood.groupOne.card_guessing_game;

import java.util.Scanner;

public class Game {
    // Declare necessary variables
    private static int lvlAmt;
    private static int score;
    private static boolean goodGuess;

    public Game() {
        score = 0;
        goodGuess = false;
    }

    // TODO: Implement card class to function properly
    // Set player level, will prompt user from FX
    public void setLvl(int playerLvl) {
        this.lvlAmt = playerLvl;
    }

    // Get player level
    public int getLvl() {
        return this.lvlAmt;
    }

    public void setGuess(boolean playerGuess) {
        goodGuess = playerGuess;
    }

    public boolean getGuess() {
        return this.goodGuess;
    }

    // Set the players score
    // Checks if guess is correct and gives points based on current level
    public static void setScore(int level) {
        if (!goodGuess) {
            score = (score < 50) ? 0 : score - 50;
        }
        else {
            score += 100*level;
        }

    }
}
