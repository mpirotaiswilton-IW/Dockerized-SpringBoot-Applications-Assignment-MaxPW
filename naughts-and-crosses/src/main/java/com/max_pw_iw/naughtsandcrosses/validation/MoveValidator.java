package com.max_pw_iw.naughtsandcrosses.validation;

import java.util.List;
import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class MoveValidator implements ConstraintValidator<Move,Integer> {

    List<String> moveSpots = Arrays.asList(
        "1", "2", "3",
        "4", "5", "6",
        "7", "8", "9"
        );


    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if(value == null){
            return false;
        } else if(value > 0 && value < 10) {
            return true;
        }
        return false;
    }
    
}
