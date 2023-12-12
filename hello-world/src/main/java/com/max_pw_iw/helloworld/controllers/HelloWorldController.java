package com.max_pw_iw.helloworld.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class HelloWorldController {
    @GetMapping("/")
    public ResponseEntity<String> getHelloWorld() {
        return new ResponseEntity<>("Hello World! :D", HttpStatus.OK);
    }
    
}
