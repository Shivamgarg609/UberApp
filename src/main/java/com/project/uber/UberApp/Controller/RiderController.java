package com.project.uber.UberApp.Controller;

import com.project.uber.UberApp.DTO.*;
import com.project.uber.UberApp.Services.RiderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rider")
@RequiredArgsConstructor

public class RiderController {

    private final RiderService riderService;

    @PostMapping("/requestRide")
    public ResponseEntity<RideRequeatDTO> requestRide(@RequestBody RideRequeatDTO rideRequeatDTO){

         return ResponseEntity.ok(riderService.requestRide(rideRequeatDTO));
    }

    @PostMapping("/cancelRide/{rideId}")
    public ResponseEntity<RideDTO> cancelRide(@PathVariable Long rideId){

        return ResponseEntity.ok(riderService.cancelRide(rideId));
    }

    @PostMapping("/rateDriver/")
    public ResponseEntity<DriverDTO> rateDriver(@RequestBody RateDTO rateDTO){
        return ResponseEntity.ok(riderService.rateDriver(rateDTO.getRideId(),rateDTO.getRating()));
    }

    @GetMapping("/getMyProfile")
    public ResponseEntity<RiderDTO> getMyProfile(){
        return ResponseEntity.ok(riderService.getMyProfile());
    }

    @GetMapping("/getMyRides")
    public ResponseEntity<Page<RideDTO>> getAllMyRides(@RequestParam(defaultValue = "0")Integer pageOfSet,
                                                       @RequestParam(defaultValue = "10",required = false)Integer pageSize){

        PageRequest pageRequest = PageRequest.of(pageOfSet,pageSize);
        return ResponseEntity.ok(riderService.getAllMyRides(pageRequest));
    }

    @PostMapping("/rateRider/{rideId},{rating}")
    public ResponseEntity<DriverDTO> rateDriver(@PathVariable Long rideId, @PathVariable Integer rating){
        return ResponseEntity.ok(riderService.rateDriver(rideId,rating));
    }
}
