package com.gfa.greenbay.services;

import com.gfa.greenbay.dtos.AuthDto;
import com.gfa.greenbay.dtos.RegisterDto;
import com.gfa.greenbay.exceptions.UserNameNotFoundException;
import com.gfa.greenbay.models.UserEntity;

public interface UserEntityService {

  void createUser(UserEntity user);

  Boolean usernameExists(String username);

  AuthDto register(RegisterDto registerDto);

  UserEntity getUserByName(String playerName) throws UserNameNotFoundException;

  void addMoney(UserEntity user, int money);
}
