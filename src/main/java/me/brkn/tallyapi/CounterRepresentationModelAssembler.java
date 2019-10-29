package me.brkn.tallyapi;

import me.brkn.tallyapi.controller.CounterRestController;
import me.brkn.tallyapi.model.Counter;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CounterRepresentationModelAssembler implements RepresentationModelAssembler<Counter, EntityModel<Counter>> {

    @Override
    public EntityModel<Counter> toModel(Counter counter) {
        return new EntityModel<>(counter,
                linkTo(methodOn(CounterRestController.class).readCounter(counter.getId())).withSelfRel(),
                linkTo(methodOn(CounterRestController.class).listCounters()).withRel("counters"));
    }
}