package com.server.lesx.lesxserver.repositories;

import com.server.lesx.lesxserver.entities.SmokeJournal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmokeJournalRepository extends CrudRepository<SmokeJournal, Integer> {
}
