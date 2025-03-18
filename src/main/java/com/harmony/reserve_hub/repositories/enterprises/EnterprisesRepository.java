package com.harmony.reserve_hub.repositories.enterprises;

import com.harmony.reserve_hub.domain.Enterprise;
import com.harmony.reserve_hub.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EnterprisesRepository extends JpaRepository<Enterprise, UUID> {
    Optional<Enterprise> findByName(final String name);
}
