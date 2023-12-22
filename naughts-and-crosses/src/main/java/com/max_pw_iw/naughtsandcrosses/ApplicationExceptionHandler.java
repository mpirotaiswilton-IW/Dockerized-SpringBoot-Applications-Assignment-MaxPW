package com.max_pw_iw.naughtsandcrosses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.max_pw_iw.naughtsandcrosses.exception.EntityNotFoundException;
import com.max_pw_iw.naughtsandcrosses.exception.ErrorResponse;
import com.max_pw_iw.naughtsandcrosses.exception.GameNotActiveException;
import com.max_pw_iw.naughtsandcrosses.exception.IllegalMoveException;
import com.max_pw_iw.naughtsandcrosses.exception.IllegalUserJoinException;
import com.max_pw_iw.naughtsandcrosses.exception.UserDeletingSelfException;
import com.max_pw_iw.naughtsandcrosses.exception.UserNotPlayerException;
import com.max_pw_iw.naughtsandcrosses.exception.WrongTurnMoveException;


@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(RuntimeException ex) {
        ErrorResponse error = new ErrorResponse(Arrays.asList(ex.getMessage()));  
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({IllegalUserJoinException.class, IllegalMoveException.class, 
                        GameNotActiveException.class, WrongTurnMoveException.class, 
                        UserDeletingSelfException.class})
    public ResponseEntity<Object> handleIllegalUserAction(RuntimeException ex) {
        ErrorResponse error = new ErrorResponse(Arrays.asList(ex.getMessage()));  
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleDataAccessException(EmptyResultDataAccessException ex) {
        ErrorResponse error = new ErrorResponse(Arrays.asList("Cannot delete non-existing resource"));  
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ErrorResponse error = new ErrorResponse(Arrays.asList("Data Integrity Violation: we cannot process your request."));  
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({UserNotPlayerException.class})
    public ResponseEntity<Object> handleIncorrectAccessException(DataIntegrityViolationException ex) {
        ErrorResponse error = new ErrorResponse(Arrays.asList(ex.getMessage()));  
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> errors.add(error.getDefaultMessage()));
        return new ResponseEntity<>(new ErrorResponse(errors), HttpStatus.BAD_REQUEST);
    }

}

