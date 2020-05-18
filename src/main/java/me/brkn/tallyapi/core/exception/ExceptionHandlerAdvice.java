package me.brkn.tallyapi.core.exception;

import me.brkn.tallyapi.view.exception.CounterNotFoundException;
import me.brkn.tallyapi.view.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandlerAdvice {

  @ResponseBody
  @ExceptionHandler(BusinessException.class)
  ResponseEntity<Object> businessExceptionHandler(BusinessException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
  }

  @ResponseBody
  @ExceptionHandler(CounterNotFoundException.class)
  ResponseEntity<Object> counterNotFoundExceptionHandler(CounterNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
  }

  @ResponseBody
  @ExceptionHandler(UserNotFoundException.class)
  ResponseEntity<Object> userNotFoundExceptionHandler(UserNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
  }
}
