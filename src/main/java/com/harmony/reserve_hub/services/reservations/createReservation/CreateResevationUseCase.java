package com.harmony.reserve_hub.services.reservations.createReservation;

import com.harmony.reserve_hub.domain.Enterprise;
import com.harmony.reserve_hub.domain.Reservations;
import com.harmony.reserve_hub.repositories.reservations.ReservationsRespository;
import com.harmony.reserve_hub.repositories.enterprises.EnterprisesRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateResevationUseCase {

    private final ReservationsRespository reservationsRespository;
    private final EnterprisesRepository enterpriseRepository;

    public CreateResevationUseCase(ReservationsRespository reservationsRespository, EnterprisesRepository enterpriseRepository) {
        this.reservationsRespository = reservationsRespository;
        this.enterpriseRepository = enterpriseRepository;
    }

    public Reservations execute(CreateReservationDTO createReservationDTO) {

        Enterprise enterprise = enterpriseRepository.findById(createReservationDTO.enterpriseId())
                .orElseThrow(() -> new IllegalArgumentException("Enterprise not found"));

        var reservation = new Reservations(createReservationDTO.clientName(), createReservationDTO.clientCPF(),
                createReservationDTO.checkIn(),
                createReservationDTO.checkOut(),
                createReservationDTO.room(),
                createReservationDTO.origin(),
                createReservationDTO.paymentStatus(),
                enterprise
        );

        reservationsRespository.save(reservation);
        return reservation;
    }
}
