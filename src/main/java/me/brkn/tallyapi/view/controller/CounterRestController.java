package me.brkn.tallyapi.view.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import me.brkn.tallyapi.service.counter.CounterService;
import me.brkn.tallyapi.service.counter.model.Counter;
import me.brkn.tallyapi.view.exception.CounterNotFoundException;
import me.brkn.tallyapi.view.model.assembler.CounterRepresentationModelAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.mediatype.vnderrors.VndErrors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CounterRestController {

  private final CounterService counterService;

  private final CounterRepresentationModelAssembler counterRepresentationModelAssembler;

  @Autowired
  CounterRestController(CounterService counterService,
      CounterRepresentationModelAssembler counterRepresentationModelAssembler) {
    this.counterService = counterService;
    this.counterRepresentationModelAssembler = counterRepresentationModelAssembler;
  }

  @GetMapping("/counters")
  public ResponseEntity<CollectionModel<EntityModel<Counter>>> listCounters() {
    List<EntityModel<Counter>> counters = counterService.getCounterList().stream()
        .map(counterRepresentationModelAssembler::toModel).collect(Collectors.toList());

    return ResponseEntity.ok().body(new CollectionModel<>(counters,
        linkTo(methodOn(CounterRestController.class).listCounters()).withSelfRel()));
  }

  @PostMapping("/counters")
  public ResponseEntity<EntityModel<Counter>> newCounter(@RequestBody Counter newCounter)
      throws URISyntaxException {
    EntityModel<Counter> counterEntityModel = counterRepresentationModelAssembler
        .toModel(counterService.saveCounter(newCounter));

    URI location = linkTo(
        methodOn(CounterRestController.class).readCounter(counterEntityModel.getContent().getId()))
        .toUri();

    return ResponseEntity.created(location).body(counterEntityModel);
  }

  @GetMapping("/counters/{id}")
  public ResponseEntity<EntityModel<Counter>> readCounter(@PathVariable("id") Long id) {
    return counterService.getCounter(id)
        .map(counter -> ResponseEntity.ok()
            .body(counterRepresentationModelAssembler.toModel(counter)))
        .orElseThrow(() -> new CounterNotFoundException(id));
  }

  @PutMapping("/counters/{id}")
  public ResponseEntity<EntityModel<Counter>> updateCounter(@RequestBody Counter newCounter,
      @PathVariable Long id) {
    boolean isCreated = !counterService.counterExists(id);

    Counter updatedCounter = counterService.getCounter(id)
        .map(counter -> {
          counter.setName(newCounter.getName());
          counter.setValue(newCounter.getValue());
          return counterService.saveCounter(counter);
        })
        .orElseGet(() -> {
          newCounter.setId(id);
          return counterService.saveCounter(newCounter);
        });

    EntityModel<Counter> counterEntityModel = counterRepresentationModelAssembler
        .toModel(updatedCounter);

    URI location = linkTo(methodOn(CounterRestController.class).readCounter(updatedCounter.getId()))
        .toUri();

    if (!isCreated) {
      HttpHeaders headers = new HttpHeaders();
      headers.add(HttpHeaders.LOCATION, location.toString());

      return ResponseEntity.ok().headers(headers).body(counterEntityModel);
    } else {
      return ResponseEntity.created(location).body(counterEntityModel);
    }
  }

  @DeleteMapping("/counters/{id}")
  public ResponseEntity<?> deleteCounter(@PathVariable("id") Long id) {
    return counterService.getCounter(id).map(counter -> {
      counterService.deleteCounter(counter.getId());
      return ResponseEntity.noContent().build();
    }).orElseThrow(() -> new CounterNotFoundException(id));
  }

  @PutMapping("/counters/{id}/increment")
  public ResponseEntity<EntityModel<Counter>> incrementCounter(@PathVariable Long id,
      @RequestParam Integer value) {
    Counter updatedCounter = counterService.getCounter(id)
        .map(counter -> {
          counter.setValue(counter.getValue() + value);
          return counterService.saveCounter(counter);
        })
        .orElseThrow(() -> new CounterNotFoundException(id));

    EntityModel<Counter> counterEntityModel = counterRepresentationModelAssembler
        .toModel(updatedCounter);

    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.LOCATION,
        linkTo(methodOn(CounterRestController.class).readCounter(updatedCounter.getId()))
            .toString());

    return ResponseEntity.ok().headers(headers).body(counterEntityModel);
  }

  @PutMapping("/counters/{id}/decrement")
  public ResponseEntity<?> decrementCounter(@PathVariable Long id, @RequestParam Integer value) {
    Counter counter = counterService.getCounter(id)
        .orElseThrow(() -> new CounterNotFoundException(id));

    if (counter.getValue() == 0) {
      return ResponseEntity
          .status(HttpStatus.METHOD_NOT_ALLOWED)
          .body(new VndErrors.VndError("Method not allowed",
              "You can't decrement a counter with a value of zero."));
    }

    counter.setValue(counter.getValue() - value);
    counterService.saveCounter(counter);

    EntityModel<Counter> counterEntityModel = counterRepresentationModelAssembler.toModel(counter);

    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.LOCATION,
        linkTo(methodOn(CounterRestController.class).readCounter(counter.getId())).toString());

    return ResponseEntity.ok().headers(headers).body(counterEntityModel);
  }
}