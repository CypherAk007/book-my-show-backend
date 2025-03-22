package com.backend.BookMyShowBackend.services;

import com.backend.BookMyShowBackend.models.User;
import com.backend.BookMyShowBackend.repositories.UserRepository;
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
        user.setPassword(password);
        return userRepository.save(user);
    }
//    1. check if user already exist via email -> if yes throw err
//    2. Create new user
//    3. save the new user details
//    4. return the user
}
