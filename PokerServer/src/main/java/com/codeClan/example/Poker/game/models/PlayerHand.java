package com.codeClan.example.Poker.game.models;

import java.util.List;
import java.util.ArrayList;

public class PlayerHand {

    private List<Card> playerCards;
    private String userName;

    public PlayerHand(List<Card> playerCards, String userName) {
        this.playerCards = playerCards;
        this.userName = userName;
    }

    public List<Card> getPlayerCards() {
        return playerCards;
    }

    public void setPlayerCards(List<Card> playerCards) {
        this.playerCards = playerCards;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void addAll(ArrayList<Card> cards) {
        for(int i = 0; i < cards.size(); i++){
            this.playerCards.add(cards.get(i));
        }
    }
}
