package org.example.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.dto.*;
import org.example.service.TravelPlanService;

import java.util.UUID;

@RestController
@RequestMapping("/api/travel-plans")
public class TravelPlanController {

    private final TravelPlanService service;

    public TravelPlanController(TravelPlanService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TravelPlanResponse> create(
            @Valid @RequestBody TravelPlanCreateRequest req
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(req));
    }

    @GetMapping("/{id}")
    public TravelPlanResponse get(@PathVariable UUID id) {
        return service.get(id);
    }

    @PutMapping("/{id}")
    public TravelPlanResponse update(
            @PathVariable UUID id,
            @Valid @RequestBody TravelPlanUpdateRequest req
    ) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
