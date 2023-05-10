package com.gfa.greenbay.services;

import com.gfa.greenbay.dtos.ItemDto;
import com.gfa.greenbay.models.UserEntity;

public interface BidService {

  ItemDto bidOnItem(Long itemId, int bidAmount, UserEntity user);
}
