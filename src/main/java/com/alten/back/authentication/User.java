package com.alten.back.authentication;

public record User(String username,
        String firstname,
        String email,
        String password) {
}
