package com.ignite.gestao_vagas.modules.candidate;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<
  CandidateEntity, UUID
> {

  // will be use to validade if entity already exists
  Optional<CandidateEntity> findByUsernameOrEmail(
    String username, String email
  );

}
