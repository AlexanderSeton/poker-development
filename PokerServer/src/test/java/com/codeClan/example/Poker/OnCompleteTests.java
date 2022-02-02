//package com.codeClan.example.Poker;
//
//import com.codeClan.example.Poker.game.models.*;
//import com.codeClan.example.Poker.game.models.game.GameEnd;
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//
//@SpringBootTest
//public class OnCompleteTests {
//
//    GameTable gameTable;
//    GameTable gameTable2;
//    Player player1;
//    Player player2;
//    Player player3;
//    Card ace1;
//    Card ace2;
//    Card ace3;
//    Card ace4;
//    Card king1;
//    Card king2;
//    Card queen1;
//    Card queen2;
//    Card jack1;
//    Card jack2;
//    Card ten1;
//    Card ten2;
//    Card nine1;
//    Card nine2;
//    Card eight1;
//    Card seven1;
//    Card four1;
//    Card five1;
//    List<Card> cards;
//    List<Card> cards2;
//    List<Card> cards3;
//    List<Card> cards4;
//    PlayerHand hand1;
//    PlayerHand hand2;
//    PlayerHand hand3;
//    Deck deck1;
//    List<Player> players1;
//    List<Player> players2;
//    ArrayList<Card> board1;
//    ArrayList<PlayerHand> hands;
//    ArrayList<PlayerHand> hands2;
//    GameEnd gameEnd;
//
//    @Before
//    public void before() {
//
//        player1 = new Player("Player1", 100.0, "Player1", "123");
//        player2 = new Player("Player2", 100.0, "Player2", "123");
//        player3 = new Player("Player3", 100.0, "Player3", "123");
//        ace1 = new Card(CardSuitType.CLUBS, CardFace.ACE);
//        ace2 = new Card(CardSuitType.DIAMONDS, CardFace.ACE);
//        ace3 = new Card(CardSuitType.HEARTS, CardFace.ACE);
//        ace4 = new Card(CardSuitType.SPADES, CardFace.ACE);
//        king1 = new Card(CardSuitType.HEARTS, CardFace.KING);
//        king2 = new Card(CardSuitType.DIAMONDS, CardFace.KING);
//        queen1 = new Card(CardSuitType.CLUBS, CardFace.QUEEN);
//        queen2 = new Card(CardSuitType.HEARTS, CardFace.QUEEN);
//        jack1 = new Card(CardSuitType.HEARTS, CardFace.JACK);
//        jack2 = new Card(CardSuitType.CLUBS, CardFace.JACK);
//        ten1 = new Card(CardSuitType.HEARTS, CardFace.TEN);
//        ten2 = new Card(CardSuitType.CLUBS, CardFace.TEN);
//        nine1 = new Card(CardSuitType.CLUBS, CardFace.NINE);
//        nine2 = new Card(CardSuitType.HEARTS, CardFace.NINE);
//        eight1 = new Card(CardSuitType.CLUBS, CardFace.EIGHT);
//        seven1 = new Card(CardSuitType.HEARTS, CardFace.SEVEN);
//        four1 = new Card(CardSuitType.DIAMONDS, CardFace.FOUR);
//        five1 = new Card(CardSuitType.DIAMONDS, CardFace.FIVE);
//        cards = new ArrayList<>();
//        cards.add(ace4);
//        cards.add(ace3);
//        cards.add(jack1);
//        cards.add(five1);
//        cards.add(queen1);
//        cards2 = new ArrayList<>();
//        cards2.add(queen2);
//        cards2.add(ace2);
//        cards3 = new ArrayList<>();
//        cards3.add(king2);
//        cards3.add(ace1);
//        cards4 = new ArrayList<>();
//        cards4.add(seven1);
//        cards4.add(jack2);
//        player1.setHand(cards2);
//        player2.setHand(cards3);
//        player3.setHand(cards4);
//        hand1 = new PlayerHand(cards2, "Player1");
//        hand2 = new PlayerHand(cards3, "Player2");
//        hand3 = new PlayerHand(cards4, "Player3");
//        players1 = new ArrayList<>(Arrays.asList(player1, player2, player3));
//        players2 = new ArrayList<>(Arrays.asList(player1));
//        board1 = new ArrayList<>();
//        board1.addAll(cards);
//        gameTable = new GameTable(30, players1, board1,40.0);
//        gameTable2 = new GameTable(30, players2, board1, 40.0);
//        deck1 = new Deck();
//        gameEnd = new GameEnd();
//        hands = new ArrayList<>();
//        hands.add(hand1);
//        hands.add(hand2);
//        hands.add(hand3);
//        hands2 = new ArrayList<>();
//        hands2.add(hand1);
//    }
//
//    @Test
//    public void canCalculateRemainingPlayers() {
//        assertEquals(players1, gameEnd.calculateRemainingPlayers((ArrayList<Player>) players1));
//    }
//
//    @Test
//    public void canCalculatePotShare() {
//        assertEquals(10.0, gameEnd.calculatePotShare(30.0, hands), 0.0);
//    }
//
//    @Test
//    public void canCalculateWinners() {
//        assertEquals(hands2.get(0).getUserName(), gameEnd.calculateWinners((ArrayList<Player>) players1, board1).get(0).getUserName());
//    }
//
//    @Test
//    public void canPayWinners() {
//        ArrayList<PlayerHand> winners = gameEnd.calculateWinners((ArrayList<Player>) players1, board1);
//        gameEnd.payWinners(30, winners, (ArrayList<Player>) players1);
//        assertEquals(130.0, player1.getStack(), 0.0);
//    }
//
//    @Test
//    public void canRunOverall() {
//        gameEnd.onComplete(gameTable);
//        assertEquals(130.0, player1.getStack(), 0.0);
//    }
//
//    @Test
//    public void canRunOverallWithOneRemaining() {
//        gameEnd.onComplete(gameTable);
//        assertEquals(130.0, player1.getStack(), 0.0);
//    }
//}
