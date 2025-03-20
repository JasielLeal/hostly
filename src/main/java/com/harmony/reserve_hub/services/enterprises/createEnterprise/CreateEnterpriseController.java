package com.harmony.reserve_hub.services.enterprises.createEnterprise;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enterprises")
public class CreateEnterpriseController {

    private final CreateEnterpriseUseCase createEnterpriseUseCase;

    public CreateEnterpriseController(CreateEnterpriseUseCase createEnterpriseUseCase) {
        this.createEnterpriseUseCase = createEnterpriseUseCase;
    }

    @PostMapping("/create")
    public void createEnterprise(@Valid @RequestBody  CreateEnterpriseDTO createEnterpriseDTO , HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        String token = authorizationHeader.substring(7);

        createEnterpriseUseCase.execute(createEnterpriseDTO, token);

        return;
    }

}
