package com.schibani.katatenis;

import static com.schibani.katatenis.GameScore.*;

public class Player {

    private GameScore currentGameScore;

    private int currentSetScore;

    private int numberOfSetsWinned;

    private static int MAX_SETSCORE_VALUE = 7;

    private Player() {
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

    public void winGameAgainst(Player oppenent) {
        if (currentSetScore < MAX_SETSCORE_VALUE) {
            incrementCurrentSetScore();
        }

        if (isSetWinned(currentSetScore, oppenent.getCurrentSetScore())) {
            incrementNumberOfSetsWinned();
        }
    }

    private void incrementNumberOfSetsWinned() {
        numberOfSetsWinned++;
    }

    private void incrementCurrentSetScore() {
        currentSetScore++;
    }

    private boolean isSetWinned(int setScore, int setScoreOfOtherPlayer) {
        return (setScore == MAX_SETSCORE_VALUE) || (setScore == 6 && setScoreOfOtherPlayer <= 4);
    }

    public GameScore getCurrentGameScore() {
        return currentGameScore;
    }

    public int getCurrentSetScore() {
        return currentSetScore;
    }

    private void getIncrementedSetScore() {
        currentSetScore++;
    }

    public int getNumberOfSetsWinned() {
        return numberOfSetsWinned;
    }


    public static final class PlayerBuilder {
        private GameScore currentGameScore = POINT_0;
        private int currentSetScore;
        private int numberOfSetsWinned;

        private PlayerBuilder() {
        }

        public static PlayerBuilder builder() {
            return new PlayerBuilder();
        }

        public PlayerBuilder withCurrentGameScore(GameScore currentGameScore) {
            this.currentGameScore = currentGameScore;
            return this;
        }

        public PlayerBuilder withCurrentSetScore(int currentSetScore) {
            this.currentSetScore = currentSetScore;
            return this;
        }

        public PlayerBuilder withNumberOfSetsWinned(int numberOfSetsWinned) {
            this.numberOfSetsWinned = numberOfSetsWinned;
            return this;
        }

        public Player build() {
            Player player = new Player();
            player.numberOfSetsWinned = this.numberOfSetsWinned;
            player.currentGameScore = this.currentGameScore;
            player.currentSetScore = this.currentSetScore;
            return player;
        }
    }
}
