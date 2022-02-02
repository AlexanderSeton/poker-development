package com.codeClan.example.Poker.game.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "suit")
    @Enumerated(value = EnumType.STRING)
    private CardSuitType cardSuitType;

    @Column(name = "value")
    @Enumerated(value = EnumType.ORDINAL)
    private CardFace cardFace;
    
    @ManyToOne
    @JoinColumn(name = "player_id")
    @JsonIgnoreProperties({"players"})
    private Player player;

    @ManyToOne
    @JoinColumn(name = "game_table_id")
    private GameTable gameTable;

    public Card() {
    }

    public Card(CardSuitType cardSuitType, CardFace cardFace) {
        this.cardSuitType = cardSuitType;
        this.cardFace = cardFace;
    }

    public CardSuitType getCardSuitType() {
        return cardSuitType;
    }

    public void setCardSuitType(CardSuitType cardSuitType) {
        this.cardSuitType = cardSuitType;
    }

    public CardFace getCardValue() {
        return cardFace;
    }

    public void setCardValue(CardFace cardFace) {
        this.cardFace = cardFace;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public GameTable getGameTable() {
        return gameTable;
    }

    public void setGameTable(GameTable gameTable) {
        this.gameTable = gameTable;
    }

}
