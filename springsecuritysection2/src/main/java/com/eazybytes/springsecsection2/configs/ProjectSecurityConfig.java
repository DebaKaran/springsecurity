package com.eazybytes.springsecsection1.configs;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
}
