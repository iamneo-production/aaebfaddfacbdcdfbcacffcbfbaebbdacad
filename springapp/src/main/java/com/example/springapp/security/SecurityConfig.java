package com.example.springapp.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

   @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
       http
               .authorizeRequests(authorize -> {
                   authorize
                        //    .antMatchers("/hello").authenticated()
                           .anyRequest().permitAll();
               })
               .httpBasic(withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails user = User.withUsername("root")
            .password(passwordEncoder().encode("examly"))
            .roles("USER")
            .build();

        return new InMemoryUserDetailsManager(user);
    }
}

   
//     @Bean
//     SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//         http
//                 .authorizeRequests(authorize -> authorize
//                         .antMatchers("/hello").authenticated()
//                         .anyRequest().permitAll())
//                 .httpBasic(withDefaults());
//         return http.build();
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     @Bean
//     public InMemoryUserDetailsManager userDetailsManager() {
//         UserDetails user = User.withUsername("root")
//             .password(passwordEncoder().encode("examly")) // Encode the password
//             .roles("USER")
//             .build();
//         return new InMemoryUserDetailsManager(user);
//     }
// }
