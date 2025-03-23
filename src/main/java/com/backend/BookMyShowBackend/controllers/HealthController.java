package com.backend.BookMyShowBackend.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {
    @GetMapping
    @Operation(summary="Check Application Health",description = "API to check Application Health!!")
    public ResponseEntity<String> health(){
        return ResponseEntity.ok("Application is Running!!!");
    }
}
