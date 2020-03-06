package me.brkn.tallyapi.view.controller;

import me.brkn.tallyapi.service.security.UserService;
import me.brkn.tallyapi.service.security.model.SecurityUser;
import me.brkn.tallyapi.view.exception.UserNotFoundException;
import me.brkn.tallyapi.view.model.assembler.UserRepresentationModelAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

    private final UserService userService;

    private final UserRepresentationModelAssembler userRepresentationModelAssembler;

    @Autowired
    UserRestController(UserService userService, UserRepresentationModelAssembler userRepresentationModelAssembler) {
        this.userService = userService;
        this.userRepresentationModelAssembler = userRepresentationModelAssembler;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<EntityModel<SecurityUser>> readUser(@PathVariable("username") String username) {
        return userService.getUserByUsername(username)
                .map(counter -> ResponseEntity.ok().body(userRepresentationModelAssembler.toModel(counter)))
                .orElseThrow(() -> new UserNotFoundException(username));

    }
    /*
    @PostMapping("/users")
    public ResponseEntity<EntityModel<SecurityUser>> newCounter(@RequestBody SecurityUser newSecurityUser) throws URISyntaxException {
        EntityModel<SecurityUser> userEntityModel = userRepresentationModelAssembler.toModel(userRepository.save(newSecurityUser));

        URI location = linkTo(methodOn(UserRestController.class).readUser(userEntityModel.getContent().getUsername())).toUri();

        return ResponseEntity.created(location).body(userEntityModel);
    }
    */
}
