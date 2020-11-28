package com.courses.academy.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/health")
public class HealthController {
    
    @ApiOperation(value= "Checks if the application is running", response = String.class)
    @GetMapping
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok().build();
    }
}