package com.backend.BookMyShowBackend.models;

import jakarta.persistence.Entity;

import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class MovieShow extends BaseModel{
    @ManyToOne
    private Movie movie;
    private Date startTime;
    private Date endTime;
    @ManyToOne
    private Screen screen;

    private List<Feature> features;
}
