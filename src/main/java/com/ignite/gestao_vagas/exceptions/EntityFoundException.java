package com.ignite.gestao_vagas.exceptions;

public class EntityFoundException extends RuntimeException {

  public EntityFoundException(String resource) {
    super(resource + " already exists!");
  }

}
