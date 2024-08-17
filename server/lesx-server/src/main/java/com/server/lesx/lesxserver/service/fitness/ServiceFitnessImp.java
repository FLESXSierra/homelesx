package com.server.lesx.lesxserver.service.fitness;

import com.server.lesx.lesxserver.entities.Fitness;
import com.server.lesx.lesxserver.repositories.FitnessRepository;
import com.server.lesx.lesxserver.service.fitness.base.ServiceFitnessBase;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class ServiceFitnessImp implements ServiceFitnessBase {

    @Autowired
    FitnessRepository fitnessRepository;

    @Override
    public Fitness createFitnessRegistry(Fitness fitness) {
        return fitnessRepository.save(fitness);
    }

    @Override
    public Fitness getFitnessRegistryById(Integer id) {
        log.info("Trying to find [{}] Fitness Registry", id);
        Optional<Fitness> fitness = fitnessRepository.findById(id);
        return fitness.orElse(null);
    }

    @Override
    public boolean removeFitnessRegistryById(Integer id) {
        try {
            fitnessRepository.deleteById(id);
            log.info("Removed Fitness by ID: [{}]", id);
            return true;
        } catch (Exception e) {
            log.error("Failed deleting id: [{}]", id);
            return false;
        }
    }

    @Override
    public boolean removeFitnessRegistryByIds(List<Integer> ids) {
        try {
            fitnessRepository.deleteAllById(ids);
            log.info("Removed Fitness by IDs: [{}]", ids);
            return true;
        } catch (Exception e) {
            log.error("Failed deleting ids: [{}]", ids);
            return false;
        }
    }

    @Override
    public List<Fitness> getFitnessRegistry() {
        log.info("Trying to find all Fitness Registries");
        Iterable<Fitness> all = fitnessRepository.findAll();
        if (!IteratorUtils.isEmpty(all.iterator())) {
            log.info("Found all Fitness Registries");
            return IteratorUtils.toList(all.iterator());
        }
        return new ArrayList<>();
    }

    @Override
    public Fitness updateFitnessRegistryById(Fitness fitness) {
        if (fitnessRepository.existsById(fitness.getId())) {
            Fitness save = fitnessRepository.save(fitness);
            log.info("Updated Fitness by ID: [{}]", fitness.getId());
            return save;
        }
        log.warn("No Fitness found by ID: [{}]", fitness.getId());
        return null;
    }
}
