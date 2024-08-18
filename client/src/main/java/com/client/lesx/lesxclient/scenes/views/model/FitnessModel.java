package com.client.lesx.lesxclient.scenes.views.model;

import com.client.lesx.lesxclient.scenes.views.model.item.FitnessItem;
import com.client.lesx.lesxclient.scenes.views.objects.Fitness;
import javafx.beans.property.*;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static com.client.lesx.lesxclient.scenes.views.model.builder.ModelObjectsFactory.buildFitnessItemFromModel;

public class FitnessModel {

    private Integer id;
    private DoubleProperty weight;
    private BooleanProperty workoutDay;
    private ObjectProperty<LocalDate> date;

    public FitnessModel() {
        id = -1;
        this.weight = new SimpleDoubleProperty(0);
        this.workoutDay = new SimpleBooleanProperty(false);
        this.date = new SimpleObjectProperty<>(LocalDate.now());
    }

    public FitnessModel(Fitness fitness) {
        this.id = fitness.getId();
        this.weight = new SimpleDoubleProperty(fitness.getWeight());
        this.workoutDay = new SimpleBooleanProperty(fitness.isWorkoutDay());
        this.date = new SimpleObjectProperty<>(fitness.getDate());
    }

    public FitnessItem getAsFitnessItem() {
        return buildFitnessItemFromModel(this);
    }

    public Integer getId() {
        return id;
    }

    public double getWeight() {
        return weight.get();
    }

    public void setWeight(Double newValue) {
        weight.setValue(newValue);
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

    public void setWorkoutDay(Boolean newValue) {
        workoutDay.setValue(newValue);
    }

    public LocalDate getDate() {
        return date.get();
    }

    public void setDate(LocalDate newValue) {
        date.setValue(newValue);
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }
}
