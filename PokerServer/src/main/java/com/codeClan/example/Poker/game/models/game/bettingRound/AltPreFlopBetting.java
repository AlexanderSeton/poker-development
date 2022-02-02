package com.codeClan.example.Poker.game.models.game.bettingRound;

import com.codeClan.example.Poker.game.models.Player;

import java.util.List;

public class AltPreFlopBetting implements IBetting {

    private List<Player> players;
    private double pot;
    private double largestContribution;
    private boolean complete;
    private double bigBlind;
    int activeIndex;
    int startingIndex;
    boolean firstRound;


    public AltPreFlopBetting(List<Player> players, double bigBlind) {
        this.players = players;
        this.pot = pot;
        this.complete = false;
        this.largestContribution = bigBlind;
        this.bigBlind = bigBlind;
        this.activeIndex = 0;
        this.startingIndex = 0;
        this.firstRound = true;
    }

    public AltPreFlopBetting() {

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

//    game methods

    public void setFirstPlayerToActive() {
        for (Player player: this.players) {
            if (player.isBigBlind()) {
                activeIndex = this.players.indexOf(player) + 1;
                startingIndex = activeIndex;
                player.setActive(true);
            }
        }
    }

    public void setUpRound() {

        for (Player player : this.players) {
            player.setActive(false);
        }
        setFirstPlayerToActive();
    }

    public boolean checkSinglePlayerRemaining(){
        int count = 0;
        for (Player player : players) {
            if (!player.getFolded()){
                count++;
            }
        }
        return count < 2;
    }

    public void setNextUnfoldedToActive(){
        int activePlayerIndex = 0;
        for (Player player: this.players) {
            if (player.isActive()) {
                activePlayerIndex = this.players.indexOf(player);
            }
        }

        int playerCheckIndex = 0;

        if(activePlayerIndex != players.size() -1){
            playerCheckIndex = activePlayerIndex + 1;
        }

        for (int i = playerCheckIndex; i < players.size(); i++) {
            if(!players.get(i).getFolded()) {
                players.get(i).setActive(true);
                break;
            }
            if (i == players.size() - 1) {
                i = -1;
            }
        }

    }

    // Cycle through all players from UTG, THEN ->

    // set firstRound = false, THEN ->

    // REPEAT BELOW:
    // Check if all contributions the same, if so - stop
    // Check if only one player remaining, if so - stop, if not continue
    // Move to next unfolded player

    public void onPlayerBet(double bet) {

        if(bet != 0){
            this.largestContribution = players.get(activeIndex).getContribution();
        }

        if(activeIndex == startingIndex - 1 || (startingIndex == 0 && activeIndex == players.size() - 1)) {
            firstRound = false;
        }

        if(firstRound){
            if(activeIndex == players.size() -1){
                players.get(0).setActive(true);
                players.get(activeIndex).setActive(false);
                activeIndex = 0;
            } else {
                players.get(activeIndex + 1);
                players.get(activeIndex).setActive(false);
                activeIndex++;
            }
        } else {
            if(checkContributionsAreSame() || checkSinglePlayerRemaining()){
                this.complete = true;
            } else {
                setNextUnfoldedToActive();
            }
        }

    }

    public void onPlayerFold() {
        if(checkSinglePlayerRemaining()) {
            this.complete = true;
        } else {
            setNextUnfoldedToActive();
        }
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
            if (player.getContribution() != this.findLargestContribution() && !player.getFolded()){
                playersAreSame = false;
            }
        }
        return playersAreSame;
    }

}
