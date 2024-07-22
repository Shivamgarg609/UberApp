package com.project.uber.UberApp.Services.Implememtation;

import com.project.uber.UberApp.Services.DistanceService;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

@Service
public class DistanceServiceOSRMimple implements DistanceService {
    @Override
    public double calculateDistance(Point src, Point dist) {
        return 0;
    }
}
