package com.codeClan.example.Poker.game.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private List<Card> cards;



    public Deck() {
        this.resetDeck();
    }

    public void resetDeck() {
        List<Card> newDeck = new ArrayList<>();
        for (int i=0; i<CardSuitType.values().length; i++) {
            for (int j=0; j< CardFace.values().length-1; j++) {
                Card tempCard = new Card(CardSuitType.values()[i], CardFace.values()[j]);
                newDeck.add(tempCard);
            }
        }
        Collections.shuffle(newDeck);
        this.cards = newDeck;
    }

    public Card dealCard() {
        Card temp = this.cards.get(0);
        this.cards.remove(0);
        return temp;
    }

    public List<Card> getDeck() {
        return this.cards;
    }

}
