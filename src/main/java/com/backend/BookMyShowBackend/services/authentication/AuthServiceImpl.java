package com.backend.BookMyShowBackend.services.authentication;

import com.backend.BookMyShowBackend.models.User;
import com.backend.BookMyShowBackend.models.UserRole;
import com.backend.BookMyShowBackend.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User signupClient(String name,String lastname,String phone,String email, String password) {
        Optional<User> existingUser = userRepository.findByEmail(email);

        if(existingUser.isPresent()){
            throw new RuntimeException("User With this Email Already Present");
        }

        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setLastname(lastname);
        user.setRole(UserRole.CLIENT);
        user.setPhone(phone);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    public Boolean presentByEmail(String email){
        return userRepository.findFirstByEmail(email)!=null;
    }

    public User signupCompany(String name,String phone,String email, String password) {
        Optional<User> existingUser = userRepository.findByEmail(email);

        if(existingUser.isPresent()){
            throw new RuntimeException("User With this Email Already Present");
        }

        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setRole(UserRole.COMPANY);
        user.setPhone(phone);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }
}
