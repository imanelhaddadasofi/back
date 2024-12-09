package com.alten.back.authentication;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserAccessService implements UserService {

    private static final List<ProfileUserDetails> users;

    static {
        users = new ArrayList<>();

        ProfileUserDetails alex = new ProfileUserDetails(
                "admin",
                "admin",
                "admin@admin.com",
                "password");
        users.add(alex);

        ProfileUserDetails jamila = new ProfileUserDetails(
                "emily",
                "emily",
                "emily@gmail.com",
                "password");
        users.add(jamila);
    }

    @Override
    public Optional<ProfileUserDetails> loadUserByUsername(String email) {
        return users.stream()
                .filter(c -> c.email.equals(email))
                .findFirst();
    }

    @Override
    public boolean existsCustomerWithEmail(String email) {
        return users.stream()
                .anyMatch(c -> c.email.equals(email));
    }

    @Override
    public UserDTO addUser(UserDTO user) {
        ProfileUserDetails u=new ProfileUserDetails( user.firstname(), user.username(), user.email(), user.password() );
        users.add( u );
        return user;
    }

}
