package com.max_pw_iw.naughtsandcrosses.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.max_pw_iw.naughtsandcrosses.entity.Game;
import com.max_pw_iw.naughtsandcrosses.entity.Role;
import com.max_pw_iw.naughtsandcrosses.entity.User;
import com.max_pw_iw.naughtsandcrosses.entity.UserRequest;
import com.max_pw_iw.naughtsandcrosses.exception.EntityNotFoundException;
import com.max_pw_iw.naughtsandcrosses.repository.RoleRepository;
import com.max_pw_iw.naughtsandcrosses.repository.UserRepository;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

	private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        User unwrappedUser = unwrapUser(user, id);
        return unwrapUser(user, id);
    }

    @Override
    public User getUser(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        User unwrappedUser = unwrapUser(user, username);
        return unwrapUser(user, username);
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public List<Game> getAllGamesFromUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        User unwrappedUser = unwrapUser(user, id);
        List<Game> games = unwrappedUser.getCreatedGames();
        games.addAll(unwrappedUser.getJoinedGames());
        return games;
    }

    @Override
    public User saveUser(UserRequest userRequest) {
        User user = new User(userRequest.getUsername(), userRequest.getPassword());
        user.addRole(roleRepository.findById(1L).get());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User addRoleToUser(Long id, Long roleId) {
        Optional<User> user = userRepository.findById(id);
        User unwrappedUser = unwrapUser(user, id);
        Role role = unwrapRole(roleRepository.findById(roleId), roleId);
        unwrappedUser.addRole(role);
        return userRepository.save(unwrappedUser);
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        User unwrappedUser = unwrapUser(user, id);
        userRepository.delete(unwrappedUser);
    }

    static User unwrapUser(Optional<User> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, User.class);
    }

    static User unwrapUser(Optional<User> entity, String username) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(username, User.class);
    }

    static Role unwrapRole(Optional<Role> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, Role.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        User unwrappedUser = unwrapUser(user, username);
        return new org.springframework.security.core.userdetails.User(
            unwrappedUser.getUsername(),
            unwrappedUser.getPassword(),
            unwrappedUser.getAuthorities()
        );
    }
}

