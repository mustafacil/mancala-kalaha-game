package com.bol.mancalakalahagame.model.domain;

import com.bol.mancalakalahagame.model.enums.GameState;
import com.bol.mancalakalahagame.model.enums.PitType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Board domain class
 */
public class Board {

    //Every player has 7 pits in a game.
    public static final int NUMBER_OF_PITS_OF_EACH_PLAYER = 7;

    private String id;
    private Pit[] pits;
    private Game game;

    /**
     * Board constructor
     * @param pits Array of {@link Pit} objects
     * @param game the {@link Game} info
     */
    public Board(Pit[] pits, Game game) {
        this.pits = pits;
        this.game = game;
    }

    /**
     *
     * @return current {@link Game}
     */
    public Game getGame() {
        return game;
    }

    /**
     *
     * @return current id
     */
    public String getId() {
        return id;
    }

    /**
     * create unique board id
     */
    public void setId() {

        //create unique id of board with usernames of players and current date
        List<Player> playerList = game.getPlayerList();
        String usernameOfFirstPlayer = playerList.get(0).getUsername();
        String usernameOfSecondPlayer = playerList.get(1).getUsername();

        String currentDate = new SimpleDateFormat("ddMMyyyyHHmmss").format(new Date());

        String uniqueId = usernameOfFirstPlayer + usernameOfSecondPlayer + currentDate;
        this.id = uniqueId;
    }

    /**
     * Function to retrieve total number of stones in the small {@link Pit} by index of a {@link Player}
     * @param indexOfPlayer index of the {@link Player} int a {@link Game}
     * @return Total number of stones in the small
     */
    public int getTotalNumberOfStonesInSmallPitsByIndexOfPlayer(int indexOfPlayer) {

        //Throw Exception when index of the player out of range
        if (indexOfPlayer < 0 || indexOfPlayer > game.getPlayerList().size() - 1) {
            throw new IllegalArgumentException("The player order must be a valid player number. There is no player has " + indexOfPlayer + ". place. ");
        }

        int totalNumberOfStonesInSmallPits = 0;

        //get lowest small pit index of board by index of player
        int lowestSmallPitIndex = getLowestSmallPitIndexByPlayerIndex(indexOfPlayer);

        //get highest small pit index of board by index of player
        int highestSmallPitIndex = getHighestSmallPitIndexByPlayerIndex(indexOfPlayer);

        //sum up number of stones in the small pits
        for (int i = lowestSmallPitIndex; i <= highestSmallPitIndex; i++) {
            totalNumberOfStonesInSmallPits += pits[i].getNumberOfStones();
        }

        return totalNumberOfStonesInSmallPits;

    }

    /**
     * Function to retrieve a {@link Pit} with given {@link Pit} index
     * @param index of the {@link Pit}
     * @return {@link Pit} with given index
     */
    public Pit getPitByIndex(int index){
        ////Throw Exception when index of the pit out of range
        if (index < 0 || index >= pits.length) {
            throw new IllegalArgumentException("Index is invalid. There is no pit on plate corresponding the " + index + ". index.");
        }

        return pits[index];

    }

    /**
     * Function to retrieve total number of stones with given {@link Pit} index
     * @param pitIndex of the {@link Pit}
     * @return total number of stones of the {@link Pit}
     */
    public int getNumberOfStonesOfPitByIndex(int pitIndex) {

        Pit pit = pits[pitIndex];
        return pit.getNumberOfStones();

    }

    /**
     * Function to pick all stones of the pit with given {@link Pit} index
     * @param pitIndex of the {@link Pit}
     */
    public void pickAllStonesOfPitByIndex(int pitIndex) {
        pits[pitIndex].pickAllStones();
    }

    /**
     * Function to retrieve length of {@link Pit} array
     * @return length of {@link Pit} array
     */
    @JsonIgnore
    public int getTotalNumberOfPitsOnBoard() {
        return pits.length;
    }

    /**
     * Function to retrieve the {@link PitType} with given {@link Pit} index
     * @param pitIndex of the {@link Pit}
     * @return the {@link PitType} with given {@link Pit} index
     */
    public PitType getPitTypeByIndex(int pitIndex) {
        return pits[pitIndex].getPitType();

    }

    /**
     * Function to increase total number of stones in a {@link Pit} with given index and number of stones
     * @param pitIndex of the {@link Pit}
     * @param numberOfStones number of stones to be increased
     */
    public void increaseNumberOfStonesOfPitByIndex(int pitIndex, int numberOfStones) {
        Pit pit = pits[pitIndex];
        pit.increaseNumberOfStones(numberOfStones);
    }

    /**
     * Function to retrieve lowest small {@link Pit} index of a {@link Player} with given index
     * @param indexOfPlayer index of the {@link Player}
     * @return lowest small {@link Pit} index of the {@link Player}
     */
    public int getLowestSmallPitIndexByPlayerIndex(int indexOfPlayer) {
        return indexOfPlayer * NUMBER_OF_PITS_OF_EACH_PLAYER;
    }

    /**
     * Function to retrieve highest small {@link Pit} index of a {@link Player} with given index
     * @param indexOfPlayer index of the {@link Player}
     * @return highest small {@link Pit} index of the {@link Player}
     */
    public int getHighestSmallPitIndexByPlayerIndex(int indexOfPlayer) {
        return (indexOfPlayer * NUMBER_OF_PITS_OF_EACH_PLAYER) + 5;
    }

    /**
     * Function to retrieve large {@link Pit} index of a {@link Player} with given index
     * @param indexOfPlayer index of the {@link Player}
     * @return large {@link Pit} index of the {@link Player}
     */
    public int getLargePitIndexByPlayerIndex(int indexOfPlayer) {
        return getHighestSmallPitIndexByPlayerIndex(indexOfPlayer) + 1;
    }

    /**
     * Function to retrieve opposite {@link Pit} index with given index.
     * @param indexOfPit index of the given {@link Pit}
     * @return opposite {@link Pit} index
     */
    public int getOppositePitIndexOfPitByIndex(int indexOfPit){
        return pits[indexOfPit].getOppositeIndex(indexOfPit);
    }

    /**
     * Function to retrieve {@link Player} with given small {@link Pit} index.
     * @param smallPitIndex index of the small @link Pit}
     * @return the {@link Player} has the small {@link Pit}
     */
    public Player getPlayerBySmallPitIndex(int smallPitIndex){
        return game.getPlayerBySmallPitIndex(smallPitIndex);
    }

    /**
     * Function to set {@link GameState}
     * @param gameState The new {@link GameState} info
     */
    public void setGameState(GameState gameState){
        game.setGameState(gameState);
    }

    /**
     * Function to get {@link GameState}
     * @return current {@link GameState} info
     */
    public GameState getGameState(){
        return game.getGameState();
    }

    /**
     * Function to get index of a {@link Player}
     * @param player the {@link Player} has index
     * @return index of {@link Player}
     */
    public int getIndexOfPlayer(Player player){
        return game.getIndexOfPlayer(player);
    }

    /**
     * Function to number of {@link Player} in the {@link Game}
     * @return number of {@link Player}
     */
    public int getTotalNumberOfPlayersInTheGame(){
        return game.getPlayerList().size();
    }

    /**
     *
     * @return current {@link Pit} array
     */
    public Pit[] getPits() {
        return pits;
    }

    /**
     *
     * @param game {@link Game} to set
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     *
     * @param pits {@link Pit} array to set
     */
    public void setPits(Pit[] pits) {
        this.pits = pits;
    }

    /**
     * Function to check equality two the Board objects.
     * @param o The object to be checked for equality.
     * @return If two the Board objects are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return Arrays.equals(pits, board.pits) && Objects.equals(game, board.game);
    }

    /**
     * Function to get hashcode of the Board object.
     * @return hash code of the Board object.
     */
    @Override
    public int hashCode() {
        int result = Objects.hash(game);
        result = 31 * result + Arrays.hashCode(pits);
        return result;
    }

    /**
     * Function to print fields of the Board object.
     * @return fields of the Board object in string format.
     */
    @Override
    public String toString() {
        return "Board{" +
                "pits=" + Arrays.toString(pits) +
                ", game=" + game +
                '}';
    }

}
