package com.server.lesx.lesxserver.controller;

import com.server.lesx.lesxserver.entities.StickyNotes;
import com.server.lesx.lesxserver.service.notes.ServiceStickyNotesImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/notes")
public class StickyNotesController {

    @Autowired
    private ServiceStickyNotesImp stickyNotesImp;

    @PostMapping("/create")
    public ResponseEntity<StickyNotes> createStickyNotesRegistry(@RequestBody StickyNotes stickyNotes) {
        StickyNotes save = stickyNotesImp.createStickyNotesRegistry(stickyNotes);
        if (save != null) {
            return new ResponseEntity(stickyNotes, HttpStatus.CREATED);
        }
        return ResponseEntity.internalServerError().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StickyNotes> getStickyNotesRegistryById(@PathVariable("id") Integer id) {
        StickyNotes sticky = stickyNotesImp.getStickyNotesById(id);
        if (sticky != null) {
            return new ResponseEntity(sticky, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/remove/{id}")
    public ResponseEntity<StickyNotes> removeStickyNotesById(@PathVariable("id") Integer id) {
        Boolean result = stickyNotesImp.removeStickyNotesById(id);
        return new ResponseEntity(result, result ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/remove/ids")
    public ResponseEntity<StickyNotes> removeStickyNotesByIds(@RequestBody List<Integer> ids) {
        Boolean result = stickyNotesImp.removeStickyNotesByIds(ids);
        return new ResponseEntity(result, result ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/all")
    public ResponseEntity<List<StickyNotes>> getStickyNotesRegistry() {
        List<StickyNotes> sticky = stickyNotesImp.getStickyNotes();
        if (!CollectionUtils.isEmpty(sticky)) {
            return new ResponseEntity(sticky, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/update")
    public ResponseEntity<StickyNotes> updateStickyNotesRegistryById(@RequestBody StickyNotes stickyNotes) {
        StickyNotes sticky = stickyNotesImp.updateStickyNotesById(stickyNotes);
        if (sticky != null) {
            return new ResponseEntity(sticky, HttpStatus.CREATED);
        }

        return ResponseEntity.badRequest().build();
    }
}
