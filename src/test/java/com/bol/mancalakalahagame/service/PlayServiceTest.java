package com.bol.mancalakalahagame.service;

import com.bol.mancalakalahagame.model.domain.Board;
import com.bol.mancalakalahagame.model.domain.Game;
import com.bol.mancalakalahagame.model.domain.Pit;
import com.bol.mancalakalahagame.model.domain.Player;
import com.bol.mancalakalahagame.model.enums.GameState;
import com.bol.mancalakalahagame.model.enums.PitType;
import com.bol.mancalakalahagame.repository.BoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class PlayServiceTest {

    @Mock
    private BoardRepository boardRepository;

    @Mock
    private GameService gameService;

    @Mock
    private PlayerService playerService;

    @Mock
    private BoardService boardService;

    private Board testBoard;

    private PlayService playService;

    @BeforeEach
    void setUp() {
        PitService pitService = new PitService();
        playService = new PlayService(pitService, playerService, boardService, boardRepository);
        List<Player> playerList = Arrays.asList(new Player("Username 1"), new Player("Username 2"));
        Game game = new Game(playerList, 0, GameState.WAITING_FOR_NEXT_PLAYER);
        testBoard = new Board(pitService.createPitArray(2), game);

    }

    @Test
    void makeAMove() {

        Mockito.when(boardRepository.retrieveBoardById("test")).thenReturn(testBoard);
        testBoard.setPits(getPitArrayAfterSelectedFirstSmallPit());
        Mockito.when(playerService.playGame(testBoard,0)).thenReturn(testBoard);
        Mockito.when(boardService.checkGameFinished(testBoard,2)).thenReturn(false);

        assertEquals(testBoard, playService.makeAMove(0, "test"));

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