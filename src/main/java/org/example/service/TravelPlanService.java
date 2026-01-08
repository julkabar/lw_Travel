package org.example.service;

import jakarta.persistence.OptimisticLockException;
import jakarta.transaction.Transactional;
import org.example.dto.LocationResponse;
import org.example.dto.TravelPlanCreateRequest;
import org.example.dto.TravelPlanResponse;
import org.example.entity.Location;
import org.springframework.stereotype.Service;
import org.example.dto.TravelPlanUpdateRequest;
import org.example.entity.TravelPlan;
import org.example.exception.ConflictException;
import org.example.exception.NotFoundException;
import org.example.repository.TravelPlanRepository;

import java.util.List;
import java.util.UUID;

@Service
public class TravelPlanService {

    private final TravelPlanRepository repository;

    public TravelPlanService(TravelPlanRepository repository) {
        this.repository = repository;
    }

    // ================= CREATE =================
    @Transactional
    public TravelPlanResponse create(TravelPlanCreateRequest request) {

        if (request == null) {
            throw new IllegalArgumentException("Request is null");
        }
        if (request.getTitle() == null || request.getTitle().isBlank()) {
            throw new IllegalArgumentException("Title is required");
        }

        TravelPlan plan = new TravelPlan();
        plan.setTitle(request.getTitle());
        plan.setDescription(request.getDescription());
        plan.setStartDate(request.getStartDate());
        plan.setEndDate(request.getEndDate());
        plan.setBudget(request.getBudget());
        plan.setCurrency(request.getCurrency());
        plan.setIsPublic(request.getIsPublic());

        TravelPlan saved = repository.save(plan);

        return toResponse(saved);
    }

    // ================= UPDATE =================
    @Transactional
    public TravelPlanResponse update(UUID id, TravelPlanUpdateRequest request) {

        TravelPlan plan = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Travel plan not found"));

        if (request.getVersion() == null) {
            throw new IllegalArgumentException("Version is required for update");
        }

        if (!plan.getVersion().equals(request.getVersion())) {
            throw new ConflictException(
                    "Conflict: Travel plan was modified by another request",
                    plan.getVersion()
            );
        }

        if (request.getTitle() != null) {
            plan.setTitle(request.getTitle());
        }
        if (request.getDescription() != null) {
            plan.setDescription(request.getDescription());
        }
        if (request.getStartDate() != null) {
            plan.setStartDate(request.getStartDate());
        }
        if (request.getEndDate() != null) {
            plan.setEndDate(request.getEndDate());
        }
        if (request.getBudget() != null) {
            plan.setBudget(request.getBudget());
        }
        if (request.getCurrency() != null) {
            plan.setCurrency(request.getCurrency());
        }
        if (request.getIsPublic() != null) {
            plan.setIsPublic(request.getIsPublic());
        }

        try {
            TravelPlan saved = repository.saveAndFlush(plan);
            return toResponse(saved);
        } catch (OptimisticLockException e) {
            throw new ConflictException(
                    "Conflict: Travel plan update conflict",
                    plan.getVersion()
            );
        }
    }

    // ================= GET =================
    @Transactional
    public TravelPlanResponse get(UUID id) {

        TravelPlan plan = repository.findByIdWithLocations(id)
                .orElseThrow(() -> new NotFoundException("Travel plan not found"));

        return toResponse(plan);
    }

    // ================= DELETE =================
    @Transactional
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Travel plan not found");
        }
        repository.deleteById(id);
    }

    // ================= MAPPER =================
    private TravelPlanResponse toResponse(TravelPlan plan) {

        List<LocationResponse> locations = plan.getLocations() == null
                ? List.of()
                : plan.getLocations()
                .stream()
                .map(this::toLocationResponse)
                .toList();

        return new TravelPlanResponse(
                plan.getId(),
                plan.getTitle(),
                plan.getDescription(),
                plan.getStartDate(),
                plan.getEndDate(),
                plan.getBudget(),
                plan.getCurrency(),
                plan.getIsPublic(),
                plan.getVersion(),
                plan.getCreatedAt(),
                plan.getUpdatedAt(),
                locations
        );
    }

    private LocationResponse toLocationResponse(Location location) {
        return new LocationResponse(
                location.getId(),
                location.getName(),
                location.getAddress(),
                location.getLatitude(),
                location.getLongitude(),
                location.getVisitOrder(),
                location.getArrivalDate(),
                location.getDepartureDate(),
                location.getBudget(),
                location.getNotes(),
                location.getCreatedAt(),
                location.getTravelPlan().getId(),
                location.getVersion()
        );
    }
}
