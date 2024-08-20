package com.project.uber.UberApp.repositories;

import com.project.uber.UberApp.Entities.Driver;
import com.project.uber.UberApp.Entities.Rating;
import com.project.uber.UberApp.Entities.Ride;
import com.project.uber.UberApp.Entities.Rider;
import lombok.Builder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Long> {

    List<Rating> findByRider(Rider rider);
    List<Rating> findByDriver(Driver driver);

    Optional<Rating> findByRide(Ride ride);
}
