package com.harmony.reserve_hub.services.reservations.updateReservation;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
public class UpdateReservationController {

    private final UpdateReservationUseCase updateReservationUseCase;

    public UpdateReservationController(UpdateReservationUseCase updateReservationUseCase) {
        this.updateReservationUseCase = updateReservationUseCase;
    }

    @PutMapping("/status/update")
    public void UpdateReservation(@RequestBody @Valid UpdateReservationDTO updateReservationDTO) {

        updateReservationUseCase.execute(updateReservationDTO);
    }
}
