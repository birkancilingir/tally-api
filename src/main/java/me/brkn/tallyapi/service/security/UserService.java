package me.brkn.tallyapi.service.security;

import java.util.Optional;
import me.brkn.tallyapi.service.security.model.SecurityUser;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {

  Optional<SecurityUser> getUserByUsername(String username) throws UsernameNotFoundException;

}
