package com.backend.BookMyShowBackend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Screen extends BaseModel{
    private String name;
    @OneToMany
    private List<Seat> seats;
//    @ManyToMany - as db we cant store multiple val in one col
//    so we use element collection to store list feature

    @Enumerated(value = EnumType.STRING)//only this wont work we need element collection
    @ElementCollection //store id and enum maping in another table
    private List<Feature> features;

}
