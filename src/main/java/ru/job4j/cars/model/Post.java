package ru.job4j.cars.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "auto_post")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;
    private String description;
    private LocalDateTime created = LocalDateTime.now();
    @ManyToOne()
    @JoinColumn(name = "auto_user_id", nullable = false)
    private User user;
    @OneToOne
    @JoinTable(name = "car_id")
    private Car car;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "auto_post_id", nullable = false)
    private List<PriceHistory> priceHistoryList = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "participates",
            joinColumns = {@JoinColumn(name = "post_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "user_id", nullable = false)}
    )
    private List<User> participates = new ArrayList<>();
}