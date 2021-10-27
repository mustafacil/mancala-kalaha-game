package com.bol.mancalakalahagame.service;

import com.bol.mancalakalahagame.model.domain.Board;
import com.bol.mancalakalahagame.model.domain.Game;
import com.bol.mancalakalahagame.model.domain.Pit;
import com.bol.mancalakalahagame.model.domain.Player;
import com.bol.mancalakalahagame.model.enums.GameState;
import com.bol.mancalakalahagame.model.enums.PitType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {

    @Mock
    private GameService gameService;

    private PlayerService playerService;

    private Board board;

    @BeforeEach
    void setUp() {

        PitService pitService = new PitService();
        playerService = new PlayerService(gameService);
        List<Player> playerList = Arrays.asList(new Player("test username1"), new Player("test username2"));
        Game game = new Game(playerList, 0, GameState.WAITING_FOR_NEXT_PLAYER);
        board = new Board(pitService.createPitArray(2), game);

    }

    @Test
    void playGameForPlayer() {

        board = playerService.playGame(board,0);
        assertArrayEquals(getPitArrayAfterSelectedFirstSmallPit(), board.getPits());

    }

    Pit[] getPitArrayAfterSelectedFirstSmallPit() {

        Pit[] pitArray = new Pit[14];

        for (int i = 0; i < 7; i++) {

            if (i == 6) {
                pitArray[i] = new Pit(PitType.LARGE, 1);
                continue;
            }
            if(i==0){
                pitArray[i] = new Pit(PitType.SMALL, 0);
                continue;
            }

            pitArray[i] = new Pit(PitType.SMALL, 7);

        }

        for (int i = 7; i < 14; i++) {

            if (i == 13) {
                pitArray[i] = new Pit(PitType.LARGE, 0);
                continue;
            }
            pitArray[i] = new Pit(PitType.SMALL, 6);

        }

        return pitArray;
    }
}