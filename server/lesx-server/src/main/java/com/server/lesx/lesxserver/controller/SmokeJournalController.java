package com.server.lesx.lesxserver.controller;

import com.server.lesx.lesxserver.entities.SmokeJournal;
import com.server.lesx.lesxserver.service.smoke.ServiceSmokeJournalImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/smoke")
public class SmokeJournalController {

    @Autowired
    private ServiceSmokeJournalImp smokeJournalImp;

    @PostMapping("/create")
    public ResponseEntity<SmokeJournal> createSmokeJournalRegistry(@RequestBody SmokeJournal smokeJournal) {
        SmokeJournal save = smokeJournalImp.createSmokeJournalRegistry(smokeJournal);
        if (save != null) {
            return new ResponseEntity(smokeJournal, HttpStatus.CREATED);
        }
        return ResponseEntity.internalServerError().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SmokeJournal> getSmokeJournalRegistryById(@PathVariable("id") Integer id) {
        SmokeJournal smoke = smokeJournalImp.getSmokeJournalById(id);
        if (smoke != null) {
            return new ResponseEntity(smoke, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/remove/{id}")
    public ResponseEntity<Boolean> removeSmokeJournalRegistryById(@PathVariable("id") Integer id) {
        Boolean result = smokeJournalImp.removeSmokeJournalRegistryById(id);
        return new ResponseEntity(result, result ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/remove/ids")
    public ResponseEntity<Boolean> removeSmokeJournalRegistryByIds(List<Integer> id) {
        Boolean result = smokeJournalImp.removeSmokeJournalRegistryByIds(id);
        return new ResponseEntity(result, result ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SmokeJournal>> getSmokeJournalRegistry() {
        List<SmokeJournal> smoke = smokeJournalImp.getSmokeJournal();
        if (!CollectionUtils.isEmpty(smoke)) {
            return new ResponseEntity(smoke, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/update")
    public ResponseEntity<SmokeJournal> updateSmokeJournalRegistryById(@RequestBody SmokeJournal smokeJournal) {
        SmokeJournal smokeUpdated = smokeJournalImp.updateSmokeJournalById(smokeJournal);
        if (smokeUpdated != null) {
            return new ResponseEntity(smokeUpdated, HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().build();
    }
}
