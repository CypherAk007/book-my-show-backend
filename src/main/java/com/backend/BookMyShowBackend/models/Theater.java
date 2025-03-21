package com.backend.BookMyShowBackend.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Theater {
    private String name;
    private Region region;
    private List<Screen> screens;
}
