package com.server.lesx.lesxserver.service.notes;

import com.server.lesx.lesxserver.entities.StickyNotes;
import com.server.lesx.lesxserver.repositories.StickyNotesRepository;
import com.server.lesx.lesxserver.service.notes.base.ServiceStickyNotesBase;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class ServiceStickyNotesImp implements ServiceStickyNotesBase {

    @Autowired
    StickyNotesRepository stickyNotesRepository;

    @Override
    public StickyNotes createStickyNotesRegistry(StickyNotes stickyNotes) {
        return stickyNotesRepository.save(stickyNotes);
    }

    @Override
    public StickyNotes getStickyNotesById(Integer id) {
        Optional<StickyNotes> fitness = stickyNotesRepository.findById(id);
        if (fitness.isPresent()) {
            return fitness.get();
        }
        return null;
    }

    @Override
    public Boolean removeStickyNotesById(Integer id) {
        try {
            stickyNotesRepository.deleteById(id);
            log.info("Removed Sticky Notes by ID: [{}]", id);
            return true;
        }
        catch (Exception e){
            log.error("Failed deleting id: [{}]", id);
            return false;
        }
    }

    @Override
    public Boolean removeStickyNotesByIds(List<Integer> ids) {
        try {
            stickyNotesRepository.deleteAllById(ids);
            log.info("Removed Sticky Notes by IDs: [{}]", ids);
            return true;
        }
        catch (Exception e){
            log.error("Failed deleting id: [{}]", ids);
            return false;
        }
    }

    @Override
    public List<StickyNotes> getStickyNotes() {
        Iterable<StickyNotes> all = stickyNotesRepository.findAll();
        if(!IteratorUtils.isEmpty(all.iterator())){
            log.info("Found all StickyNotes");
            return IteratorUtils.toList(all.iterator());
        }
        return new ArrayList<>();
    }

    @Override
    public StickyNotes updateStickyNotesById(StickyNotes stickyNotes) {
        if (stickyNotesRepository.existsById(stickyNotes.getId())) {
            StickyNotes save = stickyNotesRepository.save(stickyNotes);
            if (save != null) {
                log.info("Updated Sticky Notes by ID: [{}]", stickyNotes.getId());
                return save;
            }
        }
        log.warn("No Sticky Notes found by ID: [{}]", stickyNotes.getId());
        return null;
    }
}
