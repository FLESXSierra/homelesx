package com.client.lesx.lesxclient.scenes.views.objects;

import java.time.LocalDate;

public class Fitness {

    public Integer id;
    public Double weight;
    public boolean workoutDay;
    public LocalDate date;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void setWorkoutDay(boolean workoutDay) {
        this.workoutDay = workoutDay;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public Double getWeight() {
        return weight;
    }

    public boolean isWorkoutDay() {
        return workoutDay;
    }

    public LocalDate getDate() {
        return date;
    }
}
