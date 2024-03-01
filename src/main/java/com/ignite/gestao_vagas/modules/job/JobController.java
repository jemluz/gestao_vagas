package com.ignite.gestao_vagas.modules.job;

import org.springframework.web.bind.annotation.RestController;

import com.ignite.gestao_vagas.modules.job.useCases.CreateJobUseCase;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/job")
public class JobController {

  @Autowired
  private CreateJobUseCase createJobUseCase;

  @PostMapping("/")
  public JobEntity create(
    @Valid @RequestBody JobEntity newJobEntity
  ) {
    return createJobUseCase.execute(newJobEntity);
  }

}
