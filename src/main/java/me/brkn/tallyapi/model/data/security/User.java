package me.brkn.tallyapi.model.data.security;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String name;

    private String surname;

    private String username;

    private String password;

    private String passwordSalt;

    private String emailAddress;

    private Roles[] roles;
}
