package com.ignite.gestao_vagas.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ignite.gestao_vagas.exceptions.EntityFoundException;
import com.ignite.gestao_vagas.modules.candidate.CandidateEntity;
import com.ignite.gestao_vagas.modules.candidate.CandidateRepository;

@Service
public class CreateCandidateUseCase {

  @Autowired
  private CandidateRepository candidateRepository;

  public CandidateEntity execute(CandidateEntity newCandidateEntity) {
    String username = newCandidateEntity.getUsername();
    String email = newCandidateEntity.getEmail();

    // heck if newCandidate's data is already being in db
    this.candidateRepository
      .findByUsernameOrEmail(username, email)
      .ifPresent(
        user -> {
          throw new EntityFoundException("Candidate");
        }
      );

    // if is not exists above, create
    return this.candidateRepository.save(newCandidateEntity);
  }
}
