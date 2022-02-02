package com.codeClan.example.Poker.webSocket;

import com.codeClan.example.Poker.dataBase.repositories.CardRepository;
import com.codeClan.example.Poker.dataBase.repositories.GameTableRepository;
import com.codeClan.example.Poker.dataBase.repositories.PlayerRepository;
import com.codeClan.example.Poker.game.models.Card;
import com.codeClan.example.Poker.game.models.Deck;
import com.codeClan.example.Poker.game.models.GameTable;
import com.codeClan.example.Poker.game.models.Player;
import com.codeClan.example.Poker.game.models.game.Dealer;
import com.codeClan.example.Poker.game.models.game.bettingRound.PreFlopBetting;
import com.codeClan.example.Poker.webSocket.models.PlayerAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller
public class PlayerWebSocketController {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    GameTableRepository gameTableRepository;

    @Autowired
    CardRepository cardRepository;

    // CREATE GAME
    @MessageMapping("/create/game/{gameKey}")
    @SendTo("/client/greetings")

    public ResponseEntity<GameTable> gameTable(Player user, @DestinationVariable String gameKey) throws Exception {
        // check if gameKey already exists
        Optional<GameTable> checkIfExists = gameTableRepository.findGameTableByGameKey(gameKey);
        if (checkIfExists.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        else {
            Player player = playerRepository.findById(user.getId()).get();
            ArrayList<Player> players = new ArrayList<>(Arrays.asList(player));
            Deck deck = new Deck();
            GameTable gameTable = new GameTable(0.0, players, user.getBigBlindValue(), deck);
            gameTable.setGameKey(gameKey);
            gameTableRepository.save(gameTable);
            player.setGame_table(gameTable);
            playerRepository.save(player);
            System.out.println("Created game (key: " + gameKey +". User: " + player.getUsername());
            return new ResponseEntity<>(gameTable, HttpStatus.OK);
        }

    }

    // JOIN GAME
    @MessageMapping("/join/game/{gameKey}")
    @SendTo("/client/join")
    public ResponseEntity<GameTable> joinGameTable(@DestinationVariable String gameKey, Player user) throws Exception {
        System.out.println("INSIDE THE JOIN GAME METHOD"); // test
        // check if table exists...
        Optional<GameTable> gameTableCheck = gameTableRepository.findGameTableByGameKey(gameKey);
        if (gameTableCheck.isPresent()) {
            Player player = playerRepository.findById(user.getId()).get();
            GameTable gameTable = gameTableCheck.get();
            // check player is not already part of the list
            List<Player> currentPlayers = gameTable.getPlayers();
            ArrayList<Long> playerIds = new ArrayList<>();
            currentPlayers.forEach(currentPlayer -> playerIds.add(currentPlayer.getId()));
            if (!playerIds.contains(user.getId())) {
                System.out.println("Player not already at the table...");
                gameTable.addPlayer(player);
            }
            gameTableRepository.save(gameTable);
            player.setGame_table(gameTable);
            playerRepository.save(player);
            System.out.println("Join game (key: " + gameKey +". User: " + player.getUsername());
            return new ResponseEntity<>(gameTable, HttpStatus.OK);
        }
        // if no table is found
        else {
            System.out.println("table is not found");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @MessageMapping("/action/game/{gameKey}/deal")
    @SendTo("/client/deal")
    public ResponseEntity<HashMap<Long, List<String>>> handleDealingHoleCards(@DestinationVariable String gameKey, PlayerAction playerAction) {

        String action = playerAction.getAction();

        // deal
        if(action.equalsIgnoreCase("deal")) {
            System.out.println("IN method");
            GameTable table = gameTableRepository.findGameTableByGameKey(gameKey).get();
            System.out.println("table key: " + table.getGameKey());
            Dealer dealer = new Dealer(table);
            dealer.dealHoleCards();
            gameTableRepository.save(table);
            for (Player player : table.getPlayers()) {
                playerRepository.save(player);
                for (Card card : player.getHand()) {
                    card.setPlayer(player);
                    cardRepository.save(card);
                    // test
                    System.out.println(player.getUsername() + " has " + card.getCardValue() + card.getCardSuitType());
                }
            }
            gameTableRepository.save(table);
        }
        HashMap<Long, List<String>> holeCards = new HashMap<>();
        List<Player> gameTablesPlayers = gameTableRepository.findGameTableByGameKey(gameKey).get().getPlayers();
        for (Player player : gameTablesPlayers) {
            List<String> handData = new ArrayList<>();
            for (int i=0; i<2; i++) {
                handData.add(player.getHand().get(i).getCardSuitType().toString());
                handData.add(player.getHand().get(i).getCardValue().toString());
            }
            holeCards.put(player.getId(), handData);
        }
//        holeCards.put(2L, new ArrayList<>(Arrays.asList("SPADES", "TWO", "DIAMONDS", "TWO")));
        System.out.println(holeCards);
        return new ResponseEntity<>(holeCards, HttpStatus.OK);
    }

    @MessageMapping("/action/game/{id}")
    @SendTo("/client/greetings")
    public ResponseEntity<GameTable> handlePlayerAction(@DestinationVariable String id, PlayerAction playerAction) throws Exception {

        String action = playerAction.getAction();

        // bet & call
        if(action.equalsIgnoreCase("bet") || action.equalsIgnoreCase("call")) {
            GameTable table = gameTableRepository.findGameTableByGameKey(id).get();
            List<Player> players = table.getPlayers();
            Player tempPlayer = new Player();
            double amount = playerAction.getBetAmount();
            for(Player player : players) {
                if(player.isActive()) {
                    tempPlayer = player;
                }
            }
            tempPlayer.bet(amount);
            table.addToPot(amount);
            gameTableRepository.save(table);
            return new ResponseEntity<>(table, HttpStatus.OK);
        }

        // fold
        else if(action.equalsIgnoreCase("fold")) {
            GameTable table = gameTableRepository.findGameTableByGameKey(id).get();
            List<Player> players = table.getPlayers();
            Player tempPlayer = new Player();
            double amount = playerAction.getBetAmount();
            for(Player player : players) {
                if(player.isActive()) {
                    tempPlayer = player;
                }
            }
            tempPlayer.fold();
            gameTableRepository.save(table);
            return new ResponseEntity<>(table, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
