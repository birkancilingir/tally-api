package me.brkn.tallyapi.exception;

public class UserNotFoundException extends BusinessException {
    public UserNotFoundException(String username) {
        super("Could not find user " + username);
    }
}
