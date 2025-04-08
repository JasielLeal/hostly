package com.harmony.reserve_hub.services.users.forgotPassword;


import com.harmony.reserve_hub.repositories.users.UsersRepository;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/users")
@RestController
public class ForgotPasswordController {

    private final ForgotPasswordUseCase forgotPasswordUseCase;

    public ForgotPasswordController(ForgotPasswordUseCase forgotPasswordUseCase) {
        this.forgotPasswordUseCase = forgotPasswordUseCase;
    }

    @PutMapping("/forgotpassword")
    public String forgotPasswordUseCase(@RequestBody ForgotPasswordRequestDTO forgotPasswordRequestDTO) {

        return forgotPasswordUseCase.execute(forgotPasswordRequestDTO);
    }
}
