package com.gfa.greenbay.services;

import com.gfa.greenbay.dtos.ItemByIdDto;
import com.gfa.greenbay.dtos.ItemDto;
import com.gfa.greenbay.dtos.SellableItemDto;
import com.gfa.greenbay.models.Item;
import java.util.List;

public interface ItemService {

  void saveItem(Item item);

  Item convertDtoToItem(ItemDto itemDto);

  List<SellableItemDto> listAllItems(int page, int size);

  ItemByIdDto showItem(Long id);
}
