package org.example.repository;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.example.entity.TravelPlan;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface TravelPlanRepository extends JpaRepository<TravelPlan, UUID> {
    @EntityGraph(attributePaths = "locations")
    @Query("""
    select tp from TravelPlan tp
    where tp.id = :id
""")
    Optional<TravelPlan> findByIdWithLocations(@Param("id") UUID id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
    select tp from TravelPlan tp
    where tp.id = :id
""")
    Optional<TravelPlan> findByIdForLocationCreate(@Param("id") UUID id);
}
