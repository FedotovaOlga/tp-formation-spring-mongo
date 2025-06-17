package com.example.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {
	@Autowired
	private UserDetailsService userDetailsService;
	

	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// lister les comptes utilisateurs autorisés (en dur)
//	@Bean
//	InMemoryUserDetailsManager inMemoryUserDetailsManager() {
//		return new InMemoryUserDetailsManager(
//				User.builder().username("folga").password("{noop}user").build());
//	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				(auth) -> auth
				.dispatcherTypeMatchers(DispatcherType.FORWARD)
				.permitAll()
				.requestMatchers("/login").permitAll()
				.requestMatchers("/admin/**").permitAll()
//				.requestMatchers("/showPersonnes").hasRole("ADMIN")
				.anyRequest().authenticated()
				)
				.formLogin(form -> form.permitAll())
				.logout((logout) -> logout
						.permitAll())
//				.formLogin((form) -> form
//						.loginPage("/login")
//						.defaultSuccessUrl("/home", true)
//						.permitAll())
				.csrf(c -> c.disable());

		return http.build();
	}

}
