package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class TravelPlanResponse {

    private UUID id;
    private String title;
    private String description;

    @JsonProperty("start_date")
    private LocalDate startDate;

    @JsonProperty("end_date")
    private LocalDate endDate;

    private BigDecimal budget;
    private String currency;

    @JsonProperty("is_public")
    private Boolean isPublic;

    private Integer version;

    @JsonProperty("created_at")
    private Instant createdAt;

    @JsonProperty("updated_at")
    private Instant updatedAt;

    private List<LocationResponse> locations;

    public TravelPlanResponse(
            UUID id,
            String title,
            String description,
            LocalDate startDate,
            LocalDate endDate,
            BigDecimal budget,
            String currency,
            Boolean isPublic,
            Integer version,
            Instant createdAt,
            Instant updatedAt,
            List<LocationResponse> locations
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budget = budget;
        this.currency = currency;
        this.isPublic = isPublic;
        this.version = version;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.locations = locations;
    }

    // getters

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public String getCurrency() {
        return currency;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public Integer getVersion() {
        return version;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public List<LocationResponse> getLocations() {
        return locations;
    }
}
