package com.gfa.greenbay.dtos;

public class BidDto {

  private int bidAmount;

  public BidDto() {

  }

  public BidDto(int bidAmount) {
    this.bidAmount = bidAmount;
  }

  public int getBidAmount() {
    return bidAmount;
  }

  public void setBidAmount(int bidAmount) {
    this.bidAmount = bidAmount;
  }
}
