package me.brkn.tallyapi.controller;

import me.brkn.tallyapi.exception.CounterNotFoundException;
import me.brkn.tallyapi.model.Counter;
import me.brkn.tallyapi.repository.CounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
    public EntityModel<Counter> readCounter(@PathVariable("id") long id) {
        Counter counter = counterRepository.findById(id)
                .orElseThrow(() -> new CounterNotFoundException(id));

        return new EntityModel<Counter>(counter,
                linkTo(methodOn(CounterRestController.class).readCounter(id)).withSelfRel(),
                linkTo(methodOn(CounterRestController.class).listCounters()).withRel("counter"));

    }

    @PutMapping("/counters/{id}")
    public Counter updateCounter(@RequestBody Counter newCounter, @PathVariable Long id) {
        return counterRepository.findById(id)
                .map(counter -> {
                    counter.setName(newCounter.getName());
                    counter.setValue(newCounter.getValue());
                    return counterRepository.save(counter);
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