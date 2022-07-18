package com.api.loginService.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewUserResponse {
    private String name;
    private String login;
    private String cpf;
    private String password;
    private LocalDateTime dateLogin;
}
