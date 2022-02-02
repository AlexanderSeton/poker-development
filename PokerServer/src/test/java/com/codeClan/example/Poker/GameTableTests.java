//package com.codeClan.example.Poker;
//
//
//import com.codeClan.example.Poker.game.models.Card;
//import com.codeClan.example.Poker.game.models.Deck;
//import com.codeClan.example.Poker.game.models.GameTable;
//import com.codeClan.example.Poker.game.models.Player;
//import com.codeClan.example.Poker.game.models.game.Dealer;
//import com.codeClan.example.Poker.game.models.game.bettingRound.PreFlopBetting;
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//@SpringBootTest
//public class GameTableTests {
//
//    GameTable gameTable;
//    Player player1;
//    Player player2;
//    Player player3;
//    Card card1;
//    Card card2;
//    Card card3;
//    Deck deck1;
//    Dealer dealer1;
//    PreFlopBetting preFlopBetting;
//
//    @Before
//    public void before(){
//        player1 = new Player("Player1", 100.0, "Player1", "123");
//        player2 = new Player("Player2", 100.0, "Player2", "123");
//        player3 = new Player("Player3", 100.0, "Player3", "123");
//        List<Player> players1 = new ArrayList<>(Arrays.asList(player1, player2, player3));
//        ArrayList<Card> board1 = new ArrayList<>();
//        gameTable = new GameTable(0L, 100.0 ,players1, 40.0);
//        deck1 = new Deck();
//        dealer1 = new Dealer(gameTable, deck1);
//        preFlopBetting = new PreFlopBetting(players1, 30.00);
//    }
//
//    @Test
//    public void hasPlayers() {
//        assertEquals(3, gameTable.getPlayers().size());
//    }
//
//
//    @Test
//    public void hasPot(){
//        assertEquals(0,gameTable.getPot(),0.0);
//    }
//
//    @Test
//    public void hasEmptyBoard() {
//       assertEquals(0, gameTable.getBoard().size());
//    }
//
//    @Test
//    public void hasBigBlind() {
//        assertEquals(40.0, gameTable.getBigBlind(), 0.0);
//    }
//
//    @Test
//    public void hasSmallBlind() {
//        assertEquals(20.0, gameTable.getSmallBlind(), 0.0);
//    }
//
//
//    @Test
//    public void checkPlayersHaveCards(){
//        dealer1.dealHoleCards();
//        assertEquals(2, gameTable.getPlayers().get(0).getHand().size());
//        assertEquals(2, gameTable.getPlayers().get(1).getHand().size());
//        assertEquals(2, gameTable.getPlayers().get(2).getHand().size());
//    }
//
//    @Test
//    public void playerCanCall(){
//        player1.call(preFlopBetting.getLargestContribution());
//        assertEquals(70.00, player1.getStack(), 0.0);
//    }
//
//    @Test
//    public void playerCanBet() {
//        player1.bet(50);
//        assertEquals(50, player1.getStack(), 0.0);
//    }
//
//    @Test
//    public void playerFoldsWhenNotEnoughMoneyToBet() {
//        player1.bet(101.5);
//        assertTrue(player1.getFolded());
//        assertEquals(100, player1.getStack(), 0.0);
//    }
//
//    @Test
//    public void playerFoldsWhenNotEnoughMoneyToCall() {
//        player1.setStack(25);
//        player1.call(preFlopBetting.getLargestContribution());
//        assertTrue(player1.getFolded());
//    }
//
//
//
//    // pre-flop
//    @Test
//    public void canStartPreFlopBettingRound() {
//        dealer1.dealHoleCards();
//        gameTable.preFlopBettingRound();
//        System.out.println("end of test player1 stack: " + player1.getStack());
//    }
//
//
//}
