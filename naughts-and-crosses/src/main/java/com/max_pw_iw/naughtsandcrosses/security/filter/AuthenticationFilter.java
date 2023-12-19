package com.max_pw_iw.naughtsandcrosses.security.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
// import javax.servlet.ServletRequest;
// import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// import org.apache.catalina.connector.Response;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// import org.springframework.web.bind.annotation.ControllerAdvice;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.max_pw_iw.naughtsandcrosses.entity.User;
import com.max_pw_iw.naughtsandcrosses.entity.UserRequest;
import com.max_pw_iw.naughtsandcrosses.repository.UserRepository;
import com.max_pw_iw.naughtsandcrosses.security.SecurityConstants;
import com.max_pw_iw.naughtsandcrosses.security.manager.CustomAuthenticationManager;

import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{

    private CustomAuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UserRequest user = new ObjectMapper().readValue(request.getInputStream(), UserRequest.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            return authenticationManager.authenticate(authentication);
        } catch (IOException e) {
            throw new RuntimeException();
        } 
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = JWT.create()
                .withSubject(authResult.getName())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.TOKEN_EXPIRATION))
                // .withClaim("roles", authResult.getAuthorities().toString())
                .sign(Algorithm.HMAC512(SecurityConstants.SECRET_KEY));
        response.addHeader(SecurityConstants.AUTHORIZATION,SecurityConstants.BEARER + token);

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(failed.getMessage());
        response.getWriter().flush();
    }
}
