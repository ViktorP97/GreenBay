package com.gfa.greenbay.models;

import com.gfa.greenbay.security.RoleType;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String username;
  private String password;
  @Enumerated(EnumType.STRING)
  private RoleType roleType;
  @Column(nullable = false)
  private int greenBayDollars;

  @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Item> sellingItems = new ArrayList<>();

  @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Item> boughtItems = new ArrayList<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Bid> bids = new ArrayList<>();


  public UserEntity() {
    this.greenBayDollars = 0;
  }

  public UserEntity(String username, String password, RoleType roleType) {
    this.username = username;
    this.password = password;
    this.roleType = roleType;
    this.greenBayDollars = 0;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public RoleType getRoleType() {
    return roleType;
  }

  public void setRoleType(RoleType roleType) {
    this.roleType = roleType;
  }

  public int getGreenBayDollars() {
    return greenBayDollars;
  }

  public void setGreenBayDollars(int greenBayDollars) {
    this.greenBayDollars = greenBayDollars;
  }

  public List<Item> getSellingItems() {
    return sellingItems;
  }

  public void setSellingItems(List<Item> sellingItems) {
    this.sellingItems = sellingItems;
  }

  public List<Item> getBoughtItems() {
    return boughtItems;
  }

  public void setBoughtItems(List<Item> boughtItems) {
    this.boughtItems = boughtItems;
  }

  public List<Bid> getBids() {
    return bids;
  }

  public void setBids(List<Bid> bids) {
    this.bids = bids;
  }
}
