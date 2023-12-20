package com.max_pw_iw.naughtsandcrosses.web;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.max_pw_iw.naughtsandcrosses.dto.AuthRequest;
import com.max_pw_iw.naughtsandcrosses.security.TokenProvider;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class AuthController {

    private AuthenticationManager authenticationManager;
    private TokenProvider jwtTokenUtil;

    @PostMapping("/authenticate")
	public ResponseEntity<String> createUser(@Valid @RequestBody AuthRequest user) {
		final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        return ResponseEntity.ok(token);
	}



}

