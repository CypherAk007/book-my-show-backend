package com.backend.BookMyShowBackend.controllers;

import com.backend.BookMyShowBackend.dtos.ResponseStatus;
import com.backend.BookMyShowBackend.dtos.SignupRequestDTO;
import com.backend.BookMyShowBackend.dtos.SignupResponseDTO;
import com.backend.BookMyShowBackend.models.User;
import com.backend.BookMyShowBackend.services.authentication.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")  // Base path
@Tag(name = "Authentication", description = "APIs for user authentication")
public class AuthenticationController {

    private final AuthService authService;

    public AuthenticationController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/client/sign-up")
    @Operation(summary = "Client Sign-Up", description = "Registers a new client")
    public ResponseEntity<SignupResponseDTO>  signupClient(@RequestBody SignupRequestDTO requestDTO){
        SignupResponseDTO signupResponseDTO = new SignupResponseDTO();
        if(authService.presentByEmail(requestDTO.getEmail())){
//            return new ResponseEntity<>("Client with Email Already Exists!!", HttpStatus.NOT_ACCEPTABLE);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(signupResponseDTO);
        }

        try{
            User user = authService.signupClient(requestDTO.getName(),requestDTO.getLastname(),requestDTO.getPhone(),requestDTO.getEmail(),requestDTO.getPassword());
            signupResponseDTO.setUserId(user.getId());
            signupResponseDTO.setStatus(ResponseStatus.SUCCESS);
            signupResponseDTO.setMessage("User SignUp Successful!!");
        }catch (Exception e){
            signupResponseDTO.setStatus(ResponseStatus.FAILURE);
            signupResponseDTO.setMessage(e.getMessage());
        }
//        return new ResponseEntity<>(signupResponseDTO,HttpStatus.OK);
        return ResponseEntity.ok(signupResponseDTO);
    }

    @PostMapping("/company/sign-up")
    @Operation(summary = "Company Sign-Up", description = "Registers a new company")
    public ResponseEntity<?>  signupCompany(@RequestBody SignupRequestDTO requestDTO){
        if(authService.presentByEmail(requestDTO.getEmail())){
            return new ResponseEntity<>("Company with Email Already Exists!!", HttpStatus.NOT_ACCEPTABLE);
        }

        SignupResponseDTO signupResponseDTO = new SignupResponseDTO();
        try{
            User user = authService.signupCompany(requestDTO.getName(),requestDTO.getPhone(),requestDTO.getEmail(),requestDTO.getPassword());
            signupResponseDTO.setUserId(user.getId());
            signupResponseDTO.setStatus(ResponseStatus.SUCCESS);
            signupResponseDTO.setMessage("Company SignUp Successful!!");
        }catch (Exception e){
            signupResponseDTO.setStatus(ResponseStatus.FAILURE);
            signupResponseDTO.setMessage(e.getMessage());
        }
        return new ResponseEntity<>(signupResponseDTO,HttpStatus.OK);
    }
}
