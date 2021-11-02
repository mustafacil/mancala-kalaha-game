package com.bol.mancalakalahagame.service;

import com.bol.mancalakalahagame.model.domain.Board;
import com.bol.mancalakalahagame.model.domain.Player;
import com.bol.mancalakalahagame.model.enums.GameState;
import com.bol.mancalakalahagame.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class to handle gameplay related actions
 */
@Service
public class PlayService {

    private PitService pitService;
    private PlayerService playerService;
    private BoardService boardService;
    private GameService gameService;
    private BoardRepository boardRepository;


    @Autowired
    public PlayService(PitService pitService, PlayerService playerService, BoardService boardService, GameService gameService, BoardRepository boardRepository) {
        this.pitService = pitService;
        this.playerService = playerService;
        this.boardService = boardService;
        this.gameService = gameService;
        this.boardRepository = boardRepository;
    }

    /**
     * Function to do a Player Move
     * @param selectedSmallPitIndexOfBoard the small pit that the Player chooses.
     * @param boardId of current {@link Board}
     * @return updated {@link Board} after move
     */
    public Board makeAMove(int selectedSmallPitIndexOfBoard, String boardId) {


        //retrieve last board state from redis
        Board board = boardRepository.retrieveBoardById(boardId);
        boolean checkTurn = gameService.checkTurn(board.getGame(), selectedSmallPitIndexOfBoard);
        if(!checkTurn){
            return board;
        }

        //make a move and retrieve updated board
        board = playerService.playGame(board, selectedSmallPitIndexOfBoard);
        //check game is finished
        boolean finished = boardService.checkGameFinished(board, board.getTotalNumberOfPlayersInTheGame());
        if(finished){
            board.setGameState(GameState.FINISHED);
        }
        //save last state of board
        boardRepository.saveBoard(board);
        return board;
    }


}
