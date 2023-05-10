package com.gfa.greenbay.dtos;

import java.util.List;

public class ItemByIdDto {

  private String name;
  private String description;
  private String url;
  private int buyingPrice;
  private String sellerName;

  private List<BidNameDto> bids;


  public ItemByIdDto() {

  }

  public ItemByIdDto(String name, String description, String url, int buyingPrice,
      String sellerName, List<BidNameDto> bids) {
    this.name = name;
    this.description = description;
    this.url = url;
    this.buyingPrice = buyingPrice;
    this.sellerName = sellerName;
    this.bids = bids;
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

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public int getBuyingPrice() {
    return buyingPrice;
  }

  public void setBuyingPrice(int buyingPrice) {
    this.buyingPrice = buyingPrice;
  }

  public String getSellerName() {
    return sellerName;
  }

  public void setSellerName(String sellerName) {
    this.sellerName = sellerName;
  }

  public List<BidNameDto> getBids() {
    return bids;
  }

  public void setBids(List<BidNameDto> bids) {
    this.bids = bids;
  }
}
