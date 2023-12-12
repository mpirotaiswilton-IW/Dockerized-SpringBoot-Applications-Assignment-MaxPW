package com.max_pw_iw.helloworld.services;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class HelloWorldController {
    @GetMapping("/")
    public ResponseEntity<String> getHelloWorld() {
        return new ResponseEntity<>("Hello World! :D", HttpStatus.OK);
    }
    
}
