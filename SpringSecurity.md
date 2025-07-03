# Spring Security Notes

## JwtUtils

- Contains utility methods for generating, parsing, and validating JWTs.

- Include generating a token from a username, validating a JWT and extracting the username from token.

---

## JwtAuthenticationFilter

- Filters incoming requests to check for a valid JWT in the header, setting the authentication context if the token is valid.

- Extracts JWT from request header, validates it and configures the Spring Security context with user details if the token is valid.

---

## JwtAuthenticationResponse

- DTO for JWT authentication response.

---

## SecurityConfig

- Configures Spring Security filters and rules for the application.

- Sets up the security filter chain, permitting or denying access based on paths and roles.

---

##

- **Spring Security provides the tools and structure to authenticate and authorize users.**

- **Spring Security has its own inbuilt implementation of User which would work, but in a lot of cases custom is needed.**

---

## UserDetailsServiceImpl

- This is a service that loads user details from the database.

- Bridges the gap between the database (User Entity) and Spring Security (UserDetails interface).

## UserDetailsImpl

- This is a custom implementation of Spring Security's UserDetails interface.

- Needed to represent the authenticated user in Spring Security.

# STEPS :

### 1. JwtAuthenticationFilter

- Intercepts HTTP requests and extracts JWT tokens.

### 2. JwtUtils

- Generates, validates token and provides helper methods.

### 3. UserDetailsServiceImpl

- Fetches user details from the database.

### 4. UserDetailsImpl

- Provides a Spring Security-compatible representation of the user for authentication and authorization.

# Steps By Me :

- Create UseDetailsImpl inside "service" folder . Make it implements UserDetails interface.

  - This will provide require User information to Spring Security for authorization and authentication.

  ```java

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
  ```

- Create "security" folder then create "JwtUtils.java" inside it. Write all the necessary things.

- Create a "JwtAuthenticationResponse.java" inside "security" folder.

- Create a "JwtAuthenticationFilter.java" inside "security" folder.

  - extends OncePerRequestFilter => write "doFilterInternal" methods

    ```java
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

      try {
        // Get JWT From Header
        // Validate Token
        // If Valid => Get User Details
            // -- get user name -> load user -> set the auth context
      } catch (Exception e) {
        e.printStackTrace();
      }

    // Passes the request to the next filter/controller in the chain.
    // Even if authentication fails, the request still continues (but access might be denied later if the endpoint requires authentication).
      filterChain.doFilter(request, response);

    }

    ```

  - Here this load user (loadUserByUsername()) method can be custom or default . We will make our custom loadUserByUsername() method for this we will need to implements UserDetailsService interface.

- Create UserDetailsServiceImpl.java inside service folder. Make a Custom loadUserByUsername() method.

---

# Security Configuration

**Create WebSecurityConfig.java**

- ## Custom Security Configuration for our application :

  - Define the Security Rules for handling HTTP requests.

  ```java
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            .requestMatchers("/api/auth/**").permitAll()
            .requestMatchers("/api/urls/**").authenticated()
            .requestMatchers("/{shortUrl}").permitAll()
            .anyRequest().authenticated());

      // Adds your custom DaoAuthenticationProvider (typically wired with your UserDetailsService + password encoder).  
      http.authenticationProvider(authenticationProvider());

      // Registers your JwtAuthenticationFilter.
      // Tells Spring Security to run this before the default login form filter.
      // Ensures requests with JWTs are authenticated before anything else.
      http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

      // Finalizes and builds the SecurityFilterChain bean.
      return http.build();
  }
```


       ┌────────────────────────────┐
       │     Incoming Request       │
       └────────────┬───────────────┘
                    │
                    ▼
      ┌──────────────────────────────┐
      │ JwtAuthenticationFilter      │ ◄─── Custom filter (extracts and validates JWT)
      └──────────────────────────────┘
                    │
                    ▼
      ┌──────────────────────────────┐
      │ AuthenticationProvider       │ ◄─── Verifies user details
      └──────────────────────────────┘
                    │
                    ▼
      ┌──────────────────────────────┐
      │ Authorization Rules          │
      └──────────────────────────────┘
                    │
        ┌───────────┴────────────┐
        ▼                        ▼
 [Permit All]             [Authenticated]
(No login required)    (JWT must be valid,
                       User loaded from DB)
        │                        │
        ▼                        ▼
 ┌────────────┐          ┌───────────────┐
 │ Controller │          │ Controller    │
 └────────────┘          └───────────────┘

```

- Configure JwtAuthenticationFilter in filter Chain

- Spring Security recognizes it as a filter that will only be execute once per request.

- By Default, Spring Security doesn't automatically include your custom filter (JwtAuthenticationFilter) in the filter chain unless you explicitly add it.

- ## Configure DaoAuthenticationProvider

Set up how authentication is handled by Spring Security.

```java
// The method defines a custom DaoAuthenticationProvider, which is the component responsible for verifying user credentials (username + password) during authentication in Spring Security.
  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }

```



- ## Bean for AuthenticationManager and PasswordEncoder.

```java
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
      throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }
```
