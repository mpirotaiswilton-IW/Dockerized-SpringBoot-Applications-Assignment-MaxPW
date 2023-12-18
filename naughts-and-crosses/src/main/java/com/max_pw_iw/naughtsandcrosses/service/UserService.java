package com.max_pw_iw.naughtsandcrosses.service;

import java.util.List;

import com.max_pw_iw.naughtsandcrosses.entity.Game;
import com.max_pw_iw.naughtsandcrosses.entity.User;

public interface UserService {
    User getUser(Long id);
    User getUser(String username);
    List<User> getAllUsers();
    List<Game> getAllGamesFromUser(Long id);
    User saveUser(User user);
    void deleteUser(Long id);
}
