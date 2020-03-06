package me.brkn.tallyapi.view.exception;

public class UserNotFoundException extends BusinessException {
    public UserNotFoundException(String username) {
        super("Could not find user " + username);
    }
}
