package com.abhishek.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.boot.autoconfigure.integration.IntegrationProperties.Management;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class AppConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
		//Making the session Stateless
		//By default the session is stateful
		http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		//Which endpont should be accessable
			.authorizeHttpRequests(Authorize ->Authorize
					.requestMatchers("/api/admin/**").hasAnyRole("RESTURENT_OWNER","ADMIN")
					//if anyone get the token the he can access
					.requestMatchers("/api/**").authenticated()
					.anyRequest().permitAll()
					//for checking provided jwt token is valid or not
					).addFilterBefore(new JwtTokenValidator(),BasicAuthenticationFilter.class)
					.csrf(csrf->csrf.disable())
					.cors(cors->cors.configurationSource(corsConfigurationSource()));
		return http.build();
	}
	
	private CorsConfigurationSource corsConfigurationSource() {
		return new CorsConfigurationSource() {

			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				// TODO Auto-generated method stub
				CorsConfiguration cfg=new CorsConfiguration();
				cfg.setAllowedOrigins(Arrays.asList(
						""
						));
			cfg.setAllowedMethods(Collections.singletonList("*"));
			cfg.setAllowCredentials(true);
			cfg.setAllowedHeaders(Collections.singletonList("*"));
			cfg.setExposedHeaders(Arrays.asList("Authorization"));
			cfg.setMaxAge(3600L);
				return cfg;
			}
			
		};
	}
	//Bcriprt the password and store it in database
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
