package com.jobportal.jobportal_api.config;

import com.jobportal.jobportal_api.security.CustomAccessDeniedHandler;
import com.jobportal.jobportal_api.security.CustomAuthenticationEntryPoint;
import com.jobportal.jobportal_api.security.JwtAuthenticationFilter;
import com.jobportal.jobportal_api.security.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;
    private final CustomAccessDeniedHandler accessDeniedHandler;
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;

    public SecurityConfig(JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
        this.accessDeniedHandler = new CustomAccessDeniedHandler();
        this.authenticationEntryPoint = new CustomAuthenticationEntryPoint();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/api/login", "/api/register").permitAll();
                    auth.requestMatchers("/api/admin/**").hasRole("ADMIN");
                    auth.requestMatchers("/api/employer/**").hasRole("EMPLOYER");
                    auth.requestMatchers("/api/jobseeker/**").hasRole("JOB_SEEKER");
                    auth.anyRequest().authenticated();
                })
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(authenticationEntryPoint) // NEW ENTRY POINT FOR INVALID JWT
                        .accessDeniedHandler(accessDeniedHandler) // HANDLER FOR FORBIDDEN REQUESTS (403)
                )
                .addFilterBefore(
                        new JwtAuthenticationFilter(jwtTokenProvider, userDetailsService), // Pass UserDetailsService
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    // ✅ Add this to fix the PasswordEncoder error
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ✅ Define AuthenticationProvider
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
}
