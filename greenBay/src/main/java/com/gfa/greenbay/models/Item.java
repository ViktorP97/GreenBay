package com.gfa.greenbay.models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "items")
public class Item {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private String photoUrl;

  @Column(nullable = false)
  private int bidPrice;

  @Column(nullable = false)
  private int purchasePrice;

  private boolean isSold;

  @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Bid> bids = new ArrayList<>();

  @ManyToOne
  @JoinColumn(name = "seller_id")
  private UserEntity seller;

  @ManyToOne
  @JoinColumn(name = "buyer_id")
  private UserEntity buyer;

  public Item() {
    this.isSold = false;
  }

  public Item(String name, String description, String photoUrl, int startingPrice,
      int purchasePrice) {
    this.name = name;
    this.description = description;
    this.photoUrl = photoUrl;
    this.bidPrice = startingPrice;
    this.purchasePrice = purchasePrice;
    this.isSold = false;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public int getBidPrice() {
    return bidPrice;
  }

  public void setBidPrice(int bidPrice) {
    this.bidPrice = bidPrice;
  }

  public int getPurchasePrice() {
    return purchasePrice;
  }

  public void setPurchasePrice(int purchasePrice) {
    this.purchasePrice = purchasePrice;
  }

  public UserEntity getSeller() {
    return seller;
  }

  public void setSeller(UserEntity seller) {
    this.seller = seller;
  }

  public boolean isSold() {
    return isSold;
  }

  public void setSold(boolean sold) {
    isSold = sold;
  }

  public UserEntity getBuyer() {
    return buyer;
  }

  public void setBuyer(UserEntity buyer) {
    this.buyer = buyer;
  }

  public List<Bid> getBids() {
    return bids;
  }

  public void setBids(List<Bid> bids) {
    this.bids = bids;
  }
}
