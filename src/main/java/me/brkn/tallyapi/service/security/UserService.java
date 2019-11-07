package me.brkn.tallyapi.service.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {
    UserDetails getUserByUsername(String username) throws UsernameNotFoundException;
}
