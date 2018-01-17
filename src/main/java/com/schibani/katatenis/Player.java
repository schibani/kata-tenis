package com.schibani.katatenis;

import static com.schibani.katatenis.GameScore.*;

public class Player {

    private GameScore actualScore;

    public Player() {
        this.actualScore = POINT_0;
    }

    public Player(final GameScore score) {
        this.actualScore = score;
    }

    public void winPointAgainst(Player opponent){
        switch(actualScore){
            case POINT_0:
                actualScore = POINT_15;
                break;
            case POINT_15:
                actualScore = POINT_30;
                break;
            case POINT_30:
                actualScore = POINT_40;
                break;
            case POINT_40:
                if(deuceIsActivated(opponent.getActualScore())){
                    actualScore = ADVANTAGE;
                } else{
                    actualScore = WIN;
                }
                break;
        }
    }

    private boolean deuceIsActivated(GameScore opponentScore) {
        return this.actualScore == POINT_40 && opponentScore == POINT_40;
    }

    public GameScore getActualScore() {
        return actualScore;
    }

    public void setActualScore(GameScore actualScore) {
        this.actualScore = actualScore;
    }
}
