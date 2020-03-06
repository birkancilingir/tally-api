package me.brkn.tallyapi.data.repository;

import me.brkn.tallyapi.data.model.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
