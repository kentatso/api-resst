package com.api.loginService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableJpaAuditing
// permite que o JPA audite o model e permite que insira as datas automaticamente
// @EnableWebSecurity
@SpringBootApplication
@OpenAPIDefinition
public class LoginServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(LoginServiceApplication.class, args);
  }
}
