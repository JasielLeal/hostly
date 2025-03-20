package com.harmony.reserve_hub.services.users.createUser;

import jakarta.validation.constraints.NotBlank;

public record CreateUserDTO(

        @NotBlank(message = "E-mail is required")
        String email,

        @NotBlank(message = "password is required")
        String password,

        @NotBlank(message = "username is required")
        String username
) {
}
