package com.project.uber.UberApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverDTO {

    private Long id;

    private UserDTO user;

    private Double rating;

    private String name;

    private boolean available;

    private String vehichleId;

    private Point currentLocation;
}
