package me.brkn.tallyapi.data.repository;

import me.brkn.tallyapi.data.model.security.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDao, Long> {

  UserDao findByUsername(String username);

}
