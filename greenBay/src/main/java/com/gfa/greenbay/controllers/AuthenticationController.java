package com.gfa.greenbay.controllers;

import com.gfa.greenbay.dtos.MoneyDto;
import com.gfa.greenbay.dtos.RegisterDto;
import com.gfa.greenbay.exceptions.MissingParameterException;
import com.gfa.greenbay.models.UserEntity;
import com.gfa.greenbay.repositories.UserRepository;
import com.gfa.greenbay.services.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

  private final UserEntityService userService;

  private final UserRepository userRepository;

  @Autowired
  public AuthenticationController(UserEntityService userService, UserRepository userRepository) {
    this.userService = userService;
    this.userRepository = userRepository;
  }

  @PostMapping("/register")
  public ResponseEntity register(@RequestBody RegisterDto registerDto) {
    if (registerDto.getUsername() == null || registerDto.getUsername().isEmpty()) {
      throw new MissingParameterException("Username is missing!");
    }
    if (registerDto.getPassword() == null || registerDto.getPassword().isEmpty()) {
      throw new MissingParameterException("Password is missing!");
    }
    if (userService.usernameExists(registerDto.getUsername())) {
      return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
    }

    return ResponseEntity.ok(userService.register(registerDto));
  }

  @PostMapping("/add/money")
  public ResponseEntity<String> addMoneyToUser(@RequestBody MoneyDto moneyDto, Authentication authentication) {
    String username = authentication.getName();
    UserEntity user = userService.getUserByName(username);
    userService.addMoney(user, moneyDto.getAmount());
    userRepository.save(user);
    return ResponseEntity.ok("Added " + moneyDto.getAmount() + " dollars.");
  }
}