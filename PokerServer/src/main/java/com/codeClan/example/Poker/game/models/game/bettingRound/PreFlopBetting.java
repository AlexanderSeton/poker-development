package com.codeClan.example.Poker.game.models.game.bettingRound;

import com.codeClan.example.Poker.game.models.Player;
import java.util.*;


public class PreFlopBetting {

    private List<Player> players;
    private double pot;
    private double largestContribution;
    private boolean complete;
    private double bigBlind;
    private Player activePlayer;

    public PreFlopBetting(List<Player> players, double bigBlind) {
        this.players = players;
        this.pot = 0;
        this.largestContribution = bigBlind;
        this.complete = false;
        this.bigBlind = bigBlind;
        this.activePlayer = null;
    }



    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public double getPot() {
        return pot;
    }

    public void setPot(double pot) {
        this.pot = pot;
    }

    public double getLargestContribution() {
        return largestContribution;
    }

    public void setLargestContribution(double largestContribution) {
        this.largestContribution = largestContribution;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }



    // game methods

    public List<Integer> findBlindPlayersIndexes() {
        int smallBlindIndex = 0;
        int bigBlindIndex = 0;
        for (Player player: this.players) {
            if (player.isSmallBlind()) {
                smallBlindIndex = this.players.indexOf(player);
            } else if (player.isBigBlind()) {
                bigBlindIndex = this.players.indexOf(player);
            }
        }
        return new ArrayList<>(Arrays.asList(smallBlindIndex, bigBlindIndex));
    }


    public void setUpRound() {
        // ASSIGNING BLINDS
        Boolean smallBlindAssigned = false;
        Boolean bigBlindAssigned = false;
        for (Player player : this.players) {
            if (player.isSmallBlind()) {
                smallBlindAssigned = true;
            }
            if (player.isBigBlind()) {
                bigBlindAssigned = true;
            }
        }
        if (!smallBlindAssigned || !bigBlindAssigned) {
            // assign blinds to first two players in List
            this.players.get(0).setSmallBlind(true);
            this.players.get(1).setBigBlind(true);
        } else {
            // move blinds to next player
            int smallBlindIndex = this.findBlindPlayersIndexes().get(0);
            int bigBlindIndex = this.findBlindPlayersIndexes().get(1);

            this.players.get(smallBlindIndex).setSmallBlind(false);
            this.players.get(bigBlindIndex).setBigBlind(false);
            // move blinds to next two players
            smallBlindIndex ++;
            bigBlindIndex ++;
            if (bigBlindIndex > this.players.size() - 1) {
                bigBlindIndex = 0;
            }
            if (smallBlindIndex > this.players.size() - 1) {
                smallBlindIndex = 0;
            }
            this.players.get(smallBlindIndex).setSmallBlind(true);
            this.players.get(bigBlindIndex).setBigBlind(true);
        }

        // set pot to 0
        this.pot = 0;

        // set all players to inactive
        for (Player player : this.players) {
            player.setActive(false);
        }
    }

    public void addBlindsToPot() {
        for (Player player: this.players) {
            if (player.isBigBlind()) {
                player.removeFromStack(this.bigBlind);
                player.increaseContribution(this.bigBlind);
            } else if (player.isSmallBlind()) {
                player.removeFromStack(this.bigBlind / 2);
                player.increaseContribution(bigBlind / 2);
            }
        }
    }

    public void postBlindBetting() {
        // get under-the-gun player index & big-blind player index
        int firstPlayerIndex;
        int bigBlindIndex = this.findBlindPlayersIndexes().get(1);
        if(bigBlindIndex == this.players.size() -1) {
            firstPlayerIndex = 0;
        } else {
            firstPlayerIndex = bigBlindIndex ++;
        }
        // set first player to active
        this.players.get(firstPlayerIndex).setActive(true);

        // betting round flow
        Player bigBlindPlayer = this.players.get(bigBlindIndex);


//        boolean equalPotContribution = false;
//        boolean blindHasBet = false;
//
//        while (!equalPotContribution) {
//
//            equalPotContribution = true;
//
//            double largestContribution = this.findLargestContribution();
//            int counter = 0;
//
//            for (Player player : this.players) {
////                if (player.getContribution() != this.largestContribution && (player != bigBlindPlayer || blindHasBet)) {
////                if (player.getContribution() != this.findLargestContribution() && !blindHasBet) {
//                if (player.getContribution() == largestContribution) {
////                    equalPotContribution = false;
//                    counter ++;
//                }
//            }
//            if (counter != this.players.size()) {
//                equalPotContribution = false;
//            } else {
//                equalPotContribution = true
//            }
//
//
//            if (!equalPotContribution) {
//                this.handleActivePlayerTurn();
//            } else {
//                this.setComplete(true);
//                break;
//            }
//
//        }

//        int loopCounter = 0; // test

        boolean continueLoop = true;

        while(continueLoop) {

//            System.out.println("While loop counter: " + loopCounter); // test
//            loopCounter ++; // test

           this.handleActivePlayerTurn(); // player action

            continueLoop = !this.checkContributionsAreSame();
//            continueLoop = false;
            System.out.println("continueLoop: " + continueLoop); // test
            System.out.println("p1 stack contribution: " + this.players.get(0).getStack() + this.players.get(0).getContribution());
            System.out.println("p2 stack contribution: " + this.players.get(1).getStack() + this.players.get(1).getContribution());
            System.out.println("p3 stack contribution: " + this.players.get(2).getStack() + this.players.get(2).getContribution());
        }

        System.out.println("Exited while checkContributionsAreSame");
        this.setComplete(true);

    }

    public void handleActivePlayerTurn() {
//        Optional<Player> activePlayer;
        int activePlayerIndex = 0;
        for (Player player: this.players) {
            if (player.isActive()) {
                activePlayer = player;
                activePlayerIndex = this.players.indexOf(player);
            }
        }

        // wait until active player changes his "active" attribute to false after his move
        while(activePlayer.isActive()) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//                System.out.println("Waiting for active player to make their move and assign themselves as inactive");
//            }
//            System.out.println("active player:" + activePlayer);
//            System.out.println("active player stack:" + activePlayer.getStack());
            activePlayer.call(this.getLargestContribution()); // test hard-code
        }

        // player will assign himself as not active after his action
        // assign next active player
        activePlayerIndex ++;
        if (activePlayerIndex == this.players.size()) {
            activePlayerIndex = 0;
        }
        this.players.get(activePlayerIndex).setActive(true);
        // return the player that just acted
//        return activePlayer;
    }


//    find largest contribution
    public double findLargestContribution(){
        double newLargestContribution = 0;
        for (Player player: this.players){
            if (player.getContribution() > this.largestContribution){
                newLargestContribution = player.getContribution();
            } else {
                newLargestContribution = this.getLargestContribution();
            }
        }
        return newLargestContribution;
    }

//    check players have equal contributions
    public boolean checkContributionsAreSame() {
        boolean playersAreSame = true;
        for (Player player: this.players) {
            if (player.getContribution() != this.findLargestContribution()){
                playersAreSame = false;
            }
        }
        return playersAreSame;
    }


}
