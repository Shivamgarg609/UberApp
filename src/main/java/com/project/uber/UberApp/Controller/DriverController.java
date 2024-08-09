package com.project.uber.UberApp.Controller;

import com.project.uber.UberApp.DTO.RideDTO;
import com.project.uber.UberApp.DTO.RideStartDto;
import com.project.uber.UberApp.Services.DriverService;
import com.project.uber.UberApp.repositories.RideRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/drivers")
public class DriverController {

    private final RideRequestRepository rideRequestRepository;
    private final DriverService driverService;;

//    @GetMapping("/")
//    public DriverDTO getDriver(@RequestBody )
//
//

     @PostMapping("/acceptRide/{rideRequestId}")
    public ResponseEntity<RideDTO> acceptRide(@PathVariable Long rideRequestId){


         return ResponseEntity.ok(driverService.acceptRide(rideRequestId));
     }

     @PostMapping("/startRide/{rideRequestId}")
     public ResponseEntity<RideDTO> startRide(@PathVariable Long rideRequestId,
                                              @RequestBody RideStartDto rideStartDto) {
         return ResponseEntity.ok(driverService.startRide(rideRequestId, rideStartDto.getOTP()));
     }

    @PostMapping("/endRide/{rideId}")
    public ResponseEntity<RideDTO> endRide(@PathVariable Long rideId) {
        return ResponseEntity.ok(driverService.endRide(rideId));
    }

}
