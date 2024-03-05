package org.example;

public class GameMock {
    private int[] rolls = new int[21]; // 21 car on peut avoir 21 lancers au maximum
    private int currentRoll = 0; // pour savoir où on en est dans le tableau rolls

    private final TableauAffichage tableauAffichage;

    public GameMock(TableauAffichage tableauAffichage) {
        this.tableauAffichage = tableauAffichage;
        tableauAffichage.seConnecter();
    }
    public void roll(int pins) {
        rolls[currentRoll++] = pins;

        //if (pins == 10) showStrike();
        if (pins == 10) {
            if (currentRoll % 2 == 0) {
                tableauAffichage.showStrike(TableauAffichage.StrikeSerie.PREMIER);
            } else {
                tableauAffichage.showStrike(TableauAffichage.StrikeSerie.SECOND);
            }
        }

        if (currentRoll % 2 == 0 && rolls[currentRoll - 1] + rolls[currentRoll - 2] == 10) {
            tableauAffichage.showSpare();
        }
    }
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

    public void endGame() {
        tableauAffichage.updateBestScores(score());
    }
}
