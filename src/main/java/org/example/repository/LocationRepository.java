package org.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.entity.Location;

import java.util.UUID;

public interface LocationRepository extends JpaRepository<Location, UUID> {
}
