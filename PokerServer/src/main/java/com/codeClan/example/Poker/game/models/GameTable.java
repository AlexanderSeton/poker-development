package com.codeClan.example.Poker.game.models;

import com.codeClan.example.Poker.game.models.game.bettingRound.PreFlopBetting;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="game_tables")
public class GameTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "game_key")
    private String gameKey;

    @Column(name = "pot")
    private double pot;

//    @JsonBackReference
    @OneToMany(mappedBy = "game_table", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"game_table"})
    private List<Player> players;

    @JsonBackReference
    @OneToMany(mappedBy = "gameTable", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"gameTable"})
    private List<Card> board;

    @Column(name = "big_blind")
    private double bigBlind;

    @Column(name = "small_blind")
    private double smallBlind;

    @Transient
    private Deck deck;

//    public GameTable() {
//    }

    public GameTable() {
    }


    public GameTable(double pot, List<Player> players, double bigBlind, Deck deck) {

        this.pot = pot;
        this.players = players;
        this.board = new ArrayList<>();
        this.bigBlind = bigBlind;
        this.smallBlind = bigBlind / 2;
        this.deck = deck;
    }

    public String getGameKey() {
        return gameKey;
    }

    public void setGameKey(String game_key) {
        this.gameKey = game_key;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPot() {
        return pot;
    }

    public double getBigBlind() {
        return bigBlind;
    }

    public void setBigBlind(double bigBlind) {
        this.bigBlind = bigBlind;
    }

    public double getSmallBlind() {
        return smallBlind;
    }

    public void setSmallBlind(double smallBlind) {
        this.smallBlind = smallBlind;
    }

    public void setPot(double pot) {
        this.pot = pot;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Card> getBoard() {
        return board;
    }

    public void setBoard(ArrayList<Card> board) {
        this.board = board;
    }

    public void addCardToBoard(Card card) {
        this.board.add(card);
    }

    public void addToPot(double amount) {
        this.pot += amount;
    }

    public void removeFromPot(double amount){
        this.pot -= amount;
    }

    public void resetPot(){
        this.pot = 0;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    // GAME METHODS

    // round 1: pre-flop
    public void preFlopBettingRound() {
        PreFlopBetting preFlopBetting = new PreFlopBetting(this.players, this.bigBlind);
        preFlopBetting.setUpRound();
        preFlopBetting.addBlindsToPot();
        preFlopBetting.postBlindBetting();
    }


}
