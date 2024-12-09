package com.alten.back.security;

import com.alten.back.authentication.ProfileUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    public boolean isNotAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ProfileUserDetails user = (ProfileUserDetails) authentication.getPrincipal();
        return !"admin@admin.com".equals(user.getEmail()); // Vérifie l'email de l'utilisateur connecté
    }
}
