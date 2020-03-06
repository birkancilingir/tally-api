package me.brkn.tallyapi.service.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SecurityUser {
    private String username;

    private String password;

    private String[] roles;
}
