package com.ignite.gestao_vagas.modules.company;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<
  CompanyEntity, UUID
> {

  // will be use to validade if entity already exists
  Optional<CompanyEntity> findByCompanyNameOrEmail(
    String companyName, String companyEmail
  );

  // will be used to auth
  Optional<CompanyEntity> findByUsername(String username);

}
