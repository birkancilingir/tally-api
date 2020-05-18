package me.brkn.tallyapi.view.exception;

import me.brkn.tallyapi.core.exception.BusinessException;

public class CounterNotFoundException extends BusinessException {

  public CounterNotFoundException(Long id) {
    super("Could not find counter " + id);

  }

}
