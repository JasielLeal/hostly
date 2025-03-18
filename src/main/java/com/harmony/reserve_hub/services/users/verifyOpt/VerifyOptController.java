package com.harmony.reserve_hub.services.users.verifyOpt;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class VerifyOptController {

    private final VerifyOptUseCase verifyOptUseCase;

    public VerifyOptController(VerifyOptUseCase verifyOptUseCase) {
        this.verifyOptUseCase = verifyOptUseCase;
    }

    @PostMapping("/verifyopt")
    public void verifyOptUseCase(@RequestBody VerifyOptDTO verifyOptDTO){

        verifyOptUseCase.execute(verifyOptDTO);

        return;
    }
}
