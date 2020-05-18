package me.brkn.tallyapi.service.counter;

import java.util.List;
import java.util.Optional;
import me.brkn.tallyapi.service.counter.model.Counter;

public interface CounterService {

  List<Counter> getCounterList();

  Counter saveCounter(Counter newCounter);

  Optional<Counter> getCounter(Long id);

  void deleteCounter(Long id);

  boolean counterExists(Long id);
}
