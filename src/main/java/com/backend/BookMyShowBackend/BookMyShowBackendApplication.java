package com.backend.BookMyShowBackend;

import com.backend.BookMyShowBackend.controllers.UserController;
import com.backend.BookMyShowBackend.dtos.LoginRequestDTO;
import com.backend.BookMyShowBackend.dtos.LoginResponseDTO;
import com.backend.BookMyShowBackend.dtos.SignupRequestDTO;
import com.backend.BookMyShowBackend.dtos.SignupResponseDTO;
import com.backend.BookMyShowBackend.models.BaseModel;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@OpenAPIDefinition
@EnableJpaAuditing
@SpringBootApplication
//public class BookMyShowBackendApplication implements CommandLineRunner {
public class BookMyShowBackendApplication {

	private final UserController userController;

    public BookMyShowBackendApplication(UserController userController) {
        this.userController = userController;
    }

    public static void main(String[] args) {
		SpringApplication.run(BookMyShowBackendApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
////		SignupRequestDTO signupRequestDTO = new SignupRequestDTO();
////		signupRequestDTO.setEmail("abhishek@gmail.com");
////		signupRequestDTO.setPassword("12345678");
////
////		SignupResponseDTO signupResponseDTO = userController.signup(signupRequestDTO);
////		System.out.println(signupResponseDTO);
//
////		Login func
////		LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
////		loginRequestDTO.setEmail("abhishek@gmail.com");
////		loginRequestDTO.setPassword("12345678");
////		LoginResponseDTO loginResponseDTO = userController.login(loginRequestDTO);
////		System.out.println(loginResponseDTO);
//	}
}
