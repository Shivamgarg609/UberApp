package com.project.uber.UberApp.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Point;

@Entity
@Getter
@Setter
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Double rating;

  //  private String name;

    private boolean available;

    private String vehichleId;

    @Column(columnDefinition = "Geometry(Point, 4326)")
    Point currentLocation;
}
