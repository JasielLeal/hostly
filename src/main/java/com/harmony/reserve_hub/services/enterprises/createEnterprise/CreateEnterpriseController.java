package com.harmony.reserve_hub.services.enterprises.createEnterprise;

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
        System.out.println("🚀 CreateEnterpriseController carregado!");
    }

    @PostMapping("/create")
    public void createEnterprise(@RequestBody CreateEnterpriseDTO createEnterpriseDTO) {
        createEnterpriseUseCase.execute(createEnterpriseDTO);

        return;
    }

}
