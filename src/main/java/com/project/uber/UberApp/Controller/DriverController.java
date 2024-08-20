package com.project.uber.UberApp.Controller;

import com.project.uber.UberApp.DTO.*;
import com.project.uber.UberApp.Services.DriverService;
import com.project.uber.UberApp.repositories.RideRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/drivers")
public class DriverController {

    private final RideRequestRepository rideRequestRepository;
    private final DriverService driverService;

    @PostMapping("/acceptRide/{rideRequestId}")
    public ResponseEntity<RideDTO> acceptRide(@PathVariable Long rideRequestId) {
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

    @PostMapping("/cancelRide/{rideId}")
    public ResponseEntity<RideDTO> cancelRide(@PathVariable Long rideId){

        return ResponseEntity.ok(driverService.cancelRide(rideId));
    }

    @PostMapping("/rateRider/")
    public ResponseEntity<RiderDTO> rateRider(@RequestBody RateDTO rateDTO){
        return ResponseEntity.ok(driverService.rateRider(rateDTO.getRideId(),rateDTO.getRating()));
    }

    @GetMapping("/getMyProfile")
    public ResponseEntity<DriverDTO> getMyProfile(){
        return ResponseEntity.ok(driverService.getMyProfile());
    }

    @GetMapping("/getMyRides")
    public ResponseEntity<Page<RideDTO>> getAllMyRides(@RequestParam(defaultValue = "0")Integer pageOfSet,
                                                       @RequestParam(defaultValue = "10",required = false)Integer pageSize){

        PageRequest pageRequest = PageRequest.of(pageOfSet,pageSize, Sort.by(Sort.Direction.DESC,"createdTime","id"));
        return ResponseEntity.ok(driverService.getAllMyRides(pageRequest));
    }

//    @PostMapping("/rateRider/{rideId},{rating}")
//    public ResponseEntity<RiderDTO> rateRider(@PathVariable Long rideId, @PathVariable Integer rating){
//        return ResponseEntity.ok(driverService.rateRider(rideId,rating));
//    }
}
