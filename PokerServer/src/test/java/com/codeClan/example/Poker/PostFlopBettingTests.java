//package com.codeClan.example.Poker;
//
//import com.codeClan.example.Poker.game.models.Player;
//import com.codeClan.example.Poker.game.models.game.bettingRound.PostFlopBetting;
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//
//@SpringBootTest
//public class PostFlopBettingTests {
//
//    private PostFlopBetting betting1;
//    private PostFlopBetting betting2;
//    private Player player1;
//    private Player player2;
//    private Player player3;
//    private Player player4;
//    private Player player5;
//    private Player player6;
//    private Player player7;
//    private Player player8;
//    private ArrayList<Player> players1;
//    private ArrayList<Player> players2;
//
//    @Before
//    public void before() {
//        player1 = new Player("Jim", 100.0, "Jim2", "");
//        player1.setFolded(true);
//        player2 = new Player("Jim", 100.0, "Jim2", "");
//        player2.setFolded(true);
//        player3 = new Player("Jim", 100.0, "Jim2", "");
//        player3.setFolded(true);
//        player4 = new Player("Jim", 100.0, "Jim2", "");
//        player4.setFolded(false);
//        players1 = new ArrayList<>();
//        players1.add(player1);
//        players1.add(player2);
//        players1.add(player3);
//        players1.add(player4);
//        player5 = new Player("P5", 100.0, "Jim2", "");
//        player6 = new Player("P6", 100.0, "Jim2", "");
//        player7 = new Player("P7", 100.0, "Jim2", "");
//        player8 = new Player("P8", 100.0, "Jim2", "");
//        players2 = new ArrayList<>();
//        players2.add(player5);
//        players2.add(player6);
//        players2.add(player7);
//        players2.add(player8);
//        betting1 = new PostFlopBetting(players1, 100.0);
//        betting2 = new PostFlopBetting(players2, 100.00);
//    }
//
//    @Test
//    public void canSetCompleteToTrueOnOnePlayerRemaining() {
//        assertEquals(true, betting1.checkSinglePlayerRemaining());
//    }
//
//    @Test
//    public void canSetNextUnfoldedPlayerToActive() {
//        betting1.setUpRound();
//        betting1.setNextUnfoldedToActive();
//        assertEquals(true, player4.isActive());
//    }
//
//    @Test
//    public void canMoveToNextActivePlayerWithOnPlayerBet() {
//        betting1.setUpRound();
//        betting2.onPlayerBet(0);
//        assertEquals(true, player6.isActive());
//    }
//
//    @Test
//    public void canSetCompleteWithRoundOfNoBets() {
//        betting1.setUpRound();
//        betting2.onPlayerBet(0);
//        betting2.onPlayerBet(0);
//        betting2.onPlayerBet(0);
//        betting2.onPlayerBet(0);
//        assertEquals(true, betting2.isComplete());
//    }
//
//    @Test
//    public void doesntSetCompleteWithRoundWithBets() {
//        betting1.setUpRound();
//        betting2.onPlayerBet(0);
//        betting2.onPlayerBet(0);
//        player7.bet(5);
//        betting2.onPlayerBet(5);
//        player8.bet(5);
//        betting2.onPlayerBet(5);
//        assertEquals(false, betting2.isComplete());
//    }
//
//    @Test
//    public void setsCompleteInSecondRoundAfterNoCalls() {
//        betting1.setUpRound();
//        betting2.onPlayerBet(0);
//        betting2.onPlayerBet(0);
//        player7.bet(5);
//        betting2.onPlayerBet(5);
//        player8.bet(5);
//        betting2.onPlayerBet(5);
//        player5.setFolded(true);
//        betting2.onPlayerBet(0);
//        player6.setFolded(true);
//        betting2.onPlayerBet(0);
//        assertEquals(true, betting2.isComplete());
//    }
//
//    @Test
//    public void setsCompleteOnRoundWithAllEqualBets() {
//        betting1.setUpRound();
//        player5.bet(5);
//        betting2.onPlayerBet(5);
//        player6.bet(5);
//        betting2.onPlayerBet(5);
//        player7.bet(5);
//        betting2.onPlayerBet(5);
//        player8.bet(5);
//        betting2.onPlayerBet(5);
//        assertEquals(true, betting2.isComplete());
//    }
//
//    @Test
//    public void setsCompleteOnSecondRoundWithEqualBets() {
//        betting1.setUpRound();
//        player5.bet(5);
//        betting2.onPlayerBet(5);
//        player6.bet(0);
//        player6.setFolded(true);
//        betting2.onPlayerBet(0);
//        player7.bet(10);
//        betting2.onPlayerBet(10);
//        player8.bet(10);
//        betting2.onPlayerBet(10);
//        player5.bet(5);
//        betting2.onPlayerBet(5);
//        assertEquals(true, betting2.isComplete());
//    }
//
//}
