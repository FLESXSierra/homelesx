package com.server.lesx.lesxserver.service.smoke.base;

import com.server.lesx.lesxserver.entities.SmokeJournal;

import java.util.List;


public interface ServiceSmokeJournalBase {

    SmokeJournal createSmokeJournalRegistry(SmokeJournal smokeJournal);

    SmokeJournal getSmokeJournalById(Integer id);

    Boolean removeSmokeJournalRegistryById(Integer id);

    Boolean removeSmokeJournalRegistryByIds(List<Integer> id);

    List<SmokeJournal> getSmokeJournal();

    SmokeJournal updateSmokeJournalById(SmokeJournal smokeJournal);
}
