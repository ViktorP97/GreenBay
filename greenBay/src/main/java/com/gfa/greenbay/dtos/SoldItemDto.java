package com.gfa.greenbay.dtos;

public class SoldItemDto extends ItemDto {

  private String buyer;


  public SoldItemDto() {

  }
  public SoldItemDto(String name, String description, String photoUrl, int startingPrice,
      int purchasePrice, String buyer) {
    super(name, description, photoUrl, startingPrice, purchasePrice);
    this.buyer = buyer;
  }

  public String getBuyer() {
    return buyer;
  }

  public void setBuyer(String buyer) {
    this.buyer = buyer;
  }
}
