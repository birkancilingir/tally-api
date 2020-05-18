package me.brkn.tallyapi.data.model.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class UserDao {

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

  //private RolesDao[] roles;
}
