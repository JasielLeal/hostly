package com.harmony.reserve_hub.repositories.reservations;

import com.harmony.reserve_hub.domain.Enterprise;
import com.harmony.reserve_hub.domain.Reservations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ReservationsRespository extends JpaRepository<Reservations, UUID> {
    List<Reservations> findAllByEnterpriseIdAndCheckInBetween(Enterprise enterprise, ZonedDateTime start, ZonedDateTime end);
}
