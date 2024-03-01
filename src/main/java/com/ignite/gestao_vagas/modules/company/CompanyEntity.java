package com.ignite.gestao_vagas.modules.company;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity(name = "company")
@Data
public class CompanyEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @NotBlank
  @Pattern(regexp = "\\S+", message = "The field [companyName] must not contain spaces.")
  private String companyName;

  @Email(message = "The field [email] must contain a valid email.")
  private String email;

  @Length(min = 10, max = 100, message = "The password must contain between 10 and 32 characters.")
  private String password;

  private String name;
  private String website;
  private String description;

  @CreationTimestamp
  private LocalDateTime createdAt;
}