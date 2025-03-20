package com.harmony.reserve_hub.services.enterprises.createEnterprise;

import jakarta.validation.constraints.NotBlank;

public record CreateEnterpriseDTO(
        @NotBlank(message = "Name is required") String name,

        @NotBlank(message = "Address is required") String address,

        @NotBlank(message = "Phone is required") String phone
) { }
