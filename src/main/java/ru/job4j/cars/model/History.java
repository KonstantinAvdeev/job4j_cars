package ru.job4j.cars.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "history")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    @ManyToOne()
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner owner;

}