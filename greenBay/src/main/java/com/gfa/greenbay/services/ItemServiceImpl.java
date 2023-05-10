package com.gfa.greenbay.services;

import com.gfa.greenbay.dtos.BidNameDto;
import com.gfa.greenbay.dtos.ItemByIdDto;
import com.gfa.greenbay.dtos.ItemDto;
import com.gfa.greenbay.dtos.SellableItemDto;
import com.gfa.greenbay.dtos.SoldItemByIdDto;
import com.gfa.greenbay.exceptions.ItemIdNotFoundException;
import com.gfa.greenbay.models.Bid;
import com.gfa.greenbay.models.Item;
import com.gfa.greenbay.repositories.ItemRepository;
import com.gfa.greenbay.repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

  private final UserRepository userRepository;

  private final ItemRepository itemRepository;

  @Autowired
  public ItemServiceImpl(UserRepository userRepository, ItemRepository itemRepository) {
    this.userRepository = userRepository;
    this.itemRepository = itemRepository;
  }

  @Override
  public void saveItem(Item item) {
    itemRepository.save(item);
  }

  @Override
  public Item convertDtoToItem(ItemDto itemDto) {
    return new Item(itemDto.getName(), itemDto.getDescription(), itemDto.getPhotoUrl(),
        itemDto.getLastBid(), itemDto.getPurchasePrice());
  }

  @Override
  public List<SellableItemDto> listAllItems(int page, int size) {
    if (page < 0) {
      throw new IllegalArgumentException("Page number must be a positive whole number");
    }
    Pageable pageable = PageRequest.of(page, size);
    Page<Item> listOfItems = itemRepository.findAll(pageable);
    List<SellableItemDto> listSellableItems = new ArrayList<>();
    for (Item item : listOfItems) {
      if (!item.isSold()) {
        listSellableItems.add(
            new SellableItemDto(item.getName(), item.getPhotoUrl(), item.getBidPrice()));
      }
    }
    return listSellableItems;
  }

  @Override
  public ItemByIdDto showItem(Long id) {
    Optional<Item> item = itemRepository.findById(id);
    if (item.isPresent()) {
      List<BidNameDto> bidNameDtos = new ArrayList<>();
      for (Bid bid : item.get().getBids()) {
        bidNameDtos.add(new BidNameDto(bid.getAmount(), bid.getUser().getUsername()));
      }
      if (!item.get().isSold()) {
        return new ItemByIdDto(
            item.get().getName(),
            item.get().getDescription(),
            item.get().getPhotoUrl(),
            item.get().getPurchasePrice(),
            item.get().getSeller().getUsername(),
            bidNameDtos
        );
      } else {
        return new SoldItemByIdDto(
            item.get().getName(),
            item.get().getDescription(),
            item.get().getPhotoUrl(),
            item.get().getPurchasePrice(),
            item.get().getSeller().getUsername(),
            item.get().getBuyer().getUsername(),
            bidNameDtos
        );
      }
    } else {
      throw new ItemIdNotFoundException("Item id not found: " + id);
    }
  }
}

