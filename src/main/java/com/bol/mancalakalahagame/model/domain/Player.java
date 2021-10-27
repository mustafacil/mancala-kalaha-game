package com.bol.mancalakalahagame.model.domain;

import com.bol.mancalakalahagame.model.enums.PitType;

import java.util.Objects;

/**
 * Player domain class
 */
public class Player {

    private String username;

    /**
     * Player Constructor
     * @param username of the player
     */
    public Player(String username) {
        this.username = username;
    }

    /**
     *
     * @return current username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Function to do a move of a player who has turn.
     *
     * @param selectedSmallPitIndexOfBoard small pit index selected by the player
     * @param board                        board of the Game
     * @param indexOfPlayer                index of the player in the game
     * @return The {@link Pit} index with last stone added
     * @throws IllegalArgumentException If selectedSmallPitIndexOfBoard is invalid
     */
    public int play(int selectedSmallPitIndexOfBoard, Board board, int indexOfPlayer){

        //Throw Exception when pit index out of range
        if (selectedSmallPitIndexOfBoard > 13 || selectedSmallPitIndexOfBoard < 0) {
            throw new IllegalArgumentException("Cannot be selected. Selected pit index must be between 0 and 13.");
        }

        //Get number of stones to sow
        int numberOfStones = board.getNumberOfStonesOfPitByIndex(selectedSmallPitIndexOfBoard);

        //First, pick all stones of small pit
        board.pickAllStonesOfPitByIndex(selectedSmallPitIndexOfBoard);
        //Start sowing
        while (numberOfStones > 0) {
            //If it's the last pit, go back to the beginning.
            if (selectedSmallPitIndexOfBoard == board.getTotalNumberOfPitsOnBoard() - 1) {
                selectedSmallPitIndexOfBoard = -1;
            }

            //Get pit type.
            PitType pitType = board.getPitTypeByIndex(++selectedSmallPitIndexOfBoard);

            //Check the pit is large pit.
            if (pitType == PitType.LARGE) {

                //Get large pit index of player
                int playerLargePitIndexOfBoard = board.getLargePitIndexByPlayerIndex(indexOfPlayer);

                //The large pit is not current player's large pit then continue
                if (selectedSmallPitIndexOfBoard != playerLargePitIndexOfBoard) {
                    continue;
                }
            }

            //Increase number of stones of pit
            board.increaseNumberOfStonesOfPitByIndex(selectedSmallPitIndexOfBoard, 1);

            //Decrease number of stones
            --numberOfStones;

        }

        //return pit index with the last stone added.
        return selectedSmallPitIndexOfBoard;

    }

    /**
     * Function to check equality two Player objects.
     * @param o The object to be checked for equality.
     * @return If two Player objects are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(username, player.username);
    }

    /**
     * Function to get hashcode of Player object.
     * @return hash code of the Player object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    /**
     * Function to print fields of Player object.
     * @return fields of the Player object in string format.
     */
    @Override
    public String toString() {
        return "Player{" +
                "username='" + username + '\'' +
                '}';
    }
}
