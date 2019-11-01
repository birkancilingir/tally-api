package me.brkn.tallyapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ResponseBody
    @ExceptionHandler(CounterNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String counterNotFoundHandler(CounterNotFoundException ex) {
        return ex.getMessage();
    }

}
