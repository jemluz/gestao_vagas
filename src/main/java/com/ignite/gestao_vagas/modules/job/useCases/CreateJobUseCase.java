package com.ignite.gestao_vagas.modules.job.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ignite.gestao_vagas.modules.job.JobEntity;
import com.ignite.gestao_vagas.modules.job.JobRepository;

@Service
public class CreateJobUseCase {

  @Autowired
  private JobRepository jobRepository;

  public JobEntity execute(JobEntity newJobEntity) {
    return this.jobRepository.save(newJobEntity);
  }

}
