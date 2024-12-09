package com.alten.back.authentication;

import org.springframework.stereotype.Service;

import java.util.Optional;

public interface UserService {
    Optional<ProfileUserDetails> loadUserByUsername(String email);
    boolean existsCustomerWithEmail(String email);
    UserDTO addUser(UserDTO user);
}
