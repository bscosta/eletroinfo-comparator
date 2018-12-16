package com.eletroinfo.eletroinfo.comparator.cptr.repository;

import com.eletroinfo.eletroinfo.comparator.cptr.entitie.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
