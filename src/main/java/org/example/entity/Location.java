package org.example.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "travel_plan_id")
    private TravelPlan travelPlan;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "address", columnDefinition = "text")
    private String address;

    @Column(name = "latitude")
    private BigDecimal latitude;

    @Column(name = "longitude")
    private BigDecimal longitude;

    @Column(name = "visit_order")
    private Integer visitOrder;

    @Column(name = "arrival_date")
    private OffsetDateTime arrivalDate;

    @Column(name = "departure_date")
    private OffsetDateTime departureDate;

    @Column(name = "budget", precision = 10, scale = 2)
    private BigDecimal budget;

    @Column(name = "notes", columnDefinition = "text")
    private String notes;

    @Version
    @Column(name = "version", nullable = false)
    private Integer version = 1;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    // getters & setters
    public UUID getId() { return id; }
    public TravelPlan getTravelPlan() { return travelPlan; }
    public void setTravelPlan(TravelPlan travelPlan) { this.travelPlan = travelPlan; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public BigDecimal getLatitude() { return latitude; }
    public void setLatitude(BigDecimal latitude) { this.latitude = latitude; }
    public BigDecimal getLongitude() { return longitude; }
    public void setLongitude(BigDecimal longitude) { this.longitude = longitude; }
    public Integer getVisitOrder() { return visitOrder; }
    public OffsetDateTime getArrivalDate() { return arrivalDate; }
    public void setArrivalDate(OffsetDateTime arrivalDate) { this.arrivalDate = arrivalDate; }
    public OffsetDateTime getDepartureDate() { return departureDate; }
    public void setDepartureDate(OffsetDateTime departureDate) { this.departureDate = departureDate; }
    public BigDecimal getBudget() { return budget; }
    public void setBudget(BigDecimal budget) { this.budget = budget; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public void setId(UUID id) { this.id = id; }
    public void setVisitOrder(Integer visitOrder) { this.visitOrder = visitOrder; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
    public Integer getVersion() { return version; }
    public void setVersion(Integer version) { this.version = version; }
}
