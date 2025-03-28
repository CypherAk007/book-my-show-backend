package com.backend.BookMyShowBackend.controllers;

import com.backend.BookMyShowBackend.dtos.*;
import com.backend.BookMyShowBackend.models.User;
import com.backend.BookMyShowBackend.services.UserService;
import com.backend.BookMyShowBackend.services.authentication.AuthServiceImpl;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private final UserService userService;
    private final AuthServiceImpl authService;

    public UserController(UserService userService, AuthServiceImpl authService) {
        this.userService = userService;
        this.authService = authService;
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

    public LoginResponseDTO login(LoginRequestDTO requestDTO){
        LoginResponseDTO responseDTO = new LoginResponseDTO();
        try{
            User user = userService.login(requestDTO.getEmail(),requestDTO.getPassword());
            responseDTO.setUserId(user.getId());
            responseDTO.setStatus(ResponseStatus.SUCCESS);
            responseDTO.setMessage("SignUp Successful!!");
        }catch (Exception e){
            responseDTO.setStatus(ResponseStatus.FAILURE);
            responseDTO.setMessage(e.getMessage());
        }
        return responseDTO;
    }
}
