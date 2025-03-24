package com.harmony.reserve_hub.services.reservations.updateReservation;

import com.harmony.reserve_hub.domain.Reservations;

import java.util.UUID;

public record UpdateReservationDTO(UUID reservationId,  Reservations.Status status) {
}
