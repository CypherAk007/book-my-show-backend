package com.backend.BookMyShowBackend.services;

import com.backend.BookMyShowBackend.models.User;
import com.backend.BookMyShowBackend.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User signUp(String email, String password) {
        Optional<User> existingUser = userRepository.findByEmail(email);

        if(existingUser.isPresent()){
            throw new RuntimeException("User With this Email Already Present");
        }

        User user = new User();
        user.setEmail(email);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(()->new RuntimeException("No user Found!!"));
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if(passwordEncoder.matches(password,user.getPassword())){
            return user;
        }
        throw new RuntimeException("Invalid Credentials!!");
    }
//    1. check if user already exist via email -> if yes throw err
//    2. Create new user
//    3. save the new user details
//    4. return the user
}
