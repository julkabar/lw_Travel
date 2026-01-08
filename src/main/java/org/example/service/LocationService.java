package org.example.service;

import jakarta.persistence.OptimisticLockException;
import jakarta.transaction.Transactional;
import org.example.dto.LocationResponse;
import org.example.exception.ConflictException;
import org.springframework.stereotype.Service;
import org.example.dto.LocationCreateRequest;
import org.example.dto.LocationUpdateRequest;
import org.example.exception.NotFoundException;
import org.example.entity.Location;
import org.example.entity.TravelPlan;
import org.example.repository.LocationRepository;
import org.example.repository.TravelPlanRepository;

import java.util.UUID;

@Service
public class LocationService {

    private final LocationRepository locationRepository;
    private final TravelPlanRepository travelPlanRepository;

    public LocationService(LocationRepository locationRepository,
                           TravelPlanRepository travelPlanRepository) {
        this.locationRepository = locationRepository;
        this.travelPlanRepository = travelPlanRepository;
    }

    // ================= CREATE =================
    @Transactional
    public LocationResponse create(UUID travelPlanId, LocationCreateRequest request) {

        TravelPlan plan = travelPlanRepository.findByIdForLocationCreate(travelPlanId)
                .orElseThrow(() -> new NotFoundException("Travel plan not found"));

        Location location = new Location();
        location.setTravelPlan(plan);
        location.setName(request.getName());
        location.setAddress(request.getAddress());
        location.setLatitude(request.getLatitude());
        location.setLongitude(request.getLongitude());
        location.setArrivalDate(request.getArrivalDate());
        location.setDepartureDate(request.getDepartureDate());
        location.setBudget(request.getBudget());
        location.setNotes(request.getNotes());
        location.setVisitOrder(plan.getLocations().size() + 1);

        Location saved = locationRepository.save(location);

        return toResponse(saved);
    }

    // ================= UPDATE =================
    @Transactional
    public LocationResponse update(UUID id, LocationUpdateRequest request) {

        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Location not found"));

        if (!location.getVersion().equals(request.getVersion())) {
            throw new ConflictException(
                    "Conflict: Location was modified by another request",
                    location.getVersion()
            );
        }

        if (request.getName() != null) {
            location.setName(request.getName());
        }
        if (request.getAddress() != null) {
            location.setAddress(request.getAddress());
        }
        if (request.getLatitude() != null) {
            location.setLatitude(request.getLatitude());
        }
        if (request.getLongitude() != null) {
            location.setLongitude(request.getLongitude());
        }
        if (request.getArrivalDate() != null) {
            location.setArrivalDate(request.getArrivalDate());
        }
        if (request.getDepartureDate() != null) {
            location.setDepartureDate(request.getDepartureDate());
        }
        if (request.getBudget() != null) {
            location.setBudget(request.getBudget());
        }
        if (request.getNotes() != null) {
            location.setNotes(request.getNotes());
        }

        try {
            Location saved = locationRepository.saveAndFlush(location);
            return toResponse(saved);
        } catch (OptimisticLockException e) {
            throw new ConflictException(
                    "Conflict: Location update conflict",
                    location.getVersion()
            );
        }
    }

    // ================= DELETE =================
    @Transactional
    public void delete(UUID id) {
        if (!locationRepository.existsById(id)) {
            throw new NotFoundException("Location not found");
        }
        locationRepository.deleteById(id);
    }

    // ================= MAPPER =================
    private LocationResponse toResponse(Location loc) {
        return new LocationResponse(
                loc.getId(),
                loc.getName(),
                loc.getAddress(),
                loc.getLatitude(),
                loc.getLongitude(),
                loc.getVisitOrder(),
                loc.getArrivalDate(),
                loc.getDepartureDate(),
                loc.getBudget(),
                loc.getNotes(),
                loc.getCreatedAt(),
                loc.getTravelPlan().getId(),
                loc.getVersion()
        );
    }
}
