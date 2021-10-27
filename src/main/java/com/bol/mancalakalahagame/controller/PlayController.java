package com.bol.mancalakalahagame.controller;

import com.bol.mancalakalahagame.model.domain.Board;
import com.bol.mancalakalahagame.service.BoardService;
import com.bol.mancalakalahagame.service.PitService;
import com.bol.mancalakalahagame.service.PlayService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PlayController {

    private PlayService playService;
    private PitService pitService;
    private BoardService boardService;

    public PlayController(PlayService playService, PitService pitService, BoardService boardService) {
        this.playService = playService;
        this.pitService = pitService;
        this.boardService = boardService;
    }

    @PostMapping("/start")
    public ResponseEntity<Board> startGame() {
        Board board = boardService.createNewBoard();
        return new ResponseEntity<>(board, HttpStatus.CREATED);

    }

    @GetMapping("/makeamove/{smallpitindex}/boardid/{id}")
    public ResponseEntity<Board> makeAMove(@PathVariable("smallpitindex") String smallPitIndex, @PathVariable("id") String id) {
        Board board = playService.makeAMove(Integer.parseInt(smallPitIndex), id);
        return new ResponseEntity<>(board, HttpStatus.CREATED);
    }


}
