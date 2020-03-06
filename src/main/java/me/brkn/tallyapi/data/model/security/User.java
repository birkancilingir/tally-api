package me.brkn.tallyapi.data.model.security;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String name;

    private String surname;

    @Column(unique = true)
    private String username;

    private String password;

    private String passwordSalt;

    private String emailAddress;

    private Roles[] roles;
}
