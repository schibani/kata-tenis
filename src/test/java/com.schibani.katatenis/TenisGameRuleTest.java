package com.schibani.katatenis;

import org.junit.Test;

import static com.schibani.katatenis.GameScore.*;
import static org.assertj.core.api.Assertions.assertThat;

public class TenisGameRuleTest {

    @Test
    public void winGamePointAgainst_lastScoreIs0_scoreBecomes15() {
        // given
        final GameScore federerCurrentGameScore = POINT_0;
        Player federer = new Player(federerCurrentGameScore);
        Player nadal = new Player();

        // when
        federer.winGamePointAgainst(nadal);

        // then
        assertThat(federer.getCurrentGameScore()).isEqualTo(POINT_15);
    }

    @Test
    public void winGamePointAgainst_gameScoreIs15_scoreBecomes30() {
        // given
        final GameScore federerCurrentGameScore = POINT_15;
        Player federer = new Player(federerCurrentGameScore);
        Player nadal = new Player();

        // when
        federer.winGamePointAgainst(nadal);

        // then
        assertThat(federer.getCurrentGameScore()).isEqualTo(POINT_30);
    }

    @Test
    public void winGamePointAgainst_gameScoreIs30_gameScoreBecomes40() {
        // given
        final GameScore federerCurrentGameScore = POINT_30;
        Player federer = new Player(federerCurrentGameScore);
        Player nadal = new Player();

        // when
        federer.winGamePointAgainst(nadal);

        // then
        assertThat(federer.getCurrentGameScore()).isEqualTo(POINT_40);
    }

    @Test
    public void winGamePointAgainst_gameScoreIs40AndOppenentScoreNot40_gameWinned() {
        // given
        final GameScore federerCurrentGameScore = POINT_40;
        final GameScore nadalCurrentGameScore = POINT_30;
        Player federer = new Player(federerCurrentGameScore);
        Player nadal = new Player(nadalCurrentGameScore);

        // when
        federer.winGamePointAgainst(nadal);

        // then
        assertThat(federer.getCurrentGameScore()).isEqualTo(WIN);
    }

    @Test
    public void winGamePointAgainst_deuce_gameScoreBecomesAdvantage() {
        // given
        final GameScore federerCurrentGameScore = POINT_40;
        final GameScore nadalCurrentGameScore = POINT_40;
        Player federer = new Player(federerCurrentGameScore);
        Player nadal = new Player(nadalCurrentGameScore);

        // when
        federer.winGamePointAgainst(nadal);

        // then
        assertThat(federer.getCurrentGameScore()).isEqualTo(ADVANTAGE);
    }

    @Test
    public void winGamePointAgainst_gameScoreIsAdvantage_gameWinned() {
        // given
        final GameScore currentScore = ADVANTAGE;
        Player federer = new Player(currentScore);
        Player nadal = new Player(POINT_40);

        // when
        federer.winGamePointAgainst(nadal);

        // then
        assertThat(federer.getCurrentGameScore()).isEqualTo(WIN);
    }

    @Test
    public void loosePoint_advantage_gameScoreBecomes40() {
        // given
        final GameScore actualScore = ADVANTAGE;
        Player federer = new Player(actualScore);

        // when
        federer.loosePoint();

        // then
        assertThat(federer.getCurrentGameScore()).isEqualTo(POINT_40);
    }

    @Test
    public void loosePoint_gameScoreNotAdvantage_gameScoreNotChanges() {
        // given
        final GameScore currentScore = POINT_40;
        Player federer = new Player(currentScore);

        // when
        federer.loosePoint();

        // then
        assertThat(federer.getCurrentGameScore()).isEqualTo(POINT_40);
    }

    @Test
    public void winGameAgainst_gameScoreIs40_gameWinned1() {
        // given

        // when

        // then
    }

    @Test
    public void _winGamePointAgainst_gameScoreIs40_gameWinned1() {
        // given

        // when

        // then
    }

}
