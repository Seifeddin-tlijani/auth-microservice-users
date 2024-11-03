package com.abidi.users_microservices.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;


@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Autowired
    AuthenticationManager authMgr;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws
            Exception {
        http.sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf( csrf -> csrf.disable())

                .cors(cors -> cors.configurationSource(new CorsConfigurationSource()

                {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest
                                                                          request) {
                        CorsConfiguration cors = new CorsConfiguration();
                        cors.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                        cors.setAllowedMethods(Collections.singletonList("*"));
                        cors.setAllowedHeaders(Collections.singletonList("*"));
                        cors.setExposedHeaders(Collections.singletonList("Authorization"));
                        cors.setMaxAge(3600L);

                        return cors;
                    }
                }))
                .authorizeHttpRequests(requests->
                        requests


                .requestMatchers("/api/all/**").hasAnyAuthority("ADMIN","USER")
                .requestMatchers(HttpMethod.GET,"/api/getbyid/**").hasAnyAuthority("ADMIN","USER")
                .requestMatchers(HttpMethod.POST,"/api/addstage/**").hasAnyAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT,"/api/updatestage/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/api/delstage/**").hasAuthority("ADMIN")
                .anyRequest().authenticated() )

                .addFilterBefore(new JWTAuthenticationFilter (authMgr),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new
                        JWTAuthorizationFilter(),UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


}
