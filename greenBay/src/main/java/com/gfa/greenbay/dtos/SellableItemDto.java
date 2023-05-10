package com.gfa.greenbay.dtos;

public class SellableItemDto {

  private String name;
  private String url;
  private int lastBid;

  public SellableItemDto() {

  }

  public SellableItemDto(String name, String url, int lastBid) {
    this.name = name;
    this.url = url;
    this.lastBid = lastBid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public int getLastBid() {
    return lastBid;
  }

  public void setLastBid(int lastBid) {
    this.lastBid = lastBid;
  }
}
