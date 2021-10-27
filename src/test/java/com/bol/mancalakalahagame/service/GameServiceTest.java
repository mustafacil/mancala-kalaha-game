package com.bol.mancalakalahagame.service;

import com.bol.mancalakalahagame.model.domain.Game;
import com.bol.mancalakalahagame.model.domain.Player;
import com.bol.mancalakalahagame.model.enums.GameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    private GameService gameService;

    @BeforeEach
    void setUp(){

        gameService = new GameService();
    }

    @Test
    void switchTurn() {

        List<Player> playerList = Arrays.asList(new Player("Test Username1"), new Player("Test Username2"));
        Game game = new Game(playerList, 0, GameState.WAITING_FOR_NEXT_PLAYER);

        gameService.switchTurn(game);
        assertEquals(game.getNextPlayerIndex(), 1);
        gameService.switchTurn(game);
        assertEquals(game.getNextPlayerIndex(), 0);


    }
}