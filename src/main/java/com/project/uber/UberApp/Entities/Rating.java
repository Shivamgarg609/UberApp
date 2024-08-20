package com.project.uber.UberApp.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(indexes = {
        @Index(name = "idx_rating_rider", columnList ="riderId" )
})
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Ride ride;

    @ManyToOne
    private Rider rider;

    @ManyToOne
    private Driver driver;

    private Integer driverRating;

    private Integer riderRating;
}
