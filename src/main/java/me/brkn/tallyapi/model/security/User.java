package me.brkn.tallyapi.model.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private String username;

    private String password;

    private String[] roles;
}
