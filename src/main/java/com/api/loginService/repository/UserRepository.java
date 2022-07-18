package com.api.loginService.repository;

import com.api.loginService.dtos.NewUserRequest;

import com.api.loginService.model.UserModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

  Optional<UserModel> findByLogin(String login);

  Optional<UserModel> findByCpf(String cpf);

}
