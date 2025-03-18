package com.harmony.reserve_hub.services.users.auth;

import com.harmony.reserve_hub.repositories.users.UsersRepository;
import com.harmony.reserve_hub.services.users.createUser.CreateUserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class AuthController {

    private final AuthUseCase authUseCase;

    public AuthController(AuthUseCase authUseCase) {
        this.authUseCase = authUseCase;
    }

    @PostMapping("/session")
    public String login(@RequestBody AuthDTO authDTO) {
        var token = authUseCase.execute(authDTO);

        return token;
    }
}
