package com.propsur.api.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {

//    @Bean
//    public UserDetailsService userDetailsService(){
//         UserDetails user= User.builder().username("pavan").password(passwordEncoder().encode("pavan")).roles("admin").build();
//         UserDetails user1= User.builder().username("aryan").password(passwordEncoder().encode("aryan")).roles("employee").build();
//        return new InMemoryUserDetailsManager(user,user1);
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
     public AuthenticationManager  authenticationManager(AuthenticationConfiguration builder) throws Exception {
    	return builder.getAuthenticationManager();
    }
    
}
