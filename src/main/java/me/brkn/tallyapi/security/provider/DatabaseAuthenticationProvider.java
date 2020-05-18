package me.brkn.tallyapi.security.provider;

import java.util.ArrayList;
import java.util.Optional;
import me.brkn.tallyapi.service.security.UserService;
import me.brkn.tallyapi.service.security.model.SecurityUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class DatabaseAuthenticationProvider implements AuthenticationProvider {

  private static Logger logger = LogManager.getLogger(DatabaseAuthenticationProvider.class);

  private final UserService userService;

  @Autowired
  public DatabaseAuthenticationProvider(UserService userService) {
    this.userService = userService;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String name = authentication.getName();
    String password = authentication.getCredentials().toString();

    logger.info("Authenticating '{}' with password '{}'", name, password);

    Optional<SecurityUser> securityUser = userService.getUserByUsername(name);
    UserDetails userDetails = securityUser.map(user -> {
      User.UserBuilder builder = User.withUsername(user.getUsername());
      builder.password(user.getPassword());
      builder.roles(user.getRoles());
      return builder.build();
    }).orElseThrow(() -> new UsernameNotFoundException("User not found"));

    // TODO: Compare against hashed passwords here
    if (userDetails.getPassword().equals(password)) {
      return new UsernamePasswordAuthenticationToken(name, password,
          new ArrayList<>(userDetails.getAuthorities()));
    } else {
      throw new BadCredentialsException("Authentication failed");
    }
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}
