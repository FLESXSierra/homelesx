package com.client.lesx.lesxclient.scenes.views.objects;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class SmokeJournal {

    private Integer id;
    private Integer amount;
    private LocalDate date;
}
