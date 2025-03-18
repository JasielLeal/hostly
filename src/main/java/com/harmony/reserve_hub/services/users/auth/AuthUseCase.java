package com.harmony.reserve_hub.services.users.auth;

import com.harmony.reserve_hub.domain.User;
import com.harmony.reserve_hub.repositories.users.UsersRepository;
import com.harmony.reserve_hub.services.secutiry.TokenService;
import com.harmony.reserve_hub.utils.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthUseCase {

    private final UsersRepository usersRepository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    public AuthUseCase(UsersRepository usersRepository, TokenService tokenService, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }

    public String execute(AuthDTO authDTO) {
        User user = this.usersRepository.findByEmail(authDTO.email()).orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND));

        if(passwordEncoder.matches(authDTO.password(), user.getPassword())) {
            return this.tokenService.generateToken(user);
        }
        throw new CustomException("Invalid email or password", HttpStatus.FORBIDDEN);
    }
}
