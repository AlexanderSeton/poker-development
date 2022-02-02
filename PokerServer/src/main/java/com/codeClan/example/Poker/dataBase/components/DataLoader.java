package com.codeClan.example.Poker.dataBase.components;

import com.codeClan.example.Poker.dataBase.repositories.CardRepository;
import com.codeClan.example.Poker.dataBase.repositories.GameTableRepository;
import com.codeClan.example.Poker.dataBase.repositories.PlayerRepository;
import com.codeClan.example.Poker.game.models.Card;
import com.codeClan.example.Poker.game.models.Deck;
import com.codeClan.example.Poker.game.models.GameTable;
import com.codeClan.example.Poker.game.models.Player;
import com.codeClan.example.Poker.game.models.game.Dealer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.mapping.IdentifierAccessor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    GameTableRepository gameTableRepository;

    @Autowired
    CardRepository cardRepository;

    public DataLoader() {
    }

    public void run(ApplicationArguments args) {

        Player Iain = new Player("iain", 1000, "Iain123", "1234");

        Player Alex = new Player("alex", 1000, "A", "1");

        Player Stuart = new Player("stuart", 1000, "Stuart123", "2345");

        playerRepository.save(Iain);
        playerRepository.save(Alex);
        playerRepository.save(Stuart);

        Deck deck = new Deck();

        for (Card card : deck.getDeck()) {
            cardRepository.save(card);
        }

        List<Player> players1 = new ArrayList<>(Arrays.asList(Alex));
        GameTable gameTable2 = new GameTable(0.0, players1, 5.0, deck);

        List<Player> players2 = new ArrayList<>(Arrays.asList(Stuart));
        GameTable table1 = new GameTable(0.00, players2, 100, deck);

        GameTable table3 = new GameTable(0.0, new ArrayList<>(), 5, deck);
        table3.setGameKey("123");

        gameTableRepository.save(gameTable2);
        gameTableRepository.save(table1);
        gameTableRepository.save(table3);

        Alex.setGame_table(table1);
        Stuart.setGame_table(gameTable2);
        
        playerRepository.save(Iain);
        playerRepository.save(Alex);
        playerRepository.save(Stuart);

//        Dealer dealer = new Dealer(table1);
//
//        dealer.dealHoleCards();
//
//        gameTableRepository.save(table1);
//        for (Player player : table1.getPlayers()) {
//            playerRepository.save(player);
//            for (Card card : player.getHand()) {
//                cardRepository.save(card);
//            }
//        }



//        gameTableRepository.save(table1);


//        Card card = cardRepository.getById(1L);
//        List<Card> cardsList = new ArrayList<>();
//        cardsList.add(card);
//
//        Stuart.setHand(cardsList);
//        playerRepository.save(Stuart);
//        card.setPlayer(Stuart);
//        cardRepository.save(card);


//        gameTableRepository.save(table1);
//
//        gameTableRepository.save(table1);
//
//        dealer.dealHoleCards();
//
//        playerRepository.save(Stuart);
//        gameTableRepository.save(table1);
    }
}
