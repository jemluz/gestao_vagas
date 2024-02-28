package com.ignite.gestao_vagas.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ignite.gestao_vagas.exceptions.EntityFoundException;
import com.ignite.gestao_vagas.modules.company.CompanyEntity;
import com.ignite.gestao_vagas.modules.company.CompanyRepository;

@Service
public class CreateCompanyUseCase {

  @Autowired
  private CompanyRepository companyRepository;

  public CompanyEntity execute(CompanyEntity newCompanyEntity) {
    String companyEmail = newCompanyEntity.getEmail();
    String companyName = newCompanyEntity.getCompanyName();

    this.companyRepository
      .findByCompanyNameOrEmail(companyName, companyEmail)
      .ifPresent(company -> {
        throw new EntityFoundException("Company");
      });

      return this.companyRepository.save(newCompanyEntity);
  }
}
