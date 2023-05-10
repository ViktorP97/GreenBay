package com.gfa.greenbay.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthDto {

  private String token;

  private int greenBayDollars;

  public AuthDto() {
  }

  public AuthDto(String token, int greenBayDollars) {
    this.token = token;
    this.greenBayDollars = greenBayDollars;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public int getGreenBayDollars() {
    return greenBayDollars;
  }

  public void setGreenBayDollars(int greenBayDollars) {
    this.greenBayDollars = greenBayDollars;
  }
}