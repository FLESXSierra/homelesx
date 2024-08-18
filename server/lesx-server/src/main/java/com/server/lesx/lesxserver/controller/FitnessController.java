package com.server.lesx.lesxserver.controller;

import com.server.lesx.lesxserver.entities.Fitness;
import com.server.lesx.lesxserver.service.fitness.ServiceFitnessImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/fitness")
public class FitnessController {

    @Autowired
    private ServiceFitnessImp fitnessImp;

    @PostMapping("/create")
    public ResponseEntity<Fitness> createFitnessRegistry(@RequestBody Fitness fitness) {
        Fitness save = fitnessImp.createFitnessRegistry(fitness);
        if (save != null) {
            return new ResponseEntity(save, HttpStatus.CREATED);
        }
        return ResponseEntity.internalServerError().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fitness> getFitnessRegistryById(@PathVariable("id") Integer id) {
        Fitness fitness = fitnessImp.getFitnessRegistryById(id);
        if (fitness != null) {
            return new ResponseEntity(fitness, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/remove/{id}")
    public ResponseEntity<Boolean> removeFitnessRegistryById(@PathVariable("id") Integer id) {
        boolean result = fitnessImp.removeFitnessRegistryById(id);
        return new ResponseEntity(result, result ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/remove/ids")
    public ResponseEntity<Boolean> removeFitnessRegistryByIds(@RequestBody List<Integer> ids) {
        boolean result = fitnessImp.removeFitnessRegistryByIds(ids);
        return new ResponseEntity(result, result ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Fitness>> getFitnessRegistryById() {
        List<Fitness> fitness = fitnessImp.getFitnessRegistry();
        if (!CollectionUtils.isEmpty(fitness)) {
            return new ResponseEntity(fitness, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/update")
    public ResponseEntity<Fitness> updateFitnessRegistryById(@RequestBody Fitness fitness) {
        Fitness fitnessUpdated = fitnessImp.updateFitnessRegistryById(fitness);
        if (fitnessUpdated != null) {
            return new ResponseEntity(fitnessUpdated, HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().build();
    }
}
