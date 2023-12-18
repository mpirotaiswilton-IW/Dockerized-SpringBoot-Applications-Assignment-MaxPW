package com.max_pw_iw.naughtsandcrosses.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.max_pw_iw.naughtsandcrosses.entity.Game;
import com.max_pw_iw.naughtsandcrosses.entity.User;
import com.max_pw_iw.naughtsandcrosses.service.UserService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;


@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;

	@GetMapping("/{id}")
	public ResponseEntity<String> findById(@PathVariable Long id) {
		String userName = userService.getUser(id).getUsername();
		return new ResponseEntity<>(userName, HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<User>> getAllUserName(@RequestParam String param) {
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
	}

	@GetMapping("/{id}/games")
	public ResponseEntity<List<Game>> getMethodName(@RequestParam Long id) {
		return new ResponseEntity<>(userService.getAllGamesFromUser(id), HttpStatus.OK);
	}

    @PostMapping("/register")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		userService.saveUser(user);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}



}
