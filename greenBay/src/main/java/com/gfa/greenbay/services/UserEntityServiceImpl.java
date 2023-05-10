package com.gfa.greenbay.services;

import com.gfa.greenbay.dtos.AuthDto;
import com.gfa.greenbay.dtos.RegisterDto;
import com.gfa.greenbay.exceptions.UserNameNotFoundException;
import com.gfa.greenbay.models.UserEntity;
import com.gfa.greenbay.repositories.UserRepository;
import com.gfa.greenbay.security.JwtService;
import com.gfa.greenbay.security.RoleType;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserEntityServiceImpl implements UserEntityService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  @Autowired
  public UserEntityServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
      JwtService jwtService, AuthenticationManager authenticationManager) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
    this.authenticationManager = authenticationManager;
  }

  @Override
  public void createUser(UserEntity user) {
    userRepository.save(user);
  }

  @Override
  public Boolean usernameExists(String username) {
    return userRepository.existsByUsername(username);
  }

  @Override
  public AuthDto register(RegisterDto registerDto) {

    UserEntity user = new UserEntity();
    user.setUsername(registerDto.getUsername());
    user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
    user.setRoleType(RoleType.USER);
    userRepository.save(user);

    String jwtToken = jwtService.generateToken(user);
    return AuthDto.builder().token(jwtToken).greenBayDollars(user.getGreenBayDollars()).build();
  }

  @Override
  public UserEntity getUserByName(String playerName) throws UserNameNotFoundException {
    Optional<UserEntity> user = userRepository.findByUsername(playerName);
    if (user.isPresent()) {
      return user.get();
    } else {
      throw new UserNameNotFoundException("Player name not found: " + playerName);
    }
  }

  @Override
  public void addMoney(UserEntity user, int money) {
    user.setGreenBayDollars(money);
  }
}
