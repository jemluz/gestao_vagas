package com.ignite.gestao_vagas.modules.company.useCases;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ignite.gestao_vagas.modules.company.CompanyRepository;
import com.ignite.gestao_vagas.modules.company.dto.AuthCompanyDTO;

@Service
public class AuthCompanyUseCase {

  @Value("${security.token.secret}")
  private String secretKey;

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public String execute(
    AuthCompanyDTO authCompanyDTO
  ) throws AuthenticationException {

    // search for existing company at database with same dto's username
    var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername())
      .orElseThrow(() -> {
        throw new UsernameNotFoundException("Username/password incorrect.");
      });

    // try match dto's password with the one of database company
    var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

    // if not match passwords, return an error
    if (!passwordMatches) {
      throw new AuthenticationException();
    }

    Algorithm algorithm = Algorithm.HMAC256(secretKey);

    // create JSON Web Token
    // issuer -> set the token emissor (should be a unique name thats identify ur app)
    // subject -> what is this token about (what is the main info that u want to hide)
    // sign -> the chef's secret hahaha
    var token = JWT.create()
      .withIssuer("javagas")
      .withSubject(company.getId().toString())
      .sign(algorithm);

    return token;

  }

}
