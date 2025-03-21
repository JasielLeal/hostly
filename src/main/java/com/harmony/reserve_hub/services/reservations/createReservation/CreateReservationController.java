package com.harmony.reserve_hub.services.reservations.createReservation;


import com.harmony.reserve_hub.domain.Reservations;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
public class CreateReservationController {

    private final CreateResevationUseCase createResevationUseCase;

    public CreateReservationController(CreateResevationUseCase createResevationUseCase) {
        this.createResevationUseCase = createResevationUseCase;
    }

    @PostMapping("/create")
    public void CreateReservation(@RequestBody @Valid CreateReservationDTO createReservationDTO){

        Reservations reservations = createResevationUseCase.execute(createReservationDTO);
    }

}
