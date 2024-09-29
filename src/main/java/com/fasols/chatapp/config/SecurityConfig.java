package com.fasols.chatapp.config;

import com.fasols.chatapp.exceptions.handlers.CustomAccessDeniedHandler;
import com.fasols.chatapp.exceptions.handlers.CustomAuthenticationEntryPoint;
import com.fasols.chatapp.security.JwtAuthenticationFilter;
import com.fasols.chatapp.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

	@Autowired
	private CustomAccessDeniedHandler customAccessDeniedHandler;


	private CustomUserDetailsService userDetailsService;

	@Autowired
	public SecurityConfig(CustomUserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

    @Bean
//	@Order(1)
	public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authManager, PasswordEncoder passwordEncoder) throws Exception {

		http
				 .csrf(AbstractHttpConfigurer::disable)
				 .authorizeHttpRequests((authorize) -> authorize
						 .requestMatchers(HttpMethod.POST,"/auth/**").permitAll()
						 .anyRequest().authenticated()
				)
				.addFilterBefore(new JwtAuthenticationFilter(userDetailsService, passwordEncoder, "/auth/**"),
						UsernamePasswordAuthenticationFilter.class);
         return http.build();
    }
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
    
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
