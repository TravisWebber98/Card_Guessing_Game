package com.ood.groupOne.card_guessing_game;

import java.util.Scanner;

public class Game {
    // Declare necessary variables
    private static int lvlAmt;
    private static int score = 0;
    private static boolean goodGuess = false;
    private static int guess = 1;
    private static int target = 3;

//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        System.out.print("Please input level number");
//        lvlAmt = sc.nextInt();
//        System.out.print("Please input guess");
//        guess = sc.nextInt();
//
//        checkGuess();
//        setScore();
//        getHint();
//        System.out.println(score);
//    }
    // TODO: Implement card class to function properly
    // Set player level, will prompt user from FX
    public void setLvl(int playerLvl) {
        this.lvlAmt = playerLvl;
    }

    // Get player level
    public int getLvl() {
        return this.lvlAmt;
    }

    // Check users guess against system's random card
    // TODO: implement check with timer
    public static void checkGuess() {
        if (lvlAmt == 1) {
            if (guess == 1) { // First level, check for correct suit
                goodGuess = true;
            }
        }
        else if (lvlAmt == 2) {
            if (guess == 2) { // Second level, check for correct rank
                goodGuess = true;
            }
        }
        else if (lvlAmt == 3) {
            if (guess == 3) { // Third level, check for correct suit&rank
                goodGuess = true;
            }
        }
        // If person guessed incorrect, reveal correct card
//        if (!goodGuess) {
//            showCard();
//        }
    }

    // Set the players score
    // Checks if guess is correct and gives points based on current level
    public static void setScore() {
        if (!goodGuess) { // TODO: Deduct points for running out timer
            score -= 100*lvlAmt;
            if (score < 0) {
                score = 0;
            }
        }
        // Could reduce to 100*lvlAmt, discuss point sys
        else if (goodGuess && lvlAmt == 1) {
            score += 100;
            // Go to level 2
        }

        else if (goodGuess && lvlAmt == 2) {
            score += 200;
            // Go to level 3
        }

        else if (goodGuess && lvlAmt == 3) {
            score += 300;
            // End game
        }

    }

    public static String getHint() {
        int hintCounter = 0;
        if (!goodGuess) {
            if (guess < target) {
                hintCounter++;
                return "Your guess is too low";
            }
            else if (guess > target) {
                hintCounter++;
                return "Your guess is too high";
            }

        }
        return "Great guess!";
    }
}
