package me.brkn.tallyapi.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Counter {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String name;
    private int value;

    public Counter() {}

    public Counter(String name, int value) {
        this.name = name;
        this.value = value;
    }
}
