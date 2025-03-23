package com.backend.BookMyShowBackend.services.authentication;

import com.backend.BookMyShowBackend.models.User;

public interface AuthService {
    User signupClient(String name, String lastname, String phone, String email, String password);

    Boolean presentByEmail(String email);

    User signupCompany(String name,String phone,String email, String password);
}
