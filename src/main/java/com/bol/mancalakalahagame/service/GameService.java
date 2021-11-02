package com.bol.mancalakalahagame.service;

import com.bol.mancalakalahagame.model.domain.Game;
import com.bol.mancalakalahagame.model.domain.Player;
import com.bol.mancalakalahagame.model.enums.GameState;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Class for {@link Game} related actions
 */
@Service
public class GameService {

    /**
     * Function to switch a player's turn.
     * @param game current {@link Game}
     */
    public void switchTurn(Game game) {

        //get index of next player
        int index = game.getNextPlayerIndex();
        ++index;

        //check next index of player is out of range
        if (index == game.getPlayerList().size()) {
            index = 0;
        }
        //change game state
        game.setGameState(GameState.WAITING_FOR_NEXT_PLAYER);
        //change next player.
        game.setNextPlayerIndex(index);

    }

    public boolean checkTurn(Game game, int selectedSmallPitIndex){

        int indexOfPlayerBySmallPit = game.getIndexOfPlayerBySmallPitIndex(selectedSmallPitIndex);

        if(game.getNextPlayerIndex() == indexOfPlayerBySmallPit){
            return true;
        }

        return false;

    }


}
