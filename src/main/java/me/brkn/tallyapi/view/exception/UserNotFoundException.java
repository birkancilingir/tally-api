package me.brkn.tallyapi.view.exception;

import me.brkn.tallyapi.core.exception.BusinessException;

public class UserNotFoundException extends BusinessException {

  public UserNotFoundException(String username) {
    super("Could not find user " + username);
  }
}
