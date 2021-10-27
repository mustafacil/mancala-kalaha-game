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
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class BoardServiceTest {

    @Mock
    private BoardRepository boardRepository;

    private BoardService boardService;
    private Board board;

    @BeforeEach
    void setUp() {
        PitService pitService = new PitService();
        boardService = new BoardService(boardRepository, pitService);
        List<Player> playerList = Arrays.asList(new Player("Username 1"), new Player("Username 2"));
        Game game = new Game(playerList, 0, GameState.WAITING_FOR_NEXT_PLAYER);
        board = new Board(pitService.createPitArray(2), game);

    }

    @Test
    void createNewBoard() {
        assertEquals(board, boardService.createNewBoard());
    }

    @Test
    void checkGameFinished() {
        board.setPits(getPitArrayInGameFinishedState());
        assertTrue(boardService.checkGameFinished(board, 2));

    }

    @Test
    void produceBoardId() {

        String usernameOfFirstPlayer = board.getGame().getPlayerList().get(0).getUsername();
        String usernameOfSecondPlayer = board.getGame().getPlayerList().get(1).getUsername();
        String formattedDate = new SimpleDateFormat("ddMMyyyyHHmmss").format(new Date());
        String boardId = usernameOfFirstPlayer + usernameOfSecondPlayer + formattedDate;
        assertEquals(boardId, boardService.createBoardId(board));

    }


    Pit[] getPitArrayInGameFinishedState() {

        Pit[] pitArray = new Pit[14];

        for (int i = 0; i < 7; i++) {

            if (i == 6) {
                pitArray[i] = new Pit(PitType.LARGE, 30);
                continue;
            }
            pitArray[i] = new Pit(PitType.SMALL, 0);

        }

        for (int i = 7; i < 14; i++) {

            if (i == 13) {
                pitArray[i] = new Pit(PitType.LARGE, 40);
                continue;
            }
            pitArray[i] = new Pit(PitType.SMALL, 2);

        }

        return pitArray;
    }
}