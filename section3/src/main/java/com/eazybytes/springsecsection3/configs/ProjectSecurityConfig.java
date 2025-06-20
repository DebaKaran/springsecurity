package com.eazybytes.springsecsection3.configs;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        /**http.authorizeHttpRequests((requests) -> requests.anyRequest().permitAll());
        http.authorizeHttpRequests((requests) -> requests.anyRequest().denyAll());*/

        http.authorizeHttpRequests((requests) ->
                requests.requestMatchers("/myAccount", "/myBalance","/myCards","/myLoans")
                        .authenticated()
                        .requestMatchers("/contact", "/notices", "/error").permitAll());
        /** http.formLogin(flc -> flc.disable());
        http.httpBasic(hbc -> hbc.disable()); */

        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withUsername("user")
                        .password(passwordEncoder().encode("1234"))
                        .authorities("read")
                        .build();

        UserDetails admin =
                User.withUsername("admin")
                        .password(passwordEncoder().encode("1234"))
                        .authorities("admin")
                        .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}
