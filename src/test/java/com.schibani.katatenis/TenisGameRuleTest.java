package com.schibani.katatenis;

import org.junit.Test;

import static com.schibani.katatenis.GameScore.*;
import static org.assertj.core.api.Assertions.assertThat;

public class TenisGameRuleTest {

    @Test
    public void winPointAgainst_lastScoreIs0_scoreBecomes15() {
        // given
        Player federer = new Player(POINT_0);
        Player nadal = new Player();

        // when
        federer.winPointAgainst(nadal);

        // then
        assertThat(federer.getActualScore()).isEqualTo(POINT_15);
    }

    @Test
    public void winPointAgainst_scoreIs15_scoreBecomes30() {
        // given
        Player federer = new Player(POINT_15);
        Player nadal = new Player();

        // when
        federer.winPointAgainst(nadal);

        // then
        assertThat(federer.getActualScore()).isEqualTo(POINT_30);
    }

    @Test
    public void winPointAgainst_scoreIs30_scoreBecomes40() {
        // given
        Player federer = new Player(POINT_30);
        Player nadal = new Player();

        // when
        federer.winPointAgainst(nadal);

        // then
        assertThat(federer.getActualScore()).isEqualTo(POINT_40);
    }

    @Test
    public void winPointAgainst_scoreIs40AndnadalScoreNot40_gameWinned() {
        // given
        Player federer = new Player(POINT_40);
        Player nadal = new Player(POINT_30);

        // when
        federer.winPointAgainst(nadal);

        // then
        assertThat(federer.getActualScore()).isEqualTo(WIN);
    }

    @Test
    public void winPointAgainst_deuce_scoreBecomesAdvantage() {
        // given
        Player federer = new Player(POINT_40);
        Player nadal = new Player(POINT_40);

        // when
        federer.winPointAgainst(nadal);

        // then
        assertThat(federer.getActualScore()).isEqualTo(ADVANTAGE);
    }

    @Test
    public void winPointAgainst_advantage_gameWinned() {
        // given
        Player federer = new Player(ADVANTAGE);
        Player nadal = new Player(POINT_40);

        // when
        federer.winPointAgainst(nadal);

        // then
        assertThat(federer.getActualScore()).isEqualTo(WIN);
    }

    @Test
    public void loosePoint_advantage_scoreBecomes40() {
        // given
        Player federer = new Player(ADVANTAGE);

        // when
        federer.loosePoint();

        // then
        assertThat(federer.getActualScore()).isEqualTo(POINT_40);
    }

    @Test
    public void winPointAgainst_scoreIs40_gameWinned1() {
        // given

        // when

        // then
    }

}
