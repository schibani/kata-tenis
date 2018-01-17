package com.schibani.katatenis;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TenisGameRuleTest {
    
    @Test
    public void winPointAgainst_lastScoreIs0_scoreBecomes15(){
        // given
        Player player = new Player(GameScore.POINT_0);
        Player opponent = new Player();

        // when
        player.winPointAgainst(opponent);

        // then
        assertThat(player.getActualScore()).isEqualTo(GameScore.POINT_15);
    }

    @Test
    public void winPointAgainst_scoreIs15_scoreBecomes30(){
        // given
        Player player = new Player(GameScore.POINT_15);
        Player opponent = new Player();

        // when
        player.winPointAgainst(opponent);

        // then
        assertThat(player.getActualScore()).isEqualTo(GameScore.POINT_30);
    }

    @Test
    public void winPointAgainst_scoreIs30_scoreBecomes40(){
        // given
        Player player = new Player(GameScore.POINT_30);
        Player opponent = new Player();

        // when
        player.winPointAgainst(opponent);

        // then
        assertThat(player.getActualScore()).isEqualTo(GameScore.POINT_40);
    }

    @Test
    public void winPointAgainst_scoreIs40AndOpponentScoreNot40_gameWinned(){
        // given
        Player player = new Player(GameScore.POINT_40);
        Player opponent = new Player(GameScore.POINT_30);

        // when
        player.winPointAgainst(opponent);

        // then
        assertThat(player.getActualScore()).isEqualTo(GameScore.WIN);
    }





    @Test
    public void winPointAgainst_scoreIs40_gameWinned(){
        // given
        Player player = new Player(GameScore.POINT_0);
        Player opponent = new Player();

        // when
        player.winPointAgainst(opponent);

        // then
        assertThat(player.getActualScore()).isEqualTo(GameScore.POINT_15);
    }










    @Test
    public void winPointAgainst_scoreIs40_gameWinned1(){
        // given

        // when

        // then
    }

}
