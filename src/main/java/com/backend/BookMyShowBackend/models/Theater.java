package com.backend.BookMyShowBackend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Theater {
    private String name;
    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @OneToMany
    private List<Screen> screens;
}
