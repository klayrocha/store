package com.klayrocha.store;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.klayrocha.store.model.User;
import com.klayrocha.store.service.UserService;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserService userService, BCryptPasswordEncoder passwordEncoder) {
		return args -> {
			initUsers(userService, passwordEncoder);
		};
	}

	private void initUsers(UserService userService, BCryptPasswordEncoder passwordEncoder) {
		User user = new User();
		user.setFullName("Francis Klay Rocha");;
		user.setUsername("klayrocha");;
		user.setPassword("123456");;
		if(userService.loadUserByUsername(user.getUsername()) == null) {
			userService.create(user);
		}
	}
}
