package me.brkn.tallyapi.controller;

import me.brkn.tallyapi.UserRepresentationModelAssembler;
import me.brkn.tallyapi.exception.UserNotFoundException;
import me.brkn.tallyapi.model.security.User;
import me.brkn.tallyapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserRestController {

    private final UserRepository userRepository;

    private final UserRepresentationModelAssembler userRepresentationModelAssembler;

    @Autowired
    UserRestController(UserRepository userRepository, UserRepresentationModelAssembler userRepresentationModelAssembler) {
        this.userRepository = userRepository;
        this.userRepresentationModelAssembler = userRepresentationModelAssembler;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<EntityModel<User>> readUser(@PathVariable("username") String username) {
        return userRepository.findById(username)
                .map(counter -> ResponseEntity.ok().body(userRepresentationModelAssembler.toModel(counter)))
                .orElseThrow(() -> new UserNotFoundException(username));
    }

    @PostMapping("/users")
    public ResponseEntity<EntityModel<User>> newCounter(@RequestBody User newUser) throws URISyntaxException {
        EntityModel<User> userEntityModel = userRepresentationModelAssembler.toModel(userRepository.save(newUser));

        URI location = linkTo(methodOn(UserRestController.class).readUser(userEntityModel.getContent().getUsername())).toUri();

        return ResponseEntity.created(location).body(userEntityModel);
    }
}
