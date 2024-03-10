package com.ignite.gestao_vagas.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ignite.gestao_vagas.exceptions.EntityFoundException;
import com.ignite.gestao_vagas.modules.company.CompanyEntity;
import com.ignite.gestao_vagas.modules.company.CompanyRepository;

@Service
public class CreateCompanyUseCase {

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public CompanyEntity execute(CompanyEntity newCompanyEntity) {

    String companyEmail = newCompanyEntity.getEmail();
    String companyName = newCompanyEntity.getCompanyName();
    String companyPass = newCompanyEntity.getPassword();

    // check if newCompany's data is already being in db
    this.companyRepository
      .findByCompanyNameOrEmail(companyName, companyEmail)
      .ifPresent(company -> {
        throw new EntityFoundException("Company");
      });

    // encode password and overwrite in entity
    var passwordEncoded = passwordEncoder.encode(companyPass);
    newCompanyEntity.setPassword(passwordEncoded);

    // if is not already exists above, create
    return this.companyRepository.save(newCompanyEntity);
  }

}
