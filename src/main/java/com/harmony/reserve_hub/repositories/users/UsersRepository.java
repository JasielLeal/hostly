package com.harmony.reserve_hub.repositories.users;

import com.harmony.reserve_hub.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
}
