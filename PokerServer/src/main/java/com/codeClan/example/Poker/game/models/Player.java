package com.codeClan.example.Poker.game.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="stack")
    private double stack;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @JsonBackReference
    @OneToMany(mappedBy = "player", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"player"})
    private List<Card> hand;

    @Column(name = "big_blind")
    private boolean isBigBlind;

    @Column(name = "small_blind")
    private boolean isSmallBlind;

    @Column(name = "active")
    private boolean isActive;

    @Column(name = "contribution")
    private double contribution;

    @Transient
    private boolean folded;

    @Transient
    private double bigBlindValue;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_table_id", nullable = true)
    @JsonIgnoreProperties({"players"})
    private GameTable game_table;


    public Player() {
    }

    public Player(String name, double stack, String username, String password) {
        this.name = name;
        this.stack = stack;
        this.username = username;
        this.password = password;
        this.hand = new ArrayList<Card>();
        this.isBigBlind = false;
        this.isSmallBlind = false;
        this.isActive = false;
        this.contribution = 0.0;
        this.folded = false;
        this.game_table = null;
        this.bigBlindValue = 0;
    }

    public double getBigBlindValue() {
        return bigBlindValue;
    }

    public void setBigBlindValue(double bigBlindValue) {
        this.bigBlindValue = bigBlindValue;
    }

    public GameTable getGame_table() {
        return game_table;
    }

    public void setGame_table(GameTable gameTable) {
        this.game_table = gameTable;
    }

    public boolean isFolded() {
        return folded;
    }

    public void setFolded(boolean folded) {
        this.folded = folded;
    }

//    public GameTable getGameTable() {
//        return game_table;
//    }
//
//    public void setGameTable(GameTable gameTable) {
//        this.game_table = gameTable;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getStack() {
        return stack;
    }

    public void setStack(double stack) {
        this.stack = stack;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double addToStack(double amount){
        return  this.stack += amount;
    }

    public double removeFromStack(double amount){
        return this.stack -= amount;
    }

    public void addCard(Card card){
        this.hand.add(card);
    }

    public boolean isBigBlind() {
        return isBigBlind;
    }

    public void setBigBlind(boolean BigBlind) {
        isBigBlind = BigBlind;
    }

    public boolean isSmallBlind() {
        return isSmallBlind;
    }

    public void setSmallBlind(boolean smallBlind) {
        isSmallBlind = smallBlind;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean getFolded() {
        return this.folded;
    }

    public void setFolded(Boolean folded) {
        this.folded = folded;
    }

    public void increaseContribution(double amount){
        this.contribution += amount;
    }

    public double getContribution() {
        return contribution;
    }

    public void setContribution(double amount) {
        this.contribution = amount;
    }

    public boolean checkStackEnough(double betSize) {
        if (this.stack >= betSize) {
            return  true;
        }
        return false;
    }

    public void bet(double betSize) {
        if (this.checkStackEnough(betSize)) {
            this.removeFromStack(betSize);
            this.increaseContribution(betSize);
            this.setActive(false);
        } else {
            this.fold();
        }
    }

    public void call(double largestContribution) {
        double amountToCall = largestContribution - this.getContribution();
        if (this.checkStackEnough(amountToCall)) {
            this.removeFromStack(amountToCall);
            this.increaseContribution(amountToCall);
            this.setActive(false);
        } else {
            this.fold();
        }
    }

    public void fold() {
        System.out.println("Folded");
        this.setActive(false);
        this.setFolded(true);
    }


}
