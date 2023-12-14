package com.max_pw_iw.naughtsandcrosses.service;

import com.max_pw_iw.naughtsandcrosses.entity.Game;
import com.max_pw_iw.naughtsandcrosses.entity.User;

public interface GameService {
    Game getGame(long id);
    Game createGame(Game game, String username);
    Game joinGame(long id, String username);
    Game addMove(long id, int move, String username);
    void deleteGame(long id);
}
