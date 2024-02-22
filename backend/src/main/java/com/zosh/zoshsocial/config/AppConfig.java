package com.zosh.zoshsocial.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
//            .sessionManagement(
//                    management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            )
            .authorizeHttpRequests(auth ->
                auth
                        .requestMatchers("/api/v1/**").authenticated()
                        .anyRequest().permitAll()
            )
            .httpBasic(Customizer.withDefaults())
            .csrf(csrf -> csrf.disable())
                ;

        return http.build();
    }
}
