package com.project.uber.UberApp.Services.Implememtation;

import com.project.uber.UberApp.Services.DistanceService;
import lombok.Data;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class DistanceServiceOSRMimple implements DistanceService {

    private static String Osrm_URL = "http://router.project-osrm.org/route/v1/driving/";
    @Override
    public double calculateDistance(Point src, Point dist) {

        try {
            String uri = src.getX() + "," + src.getY() + ";" + dist.getX() + ","+dist.getY();
            OSRMResponseDto osrmResponseDto = RestClient.builder().baseUrl(Osrm_URL)
                    .build()
                    .get()
                    .uri(uri)
                    .retrieve().body(OSRMResponseDto.class);

            return osrmResponseDto.getRoutes().get(0).getDistance() / 1000.0;
        }
        catch (Exception ex){
            throw new RuntimeException("Error getting data from ORSM"+ ex.getMessage());
        }
        }
}

@Data
class OSRMResponseDto{
    private List<OSRMRoute> routes;
}

@Data
class OSRMRoute{

    private Double distance ;
}
