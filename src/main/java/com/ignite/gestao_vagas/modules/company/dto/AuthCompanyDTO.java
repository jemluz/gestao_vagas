package com.ignite.gestao_vagas.modules.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthCompanyDTO {

  // reduce company model to able entity realize login
  private String password;
  private String username;

}
