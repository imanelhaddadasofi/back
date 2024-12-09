package com.alten.back.authentication;

import com.alten.back.exception.DuplicateResourceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

    private final UserAccessService service;

    public CustomerUserDetailsService(UserAccessService userDao) {
         this.service = userDao;
    }

    @Override
    public ProfileUserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return service.loadUserByUsername(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User  avec émail" + email + " not found"));
    }

    public UserDTO addUser(UserDTO userDTO){
        // check if email exists
        String email = userDTO.email();
        if (service.existsCustomerWithEmail(email)) {
            throw new DuplicateResourceException(
                    "email already exists"
            );
        }
        return service.addUser(userDTO);
    }
}
