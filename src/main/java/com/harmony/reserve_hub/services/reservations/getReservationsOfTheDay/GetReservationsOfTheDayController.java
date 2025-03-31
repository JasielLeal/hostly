package com.harmony.reserve_hub.services.reservations.getReservationsOfTheDay;


import com.harmony.reserve_hub.domain.Reservations;
import org.springframework.web.bind.annotation.*;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reservations")
public class GetReservationsOfTheDayController {

    private final GetReservationsOfTheDayUseCase getReservationsOfTheDayUseCase;

    public GetReservationsOfTheDayController(GetReservationsOfTheDayUseCase getReservationsOfTheDayUseCase) {
        this.getReservationsOfTheDayUseCase = getReservationsOfTheDayUseCase;
    }

    @GetMapping("/daily/{userId}/{day}")
    public List<GetReservationsOfTheDayResponse> getReservations(@PathVariable UUID userId, @PathVariable String day){
        ZonedDateTime dateTime =ZonedDateTime.parse(day);

        GetReservationsOfTheDayDTO request = new GetReservationsOfTheDayDTO(userId, dateTime);

        return getReservationsOfTheDayUseCase.execute(request)
                .stream()
                .map(GetReservationsOfTheDayResponse::fromEntity)
                .toList();
    }
}
