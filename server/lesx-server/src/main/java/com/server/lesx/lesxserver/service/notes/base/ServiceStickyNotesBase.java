package com.server.lesx.lesxserver.service.notes.base;

import com.server.lesx.lesxserver.entities.StickyNotes;

import java.util.List;


public interface ServiceStickyNotesBase {

    StickyNotes createStickyNotesRegistry(StickyNotes stickyNotes);

    StickyNotes getStickyNotesById(Integer id);

    Boolean removeStickyNotesById(Integer id);

    Boolean removeStickyNotesByIds(List<Integer> ids);

    List<StickyNotes> getStickyNotes();

    StickyNotes updateStickyNotesById(StickyNotes stickyNotes);
}
