package com.harmony.reserve_hub.services.enterprises.createEnterprise;

import com.harmony.reserve_hub.domain.Enterprise;
import com.harmony.reserve_hub.repositories.enterprises.EnterprisesRepository;
import com.harmony.reserve_hub.utils.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CreateEnterpriseUseCase {

    private final EnterprisesRepository enterprisesRepository;

    public CreateEnterpriseUseCase(EnterprisesRepository enterprisesRepository) {
        this.enterprisesRepository = enterprisesRepository;
    }

    public void execute(CreateEnterpriseDTO createEnterpriseDTO){
         var enterprise = new Enterprise(null,
                createEnterpriseDTO.name() ,createEnterpriseDTO.address(),
                createEnterpriseDTO.phone(), null, null);

         enterprisesRepository.findByName(enterprise.getName()).ifPresent(existingEnterprise -> {
             throw new CustomException("Enterprise already exists", HttpStatus.CONFLICT);
         });
    }
}
