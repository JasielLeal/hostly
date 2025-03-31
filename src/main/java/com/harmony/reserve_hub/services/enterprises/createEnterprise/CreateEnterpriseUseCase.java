package com.harmony.reserve_hub.services.enterprises.createEnterprise;

import com.harmony.reserve_hub.domain.Enterprise;
import com.harmony.reserve_hub.domain.User;
import com.harmony.reserve_hub.repositories.enterprises.EnterprisesRepository;
import com.harmony.reserve_hub.repositories.users.UsersRepository;
import com.harmony.reserve_hub.services.secutiry.TokenService;
import com.harmony.reserve_hub.utils.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class CreateEnterpriseUseCase {

    private final EnterprisesRepository enterprisesRepository;
    private final TokenService tokenService;
    private final UsersRepository usersRepository;

    public CreateEnterpriseUseCase(EnterprisesRepository enterprisesRepository, TokenService tokenService, UsersRepository usersRepository) {
        this.enterprisesRepository = enterprisesRepository;
        this.tokenService = tokenService;
        this.usersRepository = usersRepository;
    }

    public void execute(CreateEnterpriseDTO createEnterpriseDTO, String token) {

        String userIdString = tokenService.extractUserId(token);

        UUID userId = UUID.fromString(userIdString);

        User user = usersRepository.findById(userId)
                .orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND));

        var enterprise = new Enterprise(null,
                createEnterpriseDTO.name(), createEnterpriseDTO.address(),
                createEnterpriseDTO.phone(),null, user);

        enterprisesRepository.findByName(enterprise.getName()).ifPresent(existingEnterprise -> {
            throw new CustomException("Enterprise already exists", HttpStatus.CONFLICT);
        });

        enterprisesRepository.save(enterprise);

        user.setEnterprise(enterprise);
        usersRepository.save(user);
    }


}
