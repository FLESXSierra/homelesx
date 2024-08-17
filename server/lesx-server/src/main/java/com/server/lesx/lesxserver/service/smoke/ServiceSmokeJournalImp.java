package com.server.lesx.lesxserver.service.smoke;

import com.server.lesx.lesxserver.entities.SmokeJournal;
import com.server.lesx.lesxserver.repositories.SmokeJournalRepository;
import com.server.lesx.lesxserver.service.smoke.base.ServiceSmokeJournalBase;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class ServiceSmokeJournalImp implements ServiceSmokeJournalBase {

    @Autowired
    SmokeJournalRepository smokeJournalRepository;

    @Override
    public SmokeJournal createSmokeJournalRegistry(SmokeJournal smokeJournal) {
        return smokeJournalRepository.save(smokeJournal);
    }

    @Override
    public SmokeJournal getSmokeJournalById(Integer id) {
        Optional<SmokeJournal> fitness = smokeJournalRepository.findById(id);
        if (fitness.isPresent()) {
            return fitness.get();
        }
        return null;
    }

    @Override
    public Boolean removeSmokeJournalRegistryById(Integer id) {
        try {
            smokeJournalRepository.deleteById(id);
            log.info("Deleted Smoke Journal ID: [{}]", id);
            return true;
        } catch (Exception e) {
            log.error("Failed deleting id: [{}]", id);
            return false;
        }
    }

    @Override
    public Boolean removeSmokeJournalRegistryByIds(List<Integer> ids) {
        try {
            smokeJournalRepository.deleteAllById(ids);
            log.info("Deleted Smoke Journal IDs: [{}]", ids);
            return true;
        } catch (Exception e) {
            log.error("Failed deleting id: [{}]", ids);
            return false;
        }
    }

    @Override
    public List<SmokeJournal> getSmokeJournal() {
        Iterable<SmokeJournal> all = smokeJournalRepository.findAll();
        if (!IteratorUtils.isEmpty(all.iterator())) {
            return IteratorUtils.toList(all.iterator());
        }
        return new ArrayList<>();
    }

    @Override
    public SmokeJournal updateSmokeJournalById(SmokeJournal smokeJournal) {
        if (smokeJournalRepository.existsById(smokeJournal.getId())) {
            SmokeJournal save = smokeJournalRepository.save(smokeJournal);
            log.info("Updated Smoke Journal by ID: [{}]", smokeJournal.getId());
            return save;
        }
        log.warn("No Smoke Journal found by ID: [{}]", smokeJournal.getId());
        return null;
    }
}
