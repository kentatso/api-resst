package com.api.loginService.service;

import com.api.loginService.dtos.NewUserRequest;
import com.api.loginService.dtos.NewUserResponse;
import com.api.loginService.dtos.UserLoginRequest;
import com.api.loginService.model.UserModel;
import com.api.loginService.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {
  UserRepository userRepository;

  public UserModel findUserByLogin(UserLoginRequest userRequest) {
    var login = userRepository.findByLogin(userRequest.getLogin()).orElseThrow();
    return login;
  }

  public NewUserResponse createUser(NewUserRequest newUserRequest) {
    log.info("Querying information for user {}", newUserRequest);
    NewUserResponse newUserResponse = new NewUserResponse();

    Optional<UserModel> byCpf = userRepository.findByCpf(newUserRequest.getCpf());

    if (byCpf.isEmpty()) {
      log.info("Querying information for byCpf {}", byCpf);
      String passwordEncrypted = encode(newUserRequest.getPassword());
      System.out.println(passwordEncrypted);
      UserModel newUser = new UserModel(newUserRequest, passwordEncrypted);
      newUser.setPassword(passwordEncrypted);
      userRepository.save(newUser);
      BeanUtils.copyProperties(newUser, newUserResponse);
      log.info("Querying information for newUserResponse {}", newUserResponse);
    } else {
      BeanUtils.copyProperties(byCpf.orElseThrow(), newUserResponse);
      log.info("Querying information for username {}", newUserResponse);
    }
    return newUserResponse;
  }
  public String encode(String password) {
    return new BCryptPasswordEncoder().encode(password);
  }
}
