package me.brkn.tallyapi.service.counter;

import me.brkn.tallyapi.service.counter.model.Counter;

import java.util.List;
import java.util.Optional;

public interface CounterService {
    List<Counter> getCounterList();

    Counter saveCounter(Counter newCounter);

    Optional<Counter> getCounter(Long id);

    void deleteCounter(Long id);

    boolean counterExists(Long id);
}
