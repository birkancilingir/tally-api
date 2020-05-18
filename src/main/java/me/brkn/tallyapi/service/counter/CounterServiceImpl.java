package me.brkn.tallyapi.service.counter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import me.brkn.tallyapi.data.model.CounterDao;
import me.brkn.tallyapi.data.repository.CounterRepository;
import me.brkn.tallyapi.service.counter.mapper.CounterMapper;
import me.brkn.tallyapi.service.counter.model.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CounterServiceImpl implements CounterService {

  private CounterRepository counterRepository;

  @Autowired
  public CounterServiceImpl(CounterRepository counterRepository) {
    this.counterRepository = counterRepository;
  }

  @Override
  public List<Counter> getCounterList() {
    return counterRepository.findAll().stream().map(c -> new Counter(c.getId(), c.getName(),
        c.getValue())).collect(Collectors.toList());
  }

  @Override
  public Counter saveCounter(Counter newCounter) {
    return CounterMapper.INSTANCE.mapToCounter(
        counterRepository.save(new CounterDao(newCounter.getName(), newCounter.getValue())));
  }

  @Override
  public Optional<Counter> getCounter(Long id) {
    return counterRepository.findById(id)
        .map(counter -> new Counter(counter.getId(), counter.getName(), counter.getValue()));
  }

  @Override
  public void deleteCounter(Long id) {
    counterRepository.deleteById(id);
  }

  @Override
  public boolean counterExists(Long id) {
    return counterRepository.existsById(id);
  }
}
