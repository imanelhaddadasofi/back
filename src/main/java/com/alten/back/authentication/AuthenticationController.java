package com.alten.back.authentication;

import com.alten.back.security.JwtTokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("")
public class AuthenticationController {

	private final AuthenticationManager authenticationManager;
	private final JwtTokenService jwtTokenUtil;
	private final CustomerUserDetailsService userDetailsService;

	public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenService jwtTokenUtil, CustomerUserDetailsService userDetailsService) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenUtil = jwtTokenUtil;
		this.userDetailsService = userDetailsService;
	}

	@PostMapping("/token")
	public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest){

		AuthenticationResponse authenticationResponse=login( authenticationRequest );

		return ResponseEntity.ok(authenticationResponse);
	}


	
	@PostMapping("/account")
	public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO user) {
		userDetailsService.addUser(user);
		return ResponseEntity.ok(user);
	}

	public AuthenticationResponse login(AuthenticationRequest request) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.email(),
						request.password()
				)
		);
		ProfileUserDetails user = (ProfileUserDetails) authentication.getPrincipal();
		String token = jwtTokenUtil.generateToken(user);
		return new AuthenticationResponse(token, user.email, LocalDateTime.now().plusHours( 1 ) );
	}
	
}