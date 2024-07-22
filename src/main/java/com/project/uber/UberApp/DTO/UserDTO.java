package com.project.uber.UberApp.DTO;

import com.project.uber.UberApp.Entities.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String name;

    private Set<Role> roles;
}
