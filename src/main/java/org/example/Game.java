package org.example;

public class Game {

    // consructeur
    public Game() {
    }

    private int[] rolls = new int[21]; // 21 car on peut avoir 21 lancers au maximum
    private int currentRoll = 0; // pour savoir où on en est dans le tableau rolls

    public void roll(int pins) {
        rolls[currentRoll++] = pins;
    }

    // méthode pour calculer le score total
    public int score() {
        int score = 0;
        int frameIndex = 0; // pour savoir où on en est dans le tableau rolls
        for (int frame = 0; frame < 10; frame++) {
            if (isStrike(frameIndex)) {
                score += 10 + strikeBonus(frameIndex);
                frameIndex++;
            } else if (isSpare(frameIndex)) {
                score += 10 + spareBonus(frameIndex);
                frameIndex += 2;
            } else {
                score += sumOfBallsInFrame(frameIndex);
                frameIndex += 2;
            }
        }
        return score;
    }
    // méthode pour savoir si on a un "strike"
    private boolean isStrike(int framIndex) {
        return rolls[framIndex] == 10;
    }

    // méthode pour savoir si on a un "spare"
    private boolean isSpare(int frameIndex) {
        return rolls[frameIndex] + rolls[frameIndex + 1] == 10;
    }

    // méthode pour calculer le bonus d'un "strike"
    private int strikeBonus(int frameIndex) {
        return rolls[frameIndex + 1] + rolls[frameIndex + 2];
    }

    // méthode pour calculer le bonus d'un "spare"
    private int spareBonus(int frameIndex) {
        return rolls[frameIndex + 2];
    }

    // méthode pour calculer la somme des lancers d'un "frame"
    private int sumOfBallsInFrame(int frameIndex) {
        return rolls[frameIndex] + rolls[frameIndex + 1];
    }

}
