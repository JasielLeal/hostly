package com.harmony.reserve_hub.services.reservations.getReservationsOfTheDay;

import java.time.ZonedDateTime;
import java.util.UUID;

public record GetReservationsOfTheDayDTO(UUID userId, ZonedDateTime day) {
}
