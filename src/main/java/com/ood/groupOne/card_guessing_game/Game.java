package com.ood.groupOne.card_guessing_game;

public class Game {
    // Declare necessary variables
    private static int lvlAmt;
    private static int score = 0;
    private static boolean goodGuess = false;

    public Game() {
//        score = 0;
//        goodGuess = false;
    }

    // TODO: Implement card class to function properly
    // Set player level, will prompt user from FX
    public static void setLvl(int playerLvl) {
        lvlAmt = playerLvl;
    }

    // Get player level
    public static int getLvl() {
        return lvlAmt;
    }

    public static void setGuess(boolean playerGuess) {
        goodGuess = playerGuess;
    }

    public static boolean getGuess() {
        return goodGuess;
    }

    public static int getScore() {
        return score;
    }

    public static void resetScore() {
        score = 0;
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
