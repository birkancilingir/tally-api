package me.brkn.tallyapi.view.model.assembler;

import me.brkn.tallyapi.service.security.model.SecurityUser;
import me.brkn.tallyapi.view.controller.UserRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserRepresentationModelAssembler implements RepresentationModelAssembler<SecurityUser, EntityModel<SecurityUser>> {

    @Override
    public EntityModel<SecurityUser> toModel(SecurityUser securityUser) {
        EntityModel<SecurityUser> userEntityModel = new EntityModel<>(securityUser,
                linkTo(methodOn(UserRestController.class).readUser(securityUser.getUsername())).withSelfRel());

        return userEntityModel;
    }
}