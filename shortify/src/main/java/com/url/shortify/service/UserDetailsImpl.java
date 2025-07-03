package com.url.shortify.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.url.shortify.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Service
public class UserDetailsImpl implements UserDetails{

  private static final long serialVersionUID = 1L;
  private Long id;
  private String username;
  private String email;
  private String password;
  private Collection<? extends GrantedAuthority > authorities;
  

  // Spring Security requires a UserDetails object for authentication and authorization. This method converts your app’s User entity into a UserDetailsImpl instance, which Spring can work with during login, session handling, and role-based access control.
  public static UserDetailsImpl build(User user){
    GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());

    return new UserDetailsImpl(
      user.getId(),
      user.getUsername(),
      user.getEmail(),
      user.getPassword(),
      Collections.singletonList(authority)
    );
  }
  
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }
  
}
