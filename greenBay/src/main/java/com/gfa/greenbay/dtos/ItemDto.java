package com.gfa.greenbay.dtos;

public class ItemDto {

  private String name;
  private String description;
  private String photoUrl;
  private int lastBid;
  private int purchasePrice;

  public ItemDto() {

  }

  public ItemDto(String name, String description, String photoUrl, int startingPrice,
      int purchasePrice) {
    this.name = name;
    this.description = description;
    this.photoUrl = photoUrl;
    this.lastBid = startingPrice;
    this.purchasePrice = purchasePrice;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPhotoUrl() {
    return photoUrl;
  }

  public void setPhotoUrl(String photoUrl) {
    this.photoUrl = photoUrl;
  }

  public int getLastBid() {
    return lastBid;
  }

  public void setLastBid(int lastBid) {
    this.lastBid = lastBid;
  }

  public int getPurchasePrice() {
    return purchasePrice;
  }

  public void setPurchasePrice(int purchasePrice) {
    this.purchasePrice = purchasePrice;
  }
}
