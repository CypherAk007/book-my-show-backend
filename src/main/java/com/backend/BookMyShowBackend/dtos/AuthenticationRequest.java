package com.backend.BookMyShowBackend.dtos;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String username;
    private String password;
}
