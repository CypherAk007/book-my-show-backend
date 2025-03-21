package com.backend.BookMyShowBackend.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Theater {
    private String name;
    private Region region;
    private List<Screen> screens;
}
