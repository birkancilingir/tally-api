package me.brkn.tallyapi;

import me.brkn.tallyapi.controller.UserRestController;
import me.brkn.tallyapi.model.security.User;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserRepresentationModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {

    @Override
    public EntityModel<User> toModel(User user) {
        EntityModel<User> userEntityModel = new EntityModel<>(user,
                linkTo(methodOn(UserRestController.class).readUser(user.getUsername())).withSelfRel());

        return userEntityModel;
    }
}