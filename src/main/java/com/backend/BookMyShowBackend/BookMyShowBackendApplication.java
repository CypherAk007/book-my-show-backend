package com.backend.BookMyShowBackend;

import com.backend.BookMyShowBackend.controllers.UserController;
import com.backend.BookMyShowBackend.dtos.SignupRequestDTO;
import com.backend.BookMyShowBackend.dtos.SignupResponseDTO;
import com.backend.BookMyShowBackend.models.BaseModel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BookMyShowBackendApplication implements CommandLineRunner {

	private final UserController userController;

    public BookMyShowBackendApplication(UserController userController) {
        this.userController = userController;
    }

    public static void main(String[] args) {
		SpringApplication.run(BookMyShowBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		SignupRequestDTO signupRequestDTO = new SignupRequestDTO();
		signupRequestDTO.setEmail("abhishekkrishnatm@gmail.com");
		signupRequestDTO.setPassword("12345678");

		SignupResponseDTO signupResponseDTO = userController.signup(signupRequestDTO);
		System.out.println(signupResponseDTO);
	}
}
