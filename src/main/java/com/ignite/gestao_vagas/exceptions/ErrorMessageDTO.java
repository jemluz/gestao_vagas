package com.ignite.gestao_vagas.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessageDTO {

  // use to pattern up error returns
  private String message;
  private String field;

}
