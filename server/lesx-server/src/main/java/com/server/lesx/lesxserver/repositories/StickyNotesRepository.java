package com.server.lesx.lesxserver.repositories;

import com.server.lesx.lesxserver.entities.StickyNotes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StickyNotesRepository extends CrudRepository<StickyNotes, Integer> {
}
