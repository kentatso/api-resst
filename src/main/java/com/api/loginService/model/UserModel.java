package com.api.loginService.model;



import com.api.loginService.dtos.NewUserRequest;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity(name = "user_login")
@EntityListeners(AuditingEntityListener.class)
@Component
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column (name = "name", length = 255, nullable = false, unique = true)
    private String name;

    @Column (name = "cpf", length = 11, nullable = false, unique = true)
    private String cpf;

    @Column (name = "login", length = 255, nullable = false, unique = true)
    private String login;

    @Column (name = "password", length = 255, nullable = false)
    private String password;

    @Column(name = "date_login")
    @CreatedDate
    private LocalDateTime dateLogin;

    public UserModel(NewUserRequest newUserRequest, String passwordEncripted) {
        this.name = newUserRequest.getName();
        this.cpf = newUserRequest.getCpf();
        this.login = newUserRequest.getLogin();
        this.password = passwordEncripted;
    }

}
