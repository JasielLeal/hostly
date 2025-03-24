package com.harmony.reserve_hub.services.reservations.updateReservation;


import com.harmony.reserve_hub.domain.Reservations;
import com.harmony.reserve_hub.repositories.ReservationsRespository;
import com.harmony.reserve_hub.utils.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateReservationUseCase {

    private final ReservationsRespository reservationsRespository;

    public UpdateReservationUseCase(ReservationsRespository reservationsRespository) {
        this.reservationsRespository = reservationsRespository;
    }

    @Transactional
    public Reservations execute(UpdateReservationDTO updateReservationDTO){

        Reservations reservations = reservationsRespository.findById(updateReservationDTO.reservationId())
                .orElseThrow(()-> new CustomException("Reservation not found", HttpStatus.NOT_FOUND));

        reservations.setStatus(updateReservationDTO.status());

        reservationsRespository.save(reservations);
        return reservations;
    }
}
