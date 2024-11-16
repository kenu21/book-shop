package com.keniu.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuration class for security-related beans in the application. This class defines the beans
 * required for password encoding.
 */
@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * Provides a {@link PasswordEncoder} bean that uses the BCrypt hashing algorithm. BCrypt is a
     * strong hashing function that includes a built-in salt to protect against rainbow table
     * attacks and allows configurable work factors to mitigate brute-force attacks.
     *
     * @return a {@link BCryptPasswordEncoder} instance
     */
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .cors(AbstractHttpConfigurer::disable)
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(
                auth -> auth
                    .requestMatchers("/auth/**", "/swagger-ui/**",
                        "/v3/api-docs/**", "/error")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
            )
            .httpBasic(withDefaults())
            .sessionManagement(
                session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .userDetailsService(userDetailsService)
            .build();
    }
}
