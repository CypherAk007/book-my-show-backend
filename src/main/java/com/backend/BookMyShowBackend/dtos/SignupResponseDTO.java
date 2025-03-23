package com.backend.BookMyShowBackend.dtos;


import lombok.Data;


@Data
public class SignupResponseDTO {
    private ResponseStatus status;
    private Long userId;
    private String message;
    private String name;
    private String email;
    private String password;
    private String lastname;
    private String phone;

    @Override
    public String toString() {
        return "SignupResponseDTO{" +
                "status=" + status +
                ", userId=" + userId +
                ", message='" + message + '\'' +
                '}';
    }
}
