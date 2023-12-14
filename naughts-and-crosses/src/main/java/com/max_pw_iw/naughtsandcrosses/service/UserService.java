package com.max_pw_iw.naughtsandcrosses.service;

import com.max_pw_iw.naughtsandcrosses.entity.User;

public interface UserService {
    User getUser(Long id);
    User getUser(String username);
    User saveUser(User user);
    void deleteUser(Long id);
}
