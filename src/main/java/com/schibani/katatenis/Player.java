package com.schibani.katatenis;

import static com.schibani.katatenis.GameScore.*;

public class Player {

    private final int TIE_BREAK_DIFF_MIN_SCORE = 2;

    private static final int WINING_SET_SCORE = 6;

    private static int EXTENDED_WINING_SET_SCORE = 7;

    private GameScore currentGameScore;

    private int currentSetScore;

    private int numberOfSetsWinned;

    private int currentTieBreakScore;

    private boolean matchWinned;

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
        if (tieBreakActivated(currentSetScore, oppenent.getCurrentSetScore())) {
            winTieBreakGame(oppenent);
        } else {
            winSimpleGame(oppenent);
        }
    }

    private void winSimpleGame(Player oppenent) {
        if (currentSetScore < EXTENDED_WINING_SET_SCORE) {
            incrementCurrentSetScore();
        }

        if (isSetWinned(currentSetScore, oppenent.getCurrentSetScore())) {
            incrementNumberOfSetsWinned();
        }
    }

    private void winTieBreakGame(Player oppenent) {
        if (!matchWinned) {
            incrementCurrentTieBreakScore();
            if (isTieBreakRuleFullfiled(oppenent)) {
                matchWinned = true;
            }
        }
    }

    private boolean isTieBreakRuleFullfiled(Player oppenent) {
        return isMinimumTieBreakScoreReached()
                && isTieBreakScoreDifferenceBetweenPlayersReached(oppenent);
    }

    private boolean isTieBreakScoreDifferenceBetweenPlayersReached(Player oppenent) {
        return currentTieBreakScore - oppenent.getCurrentTieBreakScore() == TIE_BREAK_DIFF_MIN_SCORE;
    }

    private boolean isMinimumTieBreakScoreReached() {
        return currentTieBreakScore >= WINING_SET_SCORE;
    }

    private boolean tieBreakActivated(int currentSetScore, int otherPlayerCurrentSetScore) {
        return currentSetScore == WINING_SET_SCORE && otherPlayerCurrentSetScore == WINING_SET_SCORE;
    }

    private void incrementNumberOfSetsWinned() {
        numberOfSetsWinned++;
    }

    private void incrementCurrentSetScore() {
        currentSetScore++;
    }

    private void incrementCurrentTieBreakScore() {
        currentTieBreakScore++;
    }

    private boolean isSetWinned(int setScore, int setScoreOfOtherPlayer) {
        return (setScore == EXTENDED_WINING_SET_SCORE)
                || (setScore == WINING_SET_SCORE && setScoreOfOtherPlayer <= 4);
    }

    public GameScore getCurrentGameScore() {
        return currentGameScore;
    }

    public int getCurrentSetScore() {
        return currentSetScore;
    }

    public int getNumberOfSetsWinned() {
        return numberOfSetsWinned;
    }

    public int getCurrentTieBreakScore() {
        return currentTieBreakScore;
    }

    public boolean isMatchWinned() {
        return matchWinned;
    }

    public static final class PlayerBuilder {
        private GameScore currentGameScore = POINT_0;
        private int currentSetScore;
        private int numberOfSetsWinned;
        private int currentTieBreakScore;

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

        public PlayerBuilder withCurrentTieBreakScore(int currentTieBreakScore) {
            this.currentTieBreakScore = currentTieBreakScore;
            return this;
        }

        public Player build() {
            Player player = new Player();
            player.numberOfSetsWinned = this.numberOfSetsWinned;
            player.currentGameScore = this.currentGameScore;
            player.currentSetScore = this.currentSetScore;
            player.currentTieBreakScore = this.currentTieBreakScore;
            return player;
        }
    }
}
