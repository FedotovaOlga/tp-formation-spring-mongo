package com.example.demo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.entities.Formation;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repositories.FormationRepository;
import com.example.demo.repositories.UserRepository;

import lombok.AllArgsConstructor;

@SpringBootApplication
@AllArgsConstructor
public class SpringBootTpFormationApplication implements ApplicationRunner{
	private FormationRepository formationRepository;
	private UserRepository userRepository;
	private PasswordEncoder encoder;
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootTpFormationApplication.class, args);
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		formationRepository.saveAll(List.of(
				new Formation("Java", LocalDate.parse("2025-10-01"), 25, "Paris"),
				new Formation("PHP", LocalDate.parse("2024-11-11"), 5, "Lyon"),
				new Formation("Java et Angular", LocalDate.parse("2025-09-01"), 40, "Lyon"),
				new Formation("Vue.js", LocalDate.parse("2026-08-03"), 10, "Bordeaux")
				));
		userRepository.saveAll(
				List.of(
						new User("user", encoder.encode("user"), "Tom", "Wick", List.of(new Role("USER"))),
						new User("olga", encoder.encode("olga"), "Fedotova", "Olga", List.of(new Role("USER")))
					)
				);
	}

}
