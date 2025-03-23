package com.backend.BookMyShowBackend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class User extends BaseModel{
    private String name;
    private String email;
    private String password;
    private String lastname;
    private String phone;

    @Enumerated(EnumType.STRING)
    private UserRole role;

//    every list will have user_id
    @OneToMany(mappedBy = "user")
    private List<Booking> bookings;
}
