package com.ignite.gestao_vagas.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

  // will be used to set the error's language
  private MessageSource messageSource;

  public ExceptionHandlerController(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  /**
   * handleArgumentNotValidException
   * @param e
   * @return
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<List<ErrorMessageDTO>> handleArgumentNotValidException(MethodArgumentNotValidException e) {

    // this variable will be the final list
    List<ErrorMessageDTO> errorList = new ArrayList<>();

    // get original errors list, and parse each one
    e.getBindingResult().getFieldErrors().forEach(err -> {

      // variable that will receive the treated message
      // the getLocale() signs what is the message's language
      String finalMsg = messageSource.getMessage(err, LocaleContextHolder.getLocale());
      ErrorMessageDTO error = new ErrorMessageDTO(finalMsg, err.getField());

      // fill the final error list
      errorList.add(error);

    });

    return new ResponseEntity<>(errorList, HttpStatus.BAD_REQUEST);
  }
}
