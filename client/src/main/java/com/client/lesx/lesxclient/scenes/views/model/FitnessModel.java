package com.client.lesx.lesxclient.scenes.views.model;

import com.client.lesx.lesxclient.scenes.views.objects.Fitness;
import javafx.beans.property.*;

import java.time.LocalDate;

public class FitnessModel {

    private Integer id;
    private DoubleProperty weight;
    private BooleanProperty workoutDay;
    private ObjectProperty<LocalDate> date;

    public FitnessModel(Fitness fitness){
        this.id = fitness.getId();
        this.weight = new SimpleDoubleProperty(fitness.getWeight());
        this.workoutDay = new SimpleBooleanProperty(fitness.isWorkoutDay());
        this.date = new SimpleObjectProperty<>(fitness.getDate());
    }

    public Integer getId() {
        return id;
    }

    public double getWeight() {
        return weight.get();
    }

    public DoubleProperty weightProperty() {
        return weight;
    }

    public boolean isWorkoutDay() {
        return workoutDay.get();
    }

    public BooleanProperty workoutDayProperty() {
        return workoutDay;
    }

    public LocalDate getDate() {
        return date.get();
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }
}
