package me.brkn.tallyapi.service.security;

import me.brkn.tallyapi.service.security.model.SecurityUser;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public interface UserService {
    Optional<SecurityUser> getUserByUsername(String username) throws UsernameNotFoundException;
}
