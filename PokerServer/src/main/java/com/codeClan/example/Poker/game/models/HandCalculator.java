package com.codeClan.example.Poker.game.models;

import java.util.*;

public class HandCalculator {

    // CALCULATES SETS

    public int isFourOfAKind(PlayerHand playerHand) {
        List<Card> cards = playerHand.getPlayerCards();
        Collections.sort(cards, Comparator.comparingInt(card -> card.getCardValue().getValue()));
        int isFour = 0;
        for (int i = 0; i < cards.size() - 3; i++ ) {

            if(cards.get(i).getCardValue().getValue() == cards.get(i+1).getCardValue().getValue()
                    && cards.get(i+1).getCardValue().getValue()  == cards.get(i+2).getCardValue().getValue()
                    && cards.get(i+2).getCardValue().getValue()  == cards.get(i+3).getCardValue().getValue() ) {
                isFour = cards.get(i).getCardValue().getValue() ;
            }
        }
        if(isFour == 1){
            isFour = 14;
        }
        return isFour;
    }

    public int isThreeOfAKind(PlayerHand playerHand) {
        List<Card> cards = playerHand.getPlayerCards();
        Collections.sort(cards, Comparator.comparingInt(card -> card.getCardValue().getValue()));
        int value = 0;
        boolean aces = false;

        for (int i = 0; i < cards.size() - 2; i++ ) {
            if( cards.get(i).getCardValue().getValue() == cards.get(i+1).getCardValue().getValue()
                    && cards.get(i+1).getCardValue().getValue() == cards.get(i+2).getCardValue().getValue()) {
                value = cards.get(i).getCardValue().getValue();
                if (value == 1){
                    aces = true;
                }
            }
        }
        if (aces){
            value = 14;
        }
        return value;
    }

    public int isPair(PlayerHand playerHand) {
        List<Card> cards = playerHand.getPlayerCards();
        Collections.sort(cards, Comparator.comparingInt(card -> card.getCardValue().getValue()));
        int value = 0;
        boolean aces = false;

        for (int i = 0; i < cards.size() - 1; i++ ) {
            if(cards.get(i).getCardValue().getValue() == cards.get(i+1).getCardValue().getValue()) {
                value = cards.get(i).getCardValue().getValue();
                if (value == 1){
                    aces = true;
                }
            }
        }

        if (aces){
            value = 14;
        }
        return value;
    }


    public HashMap<String, Object> isFlush(PlayerHand playerHand) {
        List<Card> cards = playerHand.getPlayerCards();
        int value = 0;
        HashMap<String, Object> player = new HashMap<>();
        boolean ace = false;
        ArrayList<Card> hearts = new ArrayList<>();
        ArrayList<Card> diamonds = new ArrayList<>();
        ArrayList<Card> clubs = new ArrayList<>();
        ArrayList<Card> spades = new ArrayList<>();

        for (int i = 0; i < cards.size(); i++) {

            if (cards.get(i).getCardSuitType() == CardSuitType.HEARTS){
                hearts.add(cards.get(i));
            } else if (cards.get(i).getCardSuitType() == CardSuitType.DIAMONDS){
                diamonds.add(cards.get(i));
            } else if (cards.get(i).getCardSuitType() == CardSuitType.CLUBS){
                clubs.add(cards.get(i));
            } else if (cards.get(i).getCardSuitType() == CardSuitType.SPADES) {
                spades.add(cards.get(i));
            }
        }

        if (hearts.size() >= 5){
            Collections.sort(hearts, Comparator.comparingInt(card -> card.getCardValue().getValue()));
            int size = hearts.size();
            player.put("cards", hearts);
            int highest = hearts.get(size-1).getCardValue().getValue();
            value = highest;
            if(hearts.get(0).getCardValue().getValue() == 1){
                value = 14;
            }
        } else if (diamonds.size() >= 5){
            Collections.sort(diamonds, Comparator.comparingInt(card -> card.getCardValue().getValue()));
            int size = diamonds.size();
            player.put("cards", diamonds);
            int highest = diamonds.get(size-1).getCardValue().getValue();
            value = highest;
            if(diamonds.get(0).getCardValue().getValue() == 1){
                value = 14;
            }
        } else if (clubs.size() >= 5){
            Collections.sort(clubs, Comparator.comparingInt(card -> card.getCardValue().getValue()));
            int size = clubs.size();
            player.put("cards", clubs);
            int highest = clubs.get(size-1).getCardValue().getValue();
            value = highest;
            if(clubs.get(0).getCardValue().getValue() == 1){
                value = 14;
            }
        } else if (spades.size() >= 5) {
            Collections.sort(spades, Comparator.comparingInt(card -> card.getCardValue().getValue()));
            int size = spades.size();
            player.put("cards", spades);
            int highest = spades.get(size-1).getCardValue().getValue();
            value = highest;
            if(spades.get(0).getCardValue().getValue() == 1){
                value = 14;
            }
        }
        player.put("value", value);
        return player;
    }

    public int isStraight(PlayerHand playerHand) {
        List<Card> cards = playerHand.getPlayerCards();
        Collections.sort(cards, Comparator.comparingInt(card -> card.getCardValue().getValue()));
        int consecutive = 1;
        int straight = 0;
        for (int i = 0; i < cards.size() - 1; i++) {
            if(cards.get(i).getCardValue().getValue() + 1 == cards.get(i+1).getCardValue().getValue()) {
                consecutive++;
                if (consecutive > 4) {
                    straight = cards.get(i+1).getCardValue().getValue();
                }
            } else if (cards.get(i).getCardValue().getValue() == cards.get(i+1).getCardValue().getValue()) {
                //nothing required
            } else {
                consecutive = 1;
            }
        }

        if(consecutive >= 4 && cards.get(0).getCardValue().getValue() == 1 && cards.get(6).getCardValue().getValue() == 13) {
            straight = 14;
        }

        return straight;
    }

    public int isStraightFlush(PlayerHand playerHand) {

        int value = 0;
        HashMap<String, Object> player = this.isFlush(playerHand);
        ArrayList<Card> cards = (ArrayList<Card>) player.get("cards");

        if(cards != null) {
            PlayerHand hand = new PlayerHand(cards, "");
            value = this.isStraight(hand);
        }

        return value;
    }

    public int isFullHouse(PlayerHand playerHand) {
        List<Card> cards = playerHand.getPlayerCards();
        Collections.sort(cards, Comparator.comparingInt(card -> card.getCardValue().getValue()));
        int trips = this.isThreeOfAKind(playerHand);
        if(trips == 14){
            trips = 1;
        }
        int pair = 0;
        if(trips != 0) {
            for (int i = 0; i < cards.size() - 1; i++ ) {
                if(cards.get(i).getCardValue().getValue() == cards.get(i+1).getCardValue().getValue()
                        && cards.get(i).getCardValue().getValue() != trips) {

                    pair = cards.get(i).getCardValue().getValue();
                }
            }
        }
        if (pair != 0){
            if(trips == 1){
                trips = 14;
            }
            return trips;
        }

        return 0;
    }

    public int isTwoPair(PlayerHand playerHand) {
        List<Card> cards = playerHand.getPlayerCards();
        Collections.sort(cards, Comparator.comparingInt(card -> card.getCardValue().getValue()));
        int pair = this.isPair(playerHand);
        if(pair == 14){
            pair = 1;
        }
        int lowPair = 0;
        if(pair != 0) {
            for (int i = 0; i < cards.size() - 1; i++ ) {
                if(cards.get(i).getCardValue().getValue() == cards.get(i+1).getCardValue().getValue()
                        && cards.get(i).getCardValue().getValue() != pair) {

                    lowPair = cards.get(i).getCardValue().getValue();
                }
            }
        }
        if (lowPair != 0){
            if(pair == 1){
                pair = 14;
            }
            return pair;
        }

        return 0;
    }

    public int highCard(PlayerHand playerHand1) {
        List<Card> cards1 = playerHand1.getPlayerCards();
        Collections.sort(cards1, Comparator.comparingInt(card -> card.getCardValue().getValue()));
        if(cards1.get(0).getCardValue() == CardFace.ACE){
            cards1.get(0).setCardValue(CardFace.ACEHIGH);
            Collections.sort(cards1, Comparator.comparingInt(card -> card.getCardValue().getValue()));
        }
        return cards1.get(6).getCardValue().getValue();
    }

    public PlayerHand overall(PlayerHand playerHand1, PlayerHand playerHand2) {

        int value1;
        int value2;

        value1 = isStraightFlush(playerHand1);
        value2 = isStraightFlush(playerHand2);
        if(value1 > value2) {
            return playerHand1;
        } else if (value2 > value1) {
            return playerHand2;
        } else if (value1 == value2 && value1 != 0) {
            return null;
        }

        value1 = isFourOfAKind(playerHand1);
        value2 = isFourOfAKind(playerHand2);
        if(value1 > value2) {
            return playerHand1;
        } else if (value2 > value1) {
            return playerHand2;
        } else if (value1 == value2 && value1 != 0) {
            return null;
        }

        value1 = isFullHouse(playerHand1);
        value2 = isFullHouse(playerHand2);
        if(value1 > value2) {
            return playerHand1;
        } else if (value2 > value1) {
            return playerHand2;
        } else if (value1 == value2 && value1 != 0) {
            return null;
        }

        value1 = (int) isFlush(playerHand1).get("value");
        value2 = (int) isFlush(playerHand2).get("value");

        if(value1 > value2) {
            return playerHand1;
        } else if (value2 > value1) {
            return playerHand2;
        } else if (value1 == value2 && value1 != 0) {
            return null;
        }

        value1 = isStraight(playerHand1);
        value2 = isStraight(playerHand2);
        if(value1 > value2) {
            return playerHand1;
        } else if (value2 > value1) {
            return playerHand2;
        } else if (value1 == value2 && value1 != 0) {
            return null;
        }

        value1 = isThreeOfAKind(playerHand1);
        value2 = isThreeOfAKind(playerHand2);
        if(value1 > value2) {
            return playerHand1;
        } else if (value2 > value1) {
            return playerHand2;
        } else if (value1 == value2 && value1 != 0) {
            return null;
        }

        value1 = isTwoPair(playerHand1);
        value2 = isTwoPair(playerHand2);
        if(value1 > value2) {
            return playerHand1;
        } else if (value2 > value1) {
            return playerHand2;
        } else if (value1 == value2 && value1 != 0) {
            return null;
        }

        value1 = isPair(playerHand1);
        value2 = isPair(playerHand2);
        if(value1 > value2) {
            return playerHand1;
        } else if (value2 > value1) {
            return playerHand2;
        } else if (value1 == value2 && value1 != 0) {
            return null;
        }

        value1 = highCard(playerHand1);
        value2 = highCard(playerHand2);
        if(value1 > value2) {
            return playerHand1;
        } else if (value2 > value1) {
            return playerHand2;
        }

        return null;
    }

}

