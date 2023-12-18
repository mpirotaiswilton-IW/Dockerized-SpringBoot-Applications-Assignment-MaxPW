package com.max_pw_iw.naughtsandcrosses.service;

import java.util.List;

import com.max_pw_iw.naughtsandcrosses.entity.Game;
import com.max_pw_iw.naughtsandcrosses.entity.GameRequest;

public interface GameService {
    Game getGame(long id);
    List<Game> getAllGames();
    Game createGame(GameRequest game, String username);
    Game joinGame(long id, String username);
    Game addMove(long id, int move, String username);
    Game forfeitGame(long id, String username);
    void deleteGame(long id, String username);
}
