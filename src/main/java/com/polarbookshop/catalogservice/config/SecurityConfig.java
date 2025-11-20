package com.polarbookshop.catalogservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorize -> authorize
                        //Позволяет пользователям получать поздравления и книги без аутентификации, но токен должен быть обязательно
                        .requestMatchers(HttpMethod.GET, "/", "/books/**").permitAll()
                        //Любой другой запрос требует не только аутентификации, но и роли сотрудника (которая совпадает с полномочиями ROLE_employee).
                        .anyRequest().hasRole("employee"))
                .oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(Customizer.withDefaults()))
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        //Определяет преобразователь для сопоставления утверждений с объектами GrantedAuthority.
        var jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        //Применяет префикс «ROLE_» к каждой роли пользователя.
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
        //Извлекает список ролей из утверждения ролей
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("roles");

        //Определяет стратегию преобразования JWT. Мы только настроим, как создавать из него предоставленные полномочия.
        var jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }

}