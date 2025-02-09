package com.asr.sensors.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {

    @Value("${role.user.username}")
    private String roleUserUsername;
    @Value("${role.user.password}")
    private String roleUserPassword;
    @Value("${role.admin.username}")
    private String roleAdminUsername;
    @Value("${role.admin.password}")
    private String roleAdminPassword;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize)->{
                    authorize.requestMatchers("/sensor/**").permitAll();
                    authorize.requestMatchers("/sensortype/**").hasRole("ADMIN");
                    authorize.requestMatchers("/sensorunit/**").hasRole("ADMIN");
                    authorize.anyRequest().authenticated();
                }).httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        // This is a test task. So I will use hardcoded roles and users.
        UserDetails admin = User.builder()
                .username(roleAdminUsername)
                .password(passwordEncoder().encode(roleAdminPassword))
                .roles("ADMIN")
                .build();

        UserDetails user = User.builder()
                .username(roleUserUsername)
                .password(passwordEncoder().encode(roleUserPassword))
                .roles("VIEWER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

