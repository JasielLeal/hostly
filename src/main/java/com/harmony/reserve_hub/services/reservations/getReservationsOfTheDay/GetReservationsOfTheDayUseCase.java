package com.harmony.reserve_hub.services.reservations.getReservationsOfTheDay;

import com.harmony.reserve_hub.domain.Enterprise;
import com.harmony.reserve_hub.domain.Reservations;
import com.harmony.reserve_hub.domain.User;
import com.harmony.reserve_hub.repositories.reservations.ReservationsRespository;
import com.harmony.reserve_hub.repositories.enterprises.EnterprisesRepository;
import com.harmony.reserve_hub.repositories.users.UsersRepository;
import com.harmony.reserve_hub.utils.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class GetReservationsOfTheDayUseCase {

    private final EnterprisesRepository enterprisesRepository;
    private final ReservationsRespository reservationsRepository;
    private final UsersRepository usersRepository;

    public GetReservationsOfTheDayUseCase
            (EnterprisesRepository enterprisesRepository,
             ReservationsRespository reservationsRepository,
             UsersRepository usersRepository) {

        this.enterprisesRepository = enterprisesRepository;
        this.reservationsRepository = reservationsRepository;
        this.usersRepository = usersRepository;
    }

    public List<Reservations> execute(GetReservationsOfTheDayDTO getReservationsOfTheDayDTO) {

        User user = usersRepository.findById(getReservationsOfTheDayDTO.userId())
                .orElseThrow(() -> new CustomException("User Not Found", HttpStatus.NOT_FOUND));

        Enterprise enterprise = user.getEnterprise();

        if (enterprise == null) {
            throw new CustomException("User is not associated with any Enterprise", HttpStatus.NOT_FOUND);
        }

        ZonedDateTime date = getReservationsOfTheDayDTO.day();
        ZonedDateTime startOfDay = date.with(LocalTime.MIN);
        ZonedDateTime endOfDay = date.with(LocalTime.MAX);

        // Busca todas as reservas no intervalo de tempo e da empresa especificada
        return reservationsRepository.findAllByEnterpriseIdAndCheckInBetween(
                enterprise,
                startOfDay,
                endOfDay
        );
    }
}
