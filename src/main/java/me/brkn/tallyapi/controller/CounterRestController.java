package me.brkn.tallyapi.controller;

import me.brkn.tallyapi.exception.CounterNotFoundException;
import me.brkn.tallyapi.model.Counter;
import me.brkn.tallyapi.repository.CounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CounterRestController {

    private final CounterRepository counterRepository;

    @Autowired
    CounterRestController(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

    @GetMapping("/counters")
    public List<Counter> listCounters() {
        return counterRepository.findAll();
    }

    @PostMapping("/counters")
    public Counter newCounter(@RequestBody Counter newCounter) {
        return counterRepository.save(newCounter);
    }

    @GetMapping("/counters/{id}")
    public Counter readCounter(@PathVariable("id") long id) {
        return counterRepository.findById(id)
                .orElseThrow(() -> new CounterNotFoundException(id));
    }

    @PutMapping("/counters/{id}")
    public Counter updateCounter(@RequestBody Counter newCounter, @PathVariable Long id) {
        return counterRepository.findById(id)
                .map(employee -> {
                    employee.setName(newCounter.getName());
                    employee.setValue(newCounter.getValue());
                    return counterRepository.save(employee);
                })
                .orElseGet(() -> {
                    newCounter.setId(id);
                    return counterRepository.save(newCounter);
                });
    }

    @DeleteMapping("/counters/{id}")
    public void deleteCounter(@PathVariable("id") long id) {
        counterRepository.deleteById(id);
    }

}