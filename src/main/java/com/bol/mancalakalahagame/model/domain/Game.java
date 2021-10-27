package com.bol.mancalakalahagame.model.domain;

import com.bol.mancalakalahagame.model.enums.GameState;
import com.fasterxml.jackson.annotation.JsonIgnore;


import java.util.List;
import java.util.Objects;

/**
 * Game domain class
 */

public class Game {

    private List<Player> playerList;
    private int nextPlayerIndex;
    private GameState gameState;

    /**
     * Game Constructor
     * @param playerList The list of {@link Player} in the game.
     * @param nextPlayerIndex The index of {@link Player} has turn.
     * @param gameState The {@link GameState} info of the game
     */
    public Game(List<Player> playerList, int nextPlayerIndex, GameState gameState) {
        this.playerList = playerList;
        this.nextPlayerIndex = nextPlayerIndex;
        this.gameState = gameState;
    }

    /**
     *
     * @return current next player index of the {@link Player}
     */
    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    /**
     *
     * @return current {@link GameState} info of the game.
     */
    @JsonIgnore
    public GameState getGameState() {
        return gameState;
    }

    /**
     *
     * @param nextPlayerIndex The index of {@link Player} has turn.
     */
    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    /**
     * Function to get index of {@link Player} in the game
     * @param player The {@link Player} has index
     * @return index of {@link Player} in the game
     */
    public int getIndexOfPlayer(Player player) {
        return playerList.indexOf(player);

    }

    /**
     * Function to get index of {@link Player} by small {@link Pit} index
     * @param smallPitIndex of the {@link Pit}
     * @return the index of {@link Player} has the small pit.
     */
    public int getIndexOfPlayerBySmallPitIndex(int smallPitIndex) {
        //Throw Exception when pit index out of range
        if(smallPitIndex < 0 || smallPitIndex > 13){
            throw new IllegalArgumentException("Pit index must be between 0 and 13.");
        }

        return smallPitIndex / Board.NUMBER_OF_PITS_OF_EACH_PLAYER;
    }

    /**
     * Function to get {@link Player} by small {@link Pit} index
     * @param smallPitIndex of the {@link Pit}
     * @return the {@link Player} has the small {@link Pit}
     */
    public Player getPlayerBySmallPitIndex(int smallPitIndex) {
        //Throw Exception when pit index out of range
        if(smallPitIndex < 0 || smallPitIndex > 13){
            throw new IllegalArgumentException("Pit index must be between 0 and 13.");
        }

        int indexOfPlayer = getIndexOfPlayerBySmallPitIndex(smallPitIndex);
        return playerList.get(indexOfPlayer);

    }

    /**
     * Function to retrieve {@link GameState} info
     * @return the {@link GameState} info of the game
     */
    public String getGameStateInfo() {
        return gameState.getInfoMessage();
    }

    /**
     * Function to set {@link GameState} info
     * @param gameState The new {@link GameState} info
     */
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    /**
     *
     * @return current all {@link Player}s in the game
     */
    public List<Player> getPlayerList() {
        return playerList;
    }

    /**
     * Function to check equality two the Game objects.
     * @param o The object to be checked for equality.
     * @return If two the Game objects are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return nextPlayerIndex == game.nextPlayerIndex && Objects.equals(playerList, game.playerList) && gameState == game.gameState;
    }

    /**
     * Function to get hashcode of the Game object.
     * @return hash code of the Game object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(playerList, nextPlayerIndex, gameState);
    }

    /**
     * Function to print fields of the Game object.
     * @return fields of the Game object in string format.
     */
    @Override
    public String toString() {
        return "Game{" +
                "playerList=" + playerList +
                ", nextPlayerIndex=" + nextPlayerIndex +
                ", gameState=" + gameState +
                '}';
    }
}
