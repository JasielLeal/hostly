package com.harmony.reserve_hub.repositories;

import com.harmony.reserve_hub.domain.Reservations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReservationsRespository extends JpaRepository<Reservations, UUID> {
}
