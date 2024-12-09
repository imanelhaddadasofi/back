package com.alten.back.authentication;

import java.io.Serializable;
import java.time.LocalDateTime;


public class AuthenticationResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String token;
	private String login;	
	private LocalDateTime expiresIn;
	
	public AuthenticationResponse(String token, String login, LocalDateTime expiresIn) {
		super();
		this.token = token;
		this.login = login;
		this.expiresIn = expiresIn;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public LocalDateTime getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(LocalDateTime expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getToken() {
		return token;
	}

	
}