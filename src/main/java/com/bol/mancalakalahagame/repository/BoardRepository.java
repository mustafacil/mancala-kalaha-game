package com.bol.mancalakalahagame.repository;

import com.bol.mancalakalahagame.model.domain.Board;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Objects;

/**
 * Repository class for {@link Board}
 */
@Repository
public class BoardRepository {

    private static Gson converter = new Gson();

    private RedisTemplate<String, String> redisTemplate;

    @Value("${redis.board.hashname}")
    private String redisBoardHashName;

    @Autowired
    public BoardRepository(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * Save a board by instance of {@link Board} in json format
     * @param board {@link Board} instance
     */
    public void saveBoard(Board board){
        redisTemplate.opsForHash().put(redisBoardHashName, board.getId(), converter.toJson(board));
    }

    /**
     * Find a board by {@link Board}  id
     * @param id {@link Board} id
     * @return {@link Board} instance
     */
    public Board retrieveBoardById(String id){
        String boardJson = Objects.requireNonNull(redisTemplate.opsForHash().get(redisBoardHashName, id)).toString();
        return converter.fromJson(boardJson, Board.class);

    }

}
