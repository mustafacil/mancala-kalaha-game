package com.bol.mancalakalahagame.model.domain;

import com.bol.mancalakalahagame.model.enums.GameState;
import com.bol.mancalakalahagame.service.PitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class PlayerTest {

    private Player player1;
    private Player player2;

    @BeforeEach
    void setUp() {
        player1 = new Player("Test-Username1");
        player2 = new Player("Test-Username2");
    }

    @Test
    void play() {

        PitService pitService  = new PitService();
        Pit[] pitArray = pitService.createPitArray(2);
        List<Player> playerList = Arrays.asList(player1, player2);
        Game game = new Game(playerList, 0, GameState.WAITING_FOR_NEXT_PLAYER);
        Board board = new Board(pitArray, game);

        assertThrows(IllegalArgumentException.class, ()->player1.play(14, board, 0));
        assertThrows(IllegalArgumentException.class, ()->player1.play(-1, board, 0));

        int lastPit = player1.play(0, board, 0);
        assertEquals(6, lastPit);

    }

}