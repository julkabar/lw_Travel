package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TravelPlanCreateRequest {

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
        private String currency = "USD";

        @JsonProperty("is_public")
        private Boolean isPublic = false;

        @AssertTrue(message = "end_date must be after or equal to start_date")
        private boolean isDateRangeValid() {
                if (startDate == null || endDate == null) return true;
                return !endDate.isBefore(startDate);
        }

        public String getTitle() { return title; }
        public String getDescription() { return description; }
        public LocalDate getStartDate() { return startDate; }
        public LocalDate getEndDate() { return endDate; }
        public BigDecimal getBudget() { return budget; }
        public String getCurrency() { return currency; }
        public Boolean getIsPublic() { return isPublic; }
}
