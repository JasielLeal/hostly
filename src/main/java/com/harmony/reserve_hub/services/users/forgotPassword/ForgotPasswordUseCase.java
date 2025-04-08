package com.harmony.reserve_hub.services.users.forgotPassword;

import com.harmony.reserve_hub.repositories.users.UsersRepository;
import com.harmony.reserve_hub.utils.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class ForgotPasswordUseCase {

    private final UsersRepository usersRepository;

    public ForgotPasswordUseCase(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public String execute(ForgotPasswordRequestDTO forgotPasswordRequestDTO) {
        var user =  usersRepository.findByEmail(forgotPasswordRequestDTO.email())
                .orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND));

        String optCode = String.format("%06d", new Random().nextInt(999999));
        LocalDateTime optExpiration = LocalDateTime.now().plusMinutes(15);

        user.setOptExpiration(optExpiration);
        user.setOptCode(optCode);

        usersRepository.save(user);

        return optCode;
    }
}
