package com.max_pw_iw.naughtsandcrosses.web;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.max_pw_iw.naughtsandcrosses.entity.Game;
import com.max_pw_iw.naughtsandcrosses.entity.User;
import com.max_pw_iw.naughtsandcrosses.entity.UserRequest;
import com.max_pw_iw.naughtsandcrosses.security.SecurityConstants;
import com.max_pw_iw.naughtsandcrosses.service.UserService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;


@AllArgsConstructor
@RestController
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserService userService;

    @PostMapping("/authentication")
	public ResponseEntity<String> createUser(@Valid @RequestBody UserRequest user) {
		try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

        UserDetails userDetails = userService.loadUserByUsername(user.getUsername());
        String token = JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.TOKEN_EXPIRATION))
                .withClaim("roles", userDetails.getAuthorities().toString())
                .sign(Algorithm.HMAC512(SecurityConstants.SECRET_KEY));

        return ResponseEntity.ok(token);
	}



}

