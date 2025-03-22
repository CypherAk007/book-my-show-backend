package com.backend.BookMyShowBackend.controllers;

import com.backend.BookMyShowBackend.dtos.ResponseStatus;
import com.backend.BookMyShowBackend.dtos.SignupRequestDTO;
import com.backend.BookMyShowBackend.dtos.SignupResponseDTO;
import com.backend.BookMyShowBackend.models.User;
import com.backend.BookMyShowBackend.services.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    public SignupResponseDTO signup(SignupRequestDTO requestDTO){
        SignupResponseDTO signupResponseDTO = new SignupResponseDTO();
        try{
            User user = userService.signUp(requestDTO.getEmail(),requestDTO.getPassword());
            signupResponseDTO.setUserId(user.getId());
            signupResponseDTO.setStatus(ResponseStatus.SUCCESS);
            signupResponseDTO.setMessage("SignUp Successfull!!");
        }catch (Exception e){
            signupResponseDTO.setStatus(ResponseStatus.FAILURE);
            signupResponseDTO.setMessage(e.getMessage());
        }
        return signupResponseDTO;
    }
}
