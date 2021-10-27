package com.bol.mancalakalahagame.model.domain;

import com.bol.mancalakalahagame.model.enums.PitType;

import java.util.Objects;

/**
 * Pit domain class
 */
public class Pit {

    private PitType pitType;
    private int numberOfStones;


    /**
     * Pit Constructor
     * @param pitType The {@link PitType} of the pit.
     * @param numberOfStones Total number of stones in the pit.
     */
    public Pit(PitType pitType, int numberOfStones) {
        this.pitType = pitType;
        this.numberOfStones = numberOfStones;
    }

    /**
     *
     * @return current total number of stones of the pit.
     */
    public int getNumberOfStones() {
        return numberOfStones;
    }

    /**
     *
     * @return current {@link PitType} of the pit
     */
    public PitType getPitType() {
        return pitType;
    }

    /**
     * Function to increase total number of stones
     * @param numberOfStonesAdded number of stones to be increased
     */
    public void increaseNumberOfStones(int numberOfStonesAdded) {
        numberOfStones += numberOfStonesAdded;
    }

    /**
     * Function to pick all stones of the pit.
     */
    public void pickAllStones() {
        numberOfStones = 0;
    }

    /**
     * Function to retrieve opposite pit index of the pit.
     * @param pitIndexOfBoard The current pit index.
     * @return opposite pit index of the current pit.
     */
    public int getOppositeIndex(int pitIndexOfBoard) {
        return 5 - pitIndexOfBoard + 7;
    }

    /**
     * Function to check equality two the Pit objects.
     * @param o The object to be checked for equality.
     * @return If two the Pit objects are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pit pit = (Pit) o;
        return numberOfStones == pit.numberOfStones && pitType == pit.pitType;
    }

    /**
     * Function to get hashcode of the Pit object.
     * @return hash code of the Pit object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(pitType, numberOfStones);
    }

    /**
     * Function to print fields of the Pit object.
     * @return fields of the Pit object in string format.
     */
    @Override
    public String toString() {
        return pitType + ":" + numberOfStones;
    }

}
