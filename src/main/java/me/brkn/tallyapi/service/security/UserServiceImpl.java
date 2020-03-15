package me.brkn.tallyapi.service.security;


import me.brkn.tallyapi.data.model.security.UserDao;
import me.brkn.tallyapi.data.repository.UserRepository;
import me.brkn.tallyapi.service.security.model.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<SecurityUser> getUserByUsername(String username) {
        UserDao user = userRepository.findByUsername(username);
        if (user == null) {
            return Optional.empty();
        }

        String[] roles = (String[]) Arrays.stream(user.getRoles()).map(r -> r.getName()).collect(Collectors.toList()).toArray();
        return Optional.of(new SecurityUser(user.getUsername(), user.getPassword(), roles));
    }

}
