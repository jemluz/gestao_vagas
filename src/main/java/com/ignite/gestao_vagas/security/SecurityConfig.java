package com.ignite.gestao_vagas.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  // HttpSecurity class is used to setup security rules for many different URLs and request partterns.

  // overwrite security filter config
  @Bean
  SecurityFilterChain securityFilterChain(
    HttpSecurity http
  ) throws Exception {

    // about csrf.disable() :
    // is interesting to  use while development proccess

    // setup public and authed routes manually
    http.csrf(csrf -> csrf.disable())
      .authorizeHttpRequests(
        auth -> {

          auth.requestMatchers("/candidate/").permitAll()
            .requestMatchers("/company/").permitAll();

          auth.anyRequest().authenticated();

        }
      );

    return http.build();

  }

  // overwrite the password encode strategy
  // will be injected in other pieces of app to encode passwords
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
