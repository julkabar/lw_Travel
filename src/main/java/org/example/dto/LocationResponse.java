package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;

public class LocationResponse {

    private UUID id;
    private String name;
    private String address;
    private BigDecimal latitude;
    private BigDecimal longitude;

    @JsonProperty("visit_order")
    private Integer visitOrder;

    @JsonProperty("arrival_data")
    private OffsetDateTime arrivalDate;

    @JsonProperty("departure_data")
    private OffsetDateTime departureDate;

    private BigDecimal budget;
    private String notes;

    @JsonProperty("create_at")
    private Instant createdAt;

    @JsonProperty("travel_plan_id")
    private UUID travelPlanId;

    @JsonProperty("version")
    private Integer version;

    public LocationResponse(
            UUID id,
            String name,
            String address,
            BigDecimal latitude,
            BigDecimal longitude,
            Integer visitOrder,
            OffsetDateTime arrivalDate,
            OffsetDateTime departureDate,
            BigDecimal budget,
            String notes,
            Instant createdAt,
            UUID travelPlanId,
            Integer version
    ) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.visitOrder = visitOrder;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.budget = budget;
        this.notes = notes;
        this.createdAt = createdAt;
        this.travelPlanId = travelPlanId;
    }

    // getters

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public Integer getVisitOrder() {
        return visitOrder;
    }

    public OffsetDateTime getArrivalDate() {
        return arrivalDate;
    }

    public OffsetDateTime getDepartureDate() {
        return departureDate;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public String getNotes() {
        return notes;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public UUID getTravelPlanId() {
        return travelPlanId;
    }
}
