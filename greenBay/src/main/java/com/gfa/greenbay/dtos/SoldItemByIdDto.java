package com.gfa.greenbay.dtos;

import java.util.List;

public class SoldItemByIdDto extends ItemByIdDto{

  private String buyerName;

  public SoldItemByIdDto() {

  }

  public SoldItemByIdDto(String name, String description, String url, int buyingPrice,
      String sellerName, String buyerName, List<BidNameDto> bids) {
    super(name, description, url, buyingPrice, sellerName, bids);
    this.buyerName = buyerName;
  }

  public String getBuyerName() {
    return buyerName;
  }

  public void setBuyerName(String buyerName) {
    this.buyerName = buyerName;
  }
}
