package com.ignite.gestao_vagas.modules.candidate.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ignite.gestao_vagas.modules.candidate.CandidateEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/candidate")
public class CandidateController {

  @PostMapping("/")
  public void create( @RequestBody CandidateEntity candidateEntity ) {
    System.out.println(candidateEntity.getEmail());
  }

}
