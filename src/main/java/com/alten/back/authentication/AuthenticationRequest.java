package com.alten.back.authentication;

public record AuthenticationRequest (String email,
									 String password)
{
}