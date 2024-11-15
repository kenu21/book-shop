package com.keniu.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuration class for security-related beans in the application.
 * This class defines the beans required for password encoding.
 */
@Configuration
public class SecurityConfig {

    /**
     * Provides a {@link PasswordEncoder} bean that uses the BCrypt hashing algorithm.
     * BCrypt is a strong hashing function that includes a built-in salt to protect against
     * rainbow table attacks and allows configurable work factors to mitigate brute-force attacks.
     *
     * @return a {@link BCryptPasswordEncoder} instance
     */
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/public-endpoint").permitAll()  // Публичные эндпойнты
                .anyRequest().authenticated()  // Все остальные требуют аутентификации
            )
            .formLogin(withDefaults())  // Включение формы логина с настройками по умолчанию
            .logout(withDefaults());  // Включение logout с настройками по умолчанию
        return http.build();
    }
}
