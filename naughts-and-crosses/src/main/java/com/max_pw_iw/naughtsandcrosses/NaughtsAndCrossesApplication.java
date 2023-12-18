package com.max_pw_iw.naughtsandcrosses;

// import java.time.LocalDate;

// import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.annotation.Bean;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// import com.max_pw_iw.naughtsandcrosses.entity.User;
import com.max_pw_iw.naughtsandcrosses.repository.UserRepository;

import lombok.AllArgsConstructor;

@SpringBootApplication @AllArgsConstructor
public class NaughtsAndCrossesApplication{

	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(NaughtsAndCrossesApplication.class, args);
	}

	// @Override
	// public void run(String... args) throws Exception {

	// 	BCryptPasswordEncoder tempBCryptPasswordEncoder = new BCryptPasswordEncoder();

	// 	User admin = new User("DEV_ADMIN", tempBCryptPasswordEncoder.encode("admin_pass"));
	// 	userRepository.save(admin);
	// }

	// @Bean
	// public BCryptPasswordEncoder bCryptPasswordEncoder() {
	// 	return new BCryptPasswordEncoder();
	// }
}
