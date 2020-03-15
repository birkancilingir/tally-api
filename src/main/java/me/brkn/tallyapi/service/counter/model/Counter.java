package me.brkn.tallyapi.service.counter.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Counter {

    private long id;

    private String name;

    private int value;

}
