package com.project.uber.UberApp.Advices;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Builder
@Data
public class ApiError {

    private HttpStatus status;

    private String message;

    private List<String> subErrors;
}
