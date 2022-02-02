package com.codeClan.example.Poker;

import com.codeClan.example.Poker.game.models.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.Assert.assertEquals;


@SpringBootTest
public class CalculatorTests {

    Card ace1;
    Card ace2;
    Card ace3;
    Card ace4;
    Card king1;
    Card king2;
    Card queen1;
    Card queen2;
    Card jack1;
    Card jack2;
    Card ten1;
    Card ten2;
    Card nine1;
    Card nine2;
    Card eight1;
    Card seven1;
    Card four1;
    Card five1;
    List<Card> cards;
    List<Card> cards2;
    List<Card> cards3;
    List<Card> cards4;
    List<Card> cards5;
    List<Card> cards6;
    List<Card> cards7;
    List<Card> cards8;
    List<Card> cards9;
    List<Card> cards10;
    List<Card> cards11;
    List<Card> cards12;
    List<Card> cards13;
    PlayerHand hand1;
    PlayerHand hand2;
    PlayerHand hand3;
    PlayerHand hand4;
    PlayerHand hand5;
    PlayerHand hand6;
    PlayerHand hand7;
    PlayerHand hand8;
    PlayerHand hand9;
    PlayerHand hand10;
    PlayerHand hand11;
    PlayerHand hand12;
    PlayerHand hand13;
    HandCalculator calc;


    @Before
    public void before() {
        ace1 = new Card(CardSuitType.CLUBS, CardFace.ACE);
        ace2 = new Card(CardSuitType.DIAMONDS, CardFace.ACE);
        ace3 = new Card(CardSuitType.HEARTS, CardFace.ACE);
        ace4 = new Card(CardSuitType.SPADES, CardFace.ACE);
        king1 = new Card(CardSuitType.HEARTS, CardFace.KING);
        king2 = new Card(CardSuitType.DIAMONDS, CardFace.KING);
        queen1 = new Card(CardSuitType.CLUBS, CardFace.QUEEN);
        queen2 = new Card(CardSuitType.HEARTS, CardFace.QUEEN);
        jack1 = new Card(CardSuitType.HEARTS, CardFace.JACK);
        jack2 = new Card(CardSuitType.CLUBS, CardFace.JACK);
        ten1 = new Card(CardSuitType.HEARTS, CardFace.TEN);
        ten2 = new Card(CardSuitType.CLUBS, CardFace.TEN);
        nine1 = new Card(CardSuitType.CLUBS, CardFace.NINE);
        nine2 = new Card(CardSuitType.HEARTS, CardFace.NINE);
        eight1 = new Card(CardSuitType.CLUBS, CardFace.EIGHT);
        seven1 = new Card(CardSuitType.HEARTS, CardFace.SEVEN);
        four1 = new Card(CardSuitType.DIAMONDS, CardFace.FOUR);
        five1 = new Card(CardSuitType.DIAMONDS, CardFace.FIVE);
        cards = new ArrayList<>();
        cards.add(ace1);
        cards.add(ace2);
        cards.add(ace3);
        cards.add(ace4);
        cards.add(king1);
        cards.add(king2);
        cards.add(queen1);
        cards2 = new ArrayList<>();
        cards2.add(queen2);
        cards2.add(ace2);
        cards2.add(ace3);
        cards2.add(ace4);
        cards2.add(king1);
        cards2.add(king2);
        cards2.add(queen1);
        cards3 = new ArrayList<>();
        cards3.add(queen2);
        cards3.add(ace2);
        cards3.add(ace3);
        cards3.add(ace4);
        cards3.add(king1);
        cards3.add(king2);
        cards3.add(ace1);
        cards4 = new ArrayList<>();
        cards4.add(queen2);
        cards4.add(ace2);
        cards4.add(ace3);
        cards4.add(nine1);
        cards4.add(king1);
        cards4.add(king2);
        cards4.add(queen1);
        cards5 = new ArrayList<>();
        cards5.add(nine1);
        cards5.add(ten1);
        cards5.add(ten2);
        cards5.add(queen2);
        cards5.add(jack2);
        cards5.add(jack1);
        cards5.add(queen1);
        cards6 = new ArrayList<>();
        cards6.add(nine1);
        cards6.add(ten1);
        cards6.add(jack1);
        cards6.add(queen2);
        cards6.add(king1);
        cards6.add(ace1);
        cards6.add(eight1);
        cards7 = new ArrayList<>();
        cards7.add(ace3);
        cards7.add(ace4);
        cards7.add(jack2);
        cards7.add(queen1);
        cards7.add(ten2);
        cards7.add(nine1);
        cards7.add(eight1);
        cards8 = new ArrayList<>();
        cards8.add(ace3);
        cards8.add(king1);
        cards8.add(queen2);
        cards8.add(nine2);
        cards8.add(eight1);
        cards8.add(ten1);
        cards8.add(seven1);
        cards9 = new ArrayList<>();
        cards9.add(ace3);
        cards9.add(ace1);
        cards9.add(ace2);
        cards9.add(nine2);
        cards9.add(eight1);
        cards9.add(ten1);
        cards9.add(seven1);
        cards10 = new ArrayList<>();
        cards10.add(eight1);
        cards10.add(seven1);
        cards10.add(ace3);
        cards10.add(queen2);
        cards10.add(king1);
        cards10.add(four1);
        cards10.add(five1);
        cards11 = new ArrayList<>();
        cards11.add(eight1);
        cards11.add(seven1);
        cards11.add(jack1);
        cards11.add(queen2);
        cards11.add(king1);
        cards11.add(four1);
        cards11.add(five1);
        cards12 = new ArrayList<>();
        cards12.add(four1);
        cards12.add(five1);
        cards13 = new ArrayList<>();
        cards13.add(queen2);
        cards13.add(ace1);
        calc = new HandCalculator();
        hand1 = new PlayerHand(cards, "");
        hand2 = new PlayerHand(cards2, "");
        hand3 = new PlayerHand(cards3, "");
        hand4 = new PlayerHand(cards4, "");
        hand5 = new PlayerHand(cards5, "");
        hand6 = new PlayerHand(cards6, "");
        hand7 = new PlayerHand(cards7, "");
        hand8 = new PlayerHand(cards8, "");
        hand9 = new PlayerHand(cards9, "");
        hand10 = new PlayerHand(cards10, "");
        hand11 = new PlayerHand(cards11, "");
        hand12 = new PlayerHand(cards12, "");
        hand13 = new PlayerHand(cards13, "");
    }

    @Test
    public void canDetermineFourOfAKind() {
        assertEquals(14, calc.isFourOfAKind(hand1));
    }

    @Test
    public void canDetermineNotFourOfAKind() {
        assertEquals(0, calc.isFourOfAKind(hand2));
    }

    @Test
    public void canDetermineFourOfAKindOutOfOrder() {
        assertEquals(14, calc.isFourOfAKind(hand3));
    }

    @Test
    public void canDetermineTrips() {
        assertEquals(14, calc.isThreeOfAKind(hand2));
    }

    @Test
    public void canDetermineNotTrips() {
        assertEquals(0, calc.isThreeOfAKind(hand4));
    }

    @Test
    public void canDeterminePair() {
        assertEquals(12, calc.isPair(hand5));
    }

    @Test
    public void canDetermineNotPair() {
        assertEquals(0, calc.isPair(hand6));
    }

    @Test
    public void canDetermineFlush() {
        assertEquals(12, calc.isFlush(hand7).get("value"));
    }

    @Test
    public void canDetermineNotFlush() {
        assertEquals(0, calc.isFlush(hand1).get("value"));
    }

    @Test
    public void canDetermineStraight() {
        assertEquals(12, calc.isStraight(hand7));
    }

    @Test
    public void canDetermineNotStraight() {
        assertEquals(0, calc.isStraight(hand1));
    }

    @Test
    public void canDetermineStraightAceHigh() {
        assertEquals(14, calc.isStraight(hand6));
    }

    @Test
    public void canDetermineStraightFlush() {
        assertEquals(12, calc.isStraightFlush(hand7));
    }

    @Test
    public void canDetermineNotStraightFlushwithStriaght() {
        assertEquals(0, calc.isStraightFlush(hand6));
    }

    @Test
    public void canDetermineNotStraightFlushWithFLush() {
        assertEquals(0, calc.isStraightFlush(hand8));
    }

    @Test
    public void canDetermineFullHouse() {
        assertEquals(14, calc.isFullHouse(hand2));
    }

    @Test
    public void canDetermineNotFullHouse(){
        assertEquals(0, calc.isFullHouse(hand4));
    }

    @Test
    public void canDetermineNotFullHouseWhenTripsOnly(){
        assertEquals(0, calc.isFullHouse(hand9));
    }

    @Test
    public void canDetermineTwoPair() {
        assertEquals(14, calc.isTwoPair(hand4));
    }

    @Test
    public void canDetermineNotTwoPairWithSinglePair() {
        assertEquals(0, calc.isTwoPair(hand7));
    }

    @Test
    public void canReturnHighCard() {
        assertEquals(12, calc.highCard(hand5));
    }

    @Test
    public void canCompareStraightFlushAndNot() {
        assertEquals(hand7, calc.overall(hand7, hand2));
    }

    @Test
    public void canCompareStraightFlushAndFullHouse() {
        assertEquals(hand7, calc.overall(hand7, hand6));
    }

    @Test
    public void canCompareStraightFlushAndFourOfAKind() {
        assertEquals(hand7, calc.overall(hand7, hand1));
    }

    @Test
    public void canCompareFullHouseAndNot() {
        assertEquals(hand2, calc.overall(hand6, hand2));
    }

    @Test
    public void canCompareFlushAndNot() {
        assertEquals(hand7, calc.overall(hand7, hand5));
    }

    @Test
    public void canCompareFourOfAKindAndNot() {
        assertEquals(hand1, calc.overall(hand1, hand5));
    }

    @Test
    public void canCompareTripsAndNot() {
        assertEquals(hand2, calc.overall(hand2, hand5));
    }

    @Test
    public void canCompareTwoPairAndNot() {
        assertEquals(hand4, calc.overall(hand5, hand4));
    }

    @Test
    public void canComparePairAndNot() {
        assertEquals(hand5, calc.overall(hand5, hand10));
    }

    @Test
    public void canCompareHighCardsEqual() {
        assertEquals(null, calc.overall(hand10, hand10));
    }

    @Test
    public void canCompareHighCardsNotEqual() {
        assertEquals(hand10, calc.overall(hand11, hand10));
    }

}

