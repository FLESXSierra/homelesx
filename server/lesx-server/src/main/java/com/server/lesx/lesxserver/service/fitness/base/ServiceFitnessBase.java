package com.server.lesx.lesxserver.service.fitness.base;

import com.server.lesx.lesxserver.entities.Fitness;

import java.util.List;


public interface ServiceFitnessBase {

    Fitness createFitnessRegistry(Fitness fitness);

    Fitness getFitnessRegistryById(Integer id);

    boolean removeFitnessRegistryById(Integer id);

    boolean removeFitnessRegistryByIds(List<Integer> ids);

    List<Fitness> getFitnessRegistry();

    Fitness updateFitnessRegistryById(Fitness fitness);
}
