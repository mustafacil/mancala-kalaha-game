package com.bol.mancalakalahagame.model.domain;

import com.bol.mancalakalahagame.model.enums.GameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GameTest {

    private Player player1;
    private Player player2;
    private List<Player> playerList;
    private Game game;

    @BeforeEach
    void setUp(){
        player1 =  new Player("Test Username1");
        player2 =  new Player("Test Username2");
        playerList  = Arrays.asList(player1, player2);
        game = new Game(playerList, 0, GameState.WAITING_FOR_NEXT_PLAYER);

    }


    @Test
    void getNextPlayerIndex() {

        assertEquals(0, game.getNextPlayerIndex());

    }

    @Test
    void setNextPlayerIndex() {
        
        game.setNextPlayerIndex(1);
        assertEquals(1, game.getNextPlayerIndex());
    }

    @Test
    void getIndexOfPlayer() {

        assertEquals(0, game.getIndexOfPlayer(player1));
        assertEquals(1, game.getIndexOfPlayer(player2));
    }

    @Test
    void getIndexOfPlayerBySmallPitIndex() {

        assertThrows(IllegalArgumentException.class, ()->game.getIndexOfPlayerBySmallPitIndex(-1));
        assertThrows(IllegalArgumentException.class, ()->game.getIndexOfPlayerBySmallPitIndex(14));

        assertEquals(0, game.getIndexOfPlayerBySmallPitIndex(0));

        assertEquals(1, game.getIndexOfPlayerBySmallPitIndex(8));
    }

    @Test
    void getPlayerBySmallPitIndex() {
        assertThrows(IllegalArgumentException.class, ()->game.getPlayerBySmallPitIndex(-1));
        assertThrows(IllegalArgumentException.class, ()->game.getPlayerBySmallPitIndex(14));

        assertEquals(player1, game.getPlayerBySmallPitIndex(0));
        assertEquals(player2, game.getPlayerBySmallPitIndex(8));

    }

    @Test
    void getGameStateInfo() {

        assertEquals(GameState.WAITING_FOR_NEXT_PLAYER.getInfoMessage(), game.getGameStateInfo());
    }

    @Test
    void setGameState() {
        game.setGameState(GameState.FINISHED);
        assertEquals(GameState.FINISHED, game.getGameState());
    }

    @Test
    void getPlayerList() {

        assertEquals(playerList, game.getPlayerList());
    }
}