package me.brkn.tallyapi.controller;

import me.brkn.tallyapi.CounterRepresentationModelAssembler;
import me.brkn.tallyapi.exception.CounterNotFoundException;
import me.brkn.tallyapi.model.Counter;
import me.brkn.tallyapi.repository.CounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class CounterRestController {

    private final CounterRepository counterRepository;

    private final CounterRepresentationModelAssembler counterRepresentationModelAssembler;

    @Autowired
    CounterRestController(CounterRepository counterRepository, CounterRepresentationModelAssembler counterRepresentationModelAssembler) {
        this.counterRepository = counterRepository;
        this.counterRepresentationModelAssembler = counterRepresentationModelAssembler;
    }

    @GetMapping("/counters")
    public CollectionModel<EntityModel<Counter>> listCounters() {
        List<EntityModel<Counter>> counters = counterRepository.findAll().stream()
                .map(counterRepresentationModelAssembler::toModel).collect(Collectors.toList());

        return new CollectionModel<>(counters,
                linkTo(methodOn(CounterRestController.class).listCounters()).withSelfRel());
    }

    @PostMapping("/counters")
    public  ResponseEntity<EntityModel<Counter>> newCounter(@RequestBody Counter newCounter) throws URISyntaxException {
        EntityModel<Counter> counter = counterRepresentationModelAssembler.toModel(counterRepository.save(newCounter));

        URI location = linkTo(methodOn(CounterRestController.class).readCounter(counter.getContent().getId())).toUri();
        
        return ResponseEntity.created(location).body(counter);
    }

    @GetMapping("/counters/{id}")
    public EntityModel<Counter> readCounter(@PathVariable("id") long id) {
        Counter counter = counterRepository.findById(id)
                .orElseThrow(() -> new CounterNotFoundException(id));

        return counterRepresentationModelAssembler.toModel(counter);
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