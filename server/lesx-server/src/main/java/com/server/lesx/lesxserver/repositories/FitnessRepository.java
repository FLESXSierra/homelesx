package com.server.lesx.lesxserver.repositories;

import com.server.lesx.lesxserver.entities.Fitness;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FitnessRepository extends CrudRepository<Fitness, Integer> {
}
