package com.backend.BookMyShowBackend.controllers;

import com.backend.BookMyShowBackend.dtos.AuthenticationRequest;
import com.backend.BookMyShowBackend.dtos.ResponseStatus;
import com.backend.BookMyShowBackend.dtos.SignupRequestDTO;
import com.backend.BookMyShowBackend.dtos.SignupResponseDTO;
import com.backend.BookMyShowBackend.models.User;
import com.backend.BookMyShowBackend.repositories.UserRepository;
import com.backend.BookMyShowBackend.services.authentication.AuthService;
import com.backend.BookMyShowBackend.services.jwt.UserDetailsServiceImpl;
import com.backend.BookMyShowBackend.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")  // Base path
@Tag(name = "Authentication", description = "APIs for user authentication")
public class AuthenticationController {
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    private final AuthService authService;

    private final AuthenticationManager authenticationManager;

    private final UserDetailsServiceImpl userDetailsService;

    private final JwtUtil jwtUtil;

    private final UserRepository userRepository;

    public AuthenticationController(AuthService authService, AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService, JwtUtil jwtUtil, UserRepository userRepository) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
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

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) {
        System.out.println("Received login request: " + authenticationRequest); // Debugging log


        try {
            // ✅ Validate Input
            if (authenticationRequest.getUsername() == null || authenticationRequest.getPassword() == null) {
                return ResponseEntity.badRequest().body("Username or password cannot be empty");
            }

            // ✅ Authenticate User
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()));

            // ✅ Load User Details
            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            final String jwt = jwtUtil.generateToken(userDetails.getUsername());

            // ✅ Fetch User Information
            User user = userRepository.findFirstByEmail(authenticationRequest.getUsername());
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
            }

            // ✅ Add Headers
            response.addHeader("Authorization", "Bearer " + jwt);
            response.addHeader("Access-Control-Expose-Headers", "Authorization");

            // ✅ Return Response with Token
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("userId", user.getId());
            responseBody.put("role", user.getRole());
            responseBody.put("token", jwt);

            return ResponseEntity.ok(responseBody);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect username or password");
        }
    }

}
