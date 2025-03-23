package com.backend.BookMyShowBackend.controllers;

import com.backend.BookMyShowBackend.dtos.ResponseStatus;
import com.backend.BookMyShowBackend.dtos.SignupRequestDTO;
import com.backend.BookMyShowBackend.dtos.SignupResponseDTO;
import com.backend.BookMyShowBackend.models.User;
import com.backend.BookMyShowBackend.services.authentication.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


public class AuthenticationController {

    private final AuthService authService;

    public AuthenticationController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/client/sign-up")
    public ResponseEntity<?>  signupClient(@RequestBody SignupRequestDTO requestDTO){
        if(authService.presentByEmail(requestDTO.getEmail())){
            return new ResponseEntity<>("Client with Email Already Exists!!", HttpStatus.NOT_ACCEPTABLE);
        }

        SignupResponseDTO signupResponseDTO = new SignupResponseDTO();
        try{
            User user = authService.signupClient(requestDTO.getName(),requestDTO.getLastname(),requestDTO.getPhone(),requestDTO.getEmail(),requestDTO.getPassword());
            signupResponseDTO.setUserId(user.getId());
            signupResponseDTO.setStatus(ResponseStatus.SUCCESS);
            signupResponseDTO.setMessage("User SignUp Successful!!");
        }catch (Exception e){
            signupResponseDTO.setStatus(ResponseStatus.FAILURE);
            signupResponseDTO.setMessage(e.getMessage());
        }
        return new ResponseEntity<>(signupResponseDTO,HttpStatus.OK);
    }

    @PostMapping("/company/sign-up")
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
