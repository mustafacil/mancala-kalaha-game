package com.bol.mancalakalahagame.service;

import com.bol.mancalakalahagame.model.domain.Board;
import com.bol.mancalakalahagame.model.domain.Pit;
import com.bol.mancalakalahagame.model.enums.PitType;
import org.springframework.stereotype.Service;

/**
 * Class for {@link Pit} related actions
 */
@Service
public class PitService {


    /**
     * Function to create {@link Pit} array
     * @param numberOfPlayer number of players in the game.
     * @return {@link Pit} array
     */
    public Pit[] createPitArray(int numberOfPlayer) {

        //get total number of pits on board by number of player
        int totalNumberOfPitsOnBoard = Board.NUMBER_OF_PITS_OF_EACH_PLAYER * numberOfPlayer;


        Pit[] pitArray = new Pit[totalNumberOfPitsOnBoard];

        //create pit array
        for (int i = 1; i <= totalNumberOfPitsOnBoard; i++) {

            //One of pit is a large pit.
            if (i % 7 == 0) {
                pitArray[i - 1] = new Pit(PitType.LARGE, 0);
                continue;
            }

            //Every player has 6 small pits.
            pitArray[i - 1] = new Pit(PitType.SMALL, 6);


        }

        return pitArray;

    }
}
