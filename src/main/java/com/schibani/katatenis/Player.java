package com.schibani.katatenis;

import static com.schibani.katatenis.GameScore.*;

public class Player {

    private GameScore currentGameScore;

    private GameScore currentSetScore;

    public Player() {
        this.currentGameScore = POINT_0;
    }

    public Player(final GameScore score) {
        this.currentGameScore = score;
    }

    public void winGamePointAgainst(Player opponent) {
        switch (currentGameScore) {
            case POINT_0:
                currentGameScore = POINT_15;
                break;
            case POINT_15:
                currentGameScore = POINT_30;
                break;
            case POINT_30:
                currentGameScore = POINT_40;
                break;
            case POINT_40:
                if (deuceIsActivated(opponent.getCurrentGameScore())) {
                    currentGameScore = ADVANTAGE;
                } else {
                    currentGameScore = WIN;
                }
                break;
            case ADVANTAGE:
                currentGameScore = WIN;
                break;
        }
    }

    private boolean deuceIsActivated(GameScore opponentScore) {
        return this.currentGameScore == POINT_40 && opponentScore == POINT_40;
    }

    public void loosePoint() {
        if (currentGameScore == ADVANTAGE) {
            currentGameScore = POINT_40;
        }
    }

    private void winSet(){

    }

    public GameScore getCurrentGameScore() {
        return currentGameScore;
    }

    public GameScore getCurrentSetScore() {
        return currentSetScore;
    }
}
