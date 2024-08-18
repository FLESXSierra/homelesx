package com.client.lesx.lesxclient.scenes.views.objects;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Fitness {

    public Integer id;
    public Double weight;
    public boolean workoutDay;
    public LocalDate date;

}
