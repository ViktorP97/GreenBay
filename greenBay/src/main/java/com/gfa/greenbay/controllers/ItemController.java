package com.gfa.greenbay.controllers;

import com.gfa.greenbay.dtos.BidDto;
import com.gfa.greenbay.dtos.ItemDto;
import com.gfa.greenbay.dtos.SellableItemDto;
import com.gfa.greenbay.exceptions.BidTooLowException;
import com.gfa.greenbay.exceptions.ItemIdNotFoundException;
import com.gfa.greenbay.exceptions.ItemNotSellableException;
import com.gfa.greenbay.exceptions.NotEnoughGreenBayDollarsException;
import com.gfa.greenbay.models.Item;
import com.gfa.greenbay.models.UserEntity;
import com.gfa.greenbay.services.BidServiceImpl;
import com.gfa.greenbay.services.ItemServiceImpl;
import com.gfa.greenbay.services.UserEntityService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ItemController {

  private final UserEntityService userEntityService;
  private final ItemServiceImpl itemService;
  private final BidServiceImpl bidService;

  @Autowired
  public ItemController(UserEntityService userEntityService, ItemServiceImpl itemService, BidServiceImpl bidService) {
    this.userEntityService = userEntityService;
    this.itemService = itemService;
    this.bidService = bidService;
  }

  @PostMapping("/add")
  public ResponseEntity addNewItem(Authentication authentication, @RequestBody ItemDto itemDto) {
    String username = authentication.getName();
    UserEntity user = userEntityService.getUserByName(username);
    Item item = itemService.convertDtoToItem(itemDto);
    item.setSeller(user);
    itemService.saveItem(item);
    return ResponseEntity.status(201).body(itemDto);
  }

  @GetMapping("/items")
  public ResponseEntity listAllItems(@RequestParam(name = "page", defaultValue = "0") int page,
      @RequestParam(name = "size", defaultValue = "20") int size) {
    try {
      List<SellableItemDto> sellableItemDtos = itemService.listAllItems(page, size);
      return ResponseEntity.ok(sellableItemDtos);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping("item/{id}")
  public ResponseEntity getItemById(@PathVariable Long id) {
    return ResponseEntity.ok(itemService.showItem(id));
  }

  @PostMapping("item/{id}/bid")
  public ResponseEntity bidOnItem(@PathVariable Long id, Authentication authentication, @RequestBody BidDto bidDto) {
    try {
      String username = authentication.getName();
      UserEntity user = userEntityService.getUserByName(username);
      return ResponseEntity.ok(bidService.bidOnItem(id, bidDto.getBidAmount(), user));
    } catch (ItemIdNotFoundException e) {
      return ResponseEntity.notFound().build();
    } catch (ItemNotSellableException | BidTooLowException | NotEnoughGreenBayDollarsException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}
