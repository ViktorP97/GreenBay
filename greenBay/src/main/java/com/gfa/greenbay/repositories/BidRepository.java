package com.gfa.greenbay.repositories;

import com.gfa.greenbay.models.Bid;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {

  List<Bid> findByItemIdOrderByAmountDesc(Long itemId);

  Optional<Bid> findTopByItemIdOrderByAmountDesc(Long itemId);

}