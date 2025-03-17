package com.harmony.reserve_hub.services.users.verifyOpt;

import com.harmony.reserve_hub.repositories.users.UsersRepository;
import com.harmony.reserve_hub.utils.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class VerifyOptUseCase {
    private final UsersRepository usersRepository;

    public VerifyOptUseCase(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public void execute(VerifyOptDTO verifyOptDTO) {
        var user =  usersRepository.findByEmail(verifyOptDTO.email())
                .orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND));

        LocalDateTime today = LocalDateTime.now();

        if(user.getOptExpiration().isBefore(today)){
            throw new CustomException("User has expired", HttpStatus.EXPECTATION_FAILED);
        }

        user.setOptCode(null);
        user.setOptExpiration(null);

        usersRepository.save(user);

        return;
    }

}
