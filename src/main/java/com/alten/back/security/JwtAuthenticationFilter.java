package com.alten.back.security;

import com.alten.back.authentication.CustomerUserDetailsService;
import com.alten.back.authentication.ProfileUserDetails;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final CustomerUserDetailsService userDetailsService;

	private final JWTService jwtTokenUtil;


	public JwtAuthenticationFilter(CustomerUserDetailsService userDetailsService, JWTService jwtTokenUtil) {
		this.userDetailsService = userDetailsService;
		this.jwtTokenUtil = jwtTokenUtil;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {String authHeader = request.getHeader("Authorization");

		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			chain.doFilter(request, response);
			return;
		}

		String jwt = authHeader.substring(7);
		String subject = jwtTokenUtil.getSubject(jwt);

		if (subject != null &&
				SecurityContextHolder.getContext().getAuthentication() == null) {
			ProfileUserDetails userDetails = userDetailsService.loadUserByUsername(subject);
			if (jwtTokenUtil.isTokenValid(jwt, userDetails.getEmail())) {
				UsernamePasswordAuthenticationToken authenticationToken =
						new UsernamePasswordAuthenticationToken(
								userDetails, null, userDetails.getAuthorities()
						);
				authenticationToken.setDetails(
						new WebAuthenticationDetailsSource().buildDetails(request)
				);
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}
		chain.doFilter(request, response);
	}

}