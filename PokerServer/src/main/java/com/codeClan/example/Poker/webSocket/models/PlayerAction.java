package com.codeClan.example.Poker.webSocket.models;

public class PlayerAction {

    private String action;
    private long playerId;
    private double betAmount;
    private String gameTableKey;

    public PlayerAction(String action, long playerId, double betAmount, String gameTableKey) {
        this.action = action;
        this.playerId = playerId;
        this.betAmount = betAmount;
        this.gameTableKey = gameTableKey;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public double getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(double betAmount) {
        this.betAmount = betAmount;
    }

    public String getGameTableKey() {
        return gameTableKey;
    }

    public void setGameTableKey(String gameTableKey) {
        this.gameTableKey = gameTableKey;
    }
}
