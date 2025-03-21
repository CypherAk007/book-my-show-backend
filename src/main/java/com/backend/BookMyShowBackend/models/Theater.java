package com.backend.BookMyShowBackend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Theater {
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
//    above annot tells that until we dont call theater.getRegion()
//    dont load the obj during container startup
    @JoinColumn(name = "region_id")
    private Region region;

    @OneToMany
    private List<Screen> screens;
}
