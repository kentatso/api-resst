package com.api.loginService.controller;

import com.api.loginService.dtos.NewUserRequest;
import com.api.loginService.dtos.NewUserResponse;
import com.api.loginService.dtos.UserLoginRequest;
import com.api.loginService.model.UserModel;
import com.api.loginService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

  @Autowired UserService userService;

  @PostMapping(value = "/login", produces = "application/json")
  public ResponseEntity<UserModel> loginExecute(@RequestBody UserLoginRequest userRequest) {
    UserModel userLogged = userService.findUserByLogin(userRequest);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(userLogged);
  }

  @PostMapping(value = "/createUser", produces = "application/json")
  public ResponseEntity<NewUserResponse> createNewUser(@RequestBody NewUserRequest userRequest) {
    NewUserResponse user = userService.createUser(userRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(user);
  }

  //    @PostMapping("/createUser")
  //    public NewUserResponse createNewUser(@RequestBody NewUserRequest userRequest) {
  //        return userService.createUser(userRequest);
  //    }

}
