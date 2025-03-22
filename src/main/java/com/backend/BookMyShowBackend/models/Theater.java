package com.backend.BookMyShowBackend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Theater extends BaseModel{
    private String name;

//    below annot tells that until we dont call theater.getRegion()
//    dont load the obj during container startup
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region")
    private Region region;

    @OneToMany
    private List<Screen> screens;
}
