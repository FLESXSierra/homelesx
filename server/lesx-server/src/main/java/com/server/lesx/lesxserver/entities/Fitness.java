package com.server.lesx.lesxserver.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fitness")
public class Fitness {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "weight")
    private Double weight;
    @Column(name = "workout_day")
    private boolean workoutDay;
    @Column(name = "date")
    private LocalDate date;
}
