package com.backend.BookMyShowBackend.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO {
    private ResponseStatus status;
    private Long userId;
    private String message;

    @Override
    public String toString() {
        return "SignupResponseDTO{" +
                "status=" + status +
                ", userId=" + userId +
                ", message='" + message + '\'' +
                '}';
    }
}
