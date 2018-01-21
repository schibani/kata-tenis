package com.schibani.katatenis;

import com.schibani.katatenis.Player.PlayerBuilder;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.schibani.katatenis.GameScore.*;
import static org.assertj.core.api.Assertions.assertThat;

public class TenisGameRuleTest {

    private PlayerBuilder playerBuilder = PlayerBuilder.builder();

    @Test
    public void winGamePointAgainst_lastScoreIs0_scoreBecomes15() {
        // given
        final GameScore federerCurrentGameScore = POINT_0;
        Player federer = playerBuilder.withCurrentGameScore(federerCurrentGameScore).build();
        Player nadal = playerBuilder.build();

        // when
        federer.winGamePointAgainst(nadal);

        // then
        assertThat(federer.getCurrentGameScore()).isEqualTo(POINT_15);
    }

    @Test
    public void winGamePointAgainst_gameScoreIs15_scoreBecomes30() {
        // given
        final GameScore federerCurrentGameScore = POINT_15;
        Player federer = playerBuilder.withCurrentGameScore(federerCurrentGameScore).build();
        Player nadal = playerBuilder.build();

        // when
        federer.winGamePointAgainst(nadal);

        // then
        assertThat(federer.getCurrentGameScore()).isEqualTo(POINT_30);
    }

    @Test
    public void winGamePointAgainst_gameScoreIs30_gameScoreBecomes40() {
        // given
        final GameScore federerCurrentGameScore = POINT_30;
        Player federer = playerBuilder.withCurrentGameScore(federerCurrentGameScore).build();
        Player nadal = playerBuilder.build();

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
        Player federer = playerBuilder.withCurrentGameScore(federerCurrentGameScore).build();
        Player nadal = playerBuilder.withCurrentGameScore(nadalCurrentGameScore).build();

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
        Player federer = playerBuilder.withCurrentGameScore(federerCurrentGameScore).build();
        Player nadal = playerBuilder.withCurrentGameScore(nadalCurrentGameScore).build();

        // when
        federer.winGamePointAgainst(nadal);

        // then
        assertThat(federer.getCurrentGameScore()).isEqualTo(ADVANTAGE);
    }

    @Test
    public void winGamePointAgainst_gameScoreIsAdvantage_gameWinned() {
        // given
        final GameScore currentScore = ADVANTAGE;
        Player federer = playerBuilder.withCurrentGameScore(currentScore).build();
        Player nadal = playerBuilder.withCurrentGameScore(POINT_40).build();

        // when
        federer.winGamePointAgainst(nadal);

        // then
        assertThat(federer.getCurrentGameScore()).isEqualTo(WIN);
    }

    @Test
    public void loosePoint_advantage_gameScoreBecomes40() {
        // given
        final GameScore actualScore = ADVANTAGE;
        Player federer = playerBuilder.withCurrentGameScore(actualScore).build();

        // when
        federer.loosePoint();

        // then
        assertThat(federer.getCurrentGameScore()).isEqualTo(POINT_40);
    }

    @Test
    public void loosePoint_gameScoreNotAdvantage_gameScoreNotChanges() {
        // given
        final GameScore currentScore = POINT_40;
        Player federer = playerBuilder.withCurrentGameScore(currentScore).build();

        // when
        federer.loosePoint();

        // then
        assertThat(federer.getCurrentGameScore()).isEqualTo(POINT_40);
    }

    @Test
    public void winGameAgainst_setScoreBetween0And5_setScoreIncremented() {
        // given
        final List<Integer> setScores = Arrays.asList(0, 1, 2, 3, 4);
        final Player nadal = playerBuilder.build();

        setScores.forEach(currentSetScore -> {
            final Player federer = playerBuilder.withCurrentSetScore(currentSetScore).build();

            // when
            federer.winGameAgainst(nadal);

            // then
            assertThat(federer.getCurrentSetScore()).isEqualTo(currentSetScore + 1);
        });
    }

    @Test
    public void winGameAgainst_setScoreIs5AndOtherPlayerScoreIsLessThan4_setWinned() {
        // given
        final int federerSetScore = 5;
        final List<Integer> nadalSetScores = Arrays.asList(0, 1, 2, 3, 4);
        nadalSetScores.forEach(nadalCurrentSetScore -> {
            final Player federer = playerBuilder.withCurrentSetScore(federerSetScore).build();
            final Player nadal = playerBuilder.withCurrentSetScore(nadalCurrentSetScore).build();
            // when
            federer.winGameAgainst(nadal);

            // then
            assertThat(federer.getCurrentSetScore()).isEqualTo(6);
            assertThat(federer.getNumberOfSetsWinned()).isEqualTo(1);
        });
    }

    @Test
    public void winGameAgainst_setScoreIs5AndOtherPlayerScoreIs5_setNotWinned() {
        // given
        final int federerSetScore = 5;
        final int nadalSetScore = 5;
        final int federerNumberOfSetsWinned = 2;
        final Player federer = playerBuilder
                .withCurrentSetScore(federerSetScore)
                .withNumberOfSetsWinned(federerNumberOfSetsWinned)
                .build();
        final Player nadal = playerBuilder.withCurrentSetScore(nadalSetScore).build();

        // when
        federer.winGameAgainst(nadal);

        // then
        assertThat(federer.getCurrentSetScore()).isEqualTo(6);
        assertThat(federer.getNumberOfSetsWinned()).isEqualTo(2);
    }

    @Test
    public void winGameAgainst_setScoreIs6AndOtherPlayerScoreIs5_setWinned() {
        // given
        final int federerSetScore = 6;
        final int nadalSetScore = 5;
        final int federerNumberOfSetsWinned = 3;
        final Player federer = playerBuilder
                .withCurrentSetScore(federerSetScore)
                .withNumberOfSetsWinned(federerNumberOfSetsWinned)
                .build();
        final Player nadal = playerBuilder.withCurrentSetScore(nadalSetScore).build();

        // when
        federer.winGameAgainst(nadal);

        // then
        assertThat(federer.getCurrentSetScore()).isEqualTo(7);
        assertThat(federer.getNumberOfSetsWinned()).isEqualTo(4);
    }

    @Test
    public void winGameAgainst_setScoreIs5AndOtherPlayerScoreIs6_tieBreakAnSetNotWinned() {
        // given
        final int federerSetScore = 5;
        final int nadalSetScore = 6;
        final int federerNumberOfSetsWinned = 2;
        final Player federer = playerBuilder
                .withCurrentSetScore(federerSetScore)
                .withNumberOfSetsWinned(federerNumberOfSetsWinned)
                .build();
        final Player nadal = playerBuilder.withCurrentSetScore(nadalSetScore).build();

        // when
        federer.winGameAgainst(nadal);

        // then
        assertThat(federer.getCurrentSetScore()).isEqualTo(6);
        assertThat(federer.getNumberOfSetsWinned()).isEqualTo(2);
    }

    @Test
    public void winGameAgainst_setScoreIs6AndOtherPlayerScoreIs6_tieBreakIncrementedAndMatchNotYetWinned() {
        // given
        final int federerSetScore = 6;
        final int nadalSetScore = 6;
        final int federerNumberOfSetsWinned = 2;
        final Player federer = playerBuilder
                .withCurrentSetScore(federerSetScore)
                .withNumberOfSetsWinned(federerNumberOfSetsWinned)
                .withCurrentTieBreakScore(0)
                .build();
        final Player nadal = playerBuilder.withCurrentSetScore(nadalSetScore).build();

        // when
        federer.winGameAgainst(nadal);

        // then
        assertThat(federer.getCurrentSetScore()).isEqualTo(6);
        assertThat(federer.getNumberOfSetsWinned()).isEqualTo(2);
        assertThat(federer.getCurrentTieBreakScore()).isEqualTo(1);
        assertThat(federer.isMatchWinned()).isFalse();
    }

    @Test
    public void winGameAgainst_tieBreakScoreMinNotReached_tieBreakIncrementedAndMatchNotYetWinned() {
        // given
        final int federerTieBreakScore = 4;
        final int nadalTieBreakScore = 1;
        final Player federer = playerBuilder
                .withCurrentSetScore(6)
                .withCurrentTieBreakScore(federerTieBreakScore)
                .build();
        final Player nadal = playerBuilder
                .withCurrentSetScore(6)
                .withCurrentTieBreakScore(nadalTieBreakScore)
                .build();

        // when
        federer.winGameAgainst(nadal);

        // then
        assertThat(federer.getCurrentTieBreakScore()).isEqualTo(5);
        assertThat(federer.isMatchWinned()).isFalse();
    }

    @Test
    public void winGameAgainst_tieBreakScoreMinReachedButDiffScoreNotReached_tieBreakIncrementedAndMatchNotYetWinned() {
        // given
        final int federerTieBreakScore = 5;
        final int nadalTieBreakScore = 5;
        final Player federer = playerBuilder
                .withCurrentSetScore(6)
                .withCurrentTieBreakScore(federerTieBreakScore)
                .build();
        final Player nadal = playerBuilder
                .withCurrentSetScore(6)
                .withCurrentTieBreakScore(nadalTieBreakScore)
                .build();

        // when
        federer.winGameAgainst(nadal);

        // then
        assertThat(federer.getCurrentTieBreakScore()).isEqualTo(6);
        assertThat(federer.isMatchWinned()).isFalse();
    }

    @Test
    public void winGameAgainst_tieBreakScoreRulleFullfiled_matchWinned() {
        // given
        final int federerTieBreakScore = 6;
        final int nadalTieBreakScore = 5;
        final Player federer = playerBuilder
                .withCurrentSetScore(6)
                .withCurrentTieBreakScore(federerTieBreakScore)
                .build();
        final Player nadal = playerBuilder
                .withCurrentSetScore(6)
                .withCurrentTieBreakScore(nadalTieBreakScore)
                .build();

        // when
        federer.winGameAgainst(nadal);

        // then
        assertThat(federer.getCurrentTieBreakScore()).isEqualTo(7);
        assertThat(federer.isMatchWinned()).isTrue();
    }
}
