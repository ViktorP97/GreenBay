package com.gfa.greenbay.dtos;

public class BidNameDto {

  private int amount;
  private String username;

  public BidNameDto() {

  }

  public BidNameDto(int amount, String username) {
    this.amount = amount;
    this.username = username;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
