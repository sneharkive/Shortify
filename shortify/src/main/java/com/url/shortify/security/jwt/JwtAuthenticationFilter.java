package com.url.shortify.security.jwt;

import java.io.IOException;

import javax.swing.Spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  @Autowired
  private JwtUtils jwtTokenProvider;

  @Autowired
  private UserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
        
    try {
      String jwt = jwtTokenProvider.getJwtFromHeader(request);

      if (jwt != null && jwtTokenProvider.validateToken(jwt)) {
        String username = jwtTokenProvider.getUserNameFromJwtToken(jwt);  // get username
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);  // load user
        
        if (userDetails != null) {

// Creates a Spring Security authentication token from the user details.
// "null" is passed instead of a password because we’re authenticating via JWT, not login form.
          UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
              null, userDetails.getAuthorities());


// Adds additional request metadata (IP, session ID) to the authentication object.
          authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

// Saves the authentication token in Spring Security’s context → ✅ now the user is considered authenticated for this request.
          SecurityContextHolder.getContext().setAuthentication(authentication);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }


// Passes the request to the next filter/controller in the chain.
// Even if authentication fails, the request still continues (but access might be denied later if the endpoint requires authentication).

    filterChain.doFilter(request, response);
  }

}




/*
 * Filters incoming requests to check for a valid JWT in the header, setting the
 * authentication context if the token is valid.
 * 
 * Extracts JWT from request header, validates it and configures the Spring
 * Security context with user details if the token is valid.
 * 
 */