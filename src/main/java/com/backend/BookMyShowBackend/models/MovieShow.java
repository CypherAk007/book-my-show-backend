package com.backend.BookMyShowBackend.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class MovieShow extends BaseModel{
    private Movie movie;
    private Date startTime;
    private Date endTime;
    private Screen screen;
    private List<Feature> features;
}
