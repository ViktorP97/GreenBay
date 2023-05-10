package com.gfa.greenbay.repositories;

import com.gfa.greenbay.models.Item;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

  Page<Item> findAll(Pageable pageable);

  Optional<Item> findById(Long id);
}
