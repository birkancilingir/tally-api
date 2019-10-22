package me.brkn.tallyapi.exception;

public class CounterNotFoundException extends RuntimeException{

    public CounterNotFoundException(Long id) {
            super("Could not find counter " + id);

    }

}
