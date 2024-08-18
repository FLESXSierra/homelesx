package com.client.lesx.lesxclient.scenes.views.model.builder.factories;

import com.client.lesx.lesxclient.scenes.views.model.item.FitnessItem;
import com.client.lesx.lesxclient.scenes.views.objects.Fitness;

import java.time.LocalDate;

public class FitnessDaoFactory {

    public Fitness convertItemToDao(FitnessItem item) {
        Fitness fitness = new Fitness();
        fitness.setId((Integer) item.getId().getValue());
        fitness.setDate((LocalDate) item.getDate().getValue());
        fitness.setWeight((Double) item.getWeight().getValue());
        fitness.setWorkoutDay((Boolean) item.getWorkoutDay().getValue());
        return fitness;
    }
}
