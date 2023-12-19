package com.max_pw_iw.naughtsandcrosses.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.max_pw_iw.naughtsandcrosses.entity.Game;
import com.max_pw_iw.naughtsandcrosses.entity.User;
import com.max_pw_iw.naughtsandcrosses.entity.UserRequest;

@Service
public interface UserService extends UserDetailsService{
    User getUser(Long id);
    User getUser(String username);
    UserDetails loadUserByUsername(String username);
    List<User> getAllUsers();
    List<Game> getAllGamesFromUser(Long id);
    User saveUser(UserRequest user);
    User addRoleToUser(Long id, Long roleId);
    void deleteUser(Long id);
}
