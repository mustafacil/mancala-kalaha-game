package com.bol.mancalakalahagame.service;

import com.bol.mancalakalahagame.model.domain.Board;
import com.bol.mancalakalahagame.model.domain.Game;
import com.bol.mancalakalahagame.model.domain.Pit;
import com.bol.mancalakalahagame.model.domain.Player;
import com.bol.mancalakalahagame.model.enums.GameState;
import com.bol.mancalakalahagame.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * Class for {@link Board} related actions
 */
@Service
public class BoardService {

    private BoardRepository boardRepository;
    private PitService pitService;

    @Autowired
    public BoardService(BoardRepository boardRepository, PitService pitService) {
        this.boardRepository = boardRepository;
        this.pitService = pitService;
    }

    /**
     * Function to check game is finished
     * @param board current {@link Board} instance
     * @param numberOfPlayers total number of players
     * @return boolean if the game is finished
     */
    public boolean checkGameFinished(Board board, int numberOfPlayers) {

        //check number of stones in small pits
        for (int i = 0; i < numberOfPlayers; i++){
            int numberOfStonesInSmallPits = board.getTotalNumberOfStonesInSmallPitsByIndexOfPlayer(i);
            if (numberOfStonesInSmallPits == 0) {
                return true;
            }
        }
        return false;

    }


    /**
     * Function to create a new {@link Board}
     * @return {@link Board}
     */
    public Board createNewBoard() {
        Pit[] pitArray = pitService.createPitArray(2);
        Player p1 = new Player("Username 1");
        Player p2 = new Player("Username 2");

        List<Player> playerList = Arrays.asList(p1, p2);

        Game game = new Game(playerList, 0, GameState.WAITING_FOR_NEXT_PLAYER);
        Board board = new Board(pitArray, game);
        board.setId();
        boardRepository.saveBoard(board);
        return board;
    }

}
