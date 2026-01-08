package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TravelPlanUpdateRequest {

    @NotBlank
    @Size(max = 200)
    private String title;

    private String description;

    @JsonProperty("start_date")
    private LocalDate startDate;

    @JsonProperty("end_date")
    private LocalDate endDate;

    @DecimalMin("0.0")
    @Digits(integer = 8, fraction = 2)
    private BigDecimal budget;

    @Pattern(regexp = "^[A-Z]{3}$")
    private String currency;

    @NotNull
    @Positive
    private Integer version;

    @JsonProperty("is_public")
    private Boolean isPublic;

    @AssertTrue(message = "end_date must be after or equal to start_date")
    private boolean isDateRangeValid() {
        if (startDate == null || endDate == null) return true;
        return !endDate.isBefore(startDate);
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public BigDecimal getBudget() { return budget; }
    public Integer getVersion() { return version; }
    public Boolean getIsPublic() { return isPublic; }
    public Boolean getPublic() { return isPublic; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public String getCurrency() { return currency; }
}
