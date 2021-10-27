package com.bol.mancalakalahagame.model.enums;

/**
 * Enum for the State of the game
 */
public enum GameState {

    WAITING_FOR_NEXT_PLAYER("Waiting For Next Player"),
    WAITING_FOR_SAME_PLAYER("Waiting For Same Player"),
    FINISHED("The Game Is Finished");

    private final String infoMessage;

    GameState(String infoMessage) {
        this.infoMessage = infoMessage;
    }

    public String getInfoMessage() {
        return infoMessage;
    }



    @Override
    public String toString() {
        return "GameState{" +
                "infoMessage='" + infoMessage + '\'' +
                '}';
    }

}
