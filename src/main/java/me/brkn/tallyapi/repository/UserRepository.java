package me.brkn.tallyapi.repository;

import me.brkn.tallyapi.model.data.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
