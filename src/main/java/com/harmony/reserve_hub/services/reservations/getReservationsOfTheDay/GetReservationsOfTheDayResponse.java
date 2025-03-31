package com.harmony.reserve_hub.services.reservations.getReservationsOfTheDay;

import com.harmony.reserve_hub.domain.Reservations;

import java.time.ZonedDateTime;
import java.util.UUID;

public record GetReservationsOfTheDayResponse(
        UUID id,
        String clientName,
        String clientCPF,
        ZonedDateTime checkIn,
        ZonedDateTime checkOut,
        String status,
        String room,
        String origin,
        String paymentStatus,
        ZonedDateTime createdAt
) {

    public static GetReservationsOfTheDayResponse fromEntity(Reservations reservation) {
        return new GetReservationsOfTheDayResponse(
                reservation.getId(),
                reservation.getClientName(),
                reservation.getClientCPF(),
                reservation.getCheckIn(),
                reservation.getCheckOut(),
                reservation.getStatus().toString(),
                reservation.getRoom(),
                reservation.getOrigin(),
                reservation.getPaymentStatus(),
                reservation.getCreatedAt()
        );
    }

}

