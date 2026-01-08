package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class LocationUpdateRequest {

    @Size(max = 200)
    private String name;

    private String address;

    @DecimalMin("-90.0")
    @DecimalMax("90.0")
    private BigDecimal latitude;

    @DecimalMin("-180.0")
    @DecimalMax("180.0")
    private BigDecimal longitude;

    @JsonProperty("arrival_date")
    private OffsetDateTime arrivalDate;

    @JsonProperty("departure_date")
    private OffsetDateTime departureDate;

    @DecimalMin("0.0")
    @Digits(integer = 8, fraction = 2)
    private BigDecimal budget;

    private String notes;

    @AssertTrue(message = "departure_date must be after or equal to arrival_date")
    private boolean isDateRangeValid() {
        if (arrivalDate == null || departureDate == null) return true;
        return !departureDate.isBefore(arrivalDate);
    }

    public String getName() { return name; }
    public String getAddress() { return address; }
    public BigDecimal getLatitude() { return latitude; }
    public BigDecimal getLongitude() { return longitude; }
    public OffsetDateTime getArrivalDate() { return arrivalDate; }
    public OffsetDateTime getDepartureDate() { return departureDate; }
    public BigDecimal getBudget() { return budget; }
    public String getNotes() { return notes; }
}
