package com.harmony.reserve_hub.services.reservations.createReservation;

import com.harmony.reserve_hub.domain.Enterprise;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.UUID;

public record CreateReservationDTO(

        @NotBlank(message = "Client name is required")
        String clientName,

        @NotBlank(message = "Client CPF is required")
        String clientCPF,

        @NotNull(message = "Check-in is required")
        ZonedDateTime  checkIn,

        @NotNull(message = "Check-out name is required")
        ZonedDateTime checkOut,

        @NotBlank(message = "Room is required")
        String room,

        @NotBlank(message = "Origin is required")
        String origin,

        @NotBlank(message = "PaymentStatus is required")
        String paymentStatus,

        @NotNull(message = "EnterpriseId is required")
        UUID enterpriseId // Agora é UUID, não mais a entidade Enterprise
) {
}
