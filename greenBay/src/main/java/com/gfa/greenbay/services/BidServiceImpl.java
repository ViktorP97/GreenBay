package com.gfa.greenbay.services;

import com.gfa.greenbay.dtos.ItemDto;
import com.gfa.greenbay.dtos.SoldItemDto;
import com.gfa.greenbay.exceptions.BidTooLowException;
import com.gfa.greenbay.exceptions.ItemIdNotFoundException;
import com.gfa.greenbay.exceptions.ItemNotSellableException;
import com.gfa.greenbay.exceptions.NotEnoughGreenBayDollarsException;
import com.gfa.greenbay.models.Bid;
import com.gfa.greenbay.models.Item;
import com.gfa.greenbay.models.UserEntity;
import com.gfa.greenbay.repositories.BidRepository;
import com.gfa.greenbay.repositories.ItemRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BidServiceImpl implements BidService {

  private final BidRepository bidRepository;
  private final ItemRepository itemRepository;
  private final UserEntityService userService;

  @Autowired
  public BidServiceImpl(BidRepository bidRepository, ItemRepository itemRepository,
      UserEntityService userService) {
    this.bidRepository = bidRepository;
    this.itemRepository = itemRepository;
    this.userService = userService;
  }

  @Override
  public ItemDto bidOnItem(Long itemId, int bidAmount, UserEntity user) {
    Optional<Item> itemOptional = itemRepository.findById(itemId);
    if (!itemOptional.isPresent()) {
      throw new ItemIdNotFoundException("Item not found");
    }
    Item item = itemOptional.get();
    if (item.isSold()) {
      throw new ItemNotSellableException("Item is not sellable");
    }
    if (bidAmount <= item.getBidPrice()) {
      throw new BidTooLowException("Bid is too low");
    }
    if (user.getGreenBayDollars() < bidAmount) {
      throw new NotEnoughGreenBayDollarsException("Not enough greenBay dollars");
    }

    Optional<Bid> lastBidOptional = bidRepository.findTopByItemIdOrderByAmountDesc(itemId);
    if (lastBidOptional.isPresent()) {
      Bid lastBid = lastBidOptional.get();
      if (bidAmount <= lastBid.getAmount()) {
        throw new BidTooLowException("Bid is too low");
      }
    }
    if (bidAmount < item.getPurchasePrice()) {
      Bid bid = new Bid();
      bid.setAmount(bidAmount);
      bid.setItem(item);
      bid.setUser(user);
      bidRepository.save(bid);
      item.setBidPrice(bidAmount);
      itemRepository.save(item);
      return new ItemDto(itemOptional.get().getName(), itemOptional.get().getDescription(),
          itemOptional.get().getPhotoUrl(), itemOptional.get().getBidPrice(),
          itemOptional.get().getPurchasePrice());
    } else {
      item.setSold(true);
      item.setBuyer(user);
      itemRepository.save(item);
      user.setGreenBayDollars(user.getGreenBayDollars() - bidAmount);
      userService.createUser(user);
      Bid bid = new Bid();
      bid.setAmount(bidAmount);
      bid.setItem(item);
      bid.setUser(user);
      bidRepository.save(bid);
      SoldItemDto soldItemDto = new SoldItemDto();
      soldItemDto.setName(itemOptional.get().getName());
      soldItemDto.setDescription(itemOptional.get().getDescription());
      soldItemDto.setPhotoUrl(itemOptional.get().getPhotoUrl());
      soldItemDto.setLastBid(bid.getAmount());
      soldItemDto.setPurchasePrice(bid.getAmount());
      soldItemDto.setBuyer(user.getUsername());
      return soldItemDto;
    }
  }
}
