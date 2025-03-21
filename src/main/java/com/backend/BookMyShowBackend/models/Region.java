package com.backend.BookMyShowBackend.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Region extends BaseModel{
    private String name;
    private List<Theater> theaters;
}
