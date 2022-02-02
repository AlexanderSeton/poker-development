package com.codeClan.example.Poker.game.models.game;

import com.codeClan.example.Poker.game.models.Card;
import com.codeClan.example.Poker.game.models.Deck;
import com.codeClan.example.Poker.game.models.GameTable;
import com.codeClan.example.Poker.game.models.Player;

import java.util.List;

public class Dealer {

    private List<Player> players;

    private GameTable gameTable;

    private Deck deck;

    public Dealer(GameTable gameTable) {
        this.gameTable = gameTable;
//        this.deck = gameTable.getDeck();
        Deck deck = new Deck();
        this.deck = deck;
    }

    public Dealer() {
    }

    public void dealHoleCards() {
        for(int i = 0; i < this.gameTable.getPlayers().size(); i++) {
            Card card1 = this.deck.dealCard();
            Card card2 = this.deck.dealCard();
            this.gameTable.getPlayers().get(i).addCard(card1);
            this.gameTable.getPlayers().get(i).addCard(card2);
            card1.setPlayer(this.gameTable.getPlayers().get(i));
            card2.setPlayer(this.gameTable.getPlayers().get(i));
        }
    }

    public void dealFlop() {
        Card card1 = deck.dealCard();
        Card card2 = deck.dealCard();
        Card card3 = deck.dealCard();
        this.gameTable.addCardToBoard(card1);
        this.gameTable.addCardToBoard(card2);
        this.gameTable.addCardToBoard(card3);
    }

    public void dealPostFlop() {
        Card card = deck.dealCard();
        this.gameTable.addCardToBoard(card);
    }
}


