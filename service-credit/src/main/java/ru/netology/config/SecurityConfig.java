package ru.netology.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

// указыавает что класс конфигурационный

@Configuration
// Spring Boot автоматически включит нужные настройки
@EnableAutoConfiguration
public class SecurityConfig {

    @Bean
    // типовой конфигурационный метод для настройки фильтроа безопасности у входящих HTTP-запросов
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // отключаем CSRF — иначе Postman может блокироваться
                .cors(cors -> cors.disable()) // отключаем CORS (не нужен при тестах через Postman)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/api/public/**").permitAll() // разрешаем свободный доступ к /api/public/**
                        .anyRequest().authenticated() // все остальные — только для авторизованных
                )
                .httpBasic(Customizer.withDefaults()); // включаем авторизацию по логину/паролю (Basic Auth, удобно в Postman)

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")              // ← логин
                        .password("password")          // ← пароль
                        .roles("USER")                 // ← роль (можно использовать в @PreAuthorize и т.д.)
                        .build();

        return new InMemoryUserDetailsManager(user); // хранение в памяти (не в БД)
    }
}
