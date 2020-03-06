package me.brkn.tallyapi.view.model.assembler;

import me.brkn.tallyapi.data.model.Counter;
import me.brkn.tallyapi.view.controller.CounterRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CounterRepresentationModelAssembler implements RepresentationModelAssembler<Counter, EntityModel<Counter>> {

    @Override
    public EntityModel<Counter> toModel(Counter counter) {
        EntityModel<Counter> counterEntityModel = new EntityModel<>(counter,
                linkTo(methodOn(CounterRestController.class).readCounter(counter.getId())).withSelfRel(),
                linkTo(methodOn(CounterRestController.class).listCounters()).withRel("counters"),
                linkTo(methodOn(CounterRestController.class).incrementCounter(counter.getId(), null)).withRel("increment"));

        if (counter.getValue() > 0) {
            counterEntityModel.add(linkTo(methodOn(CounterRestController.class).decrementCounter(counter.getId(), null)).withRel("decrement"));
        }

        return counterEntityModel;
    }
}