package com.max_pw_iw.naughtsandcrosses.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.max_pw_iw.naughtsandcrosses.entity.User;
import com.max_pw_iw.naughtsandcrosses.exception.EntityNotFoundException;
import com.max_pw_iw.naughtsandcrosses.repository.UserRepository;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        return unwrapUser(user, id);
    }

    @Override
    public User getUser(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return unwrapUser(user, 404L);
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
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
    
}

