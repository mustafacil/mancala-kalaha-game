package com.bol.mancalakalahagame.model.domain;

import com.bol.mancalakalahagame.model.enums.GameState;
import com.bol.mancalakalahagame.model.enums.PitType;
import com.bol.mancalakalahagame.service.PitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardTest {

    private Board board;
    private Player player1;
    private Player player2;

    @BeforeEach
    void setUp(){

        player1 = new Player("Test Username1");
        player2 = new Player("Test Username2");

        PitService pitService  = new PitService();
        Pit[] pitArray = pitService.createPitArray(2);
        List<Player> playerList = Arrays.asList(player1, player2);
        Game game = new Game(playerList, 0, GameState.WAITING_FOR_NEXT_PLAYER);
        board = new Board(pitArray, game);

    }

    @Test
    void getTotalNumberOfStonesInSmallPitsByIndexOfPlayer() {

        assertEquals(36, board.getTotalNumberOfStonesInSmallPitsByIndexOfPlayer(0));
        assertEquals(36, board.getTotalNumberOfStonesInSmallPitsByIndexOfPlayer(1));
    }

    @Test
    void getPitByIndex() {

        Pit pit = board.getPitByIndex(0);
        assertEquals(PitType.SMALL, pit.getPitType());

        pit = board.getPitByIndex(6);
        assertEquals(PitType.LARGE, pit.getPitType());


    }

    @Test
    void getNumberOfStonesOfPitByIndex() {

        assertEquals(6,  board.getNumberOfStonesOfPitByIndex(0));
        assertEquals(0, board.getNumberOfStonesOfPitByIndex(6));
    }

    @Test
    void pickAllStonesOfPitByIndex() {
        board.pickAllStonesOfPitByIndex(0);
        assertEquals(0, board.getNumberOfStonesOfPitByIndex(0));
    }

    @Test
    void getTotalNumberOfPitsOnBoard() {

        assertEquals(Board.NUMBER_OF_PITS_OF_EACH_PLAYER*2, board.getTotalNumberOfPitsOnBoard());
    }

    @Test
    void getPitTypeByIndex() {

        assertEquals(PitType.SMALL, board.getPitTypeByIndex(0));
        assertEquals(PitType.LARGE, board.getPitTypeByIndex(6));

    }

    @Test
    void increaseNumberOfStonesOfPitByIndex() {

        board.increaseNumberOfStonesOfPitByIndex(0, 6);
        assertEquals(12, board.getNumberOfStonesOfPitByIndex(0));

        board.increaseNumberOfStonesOfPitByIndex(6, 10);
        assertEquals(10, board.getNumberOfStonesOfPitByIndex(6));

    }

    @Test
    void getLowestSmallPitIndexByPlayerIndex() {

        assertEquals(0, board.getLowestSmallPitIndexByPlayerIndex(0));
        assertEquals(7, board.getLowestSmallPitIndexByPlayerIndex(1));
        assertEquals(14, board.getLowestSmallPitIndexByPlayerIndex(2));
        assertEquals(21, board.getLowestSmallPitIndexByPlayerIndex(3));
    }

    @Test
    void getHighestSmallPitIndexByPlayerIndex() {

        assertEquals(5, board.getHighestSmallPitIndexByPlayerIndex(0));
        assertEquals(12, board.getHighestSmallPitIndexByPlayerIndex(1));
        assertEquals(19, board.getHighestSmallPitIndexByPlayerIndex(2));
        assertEquals(26, board.getHighestSmallPitIndexByPlayerIndex(3));
    }

    @Test
    void getLargePitIndexByPlayerIndex() {

        assertEquals(6, board.getLargePitIndexByPlayerIndex(0));
        assertEquals(13, board.getLargePitIndexByPlayerIndex(1));
        assertEquals(20, board.getLargePitIndexByPlayerIndex(2));
        assertEquals(27, board.getLargePitIndexByPlayerIndex(3));

    }

    @Test
    void getOppositePitIndexOfPitByIndex() {

        assertEquals(12, board.getOppositePitIndexOfPitByIndex(0));
    }

    @Test
    void getPlayerBySmallPitIndex() {

        assertEquals(player1, board.getPlayerBySmallPitIndex(0));
        assertEquals(player2, board.getPlayerBySmallPitIndex(8));

    }

    @Test
    void getTotalNumberOfPlayersInTheGame(){
        assertEquals(2, board.getTotalNumberOfPlayersInTheGame());
    }

    @Test
    void setGameState() {

        board.setGameState(GameState.FINISHED);

        assertEquals(GameState.FINISHED, board.getGameState());
    }

    @Test
    void getIndexOfPlayer() {

        assertEquals(0, board.getIndexOfPlayer(player1));
        assertEquals(1, board.getIndexOfPlayer(player2));
    }
}