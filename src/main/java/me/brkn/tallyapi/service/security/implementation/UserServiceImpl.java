package me.brkn.tallyapi.service.security.implementation;

import me.brkn.tallyapi.model.security.User;
import me.brkn.tallyapi.service.security.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Override
    public UserDetails getUserByUsername(String username) throws UsernameNotFoundException {

        // TODO: Retrieve user from user database
        User user = findUserbyUsername(username);

        org.springframework.security.core.userdetails.User.UserBuilder builder = null;
        if (user != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.password(user.getPassword());
            builder.roles(user.getRoles());
        } else {
            throw new UsernameNotFoundException("User not found");
        }

        return builder.build();
    }

    private User findUserbyUsername(String username) {
        if(username.equalsIgnoreCase("admin")) {
            return new User(username, "adminpass", new String[]{"ADMIN", "USER"});
        } else if (username.equalsIgnoreCase("user")) {
            return new User(username, "userpass", new String[]{"USER"});
        }
        return null;
    }
}
