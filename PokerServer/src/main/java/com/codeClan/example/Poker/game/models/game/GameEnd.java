package com.codeClan.example.Poker.game.models.game;

import com.codeClan.example.Poker.game.models.*;

import java.sql.Array;
import java.util.ArrayList;

public class GameEnd {


    public ArrayList<Player> calculateRemainingPlayers(ArrayList<Player> players) {
        ArrayList<Player> remainingPlayers = new ArrayList<>();

        for (int i = 0; i < players.size(); i++){
            if(!players.get(i).getFolded()){
                remainingPlayers.add(players.get(i));
            }
        }

        return remainingPlayers;
    }

    public double calculatePotShare(double pot, ArrayList<PlayerHand> players){
        int playerNumber = players.size();
        return pot / playerNumber;
    }

    public void payWinners(double potShare, ArrayList<PlayerHand> playerHands, ArrayList<Player> players) {
        for (int i = 0; i < playerHands.size(); i++) {
            String name = playerHands.get(i).getUserName();
            for (int j = 0; j < players.size(); j++) {
                if(players.get(j).getUsername() == name){
                    players.get(j).addToStack(potShare);
                }
            }
        }
    }

    public ArrayList<PlayerHand> calculateWinners(ArrayList<Player> players, ArrayList<Card> board) {
        int numberPlayers = players.size();

        ArrayList<PlayerHand> hands = new ArrayList<>();

        for (int i = 0; i < numberPlayers; i++){

            ArrayList<Card> cards = (ArrayList<Card>) players.get(i).getHand();
            String userName = players.get(i).getUsername();
            PlayerHand hand = new PlayerHand(cards, userName);
            hand.addAll(board);

            hands.add(hand);
        }

        HandCalculator calc = new HandCalculator();

        for(int i = 0; i <= hands.size() - 1; ) {

            if(hands.size() == 1) {
                break;
            }

            PlayerHand tempWinner = calc.overall(hands.get(i), hands.get(i+1));

            if(tempWinner == hands.get(i)){
                hands.remove(hands.get(i+1));
            } else if (tempWinner == hands.get(i+1)) {
                hands.remove(hands.get(i));
            } else {
                i++;
            }
        }

        return hands;

    }

    public void onComplete(GameTable gameTable) {

        ArrayList<Player> remainingPlayers = this.calculateRemainingPlayers((ArrayList<Player>) gameTable.getPlayers());

        if(remainingPlayers.size() == 1){

            remainingPlayers.get(0).addToStack(gameTable.getPot());
        }

        ArrayList<PlayerHand> winners = calculateWinners((ArrayList<Player>) gameTable.getPlayers(), (ArrayList<Card>) gameTable.getBoard());

        double potShare = calculatePotShare(gameTable.getPot(), winners);

        this.payWinners(potShare, winners, (ArrayList<Player>) gameTable.getPlayers());

    }
}
