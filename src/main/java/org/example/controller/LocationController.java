package org.example.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.dto.*;
import org.example.service.LocationService;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class LocationController {

    private final LocationService service;

    public LocationController(LocationService service) {
        this.service = service;
    }

    @PostMapping("/travel-plans/{planId}/locations")
    public ResponseEntity<LocationResponse> create(
            @PathVariable UUID planId,
            @Valid @RequestBody LocationCreateRequest req
    ) {
        LocationResponse response = service.create(planId, req);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/locations/{id}")
    public LocationResponse update(
            @PathVariable UUID id,
            @Valid @RequestBody LocationUpdateRequest req
    ) {
        return service.update(id, req);
    }

    @DeleteMapping("/locations/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
