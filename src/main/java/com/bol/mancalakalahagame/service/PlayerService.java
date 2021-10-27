package com.bol.mancalakalahagame.service;


import com.bol.mancalakalahagame.model.domain.Board;
import com.bol.mancalakalahagame.model.domain.Player;
import com.bol.mancalakalahagame.model.enums.GameState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class to handle a move
 */
@Service
public class PlayerService {

    private GameService gameService;

    @Autowired
    public PlayerService(GameService gameService) {
        this.gameService = gameService;
    }

    /**
     * Function to do a Player Move
     * @param board {@link Board} before move
     * @param selectedSmallPitIndexOfBoard selected pit index of {@link Player}
     * @return {@link Board} after move
     */
    public Board playGame(Board board, int selectedSmallPitIndexOfBoard){
        //get total number of stones of small pit
        int numberOfStonesOfSelectedSmallPit = board.getNumberOfStonesOfPitByIndex(selectedSmallPitIndexOfBoard);
        //check total number of stones more than zero
        if (numberOfStonesOfSelectedSmallPit == 0) {
            return board;
        }

        //get current player
        Player player = board.getPlayerBySmallPitIndex(selectedSmallPitIndexOfBoard);
        //get current player index to play
        int indexOfPlayer = board.getIndexOfPlayer(player);
        //get index of pit with the last stone added
        int lastIndexOfPit = player.play(selectedSmallPitIndexOfBoard, board, indexOfPlayer);

        //check if the pit with the last stone added index is the current player's large pit index.
        if (lastIndexOfPit == board.getLargePitIndexByPlayerIndex(indexOfPlayer)) {
            //same player plays again
            board.setGameState(GameState.WAITING_FOR_SAME_PLAYER);
            return board;
        }

        //get player has the pit with the last stone added
        Player playerHaveSmallPit = board.getPlayerBySmallPitIndex(lastIndexOfPit);

        //check the pit with the last stone added is current player's  empty pit
        if (board.getNumberOfStonesOfPitByIndex(lastIndexOfPit) == 1 && player.equals(playerHaveSmallPit)) {

            //get opposite pit index
            int oppositePitIndex = board.getOppositePitIndexOfPitByIndex(lastIndexOfPit);
            //get number of stones of opposite pit
            int numberOfStonesOfOppositePit = board.getNumberOfStonesOfPitByIndex(oppositePitIndex);
            //get current player's large pit index
            int playerLargePitIndex = board.getLargePitIndexByPlayerIndex(indexOfPlayer);
            //add stones from opposite and last pit to current player's large pit
            board.increaseNumberOfStonesOfPitByIndex(playerLargePitIndex, numberOfStonesOfOppositePit + 1);
            //pick all stones of opposite pit
            board.pickAllStonesOfPitByIndex(oppositePitIndex);
            //pick all stones of last pit
            board.pickAllStonesOfPitByIndex(lastIndexOfPit);
        }
        //switch player's turn
        gameService.switchTurn(board.getGame());

        return board;
    }

}
